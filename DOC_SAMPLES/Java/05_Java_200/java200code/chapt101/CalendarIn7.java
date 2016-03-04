public class CalendarIn7{//섹션 101  CalendarMain
	private int month[] =  new int[]{31,28,31,30,31,30,31,31,30,31,30,31};//평년
	private int lmonth[] = new int[]{31,29,31,30,31,30,31,31,30,31,30,31};//윤년
	public boolean leapYearTF(int year){// 윤년을 체크
		boolean yearTF= false;
		if( (0 == (year % 4) && 0 != (year %100)) || 0 == year%400 )
			yearTF = true;
		return yearTF;
	}
	//1~year 까지 윤년의 회수 구하기
	public int howManyLeapYear(int year){
		int count = 0;
		for(int i = 1; i <=year; i++){
			if ( leapYearTF(i) ){
				count++;
			}
		}
		return count;
	}
	//2005/9/25일이면 (2005/1/1 ~2005/8/31)일 총 일수 + 25(9월)
	public int howManyDaysInYearsMonth(int year, int month, int day){
		int count = day;
		if ( leapYearTF(year) ){
			for(int i = 0; i < month-1; i++){
				count+=this.lmonth[i];
			}
		}else{
			for(int i = 0; i < month-1; i++){
				count+=this.month[i];
			}
		}
		return count;
	}
	//시작날짜가 0-->일,1-->월,2-->화,3-->수,4-->목,5-->금,6-->토
	public int startDayInCal(int year, int month){//년 월 1일의 첫날 
		int count=0;
		int leapYear=howManyLeapYear(year-1);
		int howManyDaysInYear=howManyDaysInYearsMonth(year,month,1);
		count=((leapYear)*2+(year-1-leapYear)+howManyDaysInYear);
		return count%7;
	}
	public boolean isLastDay(int year, int month,int day){//마지막날
		boolean isL=false;
		if(!leapYearTF(year)){
			if(day==this.month[month-1]){//평년 2월-->28
				isL=true;
			}
		}else{
			if(day==this.lmonth[month-1]){//윤년 2월-->29
				isL=true;
			}
		}
		return isL;
	}
	public int getDates(int year, int month){//그달에 몇일이 있는가?
		if(leapYearTF(year)){
			return this.lmonth[month-1];//윤년
		}else {
			return this.month[month-1];//평년
		}
	}
	public void printCalendar(int year, int month){
		int linecheck = 0;// 날짜를 처음 찍는 위치 지정하는 변수
		String temp = "";// 처음 문자 간격
		System.out.println("Sun\tMon\tTue\tWed\tThu\tFri\tSat");
		linecheck = startDayInCal(year, month);//1일이 무슨 요일인가?
		for(int j = 0; j < linecheck; j++){
			temp += "\t";       //1일이 수요일이면 탭 3개(일,월,화)
		}
		System.out.print(temp);
		int count=getDates(year, month);//그 달에 몇일이 있는가, 28, 29, 30, 31?
		for(int i = 1; i<= count; i++){
			System.out.print(i + "\t");
			linecheck ++;
			if(linecheck == 7)  {//토요일이냐 그러면 
				if(this.isLastDay(year,month,i)){
					return ;//마지막 날이 끝나면 한 줄아래로 내릴 필요없다.
				}
				System.out.println();
				linecheck = 0;
			}//if
		}//for
	}//printCalendar
}
