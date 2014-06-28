package JenaDQ;

import java.util.LinkedList;
import java.util.List;

import DQModel.DQModel;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.rulesys.Rule;

public class DQAssessment {

	public DQAssessment(LinkedList<DQDimension> dqDimensionList, String URI,
			String endpoint) {
		super();
		this.dqDimensionList = dqDimensionList;
		this.URI = URI;
		this.endpoint = endpoint;
	}

	public DQAssessment() {

	}

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

	public int executeAssessment() {
		// TODO - implement DQAssessment.executeAssessment
		// set FinalModel here
		int result = 0;
		Model m = ModelFactory.createDefaultModel(); 
		// Setting DQDimension parameters
		for (DQDimension dqdim : dqDimensionList) {
			setParameters(dqdim);
			try {
				m = m.union(dqdim._executeMeasurement());
				this.setFinalModel(m);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = -1;
			}
		}
		return result;
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
	}

	public int publishResult() {
		// TODO - implement DQAssessment.publishResult
		throw new UnsupportedOperationException();
	}

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

}