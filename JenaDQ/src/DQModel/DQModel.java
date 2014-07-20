package DQModel;

import java.io.InputStream;
import java.util.List;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class DQModel {
	private Model model; 
	private String format;
	
	// Constructor for URLs
	public DQModel(String userURI) {
		DataPicker dp = new DataPicker();
		DQModel aux; 
		aux = dp.getModel(userURI);
		this.model = aux.getModel();
		this.format = aux.getFormat();
	}
	
	// Constructor for file uploader
	public DQModel(InputStream in, String format) {
		DataPicker dp = new DataPicker();
		DQModel aux; 
		aux = dp.getModel(in, format);
		this.model = aux.getModel();
		this.format = aux.getFormat();
	}
	// Constructor for Endpoint
	public DQModel(String endpoint, String URI) {
		DataPicker dp = new DataPicker();
		DQModel aux; 
		aux = dp.getModel(endpoint, URI);
		this.model = aux.getModel();
		this.format = aux.getFormat();
	}
	public DQModel(String endpoint, String URI, boolean sameSubject) {
		DataPicker dp = new DataPicker();
		DQModel aux; 
		aux = dp.getModel(endpoint, URI, sameSubject);
		this.model = aux.getModel();
		this.format = aux.getFormat();
	}
	public Model getModel() {
		return model;
	}
	public void setDqmodel(Model dqmodel) {
		this.model = dqmodel;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public DQModel(Model model, String format) {
		super();
		this.model = model;
		this.format = format;
	}
	public DQModel() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return getModel().toString(); 
	} 
	
	public void showModel(){
		DataWriter dw = new DataWriter(); 
		dw.showModel(this); 
	}
	public void showModelWithFormat(String format){
		DataWriter dw = new DataWriter(); 
		dw.showModelWithFormat(this, format); 
	}
	
	// COMPARISON
	
	public Model compareModelWith(Model m){
		DQModel dq = new DQModel(m, "N3"); 
		return compareModelWith(dq); 
	}
	
	// COMPARISON
	public Model compareModelWith(DQModel m){
		Model intersection = ModelFactory.createDefaultModel();
		Model result = ModelFactory.createDefaultModel(); 
		
		StmtIterator modelA = this.getModel().listStatements(); 
		
		Statement sta=null; 
		while (modelA.hasNext()){
			sta = modelA.next(); 
			if (m.getModel().listStatements(new SimpleSelector(null, sta.getPredicate(), sta.getObject())).hasNext())
				intersection.add(sta); 
		}
		// (A v B) - (A ^ B)
		result = (this.getModel().union(m.getModel())).difference(intersection); 

		return result; 
	}

}
