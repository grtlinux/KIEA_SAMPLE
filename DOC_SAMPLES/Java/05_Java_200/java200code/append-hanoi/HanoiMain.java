
/**
 * 소수(솟수:prime)을 구하는 클래스
 * @author hyonny
 * Created on 2002. 10. 6.
 */

public class HanoiMain {

	public static void main(String[] args) {
		
		int tray=4;
		Hanois ha=new Hanois(tray);
		
		
		int [][] a=ha.getRings();
		//Hanois.showHanoi(a);
		
		Hanois.moveHanoi(a,tray,'a','b','c');
		
	}
}
