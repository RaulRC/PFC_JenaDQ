package JenaDQExceptions;

/**
 * Exception about URI
 * 
 * @author Raúl Reguillo Carmona
 * 
 */
public class URIException extends AssessmentException {

	private static final long serialVersionUID = 1L;

	/**
	 * Not Valid Uri Exception
	 */
	public URIException() {
		super("Not valid URI");
	}
}
