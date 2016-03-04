public class AntMain {

	public static void main(String[] args) {
		Ant ant=new Ant();
		ant.print();
		System.out.println(ant.toString());
		
		WaterAnt want=new WaterAnt();
		want.print();
		want.show();
		System.out.println(want.toString());
		
		WoodAnt dant=new WoodAnt();
		dant.print();
		dant.hate();
		System.out.println(dant.toString());
	}
}
