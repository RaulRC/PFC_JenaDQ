package JenaDQ;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import DQModel.DQModel;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.reasoner.rulesys.Rule;

public class APISemDQ {

	/**
	 * DQ ASSESSMENT PLAN
	 * ***********************************************************
	 */

	public DQAssessmentPlan createAssessmentPlan() {
		DQAssessmentPlan dqplan = new DQAssessmentPlan(); 
		LinkedList<DQAssessment> dqplanlist = new LinkedList<DQAssessment>();
		dqplan.setAssessmentList(dqplanlist);
		return dqplan;
	}

	public Model executeAssessmentPlan(DQAssessmentPlan dqplan) {
		return dqplan.executePlan();
	}

	public DQAssessmentPlan addDQAssessmentToPlan(DQAssessmentPlan dqplan,
			DQAssessment dqa) {
		dqplan.addDQAssessment(dqa);
		return dqplan;

	}

	/**
	 * DQ ASSESSMENT ***********************************************************
	 */

	public DQAssessment createDQAssessment(
			LinkedList<DQDimension> dqDimensionList, String uRI,
			String endpoint, List<Rule> contextualRules, int depth,
			String dQAssessmentIdentifier) {
		DQAssessment dqa = new DQAssessment(dqDimensionList, uRI, endpoint,
				contextualRules, depth, dQAssessmentIdentifier);

		return dqa;
	}

	public DQAssessment createDQAssessment(
			LinkedList<DQDimension> dqDimensionList, String uRI,
			String endpoint, List<Rule> contextualRules, List<Rule> useRules,
			int depth, String dQAssessmentIdentifier) {
		DQAssessment dqa = new DQAssessment(dqDimensionList, uRI, endpoint,
				contextualRules, useRules, depth, dQAssessmentIdentifier);

		return dqa;

	}

	public DQAssessment createDQAssessment() {
		DQAssessment dqa = new DQAssessment();
		return dqa;
	}

	public Model executeDQAssessmentGetModel(DQAssessment dq) {
		return dq.executeAssessment();
	}

	public ArrayList<MeasurementResult> executeDQAssessmentGetMeasurementResult(
			DQAssessment dq) {
		dq.executeAssessment();
		return dq.getmRes();
	}

	public DQAssessment addDQDimensionToAssessment(DQAssessment dq,
			DQDimension dqdim) {
		dq.addDQDimension(dqdim);
		return dq;
	}

	/**
	 * DQ DIM ************************************************************
	 */

	public DQDimension createDQDimensionCompleteness(DQModel targetmodel,
			List<Rule> useRules, List<Rule> contextualRuleList, int depth,
			String endpoint, String uri) {
		return new _dimCompleteness(targetmodel, useRules, contextualRuleList,
				depth, endpoint, uri);
	}

	public DQDimension createDQDimensionAccessibility(DQModel targetmodel,
			List<Rule> useRules, List<Rule> contextualRuleList, int depth,
			String endpoint, String uri) {
		return new _dimAccessibility(targetmodel, useRules, contextualRuleList,
				depth, endpoint, uri);
	}
	
	/**
	 * DQ MODEL ************************************************************
	 */

	public DQModel createDQModel(){
		return new DQModel(); 
	}
	public DQModel createDQModel(String endpoint, String URI){
		return new DQModel(endpoint, URI); 		
	}
	public DQModel createDQModel(InputStream in, String format){
		return new DQModel(in,format); 
	}
	public Model modelComparison(DQModel modelA, DQModel modelB){
		return modelA.compareModelWith(modelB);
	}
}
