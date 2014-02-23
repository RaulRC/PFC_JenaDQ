package Dominion;

import java.util.LinkedList;

import org.rosuda.REngine.Rserve.RserveException;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.tdb.TDB;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.VCARD;

import R.Dominion.*;
public class Testing {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws RserveException 
	 */
	static String personURI    = "http://somewhere/JohnSmith";
	static String fullName     = "John Smith";
	static String directory = "D:\\tempFiles2" ;
	public static void main(String[] args) throws InterruptedException {

		
		Dataset dataset = TDBFactory.createDataset(directory) ;
		Model tdb = dataset.getNamedModel("graph-bib");
//		String source = "SomeDocuments/bibliotecas.n3";
//		FileManager.get().readModel(tdb, source);
//		tdb.close();
//
//
		tdb = dataset.getNamedModel("graph-bib");
		tdb.write(System.out, "N-TRIPLES");

		
		dataset.close();

	}
}
