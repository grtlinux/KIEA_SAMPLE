//package com.jungbo.cars;
public class  Case3
{
	public static void main(String[] args) 
	{
		Car car4=new Taxi();
		Taxi taxi1=new Taxi();
		car4.show() ;
		System.out.println(car4.speed() );

		taxi1.show() ;
		System.out.println(taxi1.speed() );
	}
}
