package Dominion;

import java.io.InputStream;

import com.hp.hpl.jena.rdf.model.Model;

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

}
