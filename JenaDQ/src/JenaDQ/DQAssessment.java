package JenaDQ;

import java.util.LinkedList;

import com.hp.hpl.jena.rdf.model.Model;
/**
 * This class associates a JenaModel (RDF file or similar)
 * with a set of DQ Dimensions to assess
 * 
 * @author Raúl Reguillo
 * @param dqDimensionList
 * @param dataset
 * @param assessmentResult
 * 
 * @see JenaModel
 * 
 *
 */
@SuppressWarnings("rawtypes")
public class DQAssessment implements Comparable{

	/**
	 * Constructor
	 * @param dataset (Jena Model)
	 */
	public DQAssessment(Model dataset) {
		super();
		this.dataset = dataset;
	}


	private LinkedList<DQDimension> dqDimensionList;
	private Model dataset;
	private DQAssessment assessmentResult;

	public int compareTo() {
		// TODO - implement DQAssessment.compareTo
		throw new UnsupportedOperationException();
	}

	public boolean equals() {
		// TODO - implement DQAssessment.equals
		throw new UnsupportedOperationException();
	}

	public DQAssessment executeAssessment() {
		// TODO - implement DQAssessment.executeAssessment
		throw new UnsupportedOperationException();
	}

	public int publishResult() {
		// TODO - implement DQAssessment.publishResult
		throw new UnsupportedOperationException();
	}

	public Model getDataset() {
		return dataset;
	}

	public void setDataset(Model dataset) {
		this.dataset = dataset;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean equals(Object arg0){
		// TODO 
		return true;
	}

}