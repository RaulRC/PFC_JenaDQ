package JenaApp.actions;

import java.util.ArrayList;
import java.util.Map;

import JenaDQ.DQAssessmentPlan;
import JenaDQ.MeasurementResult;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class assessmentPlanExecute extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<MeasurementResult> mr;
	private String uriAssessment;

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
			dqplan.executePlan();
			setMr(dqplan.getmRes());
			session.put("resultModel", dqplan.getFinalModel());
			try {
				tdb(System.currentTimeMillis() + "");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			setMr(new ArrayList<MeasurementResult>());
		}

		// STORE MODEL TDB
		// String directory = "D:\\WebAppDatabases\\DatasetResult";
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

		String directory = "D:\\WebAppDatabases\\DatasetResult";
		Dataset dataset = TDBFactory.createDataset(directory);
		Map<String, Object> session = ActionContext.getContext().getSession();
		uriAssessment = "http://localhost:3030/db/AssessmentPlan_"
				+ currentTime;
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

	public ArrayList<MeasurementResult> getMr() {
		return mr;
	}

	public void setMr(ArrayList<MeasurementResult> mr) {
		this.mr = mr;
	}
}
