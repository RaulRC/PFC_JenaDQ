package JenaDQ;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import DQModel.DQModel;
import JenaDQExceptions.IdentifierException;
import JenaDQExceptions.ModelGenerationException;
import JenaDQExceptions.RuleException;
import JenaDQExceptions.URIException;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.rulesys.Rule;

/**
 * 
 * Clase principal del modelo de calidad. Permite ser extendida sobreescribiendo
 * los métodos señalados con el fin de añadir nuevas dimensiones de calidad,
 * manteniendo todas las dimensiones una funcionalidad uniforme en lo que se
 * refiere a la gestión de su ejecución en el ámbito de un plan de evaluación
 * así como la gestión de los resultados que se generen.
 * 
 * @author Raúl Reguillo Carmona
 * 
 */
public class DQDimension {

	/**
	 * 
	 * @param targetmodel <code>DQModel</code> objetivo
	 */
	public DQDimension(DQModel targetmodel) {
		super();
		this.targetModel = targetmodel;
	}

	/**
	 * Constructor vacío
	 */
	public DQDimension() {
	}

	protected String dimName;
	protected DQModel targetModel;
	protected LinkedList<MeasurementResult> dimResults;
	protected List<Rule> useRules;
	protected List<Rule> contextualRules;
	protected int depth;

	protected String URI;
	protected String endpoint;
	protected String assessmentIdentifier;
	protected ArrayList<Double> assessmentResults;

	protected Model finalModel;

	// Results

	public ArrayList<Double> getAssessmentResults() {
		return assessmentResults;
	}

	public void setAssessmentResults(ArrayList<Double> assessmentResults) {
		this.assessmentResults = assessmentResults;
	}

	public String getAssessmentIdentifier() {
		return assessmentIdentifier;
	}

	public void setAssessmentIdentifier(String assessmentIdentifier) {
		this.assessmentIdentifier = assessmentIdentifier;
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

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public List<Rule> getUseRules() {
		return useRules;
	}

	public void setUseRules(List<Rule> useRules) {
		this.useRules = useRules;
	}

	public List<Rule> getContextualRules() {
		return contextualRules;
	}

	public void setContextualRules(List<Rule> contextualRules) {
		this.contextualRules = contextualRules;
	}

	public Model getFinalModel() {
		return finalModel;
	}

	public void setFinalModel(Model finalModel) {
		this.finalModel = finalModel;
	}

	public ArrayList<MeasurementResult> getmRes() {
		return mRes;
	}

	public void setmRes(ArrayList<MeasurementResult> mRes) {
		this.mRes = mRes;
	}

	protected ArrayList<MeasurementResult> mRes;

	public String getDimName() {
		return this.dimName;
	}

	public void setDimName(String dimName) {
		this.dimName = dimName;
	}

	public DQModel getTargetModel() {
		return this.targetModel;
	}

	public void setTargetModel(DQModel targetModel) {
		this.targetModel = targetModel;
	}

	public LinkedList<MeasurementResult> getDimResults() {
		return this.dimResults;
	}

	public void setDimResults(LinkedList<MeasurementResult> dimResults) {
		this.dimResults = dimResults;
	}

	public LinkedList<MeasurementResult> executeMeasures() {
		return null;

	}

	public double calculateDQMeasure(double nNot, double nTot) {
		return (1 - (nNot / nTot));
	}

	/**
	 * Recibe una query y un endpoint, devuelve un modelo RDF
	 * 
	 * @param endpoint dirección del servicio HTTP
	 * @param queryString <code>String</code> con la consulta 
	 */
	@Deprecated
	public DQModel getResourceFromURI(String endpoint, String queryString) {
		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint,
				query);
		DQModel dq = new DQModel();
		Model results = qexec.execConstruct();
		dq.setDqmodel((Model) results);
		// for ( ; results.hasNext() ; ) {
		// QuerySolution soln = results.nextSolution() ;
		// System.out.println(soln);
		// }
		return dq;
	}

	/**
	 * 
	 * Ejecuta la(s) métrica(s) de la dimensión de calidad. El método
	 * debe ser sobreescrito cuando se particulariza la clase. 
	 * 
	 * @return modelo de Jena con los resultados de la evaluación
	 * @throws IdentifierException
	 * @throws RuleException
	 * @throws URIException
	 */
	public Model _executeMeasurement() throws IdentifierException,
			RuleException, URIException {
		return null;
	}

	/**
	 * 
	 * @return modelo Jena de resultados de la ejecución
	 * @throws ModelGenerationException
	 */
	public Model _getRDFModel() throws ModelGenerationException {
		return null;
	}

	/**
	 * 
	 * @return modelo Jena de resultados contextuales de la ejecución
	 */
	public Model _contextualFinalModel() {
		return null;
	}

	/**
	 * elimina los resultados
	 */
	public void resetResults() {
		this.assessmentResults = new ArrayList<Double>();
	}

	@Deprecated
	public void generateMRES(ArrayList<Double> results) {
	}

	public String toString() {
		return "";
	}

	/**
	 * Return a validity report (is valid)
	 * 
	 * @param inf Inference Model
	 * @return a Jena Validity Report 
	 */
	protected ValidityReport validate(InfModel inf) {
		ValidityReport val = inf.validate();
		if (val.isValid()) {
			// System.out.println("OK");
		} else {
			System.out.println("Conflicts");
			for (Iterator<?> i = val.getReports(); i.hasNext();) {
				System.out.println(" - " + i.next());
			}
		}
		return val;
	}

}