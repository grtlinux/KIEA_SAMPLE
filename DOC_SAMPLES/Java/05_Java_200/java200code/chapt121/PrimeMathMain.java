public class PrimeMathMain {

	public static void main(String[] args) {
		
		PrimeMath pm=new PrimeMath();
		
		pm.printPrime(pm.commonDividors(100));
		System.out.println("----------------------------------");
		System.out.println(" 7880599가 솟수인가? "+pm.isPri(7880599));

		for(int i=1;i<=100;i++){
			System.out.print(i+" : ");pm.printPrime(pm.commonDividors(i));
		}//
		
		System.out.println("\n약수개수 구하고 소수구하기");
		for(int i=1;i<=100;i++){
			if(pm.divisors(i)==2){
				System.out.println(i+":는 약수의 개수가 2개: 소수 ");
			}
			
		}//
	}
}
