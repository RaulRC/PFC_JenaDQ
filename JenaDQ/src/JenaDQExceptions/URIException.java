package JenaDQExceptions;

/**
 * Excepción de evaluación: error derivado de la URI
 * 
 * @author Raúl Reguillo Carmona
 * 
 */
public class URIException extends AssessmentException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construye la excepción, asignando su mensaje
	 */
	public URIException() {
		super("Not valid URI");
	}
}
