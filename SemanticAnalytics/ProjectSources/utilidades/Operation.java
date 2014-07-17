package utilidades;

import com.hp.hpl.jena.query.* ;
import java.util.LinkedList;
import java.util.List;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class Operation {
	private Model model;

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Operation(Model model) {
		super();
		this.model = model;
	} 

	/*
	 * Gets the number of the triples in the whole model
	 */
	public long getTriples(){
		return getModel().size();
	}
	/*
	 * Gets all distinct properties of a given model
	 */
	public LinkedList<Property> getAllProperties(){
		LinkedList<Property> pl = new LinkedList<Property>();
		StmtIterator ni = getModel().listStatements();
		Property prop;
		while(ni.hasNext()){
			prop = ni.nextStatement().getPredicate();
			if (!pl.contains(prop))
				pl.add(prop);
		}
		return pl; 
	}
	/*
	 * Gets the number of distinct properties of a given model
	 */
	public int getNumberOfProperties(){
		return getAllProperties().size();
	}

	/*
	 * Queries a model with a given query string
	 */
	public ResultSet queryModel(String queryString){
		Query query = QueryFactory.create(queryString); 
		QueryExecution qexec = QueryExecutionFactory.create(query, model) ;
		ResultSet results;
		  try {
		    results = qexec.execSelect() ;
		    showResults(results, query); 
		  } finally { qexec.close() ; }
		  return results;
		
	}
	public List<QuerySolution> queryModelandGetList(String queryString){
		Query query = QueryFactory.create(queryString); 
		QueryExecution qexec = QueryExecutionFactory.create(query, model) ;
		ResultSet results;

		List<QuerySolution> qs ;
		  try {
		    results = qexec.execSelect() ;
		    qs = ResultSetFormatter.toList(results);
		  } finally { qexec.close() ; }
		  return qs;
		
	}	
	public void showResults (ResultSet results, Query query){
		ResultSetFormatter.out(System.out, results, query); 
	}
	public String getStringResults (ResultSet results){
		return ResultSetFormatter.asText(results); 
	}	
}
