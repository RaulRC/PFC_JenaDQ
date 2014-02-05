package R.actions;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import R.Dominion.Rinvoker;
import java.io.InputStream;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
 
import org.apache.struts2.convention.annotation.Result;



public class Calculations extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String data;
	private double result; 
	
    private InputStream fileInputStream;

	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String execute() {
		String ret=SUCCESS;

		try{
			Rinvoker r = new Rinvoker(); 
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
	public String graph(){
		String ret=SUCCESS;

		try{

			Rinvoker r = new Rinvoker(); 
			System.out.println("DATA: " + data);
			File img = r.getPlot(data);
			fileInputStream = new FileInputStream(img);
			r.RinvokerClose();

		}
		catch(Exception e ){
			e.printStackTrace();
			ret=ERROR;
		}
		return ret; 
	}
     
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
}
