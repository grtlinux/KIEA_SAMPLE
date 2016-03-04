import java.util.*;
/**
 * Created on 2003. 8. 25.<br>
 * 칼렌다 클래스를 이용한 날짜 구하기<br>
 * java.util.Date를 이용해 하루전과 하루후를 구함<br>
 * @author hyoeun cho
 */
public class CalendarAfAndBf {
	/**
	 * 입력한 다음날을 구한다.
	 * @param d 입력 다음 날을 출력하기 위한 입력 날
	 * @return 하루 후를 출력
	 */
	public Date afterOneDay(Date d){
		//d 기준날짜를 입력한다.
		long dd=d.getTime();
		//밀리세컨드*60초*60분*24시간==하루
		return new Date(dd+1000*60*60*24);
	}
	/**
	 * 입력한 전날을 구한다.
	 * @param d 입력 전날을 출력하기 위한 입력 날
	 * @return 하루 전을 출력
	 */
	public Date beforOneDay(Date d){
		//d 기준날짜를 입력한다.
		long dd=d.getTime();
		//밀리세컨드*60초*60분*24시간==하루
		return new Date(dd-1000*60*60*24);
	}
	/**
	 * 년, 월, 일을 입력해서 <code>java.util.Date<code>로 리턴한다.
	 * @param year 년
	 * @param month 월
	 * @param day 일
	 * @return 입력한 날을 <code>java.util.Date<code>로 리턴한다.
	 */
	public Date setDate(int year,int month,int day){
		Calendar cal=Calendar.getInstance();
		cal.set(year,month-1,day);  //0~11까지 존재하기 때문에 -1
		return new Date(cal.getTimeInMillis());
	}
}
