package JenaApp.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import utilidades.DataPicker;

import DQModel.DQModel;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.StmtIterator;
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

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public String execute() {
		try {
			InputStream inA = new FileInputStream(this.file.get(0));
			InputStream inB = new FileInputStream(this.file.get(1));

			DQModel modelA = new DQModel(inA, "RDF/XML");
			DQModel modelB = new DQModel(inB, "RDF/XML");

			if(modelA.getModel().listStatements().toList().size() >= modelB.getModel().listStatements().toList().size())
				setResultModel(modelA.compareModelWith(modelB));
			else
				setResultModel(modelB.compareModelWith(modelA)); 
			
			setResultList(this.resultModel.listStatements());
			setModelA(modelA.getModel().listStatements());
			setModelB(modelB.getModel().listStatements());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
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

}
