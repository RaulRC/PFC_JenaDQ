package DQModel;

/**
 * clase auxiliar que da soporte a la clase <code>DQModel</code> permitiendo
 * mostrar por pantalla los modelos cargados
 * 
 * @author Ra�l Reguillo Carmona
 * @link JenaDQ.src.DQModel.DQModel
 * 
 */
public class DataWriter {

	/**
	 * Constructor vac�o
	 */
	public DataWriter() {

	}

	/**
	 * Muestra el modelo por salida est�ndar en el formato que �ste posea
	 * 
	 * @param dqmodel
	 *            <code>DQModel</code> que deber� tener un modelo de Jena y su
	 *            extensi�n asociados
	 */
	public void showModel(DQModel dqmodel) {
		dqmodel.getModel().write(System.out, dqmodel.getFormat());
	}

	/**
	 * Muestra el modelo por salida est�ndar en el formato que se le indique
	 * 
	 * @param dqmodel
	 *            <code>DQModel</code> que deber� tener un modelo de Jena
	 *            asociado
	 * @param format
	 *            formato de salida esperado
	 */
	public void showModelWithFormat(DQModel dqmodel, String format) {
		dqmodel.getModel().write(System.out, format);
	}
}