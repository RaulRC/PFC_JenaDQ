package Test;

import java.io.InputStream;
import java.util.LinkedList;

import DQModel.DQModel;
import JenaDQ.DQAssessment;
import JenaDQ.DQDimension;
import JenaDQ._dimCompleteness;
import JenaDQ._dimPrecisionConsistency;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class testClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String userURI = "http://dbpedia.org/data/The_Fellowship_of_the_Ring.n3"; 
		
		LinkedList<DQDimension> l = new LinkedList<DQDimension>(); 
		DQDimension dq1 = new _dimCompleteness(); 
		DQDimension dq2 = new _dimPrecisionConsistency(); 
		
		l.add(dq1); l.add(dq2); 
		
		DQModel m = new DQModel(userURI); 

		//m.write(System.out); 
		
		DQAssessment dq = new DQAssessment(l, m); 
		// Get all the ontologies involved
		System.out.println(dq.toString());
		System.out.println(m.getModel().getNsPrefixMap().toString());
		
	}

}
