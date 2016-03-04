public class NumberAn45 {

	public static void main(String[] args) {
		int toNum=10;
		
		try{
			toNum=ScannerInput.readInt();
		}catch(Exception e){
			System.out.println("예외: 타입확인 요망");
			//System.exit(1);//프로그램 끝
		}
		
		double sum=sumAn(1,toNum,1);
		System.out.println("\n1 to "+toNum+" summation="+sum);
		
		double sum2=sumAn(1,toNum,2);
		System.out.println("\n1 to "+toNum+" 1/odd summation="+sum2);
	}
	public static int numAn(int start, int n, int d){
		return (start+n*d);
	}
	public static double divNum(int start, int n, int d){
		return 1.0/numAn(start, n, d);
	}
	public static double sumAn(int start,int n, int d){
		double sum=0;
		for( int i=0; i<n;i++){
			System.out.print("["+divNum(start,i,d)+"] ");
			sum+=divNum(start,i,d);
		}
		return sum;
	}
}
