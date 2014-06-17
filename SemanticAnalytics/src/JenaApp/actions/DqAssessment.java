package JenaApp.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Dominion.Operation;
import JenaDQ.*; 
import DQModel.*; 

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DqAssessment extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private ArrayList<MeasurementResult> mr; 
	private Model model; 
	private int depth; 
	private String endpoint; 
	private String uri; 
	private File file;
	private String filename;
	
	
	public String execute() {
		Map<String, Object> session = ActionContext.getContext().getSession(); 
		String ret=SUCCESS;
		DQModel dq = new DQModel(); 
		// TODO --------------------------------------------------------
		InputStream in=null;
		try {
			in = new FileInputStream(this.file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(in)); 
		List<Rule> useRules = Rule.parseRules(Rule.rulesParserFromReader(br));
		System.out.println(useRules);
		// ----------------------------------------------------------------
		try{
			setModel((Model)session.get("model")); 
			setUri((String)session.get("URI"));
			setEndpoint((String)session.get("ENDPOINT"));
			
			dq.setDqmodel(getModel());
			_dimCompleteness comp= new _dimCompleteness(dq, useRules, getDepth(),getEndpoint(),getUri() ); 
			mr = new ArrayList<MeasurementResult>();
			mr.addAll(comp._executeMeasurement());
			setMr(mr);
		}
		catch(Exception e){
			//TODO set correct exception
			ret=ERROR;
		}
		return ret; 
	}

	public ArrayList<MeasurementResult> getMr() {
		return mr;
	}

	public void setMr(ArrayList<MeasurementResult> mr2) {
		this.mr = mr2;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
