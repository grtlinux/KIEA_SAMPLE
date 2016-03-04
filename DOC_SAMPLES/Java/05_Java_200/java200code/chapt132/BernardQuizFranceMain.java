public class BernardQuizFranceMain {

	public static void main(String[] args) {

		BernardQuizFrance quiz=new BernardQuizFrance();
		//우리나라 13 => 1이 3개 
		long a=System.currentTimeMillis();
		quiz.makef(12);
		long b=System.currentTimeMillis();
		System.out.println((b-a)+"mmsec----------------------");
		//프랑스식 31 => 3 of 1  => 1이 3개
		System.out.println("---------------------------------");
		for(int i=0;i<10;i++){
			quiz.makef(i);
		}//
	}
}
