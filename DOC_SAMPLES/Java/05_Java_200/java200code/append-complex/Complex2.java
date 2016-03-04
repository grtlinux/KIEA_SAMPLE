
public class Complex2 {
	private double real;
	private double imag;
	
	public Complex2(double real, double imag){
		this.real=real;
		this.imag=imag;
	}
	public Complex2(){
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
	public boolean equals(Object obj){
		boolean isE=false;
		Complex2 temp=(Complex2)obj;
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
