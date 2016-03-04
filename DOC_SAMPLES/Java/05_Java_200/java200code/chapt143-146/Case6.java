 //package com.jungbo.cars;
 public class Case6 {
 
 	public static void main(String[] args) {
 		Car car7=new Cargo();
 		
 		Truck truc5=new Cargo(); 
 		Cargo cago2=(Cargo)car7;
 		Cargo cago3=(Cargo)truc5;
 		Truck truc7=(Truck) car7;
 		
 		car7.show();//1->4
 		System.out.println(car7.speed());//2
 		
 		Cargo cago1=new Cargo();
 		cago1.show();//1->4
 		System.out.println(cago1.speed());//2
 		cago1.tone();//5
 		cago1.passing();//8
 		
 		cago2.show();//1->4
 		System.out.println(cago2.speed());//2
 		cago2.tone();//5
 		cago2.passing();//8
 	}
}
