package Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import DQModel.DQModel;
import JenaDQ.DQAssessment;
import JenaDQ.DQAssessmentPlan;
import JenaDQ.DQDimension;
import JenaDQ.MeasurementResult;
import JenaDQ._dimAccesibility;
import JenaDQ._dimCompleteness;

import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
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
		// String endpoint = "http://lod.openlinksw.com/sparql";
		String uri = "http://dbpedia.org/resource/Manakkara";
		String uri2 = "http://dbpedia.org/resource/Metallica";
		int depth = 2;
		List rules = Rule.parseRules(Rule.rulesParserFromReader(br));
		FileReader in2 = new FileReader("D:\\rules\\contextual.rules");
		BufferedReader br2 = new BufferedReader(in2);
		List contextualRules = Rule.parseRules(Rule.rulesParserFromReader(br2));

		System.out.println(rules.toString());
		System.out.println(contextualRules.toString());

		// TODO - API
		// SETTING PLAN - DQAPLAN
		DQAssessmentPlan dqplan = new DQAssessmentPlan();
		LinkedList<DQAssessment> dqplanlist = new LinkedList();
		dqplan.setAssessmentList(dqplanlist);

		// SETTING LIST OF DIMENSIONS I'm GOING TO ASSESS
		LinkedList<DQDimension> dqdimlist = new LinkedList();
		dqdimlist.add((DQDimension) new _dimAccesibility());
		dqdimlist.add((DQDimension) new _dimCompleteness()); 

		// ADDING ASSESSMENTS
//		dqplanlist.add(new DQAssessment(dqdimlist, uri, endpoint,
//				contextualRules, rules, depth, "IDENTIFIER_GENERATED"));
//		dqplanlist.add(new DQAssessment(dqdimlist, uri2, endpoint,
//				contextualRules, rules, 1, "IDENTIFIER_GENERATED"));
		
		dqplan.addDQAssessment(new DQAssessment(dqdimlist, uri, endpoint,
				contextualRules, rules, depth, "IDENTIFIER_GENERATED"));
		dqplan.addDQAssessment(new DQAssessment(dqdimlist, uri2, endpoint,
				contextualRules, rules, 1, "ANOTHER_IDENTIFIER"));
		

		// EXECUTE PLAN
		dqplan.executePlan();

		Model mod = dqplan.getFinalModel();

		mod.write(System.out, "N3");

	}

}
