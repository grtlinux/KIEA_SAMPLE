public class CalendarMain{
	public static void main(String[] args) {
		CalendarIn7 cal7 = new CalendarIn7();
		int year=2005;
		for(int i=1;i<13;i++){
			System.out.println("\t\t"+year+"³â\t"+i+"¿ù\n");
			cal7.printCalendar(year,i);
			System.out.println("\n---------------------------------------------------");
		}
	}
}
