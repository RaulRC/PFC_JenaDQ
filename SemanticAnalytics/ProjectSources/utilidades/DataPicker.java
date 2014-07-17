package utilidades;

import java.io.InputStream;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class DataPicker {
	private static final short EXTENSION_LENGTH = 3;
	private Model model; 
	private String format; 
	
	public Model getM() {
		return model;
	}

	public void setModel(Model m) {
		this.model = m;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public DataPicker(){
		
	}
	
	public DQModel getModel(String userURI){
		Model m = ModelFactory.createDefaultModel();
		DQModel dqmodel = new DQModel(); 
		String format = ""; 
		
		InputStream in = FileManager.get().open(userURI);
		if(in==null)
			throw new IllegalArgumentException("File not found"); 
		
		format = userURI.substring(userURI.length()-EXTENSION_LENGTH);
		switch(format.toLowerCase()){
		case ".n3" :
			m.read(in, null, "N3");
			setFormat("N3");
			break; 	
		case "ttl":
			m.read(in, null, "TTL");
			setFormat("TTL");
			break;
/*		case "xml":
			m.read(in, null, "");
			break; */
		case "rdf":
			m.read(in, null, "RDF/XML");
			setFormat("RDF/XML");
			break;
		}
		setModel(m);
		dqmodel.setDqmodel(m); 
		dqmodel.setFormat(getFormat()); 
		return dqmodel; 
	}
	public DQModel getModel(InputStream in, String format){
		Model m = ModelFactory.createDefaultModel();
		DQModel dqmodel = new DQModel(); 

		if(in==null)
			throw new IllegalArgumentException("File not found"); 
	 
		m.read(in, null, format); 
		setModel(m);
		dqmodel.setDqmodel(m); 
		dqmodel.setFormat(format); 
		return dqmodel; 
	}

	@SuppressWarnings("unused")
	public String checkFormat(String filename) {
		String format = filename.substring(filename.length()-EXTENSION_LENGTH);
		switch(format.toLowerCase()){
		case ".n3" :
			format = "N3";
			break; 	
		case "ttl":
			format = "TTL";
			break;
/*		case "xml":
			m.read(in, null, "");
			break; */
		case "rdf":
			format = "RDF/XML";
			break;
		}
		return format; 
		
	}

}
