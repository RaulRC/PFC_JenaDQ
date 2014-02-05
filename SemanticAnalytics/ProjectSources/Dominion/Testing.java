package Dominion;

import java.util.LinkedList;

import org.rosuda.REngine.Rserve.RserveException;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import R.Dominion.*;
public class Testing {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws RserveException 
	 */
	public static void main(String[] args) throws InterruptedException {
		 
		//r.getPlot("data(iris); attach(iris); plot(Sepal.Length, Petal.Length, col=unclass(Species)); dev.off()");
		for (int i=0; i<10; i++){

			Rinvoker r = new Rinvoker();
			//String data = "data(iris); attach(iris); plot(Sepal.Length, Petal.Length, col=unclass(Species)); dev.off()";
			String data = "a<-rnorm(100); barplot(a); dev.off()";	
			r.getPlot(data);
			System.out.println("Image ["+ (i+1) +"] printed");
			r.RinvokerClose();
 
			
		}

	}
}
