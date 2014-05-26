package utilities;

import java.net.MalformedURLException;
import java.net.URL;

import DQModel.DQModel;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.rdf.model.Model;

public class UriUtil {

	public static boolean isUri(String s){ // Necesito usar expresiones regulares
		// Si es URL
		// Si es del tipo tag:nombre
		System.out.println("string >>> " + s);
		boolean result = true; 
		try {
            URL url = new URL(s);
        } catch (MalformedURLException e) {
            // If there was an URL that was not it!...
        	if(!s.matches("[a-zA-Z]:."))
        		result = false; 
        }
		return result; 
	}
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
}
