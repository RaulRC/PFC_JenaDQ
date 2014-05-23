package JenaDQ;

public class MeasurementResult {

	public MeasurementResult(String mName, String mDimension, Object mResult) {
		super();
		this.mName = mName;
		this.mDimension = mDimension;
		this.mResult = mResult;
	}

	private String mName;
	private String mDimension;
	private Object mResult;

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

	public void setMResult(Object mResult) {
		this.mResult = mResult;
	}

}