public class ForOddEvenSum42 {

	public static void main(String[] args) {
		
		int toNum=100;
		/*try{   //JAVA5에서는 코멘트를 풀고 사용하자.
			toNum=ScannerInput.readInt();
		}catch(Exception e){
			System.out.println("예외: 타입확인 요망"+e);
			//System.exit(1);//프로그램 끝
		}*/
		int sumOdd=0;
		int sumEven=0;

		sumEven=summEven(1,toNum);
		System.out.println("1 to "+toNum+" even sum "+sumEven);

		sumOdd=summOdd(1,toNum);
		System.out.println("1 to "+toNum+" odd sum "+sumOdd);
	}
	public static int summEven(int start, int end){
		int sumEven=0;
		//짝수합
		for( int i=1; i<=end;i++){
			if(i%2!=0){
				continue;
			}
			sumEven+=i;
		}
		return sumEven;
	}
	public static int summOdd(int start, int end){
		int sumOdd=0;
		//짝수합
		for( int i=1; i<=end;i++){
			if(i%2==0){
				continue;
			}
			sumOdd+=i;
		}
		return sumOdd;
	}
}
