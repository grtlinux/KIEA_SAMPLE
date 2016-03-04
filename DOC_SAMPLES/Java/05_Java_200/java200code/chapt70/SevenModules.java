public class SevenModules {

	public static void main(String[] args) {
		int start=0; //기준을 만든다.서기 1년 1월이면 start=0;
		int yearstart=1;//기준이 서기 1970년 1월 1일이면 start=3;
		int sum=0;
		
		int year=2004;
		int month=11;
		int day=16;
		//서기 1년~서기 2003년 12월 31일 까지 총합
		for(int i=yearstart;i<year;i++){
			for(int j=1;j<13;j++){
				sum+=dates(i,j);
			}
		}
		//서기 2004년 1월 1일 ~10월 31일 까지 총합
		for(int j=1;j<month;j++){
			sum+=dates(year,j);
		}
		//11월 16일 -->16일 합
		System.out.println(year+"/"+month+"/"+day+"/"+
				dayMode(relativeMode(sum+day+start)));
		
		printCal(2004,12,25);
	}
	public static void printCal(int year, int month, int day){
		int start=3; //기준을 만든다.서기 1년 1월이면 start=0;
		int yearstart=1970;//기준이 서기 1970년 1월 1일이면 start=3;
		int sum=0;
		for(int i=yearstart;i<year;i++){
			for(int j=1;j<13;j++){
				sum+=dates(i,j);
			}
		}

		for(int j=1;j<month;j++){
			sum+=dates(year,j);
		}
		System.out.println(year+"/"+month+"/"+day+"/"+
				dayMode(relativeMode(sum+day+start)));
	}
	public static int relativeMode(int day){
		return (day%7);
	}
	public static boolean isLeapYear(int year){
		boolean isLeap=false;
		if((0 == (year % 4) && 0 != (year %100)) || 
			0 == year%400){
			isLeap=true;
		}
		return isLeap;
	}
	public static String dayMode(int day){
		String temp="";
		switch(day){
			case 0: temp="SUN"; break;
			case 1: temp="MON"; break;
			case 2: temp="TUE"; break;
			case 3: temp="WED"; break;
			case 4: temp="THU"; break;
			case 5: temp="FRI"; break;
			case 6: temp="SAT"; break;
		}
		return temp;
	}
	public static int dates(int year,int month){
		int temp=0;
		if(!isLeapYear(year)){
			switch(month){
				case 1: 
				case 3: 
				case 5: 
				case 7: 
				case 8: 
				case 10: 
				case 12: temp=31; break;
				case 4:
				case 6:
				case 9:
				case 11: temp=30; break;
				case 2:  temp=28; break;
			}
		}else{
			if(month==1 ||month==3||month==5||month==7
				            ||month==8||month==10||month==12){
				temp=31;
			}else if(month==4||month==6||month==9||month==11){
				temp=30;
			}else{
				temp=29;
			}
		}
		return temp;
	}
}