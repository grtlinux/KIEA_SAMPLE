public class CarMain {

	public static void main(String[] args) {
		
		Car car1=new Car();
		car1.speedUp();
		car1.speedDown();
		car1.speedUp();
		System.out.println(car1.curSpeed());
		car1.turnDirect(-30);//30도 오른쪽
		car1.turnDirect(10); //10도 왼쪽
		System.out.println(car1.curDirect());
	}
}