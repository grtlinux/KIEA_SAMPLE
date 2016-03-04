
public class HanoinsMain {

	public static void main(String[] args) {
		int tray=5;
		Hanoins hanoi=new Hanoins(tray);
		int [][] a=hanoi.getRings();
		hanoi.showHanoi(a);
		hanoi.moveHanoi(a,tray,'a','b','c');
		
	}
}
