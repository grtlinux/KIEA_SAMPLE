import java.util.*;
public class TimesGo extends TimerTask{
	public void run(){
		printDays();
	}
	private void printDays(){
		//오늘 날짜로 초기화
		Calendar cal=Calendar.getInstance();
		System.out.print("Year: "+cal.get(Calendar.YEAR));
		System.out.print("/Month: "+(cal.get(Calendar.MONTH)+1));
		System.out.print("/Day: "+cal.get(Calendar.DATE));
		System.out.print("/Hour: "+cal.get(Calendar.HOUR));
		System.out.print("/Minute: "+cal.get(Calendar.MINUTE));
		System.out.println("/Second: "+cal.get(Calendar.SECOND));
	}
}
