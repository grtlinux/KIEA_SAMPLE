import java.util.*;
public class TimesGoMain {

	public static void main(String[] args) {
		Timer t=new Timer();
		TimesGo tg=new TimesGo();
		//t.schedule(tg, 1000);//1초후 한번 실행
		//t.schedule(tg, 2000,1000);//2초후 1초마다 무한 반복
		//t.schedule(tg,new Date(System.currentTimeMillis()+3000));//3초후 1번 실행
		//3초후 1초마다 무한
		t.schedule(tg,new Date(System.currentTimeMillis()+3000),1000);
		//t.cancel();//멈추기
	}
}
