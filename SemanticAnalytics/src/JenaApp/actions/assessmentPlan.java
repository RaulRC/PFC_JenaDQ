package JenaApp.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import DQModel.DQModel;
import JenaDQ.DQAssessment;
import JenaDQ.DQAssessmentPlan;
import JenaDQ.DQDimension;
import JenaDQ.MeasurementResult;
import JenaDQ._dimAccessibility;
import JenaDQ._dimCompleteness;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class assessmentPlan extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<MeasurementResult> mr; 

	private int depth; 
	private String endpoint; 
	private String uri; 
	private List<File> file;
	private File contextualrules;
	private String identifier; 
	private boolean completeness; 
	private boolean accessibility; 
	private String uriAssessment; 


	public String getUriAssessment() {
		return uriAssessment;
	}

	public void setUriAssessment(String uriAssessment) {
		this.uriAssessment = uriAssessment;
	}

	public ArrayList<MeasurementResult> getMr() {
		return mr;
	}

	public void setMr(ArrayList<MeasurementResult> mr) {
		this.mr = mr;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public File getContextualrules() {
		return contextualrules;
	}

	public void setContextualrules(File contextualrules) {
		this.contextualrules = contextualrules;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public boolean isCompleteness() {
		return completeness;
	}

	public void setCompleteness(boolean completeness) {
		this.completeness = completeness;
	}

	public boolean isAccessibility() {
		return accessibility;
	}

	public void setAccessibility(boolean accessibility) {
		this.accessibility = accessibility;
	}

	public String init(){
		Map<String, Object> session = ActionContext.getContext().getSession(); 

		// SETTING PLAN - DQAPLAN
		DQAssessmentPlan dqplan = new DQAssessmentPlan(); // TODO Atomicity
		LinkedList<DQAssessment> dqplanlist = new LinkedList<DQAssessment>();// TODO
		dqplan.setAssessmentList(dqplanlist);// TODO

		session.put("assessmentPlan", dqplan); 

		return SUCCESS; 
	}

	/**
	 * Add new assessment
	 * @return
	 */
	public String addAssessment(){
		Map<String, Object> session = ActionContext.getContext().getSession(); 
		String ret = SUCCESS; 

		//  --------------------------------------------------------
		InputStream in=null;
		InputStream inc=null;
		try {
			in = new FileInputStream(file.get(0));
			inc = new FileInputStream(file.get(1)); 
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(in)); 
		List<Rule> useRules = Rule.parseRules(Rule.rulesParserFromReader(br));
		System.out.println(useRules);

		BufferedReader brc = new BufferedReader(new InputStreamReader(inc)); 
		List<Rule> contextualRules = Rule.parseRules(Rule.rulesParserFromReader(brc));
		System.out.println(contextualRules);

		// ----------------------------------------------------------------
		try{

			// SETTING PLAN - DQAPLAN
			DQAssessmentPlan dqplan = (DQAssessmentPlan) session.get("assessmentPlan"); 
			// SETTING LIST OF DIMENSIONS I'm GOING TO ASSESS
			LinkedList<DQDimension> dqdimlist = new LinkedList<DQDimension>();

			// Check-box check
			if(isAccessibility())
				dqdimlist.add((DQDimension) new _dimAccessibility());
			if(isCompleteness())
				dqdimlist.add((DQDimension) new _dimCompleteness()); 

			dqplan.addDQAssessment(new DQAssessment(dqdimlist, getUri(), getEndpoint(),
					contextualRules, useRules, getDepth(), getIdentifier()));

		}
		catch(Exception e){
			//TODO set correct exception
			ret=ERROR;
		}
		return ret; 
	}

	/**
	 * Execute plan
	 * @return
	 */
	public String executePlan(){
		Map<String, Object> session = ActionContext.getContext().getSession(); 
		DQAssessmentPlan dqplan = (DQAssessmentPlan) session.get("assessmentPlan"); 
		dqplan.executePlan();
		setMr(dqplan.getmRes());


		//  STORE MODEL TDB
		//		String directory = "D:\\WebAppDatabases\\DatasetResult"; 
		//		Dataset dataset = TDBFactory.createDataset(directory); 
		//		Model tdbmodel = dataset.getDefaultModel();
		//		Model m = dataset.getNamedModel("http://localhost:3030/db/" + "SOMEIDENTIFIER");
		//		m.add(dqplan.getFinalModel()); 
		//		dataset.begin(ReadWrite.WRITE);
		//		tdbmodel.add(m); 
		//		dataset.end();

		// Putting model in session for download file
		session.put("resultModel", dqplan.getFinalModel());
		try {
			tdb(System.currentTimeMillis()+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS; 
	}

	public void tdb(String currentTime) throws Exception {

		String directory = "D:\\WebAppDatabases\\DatasetResult"; 
		Dataset dataset = TDBFactory.createDataset(directory) ;
		Map<String, Object> session = ActionContext.getContext().getSession();
		uriAssessment = "http://localhost:3030/db/AssessmentPlan_"+currentTime;
		Model resultModel = (Model) session.get("resultModel");

		// Using transactions
		dataset.begin(ReadWrite.WRITE);
		try {
			Model tdbmodel = dataset.getDefaultModel();
			Model m = dataset.getNamedModel(uriAssessment);
			m.add(resultModel);

			tdbmodel.add(m);
			dataset.commit() ;
		} finally { 
			dataset.end() ; 
		}

		System.out.println(uriAssessment);

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
}
