//package com.jungbo.cars;
public class  Case2
{
	public static void main(String[] args) 
	{
		Car car3=new Truck();
		Truck truck1=new Truck();

		car3.show() ;  //overriding 
		System.out.println(car3.speed() );
		//car3.tone() //(X)
		
        truck1.show() ;  //overriding 
		System.out.println(truck1.speed() );// Car 39
		truck1.tone() ;
	}
}
