package JenaDQExceptions;

/**
 * 
 * Excepción de evaluación: error al generar modelo
 * 
 * @author Raúl Reguillo Carmona
 * 
 */
public class ModelGenerationException extends AssessmentException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construye la excepción, asignando su mensaje
	 */
	public ModelGenerationException() {
		super("Exception while generating final Model");
	}
}
