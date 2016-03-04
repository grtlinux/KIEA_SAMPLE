public class HTimerMain {
	public static void main(String[] args) {

		CommandTimer ct=new CommandTimer();
		ct.start(5000,2000,1000);//5초동안 2초후부터 1초간격으로
		//ct.start(0,0,1000);//1번
	}
}
