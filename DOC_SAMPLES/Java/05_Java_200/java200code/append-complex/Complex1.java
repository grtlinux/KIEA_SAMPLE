
public class Complex1 {
	private double real;
	private double imag;
	
	public Complex1(double real, double imag){
		this.real=real;
		this.imag=imag;
	}
	public Complex1(){
		this(0,0);
	}
	
	public double getImag() {
		return imag;
	}

	public double getReal() {
		return real;
	}

	public void setImag(double d) {
		imag = d;
	}

	public void setReal(double d) {
		real = d;
	}
	
	public String toString(){
		return real+" + "+imag+" i";
	}
	
	
}
