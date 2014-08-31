package JenaDQExceptions;

/**
 * Missing Identifier Exception
 * 
 * @author Raúl Reguillo Carmona
 * 
 */
public class IdentifierException extends AssessmentException {

	private static final long serialVersionUID = 1L;

	/**
	 * Missing Assessment Identifier Exception
	 */
	public IdentifierException() {
		super("Missing Assessment Identifier");
	}

}
