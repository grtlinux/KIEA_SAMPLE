import java.util.Date;
import java.util.Calendar;
public class CalendarAndDate {
	public static void main(String[] args) {
		Date d1=new Date();//오늘
		System.out.println(d1.toString());
		Date d2=new Date(System.currentTimeMillis());//오늘
		System.out.println(d2.toString());
		System.out.println(d1.compareTo(d2));//d1과 d2 비교 
		System.out.println(d1.after(d2));// is d1 afterday?
		System.out.println(d1.before(d2));// is d2 beforeday?
		long longDays=d1.getTime();//1970.1.1~
		Date d3=(Date)d1.clone();
		d3.setTime(longDays+1000*60*60*24*3);//3일후
		System.out.println(d3);//d3에 대한 deep copy 형식
		System.out.println(d1);//
		
		Calendar cal=Calendar.getInstance();//오늘
		System.out.println(cal.toString());
		System.out.println(cal.getTime());//Date 리턴
		System.out.println(cal.getTime().getTime());//1970.1.1~
		System.out.println(cal.get(Calendar.YEAR));//현재시간
		System.out.println(cal.get(Calendar.MONTH));//현재월
		System.out.println(cal.get(Calendar.DATE)+1);//현재날짜
		System.out.println(cal.get(Calendar.HOUR));//현재시간
		System.out.println(new Date(cal.getTime().getTime()));//오늘
	}
}
