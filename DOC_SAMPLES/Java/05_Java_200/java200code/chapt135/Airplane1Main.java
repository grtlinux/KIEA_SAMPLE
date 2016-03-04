public class Airplane1Main {

	public static void main(String[] args) {
		Airplane1 air948 =new Airplane1("이글 파이브",6000,11);
		//생성자 -- 멤버 필드의 초기화 
		System.out.println(air948.toString());	
		System.out.println("============================================");
		Airplane1 air947 =new Airplane1("센타 파이브",100);
		System.out.println(air947);	
	}
}
