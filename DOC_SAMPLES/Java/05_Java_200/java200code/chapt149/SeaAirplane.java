public class SeaAirplane extends Plane implements Ship {
	private String id="Air Balls";
	public SeaAirplane(String id){
		this.id=id;
	}
	public SeaAirplane(){
		this("LeeMass");
	}
	//abstract
	public  void fly(){
		System.out.println("엔진과 날개를 이용해서 날아감");
	}
	public  int power(){
		return 10000;
	}
	//interface
	public int move(){
		return 5;
	}// 사람을 몇명 나르는가
	public int carry(){
		return 300;
	}// 무기를 몇 정 나르는가
	
	public String toString(){
		return this.id+"가  "+power()+"마력으로  "+move()+"명을 나른다.";
	}
}
