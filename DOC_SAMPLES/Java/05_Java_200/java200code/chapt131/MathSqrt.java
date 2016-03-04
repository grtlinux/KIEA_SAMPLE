public class MathSqrt {
	
	private double a;
	private double xMin;
	public MathSqrt(double a,double xInit){
		xMin=xInit;
		this.a=a;
	}
	public MathSqrt(){
	}
	private double mean(){
		return xMin=(a/xMin+xMin)/2.0;
	}
	public double fuctValue(){
		double temp=xMin;//

		if(a<=0){
			return temp=0;  //예외처리를 하지 않고 0으로 리턴 
		}
		if(xMin==0){  //0이면 시작점을 조금 옮겨서 시작
			xMin+=MathEnd.MIDDLE;
		}
		if(xMin<0){       //음수를 넣으면 양수로 만들어서 시작
			xMin=Math.abs(xMin);
		}

		while(Math.abs(a/xMin-xMin)>MathEnd.END){
			temp=mean();  //산술평균을 구해서 기하평균을 근접하도록
		}//while
		
		return temp;
	}//
	public double getA() {//제곱근을 구하려고 하는 값
		return a;
	}
	public double getXMin() {//추측값
		return xMin;
	}
	public void setA(double d) {//제곱근을 구하려고 하는 값
		a = d;
	}
	public void setXMin(double d) {//추측값
		xMin = d;
	}
}
