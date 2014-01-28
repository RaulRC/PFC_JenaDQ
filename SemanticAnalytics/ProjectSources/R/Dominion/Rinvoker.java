package R.Dominion;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class Rinvoker {
	private RConnection c;
	// TODO This could need a singleton
	public Rinvoker(){
		try {
			c = new RConnection();
		} catch (RserveException e) {
			e.printStackTrace();
		}
	}
	
	public void RinvokerClose(){
		c.close();
	}
	// TODO --- Operations ----------------------------
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
	//TODO give more options of return type (png, bmp, jpg) etc
	/*
	 * Options: 
	 *  - Format
	 *  - barplot, point plot, etc.
	 */
	public Image getPlot(String data){
		Image img = null;
		BufferedImage bi = null; 
		REXP x; 
		try {
			x= c.parseAndEval("try(jpeg('test.jpg',quality=90))");
			c.parseAndEval(data);
			x = c.parseAndEval("r=readBin('test.jpg','raw',1024*1024); unlink('test.jpg'); r");
			img = Toolkit.getDefaultToolkit().createImage(x.asBytes());
			
			bi = toBufferedImage(img); 
			File outputfile = new File("./tempFiles/temp.jpg");
			ImageIO.write(bi, "jpg", outputfile);

		} catch (REngineException | REXPMismatchException | IOException e) {
			e.printStackTrace();
		} 
		return img; 
	}
	private static BufferedImage toBufferedImage(Image src) {
		// TODO calculates 
        int w = src.getWidth(null);
        int h = src.getHeight(null);
        int type = BufferedImage.TYPE_INT_RGB;  // other options
        if (w <= 0 || h <= 0){
        	w = 450; 
        	h = 450; 
        }
        BufferedImage dest = new BufferedImage(w, h, type);
        Graphics2D g2 = dest.createGraphics();
        g2.drawImage(src, 0, 0, null);
        g2.dispose();
        return dest;
    }
}
