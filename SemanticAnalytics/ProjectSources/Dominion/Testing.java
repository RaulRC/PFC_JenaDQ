package Dominion;

import java.util.LinkedList;

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
	 */
	public static void main(String[] args) {
		Rinvoker r = new Rinvoker(); 
		
		String data = "a<-rnorm(10); barplot(a); dev.off()";
		
		//r.getPlot("data(iris); attach(iris); plot(Sepal.Length, Petal.Length, col=unclass(Species)); dev.off()");
		r.getPlot(data);
		r.RinvokerClose();
	}
}
