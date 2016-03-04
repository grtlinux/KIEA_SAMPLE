public class MathRoot {
	
	private double initL;
	private double initR;
	private double l;
	private double r;
	private double [] m;
	
	public MathRoot(double l,double r,double [] m){
		this.l=l;
		this.r=r;
		initL=l;
		initR=r;		
		this.m=m;
	}//
	//양수 1, 0이면 0, 음수면 -1
	public int isPositive(double a){
		int isP=0;
		if(a>0){
			isP=1;
		}else if(a==0){
			isP=0;
		}else{
			isP=-1;
		}
		return isP;
	}//
	private double mean(){
		return (l+r)/2.0;
	}
	public double functMulti(){
		double temp=0.0;
		return temp=functLorR(l)*functLorR(r);
	}//
	public double functLorR(double hl){
		double temp=0.0;
		//System.out.println("functLorR"+hl);
		Function f1=new Function(hl,m);
		return temp=f1.functs();
	}//
	public double fuctValue(){
		//int count=0;  //반복수
		//System.out.println("1-----------------");
		double temp=l;
		if(isPositive(functMulti())==0){
			if(isPositive(functLorR(l))==0){
				return temp=l;
			}else if(isPositive(functLorR(r))==0){
				return temp=r;
			}
		}
		while(Math.abs(l-r)>MathEnd.END){
			 if(functLorR(l)*functLorR(mean())<0){
			   r=mean();
			 }else if(functLorR(r)*functLorR(mean())<0){
			   l=mean();
		     }else {
				if(functLorR(r)==0.0){
					return temp=r;
				 }else if (functLorR(l)==0.0){
					return temp=l;
				 }else if(functLorR(mean())==0.0){
				 	return temp=mean();
				 }
		     }
			temp=l;
		}//while
		return temp;
	}//
}
