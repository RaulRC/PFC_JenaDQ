package JenaDQ;

import java.util.LinkedList;
import com.hp.hpl.jena.rdf.model.Model;

public class DQAssessmentPlan {
	/**
	 * This class provides data structures to handle
	 * a whole dq assessment considering a set of RDF files (or URIs)
	 * and a set of DQ dimensions
	 * 
	 * @author Raúl Reguillo
	 * @param date 	the date of the assessment plan
	 * @param isPublic if is public or private. User could associate users with DQAssessmentPlan URIs in another database
	 * @param assessmentList the list we have to create to associate a dataset with a list of DQ dimension 
	 * @see DQAssessment
	 */

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