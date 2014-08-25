package JenaDQExceptions;

/**
 * Rule Exception
 * 
 * @author Raúl Reguillo Carmona
 * 
 */
public class RuleException extends AssessmentException {

	private static final long serialVersionUID = 1L;

	/**
	 * Missing Rules Exception
	 */
	public RuleException() {
		super("Missing Rules");
	}

}
