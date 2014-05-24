package JenaApp.actions;

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
	private MeasurementResult mr; 
	private Model model; 
	
	public String execute() {
		Map<String, Object> session = ActionContext.getContext().getSession(); 
		String ret=SUCCESS;
		DQModel dq = new DQModel(); 
		try{
			setModel((Model)session.get("model")); 
			dq.setDqmodel(getModel());
			_dimCompleteness comp= new _dimCompleteness(dq); 
			setMr(comp.m_interlinkingCompleteness());
		}
		catch(Exception e){
			//TODO set correct exception
			ret=ERROR;
		}
		return ret; 
	}

	public MeasurementResult getMr() {
		return mr;
	}

	public void setMr(MeasurementResult mr) {
		this.mr = mr;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

}
