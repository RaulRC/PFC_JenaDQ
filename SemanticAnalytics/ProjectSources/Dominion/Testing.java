package Dominion;

import java.util.LinkedList;

import org.rosuda.REngine.Rserve.RserveException;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.VCARD;
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

// TDB Example		
		Dataset dataset = TDBFactory.createDataset(directory) ;
//		Model tdb = dataset.getNamedModel("graph-bib");
	//	Model tdb = dataset.getNamedModel("modelo-islandia"); 
//		Model tdbmodel = TDBFactory.createModel("http://dbpedia.org/data/Iceland.n3")
		DQModel dq = new DQModel("http://dbpedia.org/data/Iceland.n3");
		dataset.begin(ReadWrite.WRITE);
		FileManager.get().readModel(dq.getModel(), "http://dbpedia.org/data/Iceland.n3");
//		tdb.write(System.out, "N-TRIPLES");
		dataset.close();
		
		Operation oper = new Operation(dq.getModel()); 
		LinkedList<Property> pl = oper.getAllProperties();
		Property p; 
		ResIterator iter = null;
// returns the number of "<string>" instances 
		while(!pl.isEmpty()){
			p = pl.remove();
			if(p.toString().contains("stateOfOrigin"))
				iter = dq.getModel().listSubjectsWithProperty(p);
		}
		System.out.println(iter.toList().size());
		
		

	}
}
