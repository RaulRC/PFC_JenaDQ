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

@Result(
	    name = "success", 
	    type = "image/jpeg", 
	    params = { 
	        "contentType", "${type}", 
	        "inputName", "stream", 
	        "bufferSize", "1024", 
	        "contentDisposition", "attachment;filename=\"${filename}\""
	    }
	)

public class Calculations extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String data;
	private double result; 
	private Rinvoker r; 
	
    private String type = "image/jpeg";
    private String filename;
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
	public String graph(){
		String ret=SUCCESS;

		try{
			filename = "temp.jpg";
			File img = new File("D:\\temp.jpg");
			fileInputStream = new FileInputStream(img);
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
