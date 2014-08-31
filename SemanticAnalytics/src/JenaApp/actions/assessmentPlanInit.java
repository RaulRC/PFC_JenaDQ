package JenaApp.actions;

import java.util.LinkedList;
import java.util.Map;

import JenaDQ.APISemDQ;
import JenaDQ.DQAssessment;
import JenaDQ.DQAssessmentPlan;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class assessmentPlanInit extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String init() {
		Map<String, Object> session = ActionContext.getContext().getSession();

		// SETTING PLAN - DQAPLAN
		DQAssessmentPlan dqplan = APISemDQ.createAssessmentPlan();

		session.put("assessmentPlan", dqplan);

		return SUCCESS;
	}
}
