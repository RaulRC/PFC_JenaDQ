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
import com.hp.hpl.jena.tdb.TDBFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class download extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private InputStream fileInputStream;

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public String tdb() throws Exception {

		String directory = "D:\\MyDatabases\\DatasetResult"; 
		Dataset dataset = TDBFactory.createDataset(directory) ;
		Map<String, Object> session = ActionContext.getContext().getSession();
		Model resultModel = (Model) session.get("resultModel");

		Model tdbmodel = dataset.getDefaultModel();
		Model m = dataset.getNamedModel("results");

		m.add(resultModel);
		dataset.begin(ReadWrite.WRITE);
		tdbmodel.add(m); 
		Model m3 = dataset.getNamedModel("results");
		m3.write(System.out);
		dataset.end() ;

		return SUCCESS;
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

	public String createFile(String format) {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Model m = (Model) session.get("resultModel");

		String formatFile = "";
		if (format.equals("rdf"))
			formatFile = "RDF/XML-ABBREV";
		else if (format.equals("n3"))
			formatFile = "N3";

		String fileName = "D:\\temp_files_assessment\\"
				+ System.currentTimeMillis() + "_assessmentResults." + format;

		try {
			FileWriter out = new FileWriter(fileName);
			m.write(out, formatFile);
			out.close();
		} catch (IOException closeException) {
			// ignore
		}
		try {
//			tdb();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return fileName;
	}
}