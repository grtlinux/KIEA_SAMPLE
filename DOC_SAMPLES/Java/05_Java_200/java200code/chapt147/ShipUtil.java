public class  ShipUtil
{
	public static void search(Ship s){

		System.out.println(s.move());
		System.out.println(s.carry());

		if(s instanceof Boat){
			Boat b=(Boat)s;
			System.out.println("Boat 이름: "+b.name());
		}else if(s instanceof Cruise){
			Cruise b=(Cruise)s;
			System.out.println("Cruise 이름:"+b.name());
		}
	}
}
