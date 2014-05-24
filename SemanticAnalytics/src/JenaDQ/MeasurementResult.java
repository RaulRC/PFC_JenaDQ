package JenaDQ;

public class MeasurementResult {

	public MeasurementResult(String mName, String mDimension) {
		super();
		this.mName = mName;
		this.mDimension = mDimension;
		this.mContextualResult = null; 
	}

	private String mName;
	private String mDimension;
	private double mResult;
	private Object mContextualResult; 

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

	public Object getmContextualResult() {
		return mContextualResult;
	}

	public void setmContextualResult(Object mContextualResult) {
		this.mContextualResult = mContextualResult;
	}

	@Override
	public String toString() {
		return "MeasurementResult [mName=" + mName + ", mDimension="
				+ mDimension + ", mResult=" + mResult + ", mContextualResult="
				+ mContextualResult + "]";
	}

}