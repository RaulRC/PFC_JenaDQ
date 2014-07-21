package JenaDQ;

/**
 * Almacena resultados de evaluación para que sean más accesibles, como una
 * alternativa a realizar consultas sobre el modelo resultante
 * 
 * @author Raúl Reguillo Carmona
 * 
 */
public class MeasurementResult {

	/**
	 * 
	 * @param mName
	 *            nombre de la métrica
	 * @param mDimension
	 *            nombre de la dimensión de calidad
	 */
	public MeasurementResult(String mName, String mDimension) {
		super();
		this.mName = mName;
		this.mDimension = mDimension;
		this.mContextualResult = null;

	}

	/**
	 * 
	 * @param mName
	 *            nombre de la métrica
	 * @param mDimension
	 *            nombre de la dimensión de calidad
	 * @param mResult
	 *            resultado numérico de la evaluación
	 */
	public MeasurementResult(String mName, String mDimension, double mResult) {
		super();
		this.mName = mName;
		this.mDimension = mDimension;
		this.mResult = mResult;
	}

	/**
	 * 
	 * @param mName
	 *            nombre de la métrica
	 * @param mDimension
	 *            nombre de la dimensión de calidad
	 * @param mResult
	 *            resultado numérico de la evaluación
	 * @param cResult
	 *            resultado contextual de la evaluación
	 */
	public MeasurementResult(String mName, String mDimension, double mResult,
			String cResult) {
		super();
		this.mName = mName;
		this.mDimension = mDimension;
		this.mResult = mResult;
		this.mContextualResult = cResult;
	}

	/**
	 * Constructor vacío
	 */
	public MeasurementResult() {

	}

	private String mName;
	private String mDimension;
	private double mResult;
	private String mContextualResult;

	public String getMName() {
		return this.mName;
	}

	public void setMName(String mName) {
		this.mName = mName;
	}

	public String getMDimension() {
		return this.mDimension;
	}

	public void setMDimension(String mDimension) {
		this.mDimension = mDimension;
	}

	public Object getMResult() {
		return this.mResult;
	}

	public void setMResult(double mResult) {
		this.mResult = mResult;
	}

	public String getmContextualResult() {
		return mContextualResult;
	}

	public void setmContextualResult(String mContextualResult) {
		this.mContextualResult = mContextualResult;
	}

	@Override
	public String toString() {
		return "MeasurementResult [mName=" + mName + ", mDimension="
				+ mDimension + ", mResult=" + mResult + ", mContextualResult="
				+ mContextualResult + "]";
	}

}