package Test;

import java.util.Iterator;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
public class testONT {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	      OntModel m = ModelFactory.createOntologyModel( OntModelSpec.RDFS_MEM );
	        String NS = "http://example.com/test#";

	        OntClass a = m.createClass( NS + "A" );
	        OntClass b = m.createClass( NS + "B" );

	        a.addSubClass( b );

	        OntProperty c = m.createOntProperty( NS + "c" );
	        c.addRange( a );

	        m.write( System.out, "RDF/XML-ABBREV" );

	}

}
