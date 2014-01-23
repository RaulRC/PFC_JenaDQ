package R.actions;
import java.util.Map;

import Dominion.*;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.util.FileManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Calculations extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private int data;
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public String execute() {
		String ret=SUCCESS;
		try{
			
		}
		catch(Exception e){
			ret=ERROR;
		}
		return ret; 
	}
}
