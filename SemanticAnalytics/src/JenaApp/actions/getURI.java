package JenaApp.actions;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Map;

import Dominion.*;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.util.FileManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class getURI extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String uri;
	private LinkedList<Property> modelProperties; 
	private Model model; 
	private String format; 
	private Exception e; 
	
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

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
			dq = new DQModel(uri); 
			setFormat(dq.getFormat()); 
			setModel(dq.getModel()); 
			dq.showModelWithFormat(getFormat());
			Operation oper = new Operation(dq.getModel());
			setModelProperties(oper.getAllProperties());
			session.put("model", dq.getModel()); 
		}
		catch(Exception e){
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
