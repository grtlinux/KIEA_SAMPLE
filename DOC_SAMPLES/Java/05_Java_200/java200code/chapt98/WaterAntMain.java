public class WaterAntMain {

	public static void main(String[] args) {
		Ant ant=new Ant();
		ant.print();
		System.out.println(ant.toString());
		
		WaterAnt want=new WaterAnt();
		want.print();
		want.show();
		System.out.println(want.toString());
	}
}
