package DQModel;

import java.io.InputStream;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

/**
 * 
 * 
 * Facilita la construcción de modelos. Si se cargan los modelos desde archivo,
 * permite distinguir directamente su extensión. Si se especifica una URI y un
 * Endpoint, automáticamente devuelve el modelo resultante o una excepción.
 * 
 * @author Raúl Reguillo Carmona
 * 
 */
public class DQModel {
	private Model model;
	private String format;

	/**
	 * Construye el modelo a través de una cadena de caracteres que debe indicar
	 * la dirección del archivo rdf. Automáticamente detectará la extensión y
	 * generará el resultado.
	 * 
	 * @param userURI
	 *            enlace hacia el archivo de datos
	 */
	public DQModel(String userURI) {
		DataPicker dp = new DataPicker();
		DQModel aux;
		aux = dp.getModel(userURI);
		this.model = aux.getModel();
		this.format = aux.getFormat();
	}

	/**
	 * Construye el modelo a partir de un archivo cargado y un formato
	 * especificado.
	 * 
	 * @param in
	 *            InputStream con el archivo leido
	 * @param format
	 *            formato del archivo que se pretende cargar
	 */
	public DQModel(InputStream in, String format) {
		DataPicker dp = new DataPicker();
		DQModel aux;
		aux = dp.getModel(in, format);
		this.model = aux.getModel();
		this.format = aux.getFormat();
	}

	/**
	 * Construye el modelo a partir de una URI y un Endpoint.
	 * 
	 * @param endpoint
	 *            dirección del servicio HTTP
	 * @param URI
	 *            modelo que se pretende cargar
	 */
	public DQModel(String endpoint, String URI) {
		DataPicker dp = new DataPicker();
		DQModel aux;
		aux = dp.getModel(endpoint, URI);
		this.model = aux.getModel();
		this.format = aux.getFormat();
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
	public DQModel(String endpoint, String URI, boolean includeSubject) {
		DataPicker dp = new DataPicker();
		DQModel aux;
		aux = dp.getModel(endpoint, URI, includeSubject);
		this.model = aux.getModel();
		this.format = aux.getFormat();
	}

	/**
	 * 
	 * @return modelo Jena asociado al <code>DQModel</code>
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * 
	 * @param dqmodel
	 *            modelo Jena que se pretende asociar al <code>DQModel</code>
	 */
	public void setDqmodel(Model dqmodel) {
		this.model = dqmodel;
	}

	/**
	 * 
	 * @return formato del modelo cargado
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * 
	 * @param format
	 *            especifica el formato del modelo que se pretende cargar
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * Construye <code>DQModel</code> a partir de un modelo de Jena y un formato
	 * 
	 * @param model
	 *            modelo Jena que se pretende cargar
	 * @param format
	 *            formato por defecto asociado al modelo
	 */
	public DQModel(Model model, String format) {
		super();
		this.model = model;
		this.format = format;
	}

	/**
	 * Constructor vacío
	 */
	public DQModel() {
	}

	@Override
	public String toString() {
		return getModel().toString();
	}

	/**
	 * Muestra el modelo en el formato especificado
	 */
	public void showModel() {
		DataWriter dw = new DataWriter();
		dw.showModel(this);
	}

	/**
	 * Muestra el modelo según el formato que se le especifique
	 * 
	 * @param format
	 *            formato de salida para mostrar el modelo
	 */
	public void showModelWithFormat(String format) {
		DataWriter dw = new DataWriter();
		dw.showModelWithFormat(this, format);
	}

	/**
	 * Compara dos modelos A y B según la operación
	 * <code>(A v B) - (A ^ B)</code>
	 * 
	 * @param m
	 *            modelo de Jena que se pretende comparar con el modelo actual
	 * @return modelo de Jena resultante de la operación de comparación
	 */
	public Model compareModelWith(Model m) {
		DQModel dq = new DQModel(m, "N3");
		return compareModelWith(dq);
	}

	/**
	 * Compara dos modelos A y B según la operación
	 * <code>(A v B) - (A ^ B)</code>
	 * 
	 * @param m
	 *            <code>DQModel</code> que se pretende comparar con el modelo
	 *            actual
	 * @return modelo de Jena resultante de la operación de comparación
	 */
	public Model compareModelWith(DQModel m) {
		Model intersection = ModelFactory.createDefaultModel();
		Model result = ModelFactory.createDefaultModel();

		StmtIterator modelA = this.getModel().listStatements();

		Statement sta = null;
		while (modelA.hasNext()) {
			sta = modelA.next();
			if (m.getModel()
					.listStatements(
							new SimpleSelector(null, sta.getPredicate(), sta
									.getObject())).hasNext())
				intersection.add(sta);
		}
		// (A v B) - (A ^ B)
		result = (this.getModel().union(m.getModel())).difference(intersection);

		return result;
	}

	/**
	 * Compara dos modelos A y B obteniendo un porcentaje de similitud
	 * 
	 * @param m
	 *            <code>DQModel</code> que se pretende comparar con el modelo
	 *            actual
	 * @return un porcentaje de similitud entre ambos modelos
	 */
	public double affinity(DQModel m) {
		Model intersection = ModelFactory.createDefaultModel();
		double total = 0;
		double intersect = 0;
		double result = -1.0;

		StmtIterator modelA = this.getModel().listStatements();

		Statement sta = null;
		while (modelA.hasNext()) {
			sta = modelA.next();
			if (m.getModel()
					.listStatements(
							new SimpleSelector(null, sta.getPredicate(), sta
									.getObject())).hasNext())
				intersection.add(sta);
		}
		total = (this.getModel().union(m.getModel())).size();
		intersect = intersection.size();

		try {
			result = (intersect * 100) / total;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
