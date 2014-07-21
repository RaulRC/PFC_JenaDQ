package JenaDQExceptions;

/**
 * Excepción de evaluación: ausencia de Identificador
 * 
 * @author Raúl Reguillo Carmona
 * 
 */
public class IdentifierException extends AssessmentException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construye la excepción, asignando su mensaje
	 */
	public IdentifierException() {
		super("Missing Assessment Identifier");
	}

}
