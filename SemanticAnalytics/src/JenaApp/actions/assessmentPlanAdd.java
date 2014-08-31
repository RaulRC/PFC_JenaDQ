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
import JenaDQ.APISemDQ;
import JenaDQ.DQAssessment;
import JenaDQ.DQAssessmentPlan;
import JenaDQ.DQDimension;
import JenaDQ.MeasurementResult;
import JenaDQ._dimAccessibility;
import JenaDQ._dimCompleteness;
import JenaDQExceptions.IdentifierException;
import JenaDQExceptions.ModelGenerationException;
import JenaDQExceptions.RuleException;
import JenaDQExceptions.URIException;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.reasoner.rulesys.Rule;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class assessmentPlanAdd extends ActionSupport {

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
	private Exception e;
	private String errorMsg; 

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

	/**
	 * Add new assessment
	 * 
	 * @return
	 */
	public String execute() throws RuleException, URIException, ModelGenerationException, IdentifierException {
		String ret = SUCCESS;
		try {
			Map<String, Object> session = ActionContext.getContext()
					.getSession();

			// --------------------------------------------------------
			InputStream in = null;
			InputStream inc = null;
			List<Rule> useRules = null;
			List<Rule> contextualRules = null;

			inc = new FileInputStream(file.get(0));
			if (isCompleteness())
				in = new FileInputStream(file.get(1));

			// We need use rules just for completeness dim
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

			// ----------------------------------------------------------------

			// SETTING PLAN - DQAPLAN
			DQAssessmentPlan dqplan = (DQAssessmentPlan) session
					.get("assessmentPlan");
			// SETTING LIST OF DIMENSIONS I'm GOING TO ASSESS
			LinkedList<DQDimension> dqdimlist = new LinkedList<DQDimension>();

			// Check-box check
			if (isAccessibility())
				dqdimlist.add((DQDimension) new _dimAccessibility());
			if (isCompleteness())
				dqdimlist.add((DQDimension) new _dimCompleteness());

			
			DQAssessment dqas = APISemDQ.createDQAssessment(dqdimlist, getUri(),
					getEndpoint(), contextualRules, useRules, getDepth(),
					getIdentifier()); 

			dqplan = APISemDQ.addDQAssessmentToPlan(dqplan, dqas);
			

		} catch (Exception e) {
			setE(e);
			e.printStackTrace();
			setErrorMsg("Check input parameters"); 
			ret = ERROR;
		}
		return ret;
	}

	public void validate() {
		boolean flag = false;
		if (getEndpoint().length() == 0) {
			addFieldError("endpoint", getText("endpoint.required"));
			flag = true;
		}
		if (getUri().length() == 0) {
			addFieldError("uri", getText("uri.required"));
			flag = true;
		}
		if (isCompleteness() && getDepth() < 1) {
			addFieldError("depth", getText("depth.required"));
			flag = true;
		}
		if (!isCompleteness() && !isAccessibility()) {
			addFieldError("completeness", getText("dqdimension.required"));
			flag = true;
		}
		if (getIdentifier().length() == 0) {
			addFieldError("identifier", getText("identifier.required"));
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
