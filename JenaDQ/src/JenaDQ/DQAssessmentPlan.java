package JenaDQ;

import java.util.LinkedList;
import com.hp.hpl.jena.rdf.model.Model;

public class DQAssessmentPlan {

	private String date;
	private boolean isPublic;
	private LinkedList<DQAssessment> assessmentList;

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public LinkedList<Model> executePlan() {
		// TODO - implement DQAssessmentPlan.executePlan
		throw new UnsupportedOperationException();
	}

	public int publishPlan() {
		// TODO - implement DQAssessmentPlan.publishPlan
		throw new UnsupportedOperationException();
	}

}