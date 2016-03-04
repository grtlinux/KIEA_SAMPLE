import java.util.*;
public class HowManyDays {
	private Calendar from=Calendar.getInstance();//¿À´Ã
	private Calendar to=Calendar.getInstance();//¿À´Ã
	public int aDay=1000*60*60*24;
	public HowManyDays(){
	}
	public HowManyDays(Calendar from,Calendar to){
		setFrom(from);
		setTo(to);
	}
	public int howManyDays(){
		return (int)((to.getTime().getTime()-
				from.getTime().getTime())/aDay);
	}
	public void setFrom(Calendar from){
		this.from=from;
	}
	public void setTo(Calendar to){
		this.to=to;
	}
	public static Calendar makeDay(int year, int month, int day){
		Calendar making=Calendar.getInstance();//¿À´Ã
		making.set(year,month-1,day);
		return making;
	}
}
