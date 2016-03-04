public class IfLeapYear2 {
	private int leapYear=2003;
	
	public void setYear(int year){
		leapYear=year;
	}
	public boolean isLeapYear(){
		boolean isLeap=false;
		if((0 == (leapYear % 4) && 0 != (leapYear %100)) || 
			0 == leapYear%400){
			isLeap=true;
		}
		return isLeap;
	}
	public void prints(){
		if(isLeapYear()){
			System.out.println(leapYear+"는 윤년입니다. ");
		}else{
			System.out.println(leapYear+"는 윤년이 아닙니다.");
		}
	}
	public static void print(){
		//System.out.println(leapYear);//에러
	}
}
