package JenaDQExceptions;

/**
 * Excepci�n gen�rica para labores de evaluaci�n
 * 
 * @author Ra�l Reguillo Carmona
 * 
 */
public class AssessmentException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor vac�o
	 */
	public AssessmentException() {

	}

	/**
	 * Establece el mensaje que se pasar� como excepci�n
	 * 
	 * @param message
	 *            mensaje que se pasar� como excepci�n
	 */
	public AssessmentException(String message) {
		super(message);
	}
}
