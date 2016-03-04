public class BernardQuizMain {

	public static void main(String[] args) {

		BernardQuiz quiz=new BernardQuiz();
		//우리나라 13 => 1이 3개 
		long a=System.currentTimeMillis();
		quiz.make(12);
		long b=System.currentTimeMillis();
		System.out.println((b-a)+"mmsec----------------------");
		
		for(int i=0;i<10;i++){
			quiz.make(i);
		}//
		//프랑스식 31 => 3 of 1  => 1이 3개
		System.out.println("---------------------------------");
		for(int i=0;i<10;i++){
			quiz.makef(i);
		}//
	}
}
