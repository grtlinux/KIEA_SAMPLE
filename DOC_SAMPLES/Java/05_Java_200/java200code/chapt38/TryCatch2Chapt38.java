public class TryCatch2Chapt38 {

	public static void main(String[] args) {
		
		String sNum="123";
		String nNum="h";
		try{
			int a=Integer.parseInt(nNum);
			System.out.println(a);
		}catch(Exception ee){
			System.out.println("==> 에러 !!!!");
			System.err.println(ee+"<==");
		}finally{
			System.out.println("난 수행되어야 만 해!!!!");
		}

		try {
			// Divide by zero.
			int x = 5;
			int y = 20 / (5 - x);
		} catch (ArithmeticException e) {
		   System.out.println("==> 0으로 나눴나 확인해 봐!!!!");
		   e.printStackTrace();
		}
	}
}
