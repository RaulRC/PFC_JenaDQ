package JenaDQ;

import java.util.LinkedList;

import com.hp.hpl.jena.rdf.model.Model;

public class DQAssessment {

	public DQAssessment(LinkedList<DQDimension> dqDimensionList, Model dataset) {
		super();
		this.dqDimensionList = dqDimensionList;
		this.dataset = dataset;
	}
	public DQAssessment() {

	}
	
	private LinkedList<DQDimension> dqDimensionList;
	private Model dataset;

	public int executeAssessment() {
		// TODO - implement DQAssessment.executeAssessment
		throw new UnsupportedOperationException();
	}

	public int publishResult() {
		// TODO - implement DQAssessment.publishResult
		throw new UnsupportedOperationException();
	}
	
	public String toString(){
		return "DQAssessment: \n" + dataset.write(System.out, "N3") + "\n" + dqDimensionList.toString();
	}

}