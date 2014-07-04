package JenaDQ;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import utilities.UriUtil;
import vocabulary.DQA;
import DQModel.DQModel;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.VCARD;

public class _dimAccesibility extends DQDimension {


	public ArrayList<MeasurementResult> getmRes() {
		return mRes;
	}

	public void setmRes(ArrayList<MeasurementResult> mRes) {
		this.mRes = mRes;
	}

	public _dimAccesibility(DQModel targetmodel, List<Rule> useRules,
			List<Rule> contextualRuleList, int depth, String endpoint,
			String uri) {
		super(targetmodel);
		this.dimName = "Accesibility";
		this.setRuleList(useRules);
		this.setDepth(depth);
		this.setEndpoint(endpoint);
		this.useRules = contextualRuleList;
		mRes = new ArrayList<MeasurementResult>();
		mRes.add(new MeasurementResult("Interlinking", this.dimName));
		this.assessmentResults = new ArrayList<Double>(); 
	}

	public _dimAccesibility(DQModel targetmodel, List<Rule> rules) {
		super(targetmodel);
		this.dimName = "Accesibility";
		this.setRuleList(rules);
		this.setDepth(0);
		mRes = new ArrayList<MeasurementResult>();
		mRes.add(new MeasurementResult("Interlinking", this.dimName));
		this.assessmentResults = new ArrayList<Double>(); 
	}

	public _dimAccesibility(DQModel targetmodel) {
		super(targetmodel);
		this.dimName = "Accesibility";
		mRes = new ArrayList<MeasurementResult>();
		mRes.add(new MeasurementResult("Interlinking", this.dimName));
		this.assessmentResults = new ArrayList<Double>(); 
	}

	public _dimAccesibility() {
		super();
		mRes = new ArrayList<MeasurementResult>();
		mRes.add(new MeasurementResult("Interlinking", this.dimName));
		this.assessmentResults = new ArrayList<Double>(); 
	}


	/**
	 * ExecuteMeasurement Completeness
	 */
	public Model _executeMeasurement() {

		RDFNode rdfn;
		Statement st;
		StmtIterator iter = this.getTargetModel().getModel().listStatements();

		int countNoUri = 0;
		int total = 0;
		double result = 0;

		while (iter.hasNext()) {
			st = iter.next();
			total++;
			rdfn = st.getObject();
			if (!rdfn.isURIResource())
				countNoUri++;
			else{
				//TODO
				// de-reference URI and check errors
				// Check if is a file or something
			}
		}
		result = this.calculateDQMeasure(countNoUri, total);
		System.out.println(this.URI + " ->>>> " + result);
		this.assessmentResults.add(result); 
		mRes.get(0).setMResult(result);
		// Generate model
		this.setFinalModel(this._getRDFModel());
		// Return model
		return this.getFinalModel();
	}

	/**
	 * Generates the final RDF Model with the rules
	 */
	public Model _getRDFModel() {

		Model m = ModelFactory.createDefaultModel();

		Reasoner reasoner = new GenericRuleReasoner(this.getContextualRules());
		InfModel inf = null;

		Literal result = null;

		result = m.createTypedLiteral(
				new Double(assessmentResults.get(0)));
		// TODO prefixes
		//			mList.get(i).setNsPrefix("dqa", DQA.NS); 
		m
		.createResource(DQA.NS + this.assessmentIdentifier)
		.addProperty(RDF.type, DQA.ACCESIBILITY)
		.addProperty(
				DQA.ACCESIBILITY,
				m.createResource()
				.addProperty(DQA.INURI, this.getURI())
				.addProperty(DQA.ACCESIBILITY_RESULT, result)); 

		// inference here
		inf = ModelFactory.createInfModel(reasoner,m);
		//			inf2.setNsPrefix("dqa", DQA.NS); 
		validate(inf);	
		return inf;
	}

	/**
	 * Return a validity report (is valid)
	 * 
	 * @param inf
	 * @return
	 */
	private ValidityReport validate(InfModel inf) {
		ValidityReport val = inf.validate();
		if (val.isValid()) {
			// System.out.println("OK");
		} else {
			System.out.println("Conflicts");
			for (Iterator<?> i = val.getReports(); i.hasNext();) {
				System.out.println(" - " + i.next());
			}
		}
		return val;
	}

	public void setRuleList(List<Rule> ruleList) {
		this.useRules = ruleList;
	}

	public ArrayList<Double> getAssessmentResults() {
		return assessmentResults;
	}

	public void setAssessmentResults(ArrayList<Double> assessmentResults) {
		this.assessmentResults = assessmentResults;
	}

}