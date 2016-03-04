public class PerpectionNumber{

	public static void main(String[] args) {
		int iters=1000;
		//방법1
		for(int i=2;i<iters;i++){
			if(Divisor.divisorSum(i)==i){
				System.out.println("완전수 "+i);
				Divisor.printDivisor(i);
			}
		}
		//방법2
		for(int i=2;i<iters;i++){
			printPerfection(i);
		}
		//방법3
		for(int i=2;i<iters;i++){
			if(divisorSum2(i)==(i*2)){
				System.out.println("완전수 "+i);
				Divisor.printDivisor(i);
			}
		}
	}
	public static void printPerfection(int n){
		if(Divisor.divisorSum(n)==n){
			System.out.println("완전수 "+n);
			Divisor.printDivisor(n);
		}
	}
	// 약수의 합을 구하는 메서드 자기 자신 포함
    public static int divisorSum2(int a){
    	int sum=1;
		for(int j=2;j<=a;j++){
			if(Divisor.isMod(a,j)){//Divisor 클래스
				sum+=j;
			}
		}
		return sum;
    }
}
