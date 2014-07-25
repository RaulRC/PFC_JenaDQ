package JenaApp.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class comparisonDownload extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private InputStream fileInputStream;
	private Exception e; 

	public InputStream getFileInputStream() {
		return fileInputStream;
	}



	public String rdf() throws Exception {
		String fileName = createFile("rdf");
		fileInputStream = new FileInputStream(new File(fileName));
		return SUCCESS;
	}

	public String n3() throws Exception {
		String fileName = createFile("n3");
		fileInputStream = new FileInputStream(new File(fileName));
		return SUCCESS;
	}

	public String createFile(String format) throws IOException {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Model m = (Model) session.get("CresultModel");

		String formatFile = "";
		if (format.equals("rdf"))
			formatFile = "RDF/XML-ABBREV";
		else if (format.equals("n3"))
			formatFile = "N3";

		String currentTime = System.currentTimeMillis() +"";
		String fileName = "D:\\temp_files_assessment\\"
				+ currentTime + "_assessmentResults." + format;

		FileWriter out =null;
		try {
			out = new FileWriter(fileName);
			if(m!=null)
				m.write(out, formatFile);
			else{
				m = ModelFactory.createDefaultModel();
				m.write(out, formatFile);
			}
		} catch (IOException closeException) {
			// ignore
		}
		finally{
			out.close();
		}

		return fileName;
	}



	public Exception getE() {
		return e;
	}



	public void setE(Exception e) {
		this.e = e;
	}
}
