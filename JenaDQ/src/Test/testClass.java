package Test;

import java.util.LinkedList;

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

		DQAssessment dq = new DQAssessment(l, m); 
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

	            "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
	            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
	            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
	            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
	            "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
	            "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" +
	            "PREFIX : <http://dbpedia.org/resource/>\n" +
	            "PREFIX dbpedia2: <http://dbpedia.org/property/>\n" +
	            "PREFIX dbpedia: <http://dbpedia.org/>\n" +
	            "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
	            "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
	            "\n" +
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
//	    }
	    
//	    Query query2 = QueryFactory.create(queryString);
//	    QueryExecution qexec2 = QueryExecutionFactory.sparqlService(service, query2);
	    Model results2 = UriUtil.getResourceFromEndpoint(service, queryString2).getModel();
	    System.out.println(results2.size());
	    results2.write(System.out, "TTL");
	    System.out.println(results2.size());
	    
//	   DQModel dqq = new DQModel(service,  "http://dbpedia.org/resource/The_Lord_of_the_Rings");
//	   dqq.showModel();
	}

}
