package JenaApp.actions;

import java.util.LinkedList;
import java.util.Map;

import DQModel.*;
import Dominion.Operation;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class uriEndpoint extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String uri;
	private LinkedList<Property> modelProperties; 
	private Model model; 
	private String endpoint; 
	private Exception e; 



	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public LinkedList<Property> getModelProperties() {
		return modelProperties;
	}

	public void setModelProperties(LinkedList<Property> modelProperties) {
		this.modelProperties = modelProperties;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}


	public String execute() {
		String ret=SUCCESS;
		Map<String, Object> session = ActionContext.getContext().getSession(); 
		System.out.println("Uri: " + uri);

		try{
			DQModel dq;
			dq = new DQModel(this.endpoint, this.uri); 
			//			setFormat(dq.getFormat()); 
			setModel(dq.getModel()); 
			//			dq.showModelWithFormat(getFormat());
			Operation oper = new Operation(dq.getModel());
			setModelProperties(oper.getAllProperties());
			session.put("model", dq.getModel()); 
			session.put("URI", getUri());
			session.put("ENDPOINT", getEndpoint());

		}
		catch(Exception e){
			ret=ERROR;
		}
		return ret; 
	}

	// TODO Adding validation
	public void validate(){
		if(getEndpoint().length()==0)
			addFieldError("endpoint", getText("endpoint.required") );
		if(getUri().length()==0)
			addFieldError("uri", getText("uri.required") );
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public Exception getE() {
		return e;
	}

	public void setE(Exception e) {
		this.e = e;
	}
}
