package JenaDQExceptions;

public class IdentifierException extends AssessmentException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IdentifierException(){
		super("Missing Assessment Identifier"); 
	}

}
