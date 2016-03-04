public class PrimeDecreaseIterate {

	public static void main(String[] args) {
		int toInt=30;
		for(int i=2;i<toInt;i++){
			printPrime(i,isPrime2(i));
		}//for
		for(int i=2;i<toInt;i++){
			printPrime(i,isPrime3(i));
		}//for
		
		System.out.println(isPrime3(10000873));
	}
	//솟수인지 확인하는 메서드
	public static boolean isPrime2(int a){
		boolean isP=true ;
		int b=a/2; //50이면 2부터 25까지의 정수로 나눈다. 
		for(int i=2;i<=b;i++){
			if(a%i==0){        //(50,2) (50,3) (50,4) .....
				isP=false;
				break;   //for탈출  //(50,2) |소수가 아니다. 
			}else isP=true;
		}
		return isP;
	}
	//솟수인지 확인하는 메서드
	public static boolean isPrime3(int a){
		boolean isP=true ;
		int b=(int)Math.sqrt(a);//50이면 2부터 7까지의 정수로 나눈다. 
		for(int i=2;i<=b;i++){
			if(a%i==0){       //(50,2) (50,3) (50,4) .....
				isP=false;
				break;   //for탈출  //(50,2) |소수가 아니다. 
			}else isP=true;
		}
		return isP;
	}
	public static void printPrime(int num, boolean isp){
		if(isp){
			System.out.println(num+"는 소수");
		}
		else{
			System.out.println(num+"는 소수가 아님");
		}
	}
}
