package JenaDQ;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import DQModel.DQModel;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.reasoner.rulesys.Rule;

/**
 * APISemDQ is the main interface between programmer and JenaDQ. It is
 * integrated using a <code>Facade pattern</code>.
 * 
 * @author Raúl Reguillo Carmona
 * 
 */
public class APISemDQ {

	/**
	 * Answer an empty <code>DQAssessmentPlan</code>
	 * 
	 * @return DQAssessmentPlan
	 */
	public DQAssessmentPlan createAssessmentPlan() {
		DQAssessmentPlan dqplan = new DQAssessmentPlan();
		LinkedList<DQAssessment> dqplanlist = new LinkedList<DQAssessment>();
		dqplan.setAssessmentList(dqplanlist);
		return dqplan;
	}

	/**
	 * Answer a single Model with all results
	 * 
	 * @param dqplan
	 * @return Jena Model
	 */
	public Model executeAssessmentPlan(DQAssessmentPlan dqplan) {
		return dqplan.executePlan();
	}

	/**
	 * Answer the same DQAssessmentPlan with the DQAssessment added.
	 * 
	 * @param dqplan
	 * @param dqa
	 * @return DQAssessmentPlan
	 */
	public DQAssessmentPlan addDQAssessmentToPlan(DQAssessmentPlan dqplan,
			DQAssessment dqa) {
		dqplan.addDQAssessment(dqa);
		return dqplan;

	}

	/**
	 * Answer a new DQAssessment with the given parameters.
	 * 
	 * @param dqDimensionList
	 * @param uRI
	 * @param endpoint
	 * @param contextualRules
	 * @param depth
	 * @param dQAssessmentIdentifier
	 * @return DQAssessment
	 */
	public DQAssessment createDQAssessment(
			LinkedList<DQDimension> dqDimensionList, String uRI,
			String endpoint, List<Rule> contextualRules, int depth,
			String dQAssessmentIdentifier) {
		DQAssessment dqa = new DQAssessment(dqDimensionList, uRI, endpoint,
				contextualRules, depth, dQAssessmentIdentifier);

		return dqa;
	}

	/**
	 * Answer a new DQAssessment with the given parameters.
	 * 
	 * @param dqDimensionList
	 * @param uRI
	 * @param endpoint
	 * @param contextualRules
	 * @param useRules
	 * @param depth
	 * @param dQAssessmentIdentifier
	 * @return DQAssessment
	 */
	public DQAssessment createDQAssessment(
			LinkedList<DQDimension> dqDimensionList, String uRI,
			String endpoint, List<Rule> contextualRules, List<Rule> useRules,
			int depth, String dQAssessmentIdentifier) {
		DQAssessment dqa = new DQAssessment(dqDimensionList, uRI, endpoint,
				contextualRules, useRules, depth, dQAssessmentIdentifier);

		return dqa;

	}

	/**
	 * Answer an empty DQAssessment.
	 * 
	 * @return DQAssessment
	 */
	public DQAssessment createDQAssessment() {
		DQAssessment dqa = new DQAssessment();
		return dqa;
	}

	/**
	 * Answer a Model with the execution of the DQAssessment.
	 * 
	 * @param dq
	 * @return Model
	 */
	public Model executeDQAssessmentGetModel(DQAssessment dq) {
		return dq.executeAssessment();
	}

	/**
	 * Answer an ArrayList of MeasurementResults which is equivalent to the
	 * Model generated.
	 * 
	 * @param dq
	 * @return ArrayList<MeasurementResult>
	 */
	public ArrayList<MeasurementResult> executeDQAssessmentGetMeasurementResult(
			DQAssessment dq) {
		dq.executeAssessment();
		return dq.getmRes();
	}

	/**
	 * Answer the same DQAssessment with the DQDimension added.
	 * 
	 * @param dq
	 * @param dqdim
	 * @return DQAssessment
	 */
	public DQAssessment addDQDimensionToAssessment(DQAssessment dq,
			DQDimension dqdim) {
		dq.addDQDimension(dqdim);
		return dq;
	}

	/**
	 * Answer a DQDimension with the given parameters.
	 * 
	 * @param targetmodel
	 * @param useRules
	 * @param contextualRuleList
	 * @param depth
	 * @param endpoint
	 * @param uri
	 * @return _dimCompleteness
	 */
	public DQDimension createDQDimensionCompleteness(DQModel targetmodel,
			List<Rule> useRules, List<Rule> contextualRuleList, int depth,
			String endpoint, String uri) {
		return new _dimCompleteness(targetmodel, useRules, contextualRuleList,
				depth, endpoint, uri);
	}

	/**
	 * Answer a DQDimension with the given parameters.
	 * 
	 * @param targetmodel
	 * @param useRules
	 * @param contextualRuleList
	 * @param depth
	 * @param endpoint
	 * @param uri
	 * @return _dimAccessibility
	 */
	public DQDimension createDQDimensionAccessibility(DQModel targetmodel,
			List<Rule> useRules, List<Rule> contextualRuleList, int depth,
			String endpoint, String uri) {
		return new _dimAccessibility(targetmodel, useRules, contextualRuleList,
				depth, endpoint, uri);
	}

	/**
	 * Answer an empty DQModel.
	 * 
	 * @return DQModel
	 */
	public DQModel createDQModel() {
		return new DQModel();
	}

	/**
	 * Answer an DQModel using the given URI and endpoint.
	 * 
	 * @param endpoint
	 * @param URI
	 * @return DQModel
	 */
	public DQModel createDQModel(String endpoint, String URI) {
		return new DQModel(endpoint, URI);
	}

	/**
	 * Answer a DQModel using an Inputstream and a file format.
	 * 
	 * @param in
	 * @param format
	 * @return DQModel
	 */
	public DQModel createDQModel(InputStream in, String format) {
		return new DQModel(in, format);
	}

	/**
	 * Answer a Model which is the comparison of modelA and modelB.
	 * 
	 * @param modelA
	 * @param modelB
	 * @return Model
	 */
	public Model modelComparison(DQModel modelA, DQModel modelB) {
		return modelA.compareModelWith(modelB);
	}
}
