package utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import DQModel.DQModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;

/**
 * Clase de utilidades para la consulta y recuperación de información de los
 * modelos semánticos a través de HTTP
 * 
 * @author Raúl Reguillo Carmona
 * 
 */
public class UriUtil {

	/**
	 * Realiza una consulta obteniendo como resultado un modelo
	 * 
	 * @param endpoint
	 *            dirección del servicio HTTP
	 * @param queryString
	 *            <code>String</code> que contiene la consulta que se desea
	 *            realizar
	 * @return <code>DQModel</code> con los resultados
	 */
	public static DQModel getResourceFromEndpoint(String endpoint,
			String queryString) {
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint,
				query);
		DQModel dq = new DQModel();
		Model results = qexec.execConstruct();
		dq.setDqmodel((Model) results);
		// for ( ; results.hasNext() ; ) {
		// QuerySolution soln = results.nextSolution() ;
		// System.out.println(soln);
		// }
		return dq;
	}

	/**
	 * Obtiene dado un modelo de Jena todos los recursos asociados a él
	 * 
	 * @param m
	 *            modelo de Jena
	 * @return <code>ArrayList</code> de <code>RDFNode</code> con todos los
	 *         recursos asociados al modelo
	 */
	public static ArrayList<RDFNode> getURIResourceList(Model m) {

		// http://jena.apache.org/tutorials/rdf_api.html
		ArrayList<RDFNode> luri = new ArrayList<RDFNode>();
		StmtIterator st = m.listStatements();
		luri.add(st.next().getSubject());
		RDFNode n;
		while (st.hasNext()) {
			n = st.next().getObject();
			if (!luri.contains(n))
				luri.add(n);
		}
		return luri;
	}

	/**
	 * 
	 * search for triples with format: uri ?p ?o in the 0 level. After that, it
	 * searchs all the relationships throughout levels with n limit.
	 * 
	 * @param endpoint
	 * @param uri
	 * @param depth
	 * @return ArrayList<RDFNode> with the recursive search in depth = n (0..n)
	 */
	public static ArrayList<RDFNode> getResourcesInLevel(String endpoint,
			String uri, int depth) {
		ArrayList<RDFNode> resources = new ArrayList<RDFNode>();
		DQModel dq = new DQModel(endpoint, uri);
		resources = getURIResourceList(dq.getModel());
		RDFNode aux;
		ArrayList<RDFNode> buffer = new ArrayList<RDFNode>();

		if (depth == 0)
			return resources;
		else {
			Iterator<RDFNode> iter = resources.iterator();
			while (iter.hasNext()) {
				aux = iter.next();
				if (aux.isResource()) {
					try {
						System.out.println("Expanding... " + aux.toString());
						buffer.addAll(getResourcesInLevel(aux.toString(),
								endpoint, depth - 1));
					} catch (Exception e) {

					}
				}
			}
		}
		resources.addAll(buffer);
		return resources;
	}

	/**
	 * 
	 * Recover one by one all the nodes derived from the first one
	 * 
	 * @param endpoint
	 * @param uri
	 * @param depth
	 * @return Leveled array list of resources
	 */
	public static ArrayList<ArrayList<RDFNode>> getResourcesInDepth(
			String endpoint, String uri, int depth) {
		// Init
		ArrayList<ArrayList<RDFNode>> resources = new ArrayList<ArrayList<RDFNode>>();
		DQModel dq = new DQModel(endpoint, uri, false);
		// LVL 0
		resources.add(getURIResourceList(dq.getModel()));

		RDFNode aux;
		HashSet<RDFNode> buffer;
		ArrayList<RDFNode> arrayBuffer;
		Iterator<RDFNode> iterNode;
		if (depth == 0)
			return resources;
		else {
			for (int i = 1; i < depth; i++) {
				iterNode = resources.get(i - 1).iterator();
				buffer = new HashSet<RDFNode>();
				while (iterNode.hasNext()) {
					aux = iterNode.next();
					try {
						if (aux.isURIResource()) {

							dq = new DQModel(endpoint, aux.toString(), false);
							buffer.addAll(getURIResourceList(dq.getModel()));

						}
					} catch (Exception e) {

						System.out.println("exception occur");
					}
				}
				arrayBuffer = new ArrayList<RDFNode>();
				arrayBuffer.addAll(buffer);
				resources.add(arrayBuffer);
			}
		}
		return resources;
	}

	/**
	 * Return an arrayList obtained through a SPARQL query of all the nodes
	 * derived from the first one
	 * 
	 * @param endpoint
	 * @param uri
	 * @param depth
	 * @return Leveled array list of resources
	 * 
	 */
	public static ArrayList<ArrayList<RDFNode>> getResourcesInDepthQuery(
			String endpoint, String uri, int depth) {
		// Init
		ArrayList<ArrayList<RDFNode>> resources = new ArrayList<ArrayList<RDFNode>>();
		DQModel dq = new DQModel(endpoint, uri, false);
		Query query;
		QueryEngineHTTP qexec;
		ArrayList<RDFNode> lvlCollection; // LVL 0

		resources.add(getURIResourceList(dq.getModel()));
		// if (depth <= 0){
		// String finalQuery = "SELECT DISTINCT ?obj WHERE { <" +
		// uri+"> ?p1 ?obj . }";
		// query = QueryFactory.create(finalQuery);
		// qexec = QueryExecutionFactory.createServiceRequest(endpoint, query);
		// ResultSet resultsQuery = qexec.execSelect();
		// lvlCollection = new ArrayList();
		//
		// while(resultsQuery.hasNext())
		// lvlCollection.add(resultsQuery.next().get("?obj"));
		//
		// resources.add(lvlCollection);
		// }
		if (depth > 0) {

			String headerQuery = "SELECT DISTINCT ?obj WHERE { <" + uri
					+ "> ?p1 ?o1 .";
			String qAux = "";
			String finalQuery = "";

			for (int i = 1; i < depth; i++) {
				lvlCollection = new ArrayList<RDFNode>();
				// Creating query
				qAux += "\n ?o" + i + " ?p" + (i + 1);
				finalQuery = headerQuery + qAux
						+ " ?obj . FILTER (!sameTERM(?obj,?o" + i + ")) .}";
				System.out.println(i + ": " + finalQuery);
				// Executing query
				query = QueryFactory.create(finalQuery);
				qexec = QueryExecutionFactory.createServiceRequest(endpoint,
						query);
				ResultSet resultsQuery = qexec.execSelect();
				// store results
				while (resultsQuery.hasNext())
					lvlCollection.add(resultsQuery.next().get("?obj"));

				resources.add(i, lvlCollection);

				// restore query
				qAux += " ?o" + (i + 1) + " . ";
			}

		}
		return resources;
	}
}
