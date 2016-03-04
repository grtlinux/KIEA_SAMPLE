public class LeapYearTotalDays {	
	private int [] month1={31,28,31,30,31,30,31,31,30,31,30,31};
	private int [] month2={31,29,31,30,31,30,31,31,30,31,30,31};

	public int getTotalDays(int year){
		int total=0;
		if(!isLeapYear(year)){
			for(int i=0;i<month1.length;i++){
				total+=month1[i];
			}
		}else{
			for(int i=0;i<month1.length;i++){
				total+=month2[i];
			}
		}
		return total;
	}
	private boolean isLeapYear(int year){
		return (0 == (year % 4) && 0 != (year %100)) || 
			   (0 == year%400 )?true:false;
	}
}
