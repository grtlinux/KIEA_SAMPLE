public class Airplane2 {

	private String nameOfAirp="C10111";   //비행기 이름
	private int fuel=10000;               //liter
	private int goPerL=10;                //Km/liter  defalut
	public Airplane2(String nameOfAirp,int fuel,int goPerL){
		this.nameOfAirp  = nameOfAirp;   //비행기 이름
		this.setFuel(fuel);
		this.goPerL      = goPerL;   //km/liter  
	 }
	public Airplane2(String nameOfAirp,int fuel){
		this(nameOfAirp,fuel,10);
	}
	public Airplane2(String nameOfAirp){
		this(nameOfAirp,10000,10);
	}
	public Airplane2(){
		this("C10111",10000,10);
	}
	public void setFuel(int fuel){
		if(fuel<1000){
			System.out.println("연료부족위험");
			this.fuel=0;
			return ;
		}
		this.fuel=fuel;
	}  
	public int getFuel() {return fuel;}
	public int getGoPerL() {return goPerL;}
	public String getNameOfAirp() {return nameOfAirp;}
	public String toString(){
		String s="";
		if(fuel>999){
			s="비행기 이름: "+nameOfAirp;
			s+="   비행거리   : "+goPerL*fuel;
		}else{
			s="이 비행기는 출발할 수 없는 비행기 입니다.";
		}
		return s;
	}
}
