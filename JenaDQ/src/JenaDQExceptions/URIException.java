package JenaDQExceptions;

/**
 * Excepci�n de evaluaci�n: error derivado de la URI
 * 
 * @author Ra�l Reguillo Carmona
 * 
 */
public class URIException extends AssessmentException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construye la excepci�n, asignando su mensaje
	 */
	public URIException() {
		super("Not valid URI");
	}
}
