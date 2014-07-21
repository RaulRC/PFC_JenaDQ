package JenaDQ;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import vocabulary.DQA;

import DQModel.DQModel;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.rulesys.Rule;

/**
 * Crea y gestiona evaluaciones. Una evaluación está compuesta de una URI
 * objetivo, una lista de dimensiones de calidad y un endpoint.
 * 
 * @author Raúl Reguillo Carmona
 * 
 */
public class DQAssessment {

	/**
	 * 
	 * @param dqDimensionList
	 *            lista de dimensiones de calidad
	 * @param URI
	 *            URI objetivo
	 * @param endpoint
	 *            dirección del servicio HTTP
	 */
	public DQAssessment(LinkedList<DQDimension> dqDimensionList, String URI,
			String endpoint) {
		super();
		this.dqDimensionList = dqDimensionList;
		this.URI = URI;
		this.endpoint = endpoint;
	}

	/**
	 * Constructor vacío
	 */
	public DQAssessment() {
		this.dqDimensionList = new LinkedList<DQDimension>();
		this.useRules = new ArrayList<Rule>();
		this.contextualRules = new ArrayList<Rule>();

	}

	/**
	 * Constructor completamente parametrizado
	 * 
	 * @param dqDimensionList
	 *            lista de dimensiones de calidad
	 * @param uRI
	 *            URI objetivo
	 * @param endpoint
	 *            dirección del servicio HTTP
	 * @param contextualRules
	 *            conjunto de reglas contextuales en formato Jena
	 * @param useRules
	 *            conjunto de reglas de uso en formato Jena
	 * @param depth
	 *            profundidad a la que se llevará a cabo la evaluación
	 * @param dQAssessmentIdentifier
	 *            identificador de evaluación
	 */
	public DQAssessment(LinkedList<DQDimension> dqDimensionList, String uRI,
			String endpoint, List<Rule> contextualRules, List<Rule> useRules,
			int depth, String dQAssessmentIdentifier) {
		super();
		this.dqDimensionList = dqDimensionList;
		URI = uRI;
		this.endpoint = endpoint;
		this.contextualRules = contextualRules;
		this.useRules = useRules;
		this.depth = depth;
		this.DQAssessmentIdentifier = dQAssessmentIdentifier;
	}

	/**
	 * 
	 * @param dqDimensionList
	 *            lista de dimensiones de calidad
	 * @param uRI
	 *            URI objetivo
	 * @param endpoint
	 *            dirección del servicio HTTP
	 * @param contextualRules
	 *            conjunto de reglas contextuales en formato Jena
	 * @param depth
	 *            profundidad a la que se llevará a cabo la evaluación
	 * @param dQAssessmentIdentifier
	 *            identificador de evaluación
	 */
	public DQAssessment(LinkedList<DQDimension> dqDimensionList, String uRI,
			String endpoint, List<Rule> contextualRules, int depth,
			String dQAssessmentIdentifier) {
		super();
		this.dqDimensionList = dqDimensionList;
		URI = uRI;
		this.endpoint = endpoint;
		this.contextualRules = contextualRules;
		this.depth = depth;
		DQAssessmentIdentifier = dQAssessmentIdentifier;
	}

	private LinkedList<DQDimension> dqDimensionList;
	private String URI;
	private String endpoint;
	private List<Rule> contextualRules;
	private List<Rule> useRules;
	private int depth;
	private String DQAssessmentIdentifier;
	private Model finalModel;
	public ArrayList<MeasurementResult> mRes;

	public ArrayList<MeasurementResult> getmRes() {
		return mRes;
	}

	public void setmRes(ArrayList<MeasurementResult> mRes) {
		this.mRes = mRes;
	}

	public LinkedList<DQDimension> getDqDimensionList() {
		return dqDimensionList;
	}

	public void setDqDimensionList(LinkedList<DQDimension> dqDimensionList) {
		this.dqDimensionList = dqDimensionList;
	}

	public List<Rule> getContextualRules() {
		return contextualRules;
	}

	public void setContextualRules(List<Rule> contextualRules) {
		this.contextualRules = contextualRules;
	}

	public List<Rule> getUseRules() {
		return useRules;
	}

	public void setUseRules(List<Rule> useRules) {
		this.useRules = useRules;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	/**
	 * 
	 * @return modelo Jena resultado de la evaluación
	 */
	public Model executeAssessment() {
		// set FinalModel here

		mRes = new ArrayList<MeasurementResult>();
		mRes.add(new MeasurementResult(this.getURI(), this.getDqDimensionList()
				.toString(), -1.0, "Assessment Data"));

		Model m = ModelFactory.createDefaultModel();
		// Setting DQDimension parameters
		// TIME
		Calendar cal = GregorianCalendar.getInstance();
		Literal value = m.createTypedLiteral(cal);
		m.createResource(DQA.NS + this.getDQAssessmentIdentifier())
				.addProperty(DQA.INTIME, value);

		for (DQDimension dqdim : dqDimensionList) {
			setParameters(dqdim);
			try {
				m = m.union(dqdim._executeMeasurement());
				this.setFinalModel(m);
				mRes.addAll(dqdim.getmRes());

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		return m;
	}

	/**
	 * Set all needed parameters for the DQDimension selected
	 * 
	 * @param dqdim
	 */
	private void setParameters(DQDimension dqdim) {
		dqdim.setTargetModel(new DQModel(this.getEndpoint(), this.getURI()));
		dqdim.setContextualRules(this.getContextualRules());
		dqdim.setUseRules(this.getUseRules());
		dqdim.setDepth(this.getDepth());
		dqdim.setURI(this.getURI());
		dqdim.setEndpoint(this.getEndpoint());
		dqdim.setAssessmentIdentifier(this.getDQAssessmentIdentifier());
		dqdim.resetResults();
	}

	// public int publishResult() {
	//
	// throw new UnsupportedOperationException();
	// }

	public String toString() {
		return "DQAssessment: \n" + this.URI + "\n"
				+ dqDimensionList.toString();
	}

	public String getURI() {
		return URI;
	}

	public void setURI(String uRI) {
		URI = uRI;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getDQAssessmentIdentifier() {
		return DQAssessmentIdentifier;
	}

	public Model getFinalModel() {
		return finalModel;
	}

	public void setFinalModel(Model finalModel) {
		this.finalModel = finalModel;
	}

	// API OPERATIONS

	/**
	 * Add a new Dimension to assess
	 * 
	 * @param dimension
	 */
	public void addDQDimension(DQDimension dimension) {
		this.getDqDimensionList().add(dimension);
	}

	/**
	 * Add new rules to existing ones
	 * 
	 * @param contextualRules
	 */
	public void addContextualRules(List<Rule> contextualRules) {
		this.getContextualRules().addAll(contextualRules);
	}

	/**
	 * Add new rules to existing ones
	 * 
	 * @param useRules
	 */
	public void addUseRules(List<Rule> useRules) {
		this.getUseRules().addAll(useRules);
	}

}