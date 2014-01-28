package R.actions;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import R.Dominion.Rinvoker;

import com.opensymphony.xwork2.ActionSupport;


public class Calculations extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String data;
	private double result; 
	private Rinvoker r; 
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String execute() {
		String ret=SUCCESS;
		try{
			r = new Rinvoker(); 
			setResult(r.simpleOperation(data));
			r.RinvokerClose();
		}
		catch(Exception e ){
			ret=ERROR;
		}
		return ret; 
	}
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
//	public void graph(){
//		
//	}
}
