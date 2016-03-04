public class FunctionMain {

	public static void main(String[] args) {
		//^¸¦ ½Â(½Â¼ö)·Î º¸ÀÚ.
		Function funct=new Function();
		double [] m={1,-1,0,-1};
		//f(x)=1*x^3-1*x^2+0*x^1-1 
		funct.setM(m); 
		funct.setX(2.0); //f(2)
		System.out.println("f(2.0)= "+funct.functs());
		double []m1={1,-1,0,-1};
		//f(x)=1*x^3-1*x^2+0*x^1-1 
		funct.setM(m1);
		funct.setX(2/3.0);  //f(2/3.0)
		System.out.println("f(2/3.0)= "+funct.functs());
	}
}
