//Laiphniz
public class LaiphnizPie {
	//periphery pie
	public static void main(String[] args) {
		//¼ö·ÅÀÌ ¸Å¿ì ´Ê´Â´Ù.
		System.out.println(Math.PI);
		System.out.println(makePie(10000000));
		System.out.println(makePie(100000000));
	}
	//From Section 44
	public static double numAn(int start, int n, int d){
		return (start+n*d);
	}
	public static double posiNega(int start, int n, int d){
		int oper=(n%2)==0?1:-1;//odd--> -1
		return oper*numAn(start,n,d);
	}
	public static double mDivide(double n){
		return 1.0/n;
	}
	public static double makePie(int n){
		double temp=0;
		for(int i=0;i<n;i++){
			temp+=mDivide(posiNega(1,i,2));
		}
		return 4.0*temp;
	}
}
