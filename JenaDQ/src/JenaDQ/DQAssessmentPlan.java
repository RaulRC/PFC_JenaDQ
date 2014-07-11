package JenaDQ;

import java.util.ArrayList;
import java.util.LinkedList;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class DQAssessmentPlan {

	private boolean isPublic;
	private LinkedList<DQAssessment> assessmentList;
	private Model finalModel;
	private ArrayList<MeasurementResult> mRes; 

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
		mRes = new ArrayList<MeasurementResult> (); 

		for (DQAssessment dqassess : this.getAssessmentList()){
			m = m.union(dqassess.executeAssessment());
			mRes.addAll(dqassess.getmRes());
		}

		this.setFinalModel(m);
		System.out.println(mRes.toString());
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

	public ArrayList<MeasurementResult> getmRes() {
		return mRes;
	}

	public void setmRes(ArrayList<MeasurementResult> mRes) {
		this.mRes = mRes;
	}

}