package vocabulary;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public class CopyOfDQA {

	private static Model m_model = ModelFactory.createDefaultModel();
	// TODO DQA.owl
	public static final String NS = "http://www.dqassessment.org/ontologies/2014/9/DQA.owl#";

	/** <p>The namespace of the vocabulary as a string</p>
	 *  @see #NS */
	public static String getURI() {return NS;}

	/** <p>The namespace of the vocabulary as a resource</p> */
	public static final Resource NAMESPACE = m_model.createResource( NS );
	
	public static final Property COMPLETENESS =  m_model.createProperty( NS + "CompletenessMeasure" );
	public static final Property COMPLVL0 =  m_model.createProperty( NS + "CompletenessMeasureLvl0" );
	public static final Property COMPLVL1 =  m_model.createProperty( NS + "CompletenessMeasureLvl1" );
	public static final Property COMPLVL2 =  m_model.createProperty( NS + "CompletenessMeasureLvl2" );
	public static final Property COMPLVL3 =  m_model.createProperty( NS + "CompletenessMeasureLvl3" );
	public static final Property COMPLVL4 =  m_model.createProperty( NS + "CompletenessMeasureLvl4" );
	
	public static final Property COMPLETENESS_CONTEXTUAL =  m_model.createProperty( NS + "ContextualCompletenessMeasure" );
	public static final Property COMPLVL0_CONTEXTUAL =  m_model.createProperty( NS + "ContextualCompletenessMeasureLvl0" );
	public static final Property COMPLVL1_CONTEXTUAL =  m_model.createProperty( NS + "ContextualCompletenessMeasureLvl1" );
	public static final Property COMPLVL2_CONTEXTUAL =  m_model.createProperty( NS + "ContextualCompletenessMeasureLvl2" );
	public static final Property COMPLVL3_CONTEXTUAL =  m_model.createProperty( NS + "ContextualCompletenessMeasureLvl3" );
	public static final Property COMPLVL4_CONTEXTUAL =  m_model.createProperty( NS + "ContextualCompletenessMeasureLvl4" );

}
