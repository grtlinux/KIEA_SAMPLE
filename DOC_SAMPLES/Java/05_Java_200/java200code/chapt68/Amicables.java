public class Amicables {//친화수

	public static void main(String[] args) {
		int iters=500, to=200;
		printDivisor(220);
		printDivisor(284);
		System.out.println(divisorSum(220));
		System.out.println(divisorSum(284));

		for(int i=to;i<iters;i++){
			for(int j=to;j<iters;j++){
				if((i!=j) && (j==divisorSum(i) && i==divisorSum(j))){
					System.out.println("친화수 "+i+" : "+j);
				}
			}
		}
		System.out.println("-------------------------------");
	}
	public static boolean isMod(int a,int b){
	   	boolean isP=false ; // 기본 나누어 떨어지지 않는다.
	   	if(a!=1 && a!=0 && b!=1 && b!=0 && a%b==0){
			isP=true; //나누어 떨어진다.
	   	}
	   	return isP;
    }
    // 약수의 합을 구하는 메서드 자기 자신 제외
    public static int divisorSum(int a){
    	int sum=1;
		for(int j=2;j<a;j++){
			if(isMod(a,j)){
				sum+=j;
			}
		}
		return sum;
    }
    public static void printDivisor(int a){
    	System.out.print("[1, ");
    	for(int j=2;j<a;j++){
			if(isMod(a,j)){
				System.out.print(j+", ");
			}
		}
    	System.out.println(a+"]");
    }
}
