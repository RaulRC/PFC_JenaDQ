package Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import utilities.UriUtil;

import DQModel.DQModel;
import JenaDQ.DQAssessment;
import JenaDQ.DQDimension;
import JenaDQ._dimCompleteness;
import JenaDQ._dimPrecisionConsistency;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.RSIterator;
import com.hp.hpl.jena.rdf.model.ReifiedStatement;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class testClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String userURI = "http://datos.gijon.es/doc/medio-ambiente/areas-recreativas.ttl";
		DQModel m = new DQModel(userURI); 
		
		LinkedList<DQDimension> l = new LinkedList<DQDimension>(); 
		_dimCompleteness dq1 = new _dimCompleteness(m); 
		DQDimension dq2 = new _dimPrecisionConsistency(m); 
			
		l.add(dq1);
		_dimCompleteness dc = (_dimCompleteness) l.element();
		System.out.println(((_dimCompleteness) l.element()).m_interlinkingCompleteness().toString()); 

//		DQAssessment dq = new DQAssessment(l, m); 
		// Get all the ontologies involved
		//System.out.println(m.getModel().getNsPrefixMap().toString());

/*
 * ENDPOINTS		
 */
	    String service = "http://dbpedia.org/sparql";
//	    String service = "http://querybuilder.dbpedia.org/";
//	    String service = "http://lod.openlinksw.com/sparql";
//		String service = "http://datos.gijon.es/page/12217-servicio-sparql";
//		String service = "es.dbpedia.org/sparql";
	    String queryString2 =  

	            "CONSTRUCT {" +
	               " <http://dbpedia.org/resource/The_Lord_of_the_Rings> ?p ?x} WHERE { " +
	               " <http://dbpedia.org/resource/The_Lord_of_the_Rings> ?p ?x }";

	    String queryString3 = 
				"CONSTRUCT FROM <http://dbpedia.org/data/The_Lord_of_the_Rings.n3> WHERE { ?s ?p ?o } ";
 
	    String queryString =  

	            "SELECT ?name ?birth ?death ?person WHERE {\n" +
	               " ?person dbo:birthPlace :Spain .\n" +
	               "?person dbo:birthDate ?birth .\n" +
	               "?person foaf:name ?name .\n" +
	               "?person dbo:deathDate ?death .\n" +
	               
	            "}\n LIMIT 10";
	    
//	    Query query = QueryFactory.create(queryString);
//	    QueryEngineHTTP qexec = QueryExecutionFactory.createServiceRequest(service, query);
//	    ResultSet results = qexec.execSelect();
//	    for ( ; results.hasNext() ; ) {
//	        QuerySolution soln = results.nextSolution() ;
//	        System.out.println(soln);
//	        soln.
//	    }
	    
//	    Query query2 = QueryFactory.create(queryString);
//	    QueryExecution qexec2 = QueryExecutionFactory.sparqlService(service, query2);
//	    Model results2 = UriUtil.getResourceFromEndpoint(service, queryString2).getModel();
//	    System.out.println(results2.size());
//	    results2.write(System.out, "TTL");
//	    System.out.println(results2.size());
	    
	   DQModel dqq = new DQModel(service,  "http://dbpedia.org/resource/World_of_Warcraft");
	   dqq.showModel();
//	   ArrayList<String> uriList = UriUtil.getURIResourceList(dqq.getModel());
//	   LinkedList<String> secondLevelList = new LinkedList<String>();
//	   System.out.println(uriList.size());
//	   ListIterator<String> iter = uriList.listIterator();
//	   String current_model_URI = "";
//	   DQModel current_model; 
//	   RDFNode n; 
//
//	   while (iter.hasNext()){
//		   current_model_URI = iter.next();
//		   current_model = new DQModel(service, current_model_URI); 
//		   try {
//			secondLevelList.addAll(UriUtil.getURIResourceList(current_model.getModel()));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	   }
//	   System.out.println(secondLevelList.toString());
//	   
//	   System.out.println(secondLevelList.size());
//

	}

}
