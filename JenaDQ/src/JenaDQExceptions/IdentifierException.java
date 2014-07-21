package JenaDQExceptions;

/**
 * Excepci�n de evaluaci�n: ausencia de Identificador
 * 
 * @author Ra�l Reguillo Carmona
 * 
 */
public class IdentifierException extends AssessmentException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construye la excepci�n, asignando su mensaje
	 */
	public IdentifierException() {
		super("Missing Assessment Identifier");
	}

}
