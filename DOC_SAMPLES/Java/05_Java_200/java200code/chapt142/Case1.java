//package com.jungbo.cars;
public class  Case1
{
	public static void main(String[] args) 
	{
		Car car1=new Car();
		Bus bus1=new Bus();//    
		Car car2=new Bus();// reference 다형성
		
		car1.show() ; 
		System.out.println(car1.speed() );
		
		bus1.show() ;
		System.out.println(bus1.speed() );
		bus1.move();

		car2.show() ;
		System.out.println(car2.speed() );
		//car2.move(); (X)
		Bus buc1=(Bus)car2;
		buc1.move(); 
		Car car0=buc1;  //argument 다형성
		car0.show() ; 
		System.out.println(car0.speed() );
		//car0.move(); //(X)
		Bus bus0=(Bus)car0;
		bus0.move(); 
		System.out.println("+++++++++++++++++++++");
		//Bus buc2=(Bus)car1; // 컴파일 까지는 문제가 없지만 실행시 에러
		//ClassCastException  발생
		//buc2.show() ; 
		//System.out.println(buc2.speed() );
		//buc2.move();
	}
}
