package Test;

import java.util.Iterator;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDB;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.util.FileManager;

public class TDBTest {

	public static void main (String [] args){
		// Make a TDB-backed dataset
		//		  String directory = "D:\\MyDatabases\\Dataset3" ;
		String directory = "D:\\MyDatabases\\Dataset4"; 
		Dataset dataset = TDBFactory.createDataset(directory) ;

		Iterator<String> list = dataset.listNames();

		while (list.hasNext()){
			System.out.println(list.next().toString());
		}
		
		// THIS WORKS!
//		Model tdbmodel = dataset.getDefaultModel();
//		Model m = dataset.getNamedModel("Led-Zeppelin");
//
//		m.add((new DQModel("http://dbpedia.org/data/Led_Zeppelin.n3")).getModel());
//		
//		dataset.begin(ReadWrite.WRITE);
//		
//		tdbmodel.add(m); 
//		
//// HAVE TO USE TRANSACTIONS
//		dataset.end() ;
		Model m = dataset.getNamedModel("Led-Zeppelin");
		m.write(System.out);
	}
}
