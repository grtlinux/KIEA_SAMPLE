public class ForOneToHundred41 {

	public static void main(String[] args) {
		
		int toNum=100;
		/*try{   //JAVA5에서는 코멘트를 풀고 사용하자.
			toNum=ScannerInput.readInt();
		}catch(Exception e){
			System.out.println("예외: 타입확인 요망"+e);
			//System.exit(1);//프로그램 끝
		}*/
		//main
		int summs=0;
		for(int i=1;i<=toNum;i++){
			summs+=i;
		}
		System.out.println("1 to "+toNum+" summation="+summs);
		
		//메서드 1
		int sum=summation(1,toNum);
		System.out.println("1 to "+toNum+" summation="+sum);
		
		//메서드 2
		int sum2=summation(toNum);
		System.out.println("1 to "+toNum+" summation="+sum2);
	}

	public static int summation(int start, int end){
		if(start>end ){return -1;}
		int sum=0;
		for(int i=start;i<=end;i++){
			sum+=i;
		}
		return sum;
	}
	public static int summation(int end){
		return summation(1,end);
	}
}
