package DQModel;

import java.io.InputStream;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

/**
 * Auxiliar class used to pick data in various ways
 * 
 * @author Raúl Reguillo Carmona
 * @link JenaDQ.src.DQModel.DQModel
 * 
 */
public class DataPicker {
	private static final short EXTENSION_LENGTH = 3;
	private Model model;
	private String format;

	/**
	 * @return Model
	 */
	public Model getM() {
		return model;
	}

	/**
	 * @param m
	 *            Model
	 */
	public void setModel(Model m) {
		this.model = m;
	}

	/**
	 * 
	 * @return String format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * 
	 * @param format
	 *            String format
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * Empty constructor
	 */
	public DataPicker() {

	}

	/**
	 * Answer a DQModel
	 * 
	 * @param userURI
	 *            adress of the file
	 * @return <code>DQModel</code>
	 */
	public DQModel getModel(String userURI) {
		Model m = ModelFactory.createDefaultModel();
		DQModel dqmodel = new DQModel();
		String format = "";

		InputStream in = FileManager.get().open(userURI);
		if (in == null)
			throw new IllegalArgumentException("File not found");

		format = userURI.substring(userURI.length() - EXTENSION_LENGTH);
		switch (format.toLowerCase()) {
		case ".n3":
			m.read(in, null, "N3");
			setFormat("N3");
			break;
		case "ttl":
			m.read(in, null, "TTL");
			setFormat("TTL");
			break;
		/*
		 * case "xml": m.read(in, null, ""); break;
		 */
		case "rdf":
			m.read(in, null, "RDF/XML");
			setFormat("RDF/XML");
			break;
		default:
			m.read(in, null, "N3");
			setFormat("N3");
			break;

		}
		setModel(m);
		dqmodel.setDqmodel(m);
		dqmodel.setFormat(getFormat());
		return dqmodel;
	}

	/**
	 * Answer a DQ Model from InputStream and format
	 * 
	 * @param in
	 *            <code>InputStream</code> of the file
	 * @param format
	 *            format of the model
	 * @return <code>DQModel</code>
	 */
	public DQModel getModel(InputStream in, String format) {
		Model m = ModelFactory.createDefaultModel();
		DQModel dqmodel = new DQModel();

		if (in == null)
			throw new IllegalArgumentException("File not found");

		m.read(in, null, format);
		setModel(m);
		dqmodel.setDqmodel(m);
		dqmodel.setFormat(format);
		return dqmodel;
	}

	/**
	 * Answer a DQModel using URI and Endpoint
	 * 
	 * @param endpoint
	 *            Address of the HTTP service
	 * @param URI
	 *            URI of the model wanted
	 * @return <code>DQModel</code>
	 */
	public DQModel getModel(String endpoint, String URI) {
		DQModel dqmodel = new DQModel();
		String constructModelString = "CONSTRUCT {" + " <" + URI
				+ "> ?p ?x} WHERE { " + " <" + URI + "> ?p ?x }";
		// Try with CONSTRUCT FROM uri
		// sameTerm URI ?x
		Query query = QueryFactory.create(constructModelString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint,
				query);
		Model m = qexec.execConstruct();
		setModel(m);
		dqmodel.setDqmodel(m);
		dqmodel.setFormat("N3");
		return dqmodel;
	}

	/**
	 * Creates a DQModel filterign the subject
	 * 
	 * @param endpoint
	 *            address of HTTP service
	 * @param URI
	 *            URI of the model
	 * 
	 * @param includeSubject
	 *            <code>false</code> avoid the URI as a subject in the model
	 * 
	 */
	public DQModel getModel(String endpoint, String URI, boolean includeSubject) {
		DQModel dqmodel = new DQModel();
		String constructModelString = "";

		if (includeSubject == true) {
			constructModelString = "CONSTRUCT {" + " <" + URI
					+ "> ?p ?x} WHERE { " + " <" + URI + "> ?p ?x }";
		} else {
			constructModelString = "CONSTRUCT {" + " <" + URI
					+ "> ?p ?x} WHERE { " + " <" + URI + "> ?p ?x . FILTER ( <"
					+ URI + "> != ?x ) }";
		}

		Query query = QueryFactory.create(constructModelString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint,
				query);
		Model m = qexec.execConstruct();
		setModel(m);
		dqmodel.setDqmodel(m);
		dqmodel.setFormat("N3");
		return dqmodel;
	}

	/**
	 * Answer the format of the model as a String
	 * 
	 * @param filename
	 *            name or path of the file
	 * @return file format
	 */
	public String checkFormat(String filename) {
		String format = filename
				.substring(filename.length() - EXTENSION_LENGTH);
		switch (format.toLowerCase()) {
		case ".n3":
			format = "N3";
			break;
		case "ttl":
			format = "TTL";
			break;
		/*
		 * case "xml": m.read(in, null, ""); break;
		 */
		case "rdf":
			format = "RDF/XML";
			break;
		}
		return format;

	}

}
