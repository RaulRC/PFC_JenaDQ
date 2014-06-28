package vocabulary;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class DQA {

	private static Model m_model = ModelFactory.createDefaultModel();
	// TODO DQA.owl
	public static final String NS = "http://www.dqassessment.org/ontologies/2014/9/DQA.owl#";

	/** <p>The namespace of the vocabulary as a string</p>
	 *  @see #NS */
	public static String getURI() {return NS;}

	/** <p>The namespace of the vocabulary as a resource</p> */
	public static final Resource NAMESPACE = m_model.createResource( NS );
	
	public static final Property COMPLETENESS =  m_model.createProperty( NS + "Completeness" );
	public static final Property COMPLETENESS_URI =  m_model.createProperty( NS + "inURI" );
	public static final Property COMPLETENESS_LEVEL =  m_model.createProperty( NS + "CompletenessInLevel" );
	public static final Property COMPLETENESS_LEVEL_RESULT =  m_model.createProperty( NS + "CompletenessMeasureInLevel" );
	
//	public static final Property COMPLETENESS_CONTEXTUAL =  m_model.createProperty( NS + "ContextualCompleteness" );
//	public static final Property COMPLETENESS_LEVEL_CONTEXTUAL =  m_model.createProperty( NS + "ContextualCompletenessInLevel" );
	public static final Property COMPLETENESS_LEVEL_RESULT_CONTEXTUAL =  m_model.createProperty( NS + "ContextualMeasureInLevel" );
	
}
