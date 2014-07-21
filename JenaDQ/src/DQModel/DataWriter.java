package DQModel;

/**
 * clase auxiliar que da soporte a la clase <code>DQModel</code> permitiendo
 * mostrar por pantalla los modelos cargados
 * 
 * @author Raúl Reguillo Carmona
 * @link JenaDQ.src.DQModel.DQModel
 * 
 */
public class DataWriter {

	/**
	 * Constructor vacío
	 */
	public DataWriter() {

	}

	/**
	 * Muestra el modelo por salida estándar en el formato que éste posea
	 * 
	 * @param dqmodel
	 *            <code>DQModel</code> que deberá tener un modelo de Jena y su
	 *            extensión asociados
	 */
	public void showModel(DQModel dqmodel) {
		dqmodel.getModel().write(System.out, dqmodel.getFormat());
	}

	/**
	 * Muestra el modelo por salida estándar en el formato que se le indique
	 * 
	 * @param dqmodel
	 *            <code>DQModel</code> que deberá tener un modelo de Jena
	 *            asociado
	 * @param format
	 *            formato de salida esperado
	 */
	public void showModelWithFormat(DQModel dqmodel, String format) {
		dqmodel.getModel().write(System.out, format);
	}
}