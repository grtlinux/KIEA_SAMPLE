public class  SwitchEnumCapt34
{
	//JAVA 5
	public static void main(String[] args) 
	{
		Season thisSeason=Season.SPRING;
		
		switch(thisSeason){//case에서 Season.SPRING 하면 에러
			case SPRING: System.out.println("SPRING");break;
			case SUMMER: System.out.println("SUMMER");break;
			case AUTUMN: System.out.println("AUTUMN");break;
			case WINTER: System.out.println("WINTER");break;
		}
		//Season 안에 있는 모든 것
		Season [ ] thisSeasons=Season.values(); 
		for(int i=0;i<thisSeasons.length;i++){
			 System.out.println(i+"  "+thisSeasons[i]);
		}
		//String을 enum으로
		Season thisSeason1=Season.valueOf("AUTUMN");
		System.out.println(thisSeason1);
	}
}
