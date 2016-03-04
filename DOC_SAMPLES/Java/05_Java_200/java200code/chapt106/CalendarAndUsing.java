import java.util.*;

public class CalendarAndUsing {

	public static void main(String[] args) {
		Calendar cal=Calendar.getInstance();
		GregorianCalendar gcal=new GregorianCalendar();
		System.out.println(gcal.getFirstDayOfWeek());
		System.out.println(gcal.isLeapYear(2004));
		Date date1=gcal.getGregorianChange();
		System.out.println(date1);
		
		Locale [] gc=GregorianCalendar.getAvailableLocales();
		showLocale(gc);
		TimeZone tz=TimeZone.getDefault() ;
		System.out.println(tz.getID());
		String [] ids=TimeZone.getAvailableIDs();
		showTimeZone(ids);
		printString(getAsia(ids));
	}
	public static void showLocale(Locale [] gc){
		System.out.println("---------Locale ----------");
		for(int i=0;i<gc.length;i++){
			System.out.println(gc[i].getCountry()
					+"  "+gc[i].getDisplayLanguage()
					+"  "+gc[i].toString());
		}
	}
	public static void showTimeZone(String [] ids){
		System.out.println("---------IDS ----------");
		for(int i=0;i<ids.length;i++){
			System.out.println(ids[i]);
		}
	}
	public static String [] getAsia(String [] ids){
		//ArrayList<String> list=new ArrayList<String>();
		ArrayList list=new ArrayList();
		for(int i=0;i<ids.length;i++){
			if(ids[i].contains("Asia")){
				list.add(ids[i]);
			}
		}
		String [] idss=new String[list.size()];
		Object [] objs=list.toArray();
		for(int i=0;i<idss.length;i++){
			idss[i]=(String)objs[i];
		}
		return idss;
	}
	public static void printString(String [] ids){
		for(int i=0;i<ids.length;i++){
			System.out.println(ids[i]);
		}
	}
}
