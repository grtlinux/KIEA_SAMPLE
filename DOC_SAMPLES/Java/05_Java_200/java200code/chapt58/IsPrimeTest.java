public class IsPrimeTest {

	public static void main(String[] args) {
		
		int toInt=10000873;
		boolean isP=true;
		for(int j=2;j<toInt;j++){
			if(toInt%j==0){
				isP=false;
				break;
			}else{
				isP=true;
			}
		}
		if(isP){
			System.out.println(toInt+"는 소수");
		}
		else{
			System.out.println(toInt+"는 소수가 아님");
		}
		
		printPrime(toInt);
	}
	public static boolean isPrime(int num){
		boolean isP=true;
		for(int j=2;j<num;j++){
			if(num%j==0){
				isP=false;
				break;
			}else{
				isP=true;
			}
		}
		return isP;
	}
	public static void printPrime(int num){
		if(isPrime(num)){
			System.out.println(num+"는 소수");
		}
		else{
			System.out.println(num+"는 소수가 아님");
		}
	}
}
