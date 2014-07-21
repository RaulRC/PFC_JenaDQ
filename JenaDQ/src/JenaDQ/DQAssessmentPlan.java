package JenaDQ;

import java.util.ArrayList;
import java.util.LinkedList;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * Crea y gestiona planes de evaluación. Un plan de evaluación consiste en un
 * conjunto de evaluaciones. Cada evaluación se realizará para una URI en su
 * Endpoint, con un conjunto de reglas específico.
 * 
 * @author Raúl Reguillo Carmona
 * 
 */
public class DQAssessmentPlan {

	private boolean isPublic;
	private LinkedList<DQAssessment> assessmentList;
	private Model finalModel;
	private ArrayList<MeasurementResult> mRes;

	/**
	 * Constructor vacío
	 */
	public DQAssessmentPlan() {

	}

	@Deprecated
	public boolean isIsPublic() {
		return this.isPublic;
	}

	@Deprecated
	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * Devuelve la lista de evaluaciones
	 * 
	 * @return lista de evaluaciones
	 */
	public LinkedList<DQAssessment> getAssessmentList() {
		return this.assessmentList;
	}

	/**
	 * Establece la lista de evaluaciones
	 * 
	 * @param dqalist
	 *            lista de evaluaciones
	 */
	public void setAssessmentList(LinkedList<DQAssessment> dqalist) {
		this.assessmentList = dqalist;
	}

	/**
	 * Ejecuta el plan de evaluación. Cada evaluación es ejecutada y sus
	 * resultados se añaden a los resultados de las demás, con el fin de tener
	 * un único modelo de resultado.
	 * 
	 * @return Model modelo de Jena con los resultados de todas las evaluaciones
	 */
	public Model executePlan() {
		Model m = ModelFactory.createDefaultModel();
		mRes = new ArrayList<MeasurementResult>();

		for (DQAssessment dqassess : this.getAssessmentList()) {
			m = m.union(dqassess.executeAssessment());
			mRes.addAll(dqassess.getmRes());
		}

		this.setFinalModel(m);
		System.out.println(mRes.toString());
		return m;
	}

	/**
	 * 
	 * @return finalModel el modelo de resultados final
	 */
	public Model getFinalModel() {
		return finalModel;
	}

	/**
	 * Establece el modelo de resultados final
	 * 
	 * @param finalModel
	 *            de resultados final
	 */
	public void setFinalModel(Model finalModel) {
		this.finalModel = finalModel;
	}

	// API OPERATIONS

	/**
	 * Add an assessment to this plan
	 * 
	 * @param assessment
	 *            evaluación a añadir en el plan
	 */
	public void addDQAssessment(DQAssessment assessment) {
		this.getAssessmentList().add(assessment);
	}

	/**
	 * 
	 * @return obtiene los resultados reflejados en el modelo final en un
	 *         formato de <code>ArrayList</code>
	 */
	public ArrayList<MeasurementResult> getmRes() {
		return mRes;
	}

	/**
	 * 
	 * @param mRes
	 *            establece los resultados del modelo final en un formato de
	 *            <code>ArrayList</code>
	 */
	public void setmRes(ArrayList<MeasurementResult> mRes) {
		this.mRes = mRes;
	}

}