package JenaDQ;

import DQModel.DQModel;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class _dimCompleteness extends DQDimension {

	public _dimCompleteness(DQModel targetmodel) {
		super(targetmodel);
		this.dimName = "Completeness"; 
		// TODO Auto-generated constructor stub
	}

	public JenaDQ.MeasurementResult m_interlinkingCompleteness() {
		MeasurementResult mRes = new MeasurementResult("InterlinkingCompleteness", this.dimName); 

		RDFNode rdfn;
		Statement st;
		StmtIterator iter = this.getTargetModel().getModel().listStatements();
		
		int countNoUri = 0; 
		int total = 0;
		double result = 0; 
		
		while(iter.hasNext()){
			st = iter.next();
			total++;
			rdfn = st.getObject(); 
			if (!rdfn.isURIResource())
				countNoUri++;
		}
		result = this.calculateDQMeasure(countNoUri, total); 
		mRes.setMResult(result);
		//System.out.println("RESULT: " + countNoUri+"/"+total +" = " + result);
		return mRes; 
	}
	public JenaDQ.MeasurementResult m2() {
		MeasurementResult mRes = new MeasurementResult("m2", this.dimName); 

		return mRes; 
	}
}