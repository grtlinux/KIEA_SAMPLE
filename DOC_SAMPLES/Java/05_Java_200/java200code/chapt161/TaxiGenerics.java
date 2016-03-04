import java.util.*;
public class TaxiGenerics
{
	public static void main(String[] args) 
	{
		Vector <Car> vc=new Vector<Car>(5,5);
		vc.add(new Car());  vc.add(new Car());  vc.add(new Car());
		vc.add(new Taxi()); vc.add(new Bus());
		System.out.println("-------------------------");
		printVector(vc);
		printVector2(vc);
		printVector3(vc);
	}
	//whild card 
	public static void printVector(Vector <? extends Car> vec){
		//interface java.lang.Iterable<T>을 구현하면 for를 사용
		for(Car c : vec){
			if(c instanceof Bus){
				c.show();
			   ((Bus)c).move();
			}else {
				c.show();
			    System.out.println(c.speed());
			}
		}
	}
	//parameterized method
	public static <T extends Car> void printVector2(Vector<T> v){
		printVector(v);
	}
	//parameterized method
	public static <T extends Car> void printVector3(Vector<T> v){
		Iterator <T> iter=v.iterator();//
		while(iter.hasNext()){
			Car c=iter.next();
			c.show();
		}
	}
}