package JenaDQ;

import java.util.LinkedList;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class DQAssessmentPlan {

	private boolean isPublic;
	private LinkedList<DQAssessment> assessmentList;
	private Model finalModel;

	public DQAssessmentPlan(boolean isPublic,
			LinkedList<DQAssessment> assessmentList) {
		super();
		this.isPublic = isPublic;
		this.assessmentList = assessmentList;
	}

	public DQAssessmentPlan() {

	}

	public boolean isIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public LinkedList<DQAssessment> getAssessmentList() {
		return this.assessmentList;
	}

	public void setAssessmentList(LinkedList<DQAssessment> dqalist) {
		this.assessmentList = dqalist;
	}

	/**
	 * Execute plan
	 * 
	 * @return
	 */
	public Model executePlan() {
		Model m = ModelFactory.createDefaultModel();

		for (DQAssessment dqassess : this.getAssessmentList())
			m = m.union(dqassess.executeAssessment());

		this.setFinalModel(m);
		return m;
	}

	public int publishPlan() {
		// TODO - implement DQAssessmentPlan.publishPlan
		throw new UnsupportedOperationException();
	}

	public Model getFinalModel() {
		return finalModel;
	}

	public void setFinalModel(Model finalModel) {
		this.finalModel = finalModel;
	}
	
	// API OPERATIONS
	
	/** 
	 * Add an assessment to Plan
	 * @param assessment
	 */
	public void addDQAssessment(DQAssessment assessment){
		this.getAssessmentList().add(assessment);
	}

}