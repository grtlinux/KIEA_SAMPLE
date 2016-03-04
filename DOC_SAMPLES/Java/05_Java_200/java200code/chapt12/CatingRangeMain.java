public class CatingRangeMain
{
	public static void main(String[] args) 
	{
		char cc='ㄱ';//
		System.out.println(cc);// char를 찍자
		System.out.println((int)cc); // int로 바꾸어 찍자
		cc='ㄴ';//
		System.out.println(cc);// char를 찍자
		System.out.println((int)cc); // int로 바꾸어 찍자
		cc='ㄷ';//
		System.out.println(cc);// char를 찍자
		System.out.println((int)cc); // int로 바꾸어 찍자
		//int d=cc;
		//System.out.println(d);
		//System.out.println((char)d);


		System.out.println("---------------------------------");

		for(int i=1;i<=100;i++){
			System.out.print((char)i+" ");//찍고 공백 추가 그자리
			// (char)i --> int를 char로 casting
			if((i%10==0)){// 10으로 나누어 떨어지는 가?
				System.out.println();//한칸을 내려라
			}
		}

	}//
}
