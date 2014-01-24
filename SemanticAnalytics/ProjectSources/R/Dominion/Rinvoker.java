package R.Dominion;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class Rinvoker {
	private RConnection c;
	// This could need a singleton
	public Rinvoker(){
		try {
			c = new RConnection();
		} catch (RserveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public double simpleOperation(String data){
		REXP x;
		double result = Math.E; 
		try {
			x = c.eval(data);
			System.out.println(x.asString());
			result = x.asDouble();
		} catch (RserveException | REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public void RinvokerClose(){
		c.close();
	}
}
