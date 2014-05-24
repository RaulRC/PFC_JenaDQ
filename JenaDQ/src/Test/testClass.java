package Test;

import java.util.LinkedList;

import DQModel.DQModel;
import JenaDQ.DQAssessment;
import JenaDQ.DQDimension;
import JenaDQ._dimCompleteness;
import JenaDQ._dimPrecisionConsistency;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class testClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String userURI = "http://dbpedia.org/data/The_Fellowship_of_the_Ring.n3"; 
//		String userURI = "http://datos.gijon.es/doc/medio-ambiente/areas-recreativas.ttl";
		DQModel m = new DQModel(userURI); 
		
		LinkedList<DQDimension> l = new LinkedList<DQDimension>(); 
		_dimCompleteness dq1 = new _dimCompleteness(m); 
		DQDimension dq2 = new _dimPrecisionConsistency(m); 
		
		l.add(dq1); 
		//System.out.println(((_dimCompleteness) l.element()).m_interlinkingCompleteness()); 
		//m.write(System.out); 

		DQAssessment dq = new DQAssessment(l, m); 
		// Get all the ontologies involved
		System.out.println(m.getModel().getNsPrefixMap().toString());

		
		
	}

}
