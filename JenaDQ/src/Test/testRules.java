package Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import DQModel.DQModel;
import JenaDQ.DQDimension;
import JenaDQ.MeasurementResult;
import JenaDQ._dimCompleteness;

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport.Report;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;

public class testRules {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */

	@SuppressWarnings({ "rawtypes", "unused" })
	public static void main(String[] args) throws FileNotFoundException {
		// RULES
		FileReader in = new FileReader("D:\\rules\\example.rules");
		BufferedReader br = new BufferedReader(in); 
		String endpoint = "http://dbpedia.org/sparql";
		String uri = "http://dbpedia.org/resource/Manakkara";
		int depth=2;
		List rules = Rule.parseRules(Rule.rulesParserFromReader(br));
		System.out.println(rules.toString());
		
		DQDimension completeness = new _dimCompleteness(new DQModel(endpoint, uri), rules, depth, endpoint, uri); 
		
		ArrayList<MeasurementResult> i = completeness._executeMeasurement();
		for(int j=0; j<i.size(); j++)
			System.out.println(i.get(j).toString());
//		for(MeasurementResult result:i)
//			System.out.println(i);
			
		
//		//MODEL
//		DQModel dq = new DQModel("http://dbpedia.org/sparql", "http://dbpedia.org/resource/The_Lord_of_the_Rings"); 
//		
//		//REASONER
//		Reasoner reasoner = new GenericRuleReasoner(rules);
//		
//		InfModel inf = ModelFactory.createInfModel(reasoner, dq.getModel());
//		
//	    ValidityReport validity = inf.validate();
//	    if (validity.isValid()) {
//	        System.out.println("OK");
//	    } else {
//	        System.out.println("Conflicts");
//	        for (Iterator i = validity.getReports(); i.hasNext(); ) {
//	            System.out.println(" - " + i.next());
//	        }
//	    }
//	    
//	    NodeIterator i = inf.listObjects();
//	    
//	    while (i.hasNext())
//	    	System.out.println(i.next().toString());
//		
	}

}
