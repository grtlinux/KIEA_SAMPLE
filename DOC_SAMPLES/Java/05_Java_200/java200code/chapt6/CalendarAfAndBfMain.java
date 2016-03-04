import java.util.*;
public class CalendarAfAndBfMain
{
	public static void main(String[] args) 
	{
		CalendarAfAndBf cal=new CalendarAfAndBf();
		//setDate의 주석을 풀자
		Date d=cal.setDate(2005, 3,2);
		System.out.println("기준날 :"+d.toString());
		System.out.println("다음날 :"+cal.afterOneDay(d));
		System.out.println("기준날 :"+cal.beforOneDay(d));
	}
}
