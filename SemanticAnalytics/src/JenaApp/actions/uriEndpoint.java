package JenaApp.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import DQModel.*;
import Dominion.Operation;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.util.FileManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class uriEndpoint extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String uri;
	private LinkedList<Property> modelProperties; 
	private Model model; 
	private String endpoint; 

	

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

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
}
