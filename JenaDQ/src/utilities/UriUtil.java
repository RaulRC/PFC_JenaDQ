package utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import DQModel.DQModel;
import DQModel.DataPicker;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Bag;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;

public class UriUtil {

	public static DQModel getResourceFromEndpoint(String endpoint, String queryString){
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);
		DQModel dq = new DQModel(); 
		Model results = qexec.execConstruct();
		dq.setDqmodel((Model) results); 
		//	    for ( ; results.hasNext() ; ) {
		//	        QuerySolution soln = results.nextSolution() ;
		//	        System.out.println(soln);
		//	    }
		return dq; 
	}
	public static ArrayList<RDFNode> getURIResourceList(Model m){
		//TODO check jena CONTAINERS
		// http://jena.apache.org/tutorials/rdf_api.html
		ArrayList<RDFNode> luri = new ArrayList<RDFNode>();
		StmtIterator st = m.listStatements();
		luri.add(st.next().getSubject());
		RDFNode n; 
		while (st.hasNext()){
			n = st.next().getObject();
			if(!luri.contains(n))
				luri.add(n);
		}
		return luri; 
	}

	// TODO ArrayList<ArrayList<RDFNode>>
	/**
	 * 
	 * This method search for triples with format: uri ?p ?o
	 * in the 0 level. After that, it searchs all the relationships
	 * throughout levels with n limit. 
	 * 
	 * @param endpoint
	 * @param uri
	 * @param depth
	 * @return ArrayList<RDFNode> with the recursive search in depth = n (0..n)
	 */
	public static ArrayList<RDFNode> getResourcesInLevel(String endpoint, String uri, int depth){
		// TODO: Search with GRAPH indication
		// TODO: Search in ArrayList<ArrayList<RDFNode>>
		ArrayList<RDFNode> resources = new ArrayList<RDFNode>();
		DQModel dq = new DQModel(endpoint, uri); 
		resources = getURIResourceList(dq.getModel()); 
		RDFNode aux; 
		ArrayList<RDFNode> buffer = new ArrayList<RDFNode>(); 

		if (depth == 0)
			return resources;
		else{
			Iterator<RDFNode> iter = resources.iterator();
			while (iter.hasNext()){
				aux = iter.next();
				if(aux.isResource()){
					try {
						System.out.println("Expanding... "+aux.toString());
						buffer.addAll(getResourcesInLevel(aux.toString(), endpoint, depth-1));
					} catch (Exception e) {
						// TODO Auto-generated catch block
					}
				}
			}
		}
		resources.addAll(buffer);
		return resources;
	}

	public static ArrayList<ArrayList<RDFNode>> getResourcesInDepth(String endpoint, String uri, int depth){
		// Init
		ArrayList<ArrayList<RDFNode>> resources = new ArrayList<ArrayList<RDFNode>>();
		DQModel dq = new DQModel(endpoint, uri,false); 
		//LVL 0
		resources.add(getURIResourceList(dq.getModel())); 

		RDFNode aux; 
		HashSet<RDFNode> buffer;
		ArrayList<RDFNode> arrayBuffer; 
		Iterator<RDFNode> iterNode;
		boolean exists = false; 

		if (depth == 0)
			return resources;
		else{
			for (int i=1; i<=depth; i++){
				iterNode = resources.get(i-1).iterator();
				buffer = new HashSet<RDFNode>(); 
				while (iterNode.hasNext()){
					aux = iterNode.next();
					exists = false; 
					try {
						if(aux.isURIResource()){

							dq = new DQModel(endpoint, aux.toString(),false); 
							buffer.addAll(getURIResourceList(dq.getModel()));

						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
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
}
