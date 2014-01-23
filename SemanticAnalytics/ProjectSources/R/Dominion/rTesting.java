package R.Dominion;

public class rTesting {

	private Rinvoker r; 
	public rTesting(){
		r = new Rinvoker(); 
	}
	public int op(int data){
		System.out.println(r.simpleOperation(data));
		return r.simpleOperation(data);
	}
}
