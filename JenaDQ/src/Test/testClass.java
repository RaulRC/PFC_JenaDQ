package Test;

import java.io.InputStream;
import java.util.LinkedList;

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
		String format = "RDF/XML"; 
		LinkedList<DQDimension> l = new LinkedList(); 
		DQDimension dq1 = new _dimCompleteness(); 
		DQDimension dq2 = new _dimPrecisionConsistency(); 
		
		l.add(dq1); l.add(dq2); 
		
		Model m = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open(userURI);
		if(in==null)
			throw new IllegalArgumentException("File not found"); 
		
		m.read(in, null, "N3"); 
		
		
		DQAssessment dq = new DQAssessment(l, m); 
		// Get all the ontologies involved
		System.out.println(m.getNsPrefixMap().toString());
		
	}

}
