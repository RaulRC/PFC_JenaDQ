package JenaDQ;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import utilities.UriUtil;
import DQModel.DQModel;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class _dimCompleteness extends DQDimension {

	@SuppressWarnings("rawtypes")
	private List ruleList;
	private int depth; 
	private String endpoint; 
	private String uriLvLZero; 

	public _dimCompleteness(DQModel targetmodel, List useRules, int depth, String endpoint, String uri) {
		super(targetmodel);
		this.dimName = "Completeness"; 
		this.setRuleList(useRules);
		this.setDepth(depth); 
		this.setEndpoint(endpoint); 
		this.setUriLvLZero(uri); 
	}

	public _dimCompleteness(DQModel targetmodel, List rules) {
		super(targetmodel);
		this.dimName = "Completeness"; 
		this.setRuleList(rules);
		this.setDepth(0); 
	}
	public _dimCompleteness(DQModel targetmodel) {
		super(targetmodel);
		this.dimName = "Completeness";  
	}

	/**
	 * @name InterlinkingCompleteness Method
	 * Calculates the number of interlinks in resources
	 * @return MeasurementResult InterlinkingCompleteness
	 */
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
	/**
	 * @name schemaCompleteness method
	 * Chek if every class and property from Ont.
	 * are present in the model too
	 * @return MeasurementResult schemaCompleteness
	 * Falta comprobar clase por clase
	 */
	public JenaDQ.MeasurementResult m_schemaCompleteness() {
		MeasurementResult mRes = new MeasurementResult("SchemaCompleteness", this.dimName); 

		Iterator<String> prefixCollection = this.getTargetModel().getModel().getNsPrefixMap().values().iterator();

		int total = 0;
		int notIn = 0;
		double result = 0;
		ExtendedIterator<OntProperty> ontProp; 

		OntModel base = ModelFactory.createOntologyModel(); 
		List<OntProperty> ontList = new LinkedList<OntProperty> (); 

		while (prefixCollection.hasNext()){
			try {
				base = ModelFactory.createOntologyModel();
				base.read(prefixCollection.next());
				ontList.addAll(base.listAllOntProperties().toList());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		System.out.println(ontList.toString());
		total = ontList.size();
		StmtIterator modelPropList = this.getTargetModel().getModel().listStatements(); 

		while (modelPropList.hasNext()){
			if(!ontList.contains(modelPropList.next().getPredicate()))
				notIn++;
		}

		//		String SOURCE = "http://www.w3.org/2000/01/rdf-schema#"; 
		//		OntModel base = ModelFactory.createOntologyModel();
		//		base.read( SOURCE, "RDF/XML" );
		//		base.write(System.out) ;
		//		System.out.println(base.listClasses().toString());
		//
		//		ExtendedIterator<OntProperty> a = base.listOntProperties();
		//		ExtendedIterator<OntClass> b = base.listNamedClasses();
		//		while (a.hasNext()){
		//			System.out.println("**"+a.next().toString());
		//		}
		//		while (b.hasNext()){
		//			System.out.println(">> "+b.next().toString());
		//		}
		//
		result =  this.calculateDQMeasure(notIn, total); 
		mRes.setMResult(result);
		return mRes; 
	}

	/**
	 * ExecuteMeasurement Completeness
	 */
	@SuppressWarnings("unused")
	public JenaDQ.MeasurementResult _executeMeasurement() {
		MeasurementResult mRes = new MeasurementResult("Global Completeness Measurement in level [0, " + getDepth()+"] ", this.dimName);
		ArrayList<ArrayList<RDFNode>> results = UriUtil.getResourcesInDepthQuery(getEndpoint(), getUriLvLZero(), getDepth());

		ArrayList<Double> resultsByLevel = new ArrayList(); 

		for(ArrayList<RDFNode> al:results)
			System.out.println(al.size()+"\t");

		Model aux = ModelFactory.createDefaultModel(); 

		DQModel dq = new DQModel(getEndpoint(), getUriLvLZero());
		Reasoner reasoner = new GenericRuleReasoner(getRuleList());

		Resource o = ResourceFactory.createResource("http://watever.com/assessment#NoCompleteness");
		Resource s = ResourceFactory.createResource(getUriLvLZero());

		InfModel inf = ModelFactory.createInfModel(reasoner,dq.getModel()); 
		StmtIterator iter = null;; 
		
		// LVL 0 - first graph
		if(getDepth()>=0){
			if(validate(inf).isValid()){
				iter = inf.listStatements(s, null, o);
				if(iter.hasNext())
					resultsByLevel.add(0.0); 
				else
					resultsByLevel.add(1.0);
			}
		}
		int total=0; 
		int notExist=0; 

		for(ArrayList<RDFNode> list:results){

			total = list.size();
			notExist = 0; 
			for(RDFNode node:list){
				if(node.isURIResource()){
					dq = new DQModel(getEndpoint(), node.toString());
					//						reasoner = new GenericRuleReasoner(getRuleList());
					s = ResourceFactory.createResource(node.toString());
					inf = ModelFactory.createInfModel(reasoner,dq.getModel()); 

					if(validate(inf).isValid()){
						iter = inf.listStatements(s, null, o);
						if(iter.hasNext()){
							notExist+=1;
							//								System.out.println(iter.next().toString());
						}
					}
				}
			}
			//				resultsByLevel.add((float) (1 - (notExist/total)));
			resultsByLevel.add(calculateDQMeasure(notExist, total));
			System.out.println("Added in level: " +calculateDQMeasure(notExist, total)+ " "+total+ " "+notExist );
			notExist=0; 
		}


		System.out.println(resultsByLevel.toString());


		mRes.setMResult(compLevel(this.getTargetModel().getModel(), getDepth()));
		return mRes;
	}

	private ValidityReport validate(InfModel inf) {
		ValidityReport val = inf.validate(); 
		if(val.isValid()){
			//			System.out.println("OK");
		}
		else{
			System.out.println("Conflicts");
			for (Iterator i = val.getReports(); i.hasNext(); ) {
				System.out.println(" - " + i.next());
			}
		}
		return val; 
	}

	private double compLevel(Model model, int dephlevel) {
		// TODO
		return 0;
	}

	@SuppressWarnings("rawtypes")
	public List getRuleList() {
		return ruleList;
	}

	public void setRuleList(@SuppressWarnings("rawtypes") List ruleList) {
		this.ruleList = ruleList;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getUriLvLZero() {
		return uriLvLZero;
	}

	public void setUriLvLZero(String uriLvLZero) {
		this.uriLvLZero = uriLvLZero;
	}
}