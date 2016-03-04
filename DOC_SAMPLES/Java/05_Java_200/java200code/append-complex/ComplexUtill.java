
public class ComplexUtill {

	public static Complex summs(Complex z1,Complex z2){
		Complex z3=null;
		z3=new Complex(z1.getReal()+z2.getReal(),
		               z1.getImag()+z2.getImag());
		return z3;
	}
	public static Complex minus(Complex z1,Complex z2){
		Complex z3=null;
		z3=new Complex(z1.getReal()-z2.getReal(),
					   z1.getImag()-z2.getImag());
		return z3;
	}
	public static Complex multi(Complex z1,Complex z2){
		Complex z3=null;
		z3=new Complex(z1.getReal()*z2.getReal()-z1.getImag()*z2.getImag(),
					   z1.getReal()*z2.getImag()+z1.getImag()*z2.getReal());
		return z3;
	}
	public static double absolute(Complex z1){
		double z3=0.0;
		z3=Math.sqrt(z1.getReal()*z1.getReal()+z1.getImag()*z1.getImag());
		return z3;
	}
	public static double size(Complex z1){
		double z3=0.0;
		z3=z1.getReal()*z1.getReal()+z1.getImag()*z1.getImag();
		return z3;
	}
	public static Complex bar(Complex z1){
		Complex z3=null;
		z3=new Complex(z1.getReal(),-z1.getImag());
		return z3;
	}
	public static Complex divide(Complex z1,Complex z2){
		Complex z3=null;
		z3=new Complex((z1.getReal()*z2.getReal()+z1.getImag()*z2.getImag())/size(z2),
					   (-z1.getReal()*z2.getImag()+z1.getImag()*z2.getReal())/size(z2));
		return z3;
	}
	
}
