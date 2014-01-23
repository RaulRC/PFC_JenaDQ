package Dominion;

import java.util.LinkedList;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class Testing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		DQModel m; 
		Operation oper; 
		m=new DQModel("http://datos.gijon.es/doc/cultura-ocio/bibliotecas.rdf");
		m.showModel();
		oper = new Operation(m.getModel());
	
		//m.showModelWithFormat("TTL");
		//System.out.println(oper.getAllProperties(m.getModel()).toString());
		//System.out.println(oper.getNumberOfProperties(m.getModel()));
		
		// Query
		
		String queryString ="PREFIX geo:<http://www.geonames.org/ontology#>" +
				" PREFIX cal:<http://www.w3.org/2002/12/cal#> " +
				" PREFIX dc:<http://purl.org/dc/terms/> " +
				"SELECT ?place ?name ?direction ?description " +
				"WHERE { " +
				"?place a<http://www.geonames.org/ontology#Feature> . " +
				"?place geo:name ?name . " +
 				"?place cal:street-address ?direction . " +
				"?place dc:description ?description" +
				"}";
		oper.queryModel(queryString);
		// TODO
	}
}
