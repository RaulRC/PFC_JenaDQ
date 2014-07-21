package JenaDQExceptions;

/**
 * Excepción genérica para labores de evaluación
 * 
 * @author Raúl Reguillo Carmona
 * 
 */
public class AssessmentException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor vacío
	 */
	public AssessmentException() {

	}

	/**
	 * Establece el mensaje que se pasará como excepción
	 * 
	 * @param message
	 *            mensaje que se pasará como excepción
	 */
	public AssessmentException(String message) {
		super(message);
	}
}
