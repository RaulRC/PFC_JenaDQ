package JenaDQExceptions;

/**
 * 
 * Excepci�n de evaluaci�n: error al generar modelo
 * 
 * @author Ra�l Reguillo Carmona
 * 
 */
public class ModelGenerationException extends AssessmentException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construye la excepci�n, asignando su mensaje
	 */
	public ModelGenerationException() {
		super("Exception while generating final Model");
	}
}
