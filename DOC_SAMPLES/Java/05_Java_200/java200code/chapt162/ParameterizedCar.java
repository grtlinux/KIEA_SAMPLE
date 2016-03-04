import java.util.*;
public class ParameterizedCar
{//creating parameterized type
	public static void main(String[] args) 
	{
		Carbarn <Car> vc=new Carbarn<Car>();
		vc.add(new Car());    
		vc.add(new Taxi()); //Taxi is Car
		vc.add(new Bus());  //Bus  is Car
		for(Car c: vc.getAllCars()){
			c.show();
			System.out.println(c.speed());
		}
		System.out.println("-------------------------");
		Carbarn <Taxi> vcb=new Carbarn<Taxi>();
		vcb.add(new Taxi());  vcb.add(new Taxi());  
		vcb.add(new Taxi());  vcb.add(new Taxi());
		//vcb.add(new Car()); //Car is not Taxi
		//vcb.add(new Bus()); //Bus is not Taxi
		//Carbarn <Car> cc=vcb;//incompatible types
		System.out.println("-------------------------");
		printCarbarn(vcb);
		printCarbarns(vc);
		printCarbarns2(vc);
	}
	public static void printCarbarn(Carbarn <Taxi> cts){
		Iterator<Taxi> iter=cts.getAllCars().iterator();
		while(iter.hasNext()){
			Taxi taxi=iter.next();
			taxi.show();
			System.out.println(taxi.speed());
		}
	}
	public static void printCarbarns(Carbarn <? extends Car> cts){
		Iterator<? extends Car> iter=cts.getAllCars().iterator();
		while(iter.hasNext()){
			Car c=iter.next();
			c.show();
			System.out.println(c.speed());
		}
	}
	public static <T extends Car> void printCarbarns2(Carbarn<T>  cts){
		//Iterator<T> iter=cts.getAllCars().iterator();
		printCarbarns(cts);
	}
}
