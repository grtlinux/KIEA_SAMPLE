//JAVA 5
public enum SeasonString  
{
	SPRING ("Spring (from 3 to 5)"),
	SUMMER ("Summer (from 6 to 8)"),
	AUTUMN ("Autumn (from 9 to 11)"),
	WINTER ("Winter (from 12 to 2)");//;사용한다.
	//위치조심할 것
	private String season;
	//생성자
	SeasonString(String sea){
		season=sea;
	}
	//메서드
	public String toString(){
		return season;
	}//valueOf()
	public String getMonth(){
		return season.substring(7);
	}
}
