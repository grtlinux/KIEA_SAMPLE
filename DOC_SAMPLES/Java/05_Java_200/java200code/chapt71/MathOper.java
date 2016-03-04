 public class MathOper {
 	//y=log10(a)=log(a)/log(10)      
 	public static double log10(double a){
 		double val=1;
 		if(a!=0 && a!=1 && a>0){
 			val=Math.log(a)/Math.log(10);
 		}
 		 return val;
 	} 
 	public static double sinh(double a){
 		 return (Math.exp(a)-Math.exp(-a))/2.0;
 	}  
 	public static double cosh(double a){
 		 return (Math.exp(a)+Math.exp(-a))/2.0;
 	}   
 	public static double tanh(double a){
 		 return sinh(a)/cosh(a);
 	}
 	public static double banOlimAtNums(double d, int nums){
 		return ((int)((d*Math.pow(10,nums)+5)/10.0))/Math.pow(10,nums-1);
 	}
	public static double burimAtNums(double d, int nums){
 		return ((int)((d*Math.pow(10,nums))/10.0))/Math.pow(10,nums-1);
 	}
 	public static double olimAtNums(double d, int nums){
 		return ((int)((d*Math.pow(10,nums)+10)/10.0))/Math.pow(10,nums-1);
 	}
 	public static double abs(double n){
 		if(n>=0){return n;}
 		else{return -n;}
 	}
}

