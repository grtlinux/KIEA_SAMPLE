public class SeasonStringMain 
{
	public static void main(String[] args) 
	{
		SeasonString season=SeasonString.AUTUMN;
		System.out.println(season.getMonth());
		System.out.println(season);//toString-->valueOf
		//overriding ¾ÈÇÏ¸é name()
		//enum types may mot be instantiated
		//SeasonString sea=new SeasonString("Fall");//error
		System.out.println(season.equals(SeasonString.SPRING));
		System.out.println(season.equals(SeasonString.AUTUMN));
		System.out.println(season.compareTo(SeasonString.SPRING));//2  A-SP
		System.out.println(season.compareTo(SeasonString.SUMMER));//1  A-SU
		System.out.println(season.compareTo(SeasonString.AUTUMN));//0  A-A
		System.out.println(season.compareTo(SeasonString.WINTER));//-1 A-W
		System.out.println(season.ordinal());//2
		System.out.println(season.name());//AUTUMN
		System.out.println(SeasonString.AUTUMN.name());//AUTUMN
		System.out.println(SeasonString.valueOf("AUTUMN"));//Autumn (from 9 to 11)
		//: is same to in(c#)
		for(SeasonString sea : SeasonString.values()){
			System.out.println(sea);//Autumn (from 9 to 11)
		}
	}
}
