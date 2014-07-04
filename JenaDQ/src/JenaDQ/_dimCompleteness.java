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

public class _dimCompleteness extends DQDimension {


	public ArrayList<MeasurementResult> getmRes() {
		return mRes;
	}

	public void setmRes(ArrayList<MeasurementResult> mRes) {
		this.mRes = mRes;
	}

	public _dimCompleteness(DQModel targetmodel, List<Rule> useRules,
			List<Rule> contextualRuleList, int depth, String endpoint,
			String uri) {
		super(targetmodel);
		this.dimName = "Completeness";
		this.setRuleList(useRules);
		this.setDepth(depth);
		this.setEndpoint(endpoint);
		this.useRules = contextualRuleList;
		mRes = null;
	}

	public _dimCompleteness(DQModel targetmodel, List<Rule> rules) {
		super(targetmodel);
		this.dimName = "Completeness";
		this.setRuleList(rules);
		this.setDepth(0);
		mRes = null;
	}

	public _dimCompleteness(DQModel targetmodel) {
		super(targetmodel);
		this.dimName = "Completeness";
	}

	public _dimCompleteness() {
		super();
	}

	/**
	 * @name InterlinkingCompleteness Method Calculates the number of interlinks
	 *       in resources
	 * @return MeasurementResult InterlinkingCompleteness
	 */
	@Deprecated
	public JenaDQ.MeasurementResult m_interlinkingCompleteness() {
		MeasurementResult mRes = new MeasurementResult(
				"InterlinkingCompleteness", this.dimName);

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
		}
		result = this.calculateDQMeasure(countNoUri, total);
		mRes.setMResult(result);
		// System.out.println("RESULT: " + countNoUri+"/"+total +" = " +
		// result);
		return mRes;
	}

	/**
	 * @name schemaCompleteness method Chek if every class and property from
	 *       Ont. are present in the model too
	 * @return MeasurementResult schemaCompleteness Falta comprobar clase por
	 *         clase
	 */
	@Deprecated
	public JenaDQ.MeasurementResult m_schemaCompleteness() {
		MeasurementResult mRes = new MeasurementResult("SchemaCompleteness",
				this.dimName);

		Iterator<String> prefixCollection = this.getTargetModel().getModel()
				.getNsPrefixMap().values().iterator();

		int total = 0;
		int notIn = 0;
		double result = 0;
		OntModel base = ModelFactory.createOntologyModel();
		List<OntProperty> ontList = new LinkedList<OntProperty>();

		while (prefixCollection.hasNext()) {
			try {
				base = ModelFactory.createOntologyModel();
				base.read(prefixCollection.next());
				ontList.addAll(base.listAllOntProperties().toList());
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
		System.out.println(ontList.toString());
		total = ontList.size();
		StmtIterator modelPropList = this.getTargetModel().getModel()
				.listStatements();

		while (modelPropList.hasNext()) {
			if (!ontList.contains(modelPropList.next().getPredicate()))
				notIn++;
		}
		result = this.calculateDQMeasure(notIn, total);
		mRes.setMResult(result);
		return mRes;
	}

	/**
	 * ExecuteMeasurement Completeness
	 */
	public Model _executeMeasurement() {
		ArrayList<ArrayList<RDFNode>> results = UriUtil
				.getResourcesInDepthQuery(getEndpoint(), this.getURI(),
						getDepth());

		// Results are gonna be here
		ArrayList<Double> resultsByLevel = new ArrayList<Double>();
		mRes = new ArrayList<MeasurementResult>();

		int total;
		int notExist;

		// LVL 0 - first graph
		// -----------------------------------------------------------
		DQModel dq = new DQModel(getEndpoint(), this.getURI());
		Reasoner reasoner = new GenericRuleReasoner(this.getUseRules());

		// TODO DQ property to assess
		Resource o = ResourceFactory.createResource(DQA.NS + "NoCompleteness");
		Resource s = ResourceFactory.createResource(this.getURI());

		InfModel inf = ModelFactory.createInfModel(reasoner, dq.getModel());
		StmtIterator iter = null;
		

		if (getDepth() >= 0) {
			if (validate(inf).isValid()) {
				iter = inf.listStatements(s, null, o);
				if (iter.hasNext())
					resultsByLevel.add(0.0);
				else
					resultsByLevel.add(1.0);
			}
		}
		// LVL 0 - first graph
		// END--------------------------------------------------------
		total = 0;
		notExist = 0;
		// For each list and each node in list
		for (ArrayList<RDFNode> list : results) {
			total = list.size();
			notExist = 0;
			for (RDFNode node : list) {
				if (node.isURIResource()) {
					dq = new DQModel(getEndpoint(), node.toString());
					s = ResourceFactory.createResource(node.toString());
					inf = ModelFactory.createInfModel(reasoner, dq.getModel());

					if (validate(inf).isValid()) {
						iter = inf.listStatements(s, null, o);
						if (iter.hasNext()) {
							notExist += 1;
						}
					}
				}
			}
			resultsByLevel.add(calculateDQMeasure(notExist, total));
			notExist = 0;
		}

		// generate RDF result
		setAssessmentResults(resultsByLevel);
		// Reasoner -> apply the contextual rules here
		// generate final RDF with DQ assessment
		this.setFinalModel(this._getRDFModel());

		generateMRES(resultsByLevel); 

		return this.finalModel;
	}

	@Deprecated
	public void generateMRES(ArrayList<Double> results) {
		// Generating DQ results
		// Setting up the Data Structure no RDF for easily pick the results
		String query = "";
		Query q = null;
		for (int i = 0; i < results.size(); i++) {

			query = "PREFIX dqa: <http://www.dqassessment.org/ontologies/2014/9/DQA.owl#>\n"
					+ "SELECT ?x WHERE { "
					+ "?a dqa:CompletenessMeasure ?y. \n"
					+ "?a dqa:ContextualMeasurel ?x . "
					+ "FILTER ( ?y = " + i + ") } \n";

			q = QueryFactory.create(query);
			QueryExecution qExe = QueryExecutionFactory.create(q,
					getFinalModel());
			ResultSet resultsRes = qExe.execSelect();

			if (resultsRes.hasNext())
				mRes.add(new MeasurementResult("Lvl " + i, this.dimName,
						results.get(i), resultsRes.next()
						.getResource("?x").toString()));
			else
				mRes.add(new MeasurementResult("Lvl " + i, this.dimName,
						results.get(i)));
		}

	}

	/**
	 * Generates the final RDF Model with the rules
	 */
	public Model _getRDFModel() {

		Model m = ModelFactory.createDefaultModel();
		@SuppressWarnings("unused")
		Date d = new Date();
		// TODO getTime no funcionará si quiero unirlos después
		@SuppressWarnings("unused")
		Resource assessment = null;

		ArrayList<Model> mList = new ArrayList<Model>();

		Reasoner reasoner2 = new GenericRuleReasoner(this.getContextualRules());
		InfModel inf2 = null;

		Literal lResult = null;
		Literal lLevel = null;

		ArrayList<Model> result = new ArrayList<Model>();
		for (int i = 0; i < assessmentResults.size(); i++) {
			mList.add(ModelFactory.createDefaultModel());

			lResult = mList.get(i).createTypedLiteral(
					new Double(assessmentResults.get(i)));
			lLevel = mList.get(i).createTypedLiteral(new Integer(i));
			// TODO prefixes
			//			mList.get(i).setNsPrefix("dqa", DQA.NS); 
			assessment = mList
					.get(i)
					.createResource(DQA.NS + this.assessmentIdentifier)
					.addProperty(RDF.type, DQA.COMPLETENESS)
					.addProperty(
							DQA.COMPLETENESS_RESULT,
							mList.get(i)
							.createResource()
							.addProperty(DQA.INURI,
									this.getURI())
									.addProperty(DQA.INLEVEL, lLevel)
									.addProperty(DQA.COMPLETENESS_MEASUREMENT,
											lResult));

			// inference here
			inf2 = ModelFactory.createInfModel(reasoner2, mList.get(i));
			//			inf2.setNsPrefix("dqa", DQA.NS); 
			validate(inf2);
			result.add(inf2);

		}
		// Time to compact all models generated in one
		for (Model mod : result)
			m = m.union(mod);
		return m;

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