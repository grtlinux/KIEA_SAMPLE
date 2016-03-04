
public class Complex {
	private double real;
	private double imag;

	public Complex(double real, double imag){
		this.real=real;
		this.imag=imag;
	}
	public Complex(){
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
		String temp="";
		if(imag>=0){
			temp= real+" + "+imag+" i";
		}else if(imag<0){
			temp= real+" "+imag+" i";
		}
		return temp;
	}
	public boolean equals(Object obj){
		boolean isE=false;
		Complex temp=(Complex)obj;
		if(temp.getReal()==this.real && temp.getImag()==this.imag){
			isE=true;
		}
		return isE;
	}
	public int hashCode(){
		int temp=0;
		temp=((int)this.real^(int)this.imag);
		return temp;
	}
}
