public class Divisor{
	//약수인가?
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
    //모든 약수 출력 
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
