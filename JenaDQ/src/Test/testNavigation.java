package Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import utilities.UriUtil;
import DQModel.DQModel;
import JenaDQ.DQDimension;
import JenaDQ._dimCompleteness;

import com.hp.hpl.jena.rdf.model.RDFNode;

public class testNavigation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//		String endpoint = "http://lod.openlinksw.com/sparql";
		String endpoint = "http://dbpedia.org/sparql";
		String uri = "http://dbpedia.org/resource/Metallica";
		int depth=2;

		ArrayList<ArrayList<RDFNode>> levels = new ArrayList<ArrayList<RDFNode>>();

		levels = UriUtil.getResourcesInDepth(endpoint, uri, depth);	
		

		
		for(int i=0; i<=depth; i++)
			System.out.print(i+":" +levels.get(i).size() + "\t" );
		
		
	}
}
