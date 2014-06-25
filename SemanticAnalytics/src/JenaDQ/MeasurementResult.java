package JenaDQ;

public class MeasurementResult {

	public MeasurementResult(String mName, String mDimension) {
		super();
		this.mName = mName;
		this.mDimension = mDimension;
		this.mContextualResult = null; 
		
	}

	public MeasurementResult(String mName, String mDimension, double mResult) {
		super();
		this.mName = mName;
		this.mDimension = mDimension;
		this.mResult = mResult;
	}
	
	public MeasurementResult(String mName, String mDimension, double mResult, String cResult) {
		super();
		this.mName = mName;
		this.mDimension = mDimension;
		this.mResult = mResult;
		this.mContextualResult=cResult; 
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