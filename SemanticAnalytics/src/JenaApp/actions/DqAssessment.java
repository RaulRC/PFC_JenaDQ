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

import utilidades.Operation;

import JenaDQ.*;
import NAMES.TDBNames;
import DQModel.*;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.reasoner.rulesys.Rule.ParserException;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DqAssessment extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private ArrayList<MeasurementResult> mr;
	private Model model;
	private int depth;
	private String endpoint;
	private String uri;
	private List<File> file;
	private File contextualrules;
	private String identifier;
	private boolean completeness;
	private boolean accessibility;
	private String uriAssessment;
	private Exception e;
	private String errorMsg;

	public String getUriAssessment() {
		return uriAssessment;
	}

	public void setUriAssessment(String uriAssessment) {
		this.uriAssessment = uriAssessment;
	}

	public File getContextualRules() {
		return contextualrules;
	}

	public void setContextualRules(File contextualRules) {
		this.contextualrules = contextualRules;
	}

	private String filename;

	public String execute() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String ret = SUCCESS;
		DQModel dq = new DQModel();
		// TODO --------------------------------------------------------
		InputStream in = null;
		InputStream inc = null;
		List<Rule> useRules = null;
		List<Rule> contextualRules = null;

		try {
			inc = new FileInputStream(file.get(0));
			if (isCompleteness())
				in = new FileInputStream(file.get(1));

			if (getFile().size() > 0) {
				BufferedReader brc = new BufferedReader(new InputStreamReader(
						inc));
				contextualRules = Rule.parseRules(Rule
						.rulesParserFromReader(brc));
			}
			if (isCompleteness()) {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));
				useRules = Rule.parseRules(Rule.rulesParserFromReader(br));
				// System.out.println(useRules);
			} else
				setDepth(0);

			// System.out.println(contextualRules);

			// ----------------------------------------------------------------

			setModel((Model) session.get("model"));
			setUri((String) session.get("URI"));
			setEndpoint((String) session.get("ENDPOINT"));
			dq.setDqmodel(getModel());

			// SETTING PLAN - DQAPLAN
			DQAssessmentPlan dqplan = new DQAssessmentPlan();
			LinkedList<DQAssessment> dqplanlist = new LinkedList<DQAssessment>();
			dqplan.setAssessmentList(dqplanlist);

			// SETTING LIST OF DIMENSIONS I'm GOING TO ASSESS
			LinkedList<DQDimension> dqdimlist = new LinkedList<DQDimension>();

			// Check-box check
			if (isAccessibility())
				dqdimlist.add((DQDimension) new _dimAccessibility());
			if (isCompleteness())
				dqdimlist.add((DQDimension) new _dimCompleteness());

			dqplan.addDQAssessment(new DQAssessment(dqdimlist, getUri(),
					getEndpoint(), contextualRules, useRules, getDepth(),
					getIdentifier()));

			dqplan.executePlan();

			setMr(dqplan.getmRes());

			// Putting model in session for download file
			session.put("resultModel", dqplan.getFinalModel());

			// Storing in TDB

			tdb(System.currentTimeMillis() + "");
		} catch (ParserException e) {
			setE(e); 
			setErrorMsg("Parser Error Exception. Please check input parameters and try again.");
			e.printStackTrace();
			return ERROR; 
		} catch (FileNotFoundException e) {
			setE(e); 
			setErrorMsg("File Error Exception. Please check input parameters and try again.");
			e.printStackTrace();
			return ERROR; 
		} catch (Exception e) {
			setE(e); 
			setErrorMsg("Parameters Exception. Please check input parameters and try again.");
			e.printStackTrace();
			return ERROR; 
		}

		return ret;
	}

	public void tdb(String currentTime) throws Exception {

		String directory = TDBNames.DIR;
		Dataset dataset = TDBFactory.createDataset(directory);
		Map<String, Object> session = ActionContext.getContext().getSession();
		uriAssessment = TDBNames.URIBASE + currentTime;
		Model resultModel = (Model) session.get("resultModel");

		// Using transactions
		dataset.begin(ReadWrite.WRITE);
		try {
			Model tdbmodel = dataset.getDefaultModel();
			Model m = dataset.getNamedModel(uriAssessment);
			m.add(resultModel);

			tdbmodel.add(m);
			dataset.commit();
		} finally {
			dataset.end();
		}

		System.out.println(uriAssessment);

	}

	public void validate() {
		boolean flag = false;

		if (isCompleteness() && getDepth() < 1) {
			addFieldError("depth", getText("depth.required"));
			flag = true;
		}
		if (!isCompleteness() && !isAccessibility()) {
			addFieldError("completeness", getText("dqdimension.required"));
			flag = true;
		}
		if (getIdentifier().length() == 0) {
			addFieldError("completeness", getText("identifier.required"));
			flag = true;
		}

		if (getFile() == null) {
			addFieldError("file", getText("file.required"));
			flag = true;
		} else if (getFile().size() == 1 && !isAccessibility()) {
			addFieldError("file", getText("file.required"));
			flag = true;
		}

		if (flag == true)
			addActionError("Input Error");
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

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
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

	public Exception getE() {
		return e;
	}

	public void setE(Exception e) {
		this.e = e;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
