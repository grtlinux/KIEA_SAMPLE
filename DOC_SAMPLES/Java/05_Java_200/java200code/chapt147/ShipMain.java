public class  ShipMain
{
	public static void main(String[] args) 
	{
		Ship ship1=new Boat();
		Ship ship2=new Cruise();
		System.out.println(ship1.move());
		System.out.println(ship1.carry());
		System.out.println(ship2.move());
		System.out.println(ship2.carry());
		ShipUtil.search(ship2);
	}
}
