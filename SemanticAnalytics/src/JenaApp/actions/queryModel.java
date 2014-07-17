package JenaApp.actions;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import utilidades.Operation;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.util.FileManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class queryModel extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String dataQuery;
	private Model model; 
	private String queryResult;
	private List<QuerySolution> qs ;
	private Exception e; 
	
	public List<QuerySolution> getQs() {
		return qs;
	}
	public void setQs(List<QuerySolution> qs) {
		this.qs = qs;
	}
	public String getQueryResult() {
		return queryResult;
	}
	public void setQueryResult(String queryResult) {
		this.queryResult = queryResult;
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public String getDataQuery() {
		return dataQuery;
	}
	public void setDataQuery(String dataQuery) {
		this.dataQuery = dataQuery;
	} 
	public String execute() {
		Map<String, Object> session = ActionContext.getContext().getSession(); 
		String ret=SUCCESS;
		
		try{
			setModel((Model)session.get("model")); 
			Operation oper = new Operation(getModel()); 
			System.out.println("Query: " + getDataQuery());
			// TODO 
			setQs(oper.queryModelandGetList(getDataQuery()));

		}
		catch(Exception e){
			//TODO set correct exception
			ret=ERROR;
		}
		return ret; 
	}
	public Exception getE() {
		return e;
	}
	public void setE(Exception e) {
		this.e = e;
	}

}