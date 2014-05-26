package JenaDQ;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import DQModel.DQModel;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class _dimCompleteness extends DQDimension {

	public _dimCompleteness(DQModel targetmodel) {
		super(targetmodel);
		this.dimName = "Completeness"; 
		// TODO Auto-generated constructor stub
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
}