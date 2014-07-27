package JenaApp.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import utilidades.DataPicker;

import DQModel.DQModel;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class modelComparison extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<File> file;
	private Model resultModel;
	private StmtIterator resultList;
	private StmtIterator modelA;
	private StmtIterator modelB;
	private String affinity;
	private Exception e;
	private String errorMsg; 
	
	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public String execute() {
		try {
			Map<String, Object> session = ActionContext.getContext().getSession();
			
			InputStream inA = new FileInputStream(this.file.get(0));
			InputStream inB = new FileInputStream(this.file.get(1));

			DQModel modelA = new DQModel(inA, "RDF/XML");
			DQModel modelB = new DQModel(inB, "RDF/XML");
			
			String aff = ""; 

			// Calculates the affinity percentage
			setAffinity("");

			if (modelA.getModel().size() >= modelB.getModel().size()) {
				setResultModel(modelA.compareModelWith(modelB));
				aff = decimalFormat(modelB.affinity(modelA));
			} else {
				setResultModel(modelB.compareModelWith(modelA));
				aff = decimalFormat(modelB.affinity(modelA));
			}
			setAffinity(aff);
			session.put("CresultModel", this.getResultModel());
			setResultList(this.resultModel.listStatements());
			setModelA(modelA.getModel().listStatements());
			setModelB(modelB.getModel().listStatements());

		} catch (Exception e) {
			setE(e); 
			setErrorMsg("File Exception. Please check both files and try again"); 
			e.printStackTrace();
			return ERROR; 
		}

		return SUCCESS;
	}

	public void validate() {
		boolean flag = false;

		if (getFile() == null) {
			addFieldError("file", getText("file.required"));
			flag = true;
		} else if (getFile().size() != 2) {
			addFieldError("file", getText("file.required"));
			flag = true;
		}

		if (flag == true)
			addActionError("Input Error");
	}

	public String decimalFormat(double number){
		DecimalFormat df = new DecimalFormat("0.00##");
		return df.format(number); 

	}
	public Model getResultModel() {
		return resultModel;
	}

	public void setResultModel(Model resultModel) {
		this.resultModel = resultModel;
	}

	public StmtIterator getResultList() {
		return resultList;
	}

	public void setResultList(StmtIterator resultList) {
		this.resultList = resultList;
	}

	public StmtIterator getModelA() {
		return modelA;
	}

	public void setModelA(StmtIterator modelA) {
		this.modelA = modelA;
	}

	public StmtIterator getModelB() {
		return modelB;
	}

	public void setModelB(StmtIterator modelB) {
		this.modelB = modelB;
	}

	public String getAffinity() {
		return affinity;
	}

	public void setAffinity(String aff) {
		this.affinity = aff;
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
