public class Odd3nEven2 {

	public static void main(String[] args) {
		showOddnEven(10);
		showOddnEven(3);
		System.out.println(getOddnEven(3));
	}
	public static boolean isOdd(int n){
		boolean isO=false;
		if(n%2==1){
			isO=true;
		}
		return isO;
	}
	public static void showOddnEven(int n){
		int temp=n;
		do{
			if(isOdd(temp)){
				temp=temp*3+1;
			}else{
				temp/=2;
			}
			System.out.print("["+temp+"] ");
		}while(temp!=1);
		System.out.println("\n---------------------");
	}
	public static String getOddnEven(int n){
		int temp=n;
		String oddStr="";
		do{
			if(isOdd(temp)){
				temp=temp*3+1;
			}else{
				temp/=2;
			}
			oddStr+=("["+temp+"] ");
		}while(temp!=1);
		return oddStr;
	}
	
}
