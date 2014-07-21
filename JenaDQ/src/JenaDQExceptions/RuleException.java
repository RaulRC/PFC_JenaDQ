package JenaDQExceptions;

/**
 * Excepci�n de evaluaci�n: error derivado de las reglas
 * 
 * @author Ra�l Reguillo Carmona
 * 
 */
public class RuleException extends AssessmentException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construye la excepci�n, asignando su mensaje
	 */
	public RuleException() {
		super("Missing Rules");
	}

}
