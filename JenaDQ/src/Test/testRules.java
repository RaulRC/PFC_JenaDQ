package Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import JenaDQ.DQAssessment;
import JenaDQ.DQAssessmentPlan;
import JenaDQ.DQDimension;
import JenaDQ._dimAccessibility;
import JenaDQ._dimCompleteness;
import JenaDQExceptions.AssessmentException;
import JenaDQExceptions.IdentifierException;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.reasoner.rulesys.Rule;

public class testRules {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */

	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public static void main(String[] args) throws FileNotFoundException, AssessmentException {
		// RULES
		FileReader in = new FileReader("D:\\rules\\example.rules");
		BufferedReader br = new BufferedReader(in);
		String endpoint = "http://dbpedia.org/sparql";
		// String endpoint = "http://lod.openlinksw.com/sparql";
		String uri = "http://dbpedia.org/resource/Manakkara";
		String uri2 = "http://dbpedia.org/resource/Metallica";
		String uri3 = "http://dbpedia.org/resource/Life_of_Pi";
		int depth = 0;
		List rules = Rule.parseRules(Rule.rulesParserFromReader(br));
		FileReader in2 = new FileReader("D:\\rules\\contextual.rules");
		BufferedReader br2 = new BufferedReader(in2);
		List contextualRules = Rule.parseRules(Rule.rulesParserFromReader(br2));

		System.out.println(rules.toString());
		System.out.println(contextualRules.toString());

		// TODO - API
		// SETTING PLAN - DQAPLAN
		DQAssessmentPlan dqplan = new DQAssessmentPlan();
		LinkedList<DQAssessment> dqplanlist = new LinkedList<DQAssessment>();
		dqplan.setAssessmentList(dqplanlist);

		// SETTING LIST OF DIMENSIONS I'm GOING TO ASSESS
		LinkedList<DQDimension> dqdimlist = new LinkedList<DQDimension>();
		dqdimlist.add((DQDimension) new _dimAccessibility());
		dqdimlist.add((DQDimension) new _dimCompleteness()); 
		
		LinkedList<DQDimension> dqdimlist2 = new LinkedList<DQDimension>();
		dqdimlist2.add((DQDimension) new _dimAccessibility());

		// ADDING ASSESSMENTS
//		dqplanlist.add(new DQAssessment(dqdimlist, uri, endpoint,
//				contextualRules, rules, depth, "IDENTIFIER_GENERATED"));
//		dqplanlist.add(new DQAssessment(dqdimlist, uri2, endpoint,
//				contextualRules, rules, 1, "IDENTIFIER_GENERATED"));
		
		dqplan.addDQAssessment(new DQAssessment(dqdimlist, uri, endpoint,
				contextualRules, rules, 2, "IDENTIFIER_ONE"));
		dqplan.addDQAssessment(new DQAssessment(dqdimlist2, uri2, endpoint,
				contextualRules, rules, 0, "IDENTIFIER_TWO"));
		dqplan.addDQAssessment(new DQAssessment(dqdimlist, uri3, endpoint,
				contextualRules, rules, 1, "IDENTIFIER_THREE"));
		

		dqplan.executePlan();
		Model mod = dqplan.getFinalModel();

		mod.write(System.out, "TTL");


	}

}
