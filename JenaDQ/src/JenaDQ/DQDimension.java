package JenaDQ;

import java.util.LinkedList;

import DQModel.DQModel;

import com.hp.hpl.jena.rdf.model.Model;

public class DQDimension {

	public DQDimension(DQModel targetmodel) {
		super();
		this.targetModel = targetmodel;
	}

	protected String dimName;
	protected DQModel targetModel;
	protected LinkedList<MeasurementResult> dimResults;

	public String getDimName() {
		return this.dimName;
	}

	public void setDimName(String dimName) {
		this.dimName = dimName;
	}

	public DQModel getTargetModel() {
		return this.targetModel;
	}

	public void setTargetModel(DQModel targetModel) {
		this.targetModel = targetModel;
	}

	public LinkedList<MeasurementResult> getDimResults() {
		return this.dimResults;
	}

	public void setDimResults(LinkedList<MeasurementResult> dimResults) {
		this.dimResults = dimResults;
	}

	public LinkedList<MeasurementResult> executeMeasures() {
		return null;

	}
	public double calculateDQMeasure(double nNot, double nTot){
		return 1 - nNot/nTot; 
	}

}