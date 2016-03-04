//John Wallis
public class JohnWallisPie {
	//periphery pie
	public static void main(String[] args) {
		//¼ö·ÅÀÌ ¸Å¿ì ´Ê´Â´Ù.
		System.out.println(Math.PI);
		System.out.println(makePie(1000000));
		System.out.println(makePie(10000000));
	}
	//From Section 43
	public static int nmAn(int start, int n, int d){
		return (start+n*d);
	}
	public static double mDivide(double n){
		//System.out.println(n);
		return (n*n)/(n-1)/(n+1);
	}
	public static double makePie(int n){
		double temp=1;
		for(int i=0;i<n;i++){
			temp*=mDivide(nmAn(2,i,2));
		}
		return 2.0*temp;
	}
}
