package utilities;

import java.net.MalformedURLException;
import java.net.URL;

public class UriUtil {

	public static boolean isUri(String s){ // Necesito usar expresiones regulares
		// Si es URL
		// Si es del tipo tag:nombre
		System.out.println("string >>> " + s);
		boolean result = true; 
		try {
            URL url = new URL(s);
        } catch (MalformedURLException e) {
            // If there was an URL that was not it!...
        	if(!s.matches("[a-zA-Z]:."))
        		result = false; 
        }
		return result; 
	}
}
