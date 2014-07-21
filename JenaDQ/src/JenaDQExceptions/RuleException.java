package JenaDQExceptions;

/**
 * Excepción de evaluación: error derivado de las reglas
 * 
 * @author Raúl Reguillo Carmona
 * 
 */
public class RuleException extends AssessmentException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construye la excepción, asignando su mensaje
	 */
	public RuleException() {
		super("Missing Rules");
	}

}
