package JenaApp.actions;

import java.util.ArrayList;
import java.util.Map;

import org.apache.jena.atlas.lib.StrUtils;

import JenaDQ.APISemDQ;
import JenaDQ.DQAssessmentPlan;
import JenaDQ.MeasurementResult;
import NAMES.TDBNames;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.update.GraphStore;
import com.hp.hpl.jena.update.GraphStoreFactory;
import com.hp.hpl.jena.update.UpdateExecutionFactory;
import com.hp.hpl.jena.update.UpdateFactory;
import com.hp.hpl.jena.update.UpdateProcessor;
import com.hp.hpl.jena.update.UpdateRequest;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class assessmentPlanExecute extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<MeasurementResult> mr;
	private String uriAssessment;
	private Exception e;
	private String errorMsg; 

	public String getUriAssessment() {
		return uriAssessment;
	}

	public void setUriAssessment(String uriAssessment) {
		this.uriAssessment = uriAssessment;
	}

	/**
	 * Execute plan
	 * 
	 * @return
	 */
	public String execute() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		DQAssessmentPlan dqplan = (DQAssessmentPlan) session
				.get("assessmentPlan");
		try {
			APISemDQ.executeAssessmentPlan(dqplan);
			setMr(dqplan.getmRes());
			session.put("resultModel", dqplan.getFinalModel());
			try {
				tdb(System.currentTimeMillis() + "");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			setE(e);
			setMr(new ArrayList<MeasurementResult>());
			setErrorMsg("Error during Execution. Please try again"); 
			return ERROR;
		}

		// STORE MODEL TDB
		// String directory = TDBNames.DIR;
		// Dataset dataset = TDBFactory.createDataset(directory);
		// Model tdbmodel = dataset.getDefaultModel();
		// Model m = dataset.getNamedModel("http://localhost:3030/db/" +
		// "SOMEIDENTIFIER");
		// m.add(dqplan.getFinalModel());
		// dataset.begin(ReadWrite.WRITE);
		// tdbmodel.add(m);
		// dataset.end();

		// Putting model in session for download file

		return SUCCESS;
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
		// store();

	}

	public void store() {

		String directory = TDBNames.DIR;
		Dataset dataset = TDBFactory.createDataset(directory);
		try {
			dataset.begin(ReadWrite.WRITE);
			GraphStore graphStore = GraphStoreFactory.create(dataset);
			String sparqlUpdateString = StrUtils.strjoinNL(
					"PREFIX ex: <http://example/>",
					"INSERT { ex:s ex:p ?now } WHERE { BIND(now() AS ?now) }");

			UpdateRequest request = UpdateFactory.create(sparqlUpdateString);
			UpdateProcessor proc = UpdateExecutionFactory.create(request,
					graphStore);
			proc.execute();

			// Finally, commit the transaction.
			dataset.commit();
			// Or call .abort()
		} finally {
			dataset.end();
		}

	}

	public ArrayList<MeasurementResult> getMr() {
		return mr;
	}

	public void setMr(ArrayList<MeasurementResult> mr) {
		this.mr = mr;
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
