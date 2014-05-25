package JenaDQ;

import java.util.LinkedList;

import DQModel.DQModel;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;

public class DQDimension {

	public DQDimension(DQModel targetmodel) {
		super();
		this.targetModel = targetmodel;
	}

	protected String dimName;
	protected DQModel targetModel;
	protected LinkedList<MeasurementResult> dimResults;

	public String getDimName() {
		return this.dimName;
	}

	public void setDimName(String dimName) {
		this.dimName = dimName;
	}

	public DQModel getTargetModel() {
		return this.targetModel;
	}

	public void setTargetModel(DQModel targetModel) {
		this.targetModel = targetModel;
	}

	public LinkedList<MeasurementResult> getDimResults() {
		return this.dimResults;
	}

	public void setDimResults(LinkedList<MeasurementResult> dimResults) {
		this.dimResults = dimResults;
	}

	public LinkedList<MeasurementResult> executeMeasures() {
		return null;

	}
	public double calculateDQMeasure(double nNot, double nTot){
		return 1 - nNot/nTot; 
	}
	/**
	 * Recibe una query y un endpoint, devuelve un modelo RDF 
	 * @param endpoint
	 * @param queryString
	 */
	public void getResourceFromURI(String endpoint, String queryString){
	    Query query = QueryFactory.create(queryString);
	    QueryEngineHTTP qexec = QueryExecutionFactory.createServiceRequest(endpoint, query);
	    ResultSet results = qexec.execSelect();
	    for ( ; results.hasNext() ; ) {
	        QuerySolution soln = results.nextSolution() ;
	        System.out.println(soln);
	    }
	}

}