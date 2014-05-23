package JenaDQ;

import com.hp.hpl.jena.rdf.model.Model;

public class DataWriter {
	
	public DataWriter(){
		
	}
	
	public Model writeModel(String userFormat){
		return null; 	
	}
	
	/*
	 * Shows the current model
	 */
	public void showModel(DQModel dqmodel){
		dqmodel.getModel().write(System.out, dqmodel.getFormat()); 
	}
	public void showModelWithFormat(DQModel dqmodel, String format){
		dqmodel.getModel().write(System.out, format); 
	}
}