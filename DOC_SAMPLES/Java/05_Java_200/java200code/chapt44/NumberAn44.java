public class NumberAn44 {

	public static void main(String[] args) {
		int toNum=10;
		
		/*try{   //JAVA5에서는 코멘트를 풀고 사용하자.
			toNum=ScannerInput.readInt();
		}catch(Exception e){
			System.out.println("예외: 타입확인 요망"+e);
			//System.exit(1);//프로그램 끝
		}*/
		
		int sum=sumAn(toNum,1,2,true);  
		System.out.println("sum = "+sum);
		int sum2=sumAn(toNum,1,2,false);  
		System.out.println("sum = "+sum2);
	}

	public static int numAn(int start, int n, int d){
		return (start+n*d);
	}
	public static int posiNega(int start, int n, int d){
		int oper=(n%2)==0?-1:1;
		return oper*numAn(start,n,d);
	}
	public static int posiNega(int start, int n, int d, boolean isEvenNega){
		int oper=isEvenNega?1:-1;
		return oper*posiNega(start,n,d);
	}
	public static int sumAn(int n,int start, int d,boolean isEvenNega){
		int sum=0;
		for( int i=0; i<n;i++){
			System.out.print("["+posiNega(start,i,d,isEvenNega)+"] ");
			sum+=posiNega(start,i,d,isEvenNega);
		}
		return sum;
	}
}
