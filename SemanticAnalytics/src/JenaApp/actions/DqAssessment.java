package JenaApp.actions;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Dominion.Operation;
import JenaDQ.*; 
import DQModel.*; 

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Model;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DqAssessment extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private LinkedList<MeasurementResult> mr; 
	private Model model; 
	
	public String execute() {
		Map<String, Object> session = ActionContext.getContext().getSession(); 
		String ret=SUCCESS;
		DQModel dq = new DQModel(); 
		try{
			setModel((Model)session.get("model")); 
			dq.setDqmodel(getModel());
			_dimCompleteness comp= new _dimCompleteness(dq); 
			mr = new LinkedList<MeasurementResult>();
			mr.add(comp.m_interlinkingCompleteness());
			mr.add(comp.m_schemaCompleteness());
			setMr(mr);
		}
		catch(Exception e){
			//TODO set correct exception
			ret=ERROR;
		}
		return ret; 
	}

	public LinkedList<MeasurementResult> getMr() {
		return mr;
	}

	public void setMr(LinkedList<MeasurementResult> mr) {
		this.mr = mr;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

}
