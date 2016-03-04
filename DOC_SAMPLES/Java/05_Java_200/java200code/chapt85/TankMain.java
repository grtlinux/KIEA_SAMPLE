public class TankMain {

	public static void main(String[] args) {
		System.out.println(Tank.howmany);
		System.out.println(Tank.getHowMany());
		
		Tank tiger=new Tank();
		Tank black=new Tank();
		Tank cats=new Tank();
		Tank hawk=new Tank();
		
		System.out.println(tiger.howfast);//member
		
		tiger.input();		tiger.increase();
		black.input();		black.increase();
		cats.input();		cats.increase();
		hawk.input();		hawk.increase();
		
		tiger.showShortLife();
		tiger.showShortLife();
		System.out.println(Tank.getHowMany());//static 
		System.out.println(tiger.getHowFast());//member
		
		System.out.println(tiger);//type@hashCode
		System.out.println(black);
		System.out.println(cats);
		System.out.println(hawk);
		
		tiger=null;  //garbage collection ´ë»ó
		System.out.println(tiger);//type@hashCode
	}
}
