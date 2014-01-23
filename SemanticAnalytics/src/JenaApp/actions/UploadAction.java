package JenaApp.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Map;

import Dominion.DQModel;
import Dominion.DataPicker;
import Dominion.Operation;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File file;
	private String filename;
	private String format; 
	private Model model; 
	private String uri;
	private LinkedList<Property> modelProperties; 

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public LinkedList<Property> getModelProperties() {
		return modelProperties;
	}

	public void setModelProperties(LinkedList<Property> modelProperties) {
		this.modelProperties = modelProperties;
	}

	public void setUpload(File file) {
		this.file = file;
	}

	public void setUploadFileName(String filename) {
		this.filename = filename;
	}

	public String execute() {
		
		Map<String, Object> session = ActionContext.getContext().getSession();  // Get session
		DataPicker dp = new DataPicker(); 
		try{
			InputStream in = new FileInputStream(this.file);
			DQModel dq;
			dq = new DQModel(in, dp.checkFormat(filename)); 
            // Not neccessary output stream, just read.
			setFormat(dq.getFormat()); 
			setModel(dq.getModel()); 
			dq.showModelWithFormat(getFormat());
			Operation oper = new Operation(dq.getModel());
			setModelProperties(oper.getAllProperties());
			session.put("model", dq.getModel()); 
			setUri("Local file"); 
			in.close();

		} catch (IOException e){
			System.out.println(e.toString());
			return ERROR;
		}
		
		return SUCCESS;
	}
}