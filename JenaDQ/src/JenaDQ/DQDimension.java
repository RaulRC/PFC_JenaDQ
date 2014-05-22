package JenaDQ;

import java.util.LinkedList;

import com.hp.hpl.jena.Jena;
import com.hp.hpl.jena.rdf.model.Model;

public class DQDimension {

	private String dimName;
	private Model targetModel;
	private LinkedList<MeasurementResult> dimResults;

	public String getDimName() {
		return this.dimName;
	}

	public void setDimName(String dimName) {
		this.dimName = dimName;
	}

	public Model getTargetModel() {
		return this.targetModel;
	}

	public void setTargetModel(Model targetModel) {
		this.targetModel = targetModel;
	}

	public LinkedList<MeasurementResult> getDimResults() {
		return this.dimResults;
	}

	public void setDimResults(LinkedList<MeasurementResult> dimResults) {
		this.dimResults = dimResults;
	}

	public LinkedList<MeasurementResult> executeMeasures() {
		// TODO - implement DQDimension.executeMeasures
		throw new UnsupportedOperationException();
	}

}