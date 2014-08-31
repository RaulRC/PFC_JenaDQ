package JenaDQExceptions;

/**
 * 
 * Model Exception
 * 
 * @author Raúl Reguillo Carmona
 * 
 */
public class ModelGenerationException extends AssessmentException {

	private static final long serialVersionUID = 1L;

	/**
	 * Exception while generating final Model
	 */
	public ModelGenerationException() {
		super("Exception while generating final Model");
	}
}
