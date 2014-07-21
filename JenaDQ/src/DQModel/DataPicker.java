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
 * clase auxiliar que da soporte a la clase <code>DQModel</code> cargando los
 * modelos que se pretenden evaluar a partir de archivos o URIs
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
	 * @return Modelo de Jena
	 */
	public Model getM() {
		return model;
	}

	/**
	 * @param m
	 *            modelo de Jena
	 */
	public void setModel(Model m) {
		this.model = m;
	}

	/**
	 * 
	 * @return formato del modelo
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * 
	 * @param format
	 *            formato del modelo
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * constructor vacío
	 */
	public DataPicker() {

	}

	/**
	 * Construye el modelo a partir de una dirección web donde reside el archivo
	 * rdf
	 * 
	 * @param userURI
	 *            dirección del archivo
	 * @return <code>DQModel</code> resultante, incluyendo modelo de Jena y
	 *         extensión
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
	 * Carga un archivo RDF a través de <code>InputStream</code> y formato de
	 * ese mismo archivo
	 * 
	 * @param in
	 *            <code>InputStream</code> del archivo que se pasa como
	 *            argumento
	 * @param format
	 *            formato del modelo a leer
	 * @return <code>DQModel</code> resultante, incluyendo modelo de Jena y
	 *         extensión
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
	 * Construye el modelo tomando la dirección del mismo y de su endpoint
	 * 
	 * @param endpoint
	 *            dirección del servicio HTTP
	 * @param URI
	 *            uri del modelo que se pretende construir
	 * @return <code>DQModel</code> resultante, incluyendo modelo de Jena y
	 *         extensión por defecto
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
	 * filtra la aparición de la uri que se utiliza como sujeto en la
	 * construcción del modelo
	 * 
	 * @param endpoint
	 *            dirección del servicio HTTP
	 * @param URI
	 *            modelo que se pretende cargar
	 * 
	 * @param includeSubject
	 *            <code>false</code> deshabilita la aparición de la URI en el
	 *            modelo resultante
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
	 * devuelve el formato del nombre del archivo que se pasa como parámetro
	 * 
	 * @param filename
	 *            nombre o ruta del archivo
	 * @return formato del archivo
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
