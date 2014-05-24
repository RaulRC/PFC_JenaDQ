package JenaDQ;

import java.util.LinkedList;

import DQModel.DQModel;

import com.hp.hpl.jena.rdf.model.Model;

public class DQAssessment {

	public DQAssessment(LinkedList<DQDimension> dqDimensionList, DQModel dataset) {
		super();
		this.dqDimensionList = dqDimensionList;
		this.dataset = dataset;
	}
	public DQAssessment() {

	}
	
	private LinkedList<DQDimension> dqDimensionList;
	private DQModel dataset;

	public int executeAssessment() {
		// TODO - implement DQAssessment.executeAssessment
		throw new UnsupportedOperationException();
	}

	public int publishResult() {
		// TODO - implement DQAssessment.publishResult
		throw new UnsupportedOperationException();
	}
	
	public String toString(){
		return "DQAssessment: \n" + dataset.toString() + "\n" + dqDimensionList.toString();
	}

}