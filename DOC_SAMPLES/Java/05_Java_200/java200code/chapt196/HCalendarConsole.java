import java.util.*;
public class  HCalendarConsole{
	int year=2003;
	int month=8;
	Calendar today;//오늘
	Calendar cal ;
	public HCalendarConsole(){
		today=Calendar.getInstance();//오늘
		cal = new GregorianCalendar();//오늘
		calInput();//원하는 년월입력
		calSet();//달력
	}
	public void calInput(){
		InputsInt ins=new InputsInt();//섹션 165 JOptionPane을 이용한 Inputs
		int [] a=ins.input(2,0,3000);//서로다른 두 수(0~3000사이의 수)를 입력받는다.
		year=a[0];//년
		month=a[1];//월
	}
	public void calSet(){
		cal.set(Calendar.YEAR,year);//년
		cal.set(Calendar.MONTH,(month-1));//월(0~11월)
		cal.set(Calendar.DATE,1);//1일이 있는 주를 기준
		System.out.println(cal.get(Calendar.YEAR)+"년/ "+(cal.get(Calendar.MONTH)+1)+"월");
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);//1일이 무슨 요일
		int j=0,k=0;
		int hopping=0;
		//System.out.println(cal.getFirstDayOfWeek());//그 주의 첫번째날 -->1일
		//System.out.println(dayOfWeek);//DAY_OF_WEEK: SUNDAY 1,MONDAY 2,TUESDAY 3
		//WEDNESDAY 4,THURSDAY 5, FRIDAY 6, SATURDAY 7. 
		System.out.println("Sun\tMon\tTue\tWed\tThu\tFri\tSat");
		for(int i=cal.getFirstDayOfWeek();i<dayOfWeek;i++){  
			j++;//일요일이면 j=0, 월요일이면 1, 화요일이면 2,...
		}
		hopping=j;
		for(int kk=0;kk<hopping;kk++){//1일이 화요일이면 \t\t
			System.out.print("\t");
		}
		for(int i=cal.getMinimum(Calendar.DAY_OF_MONTH);
        i<=cal.getMaximum(Calendar.DAY_OF_MONTH);i++){
		  cal.set(Calendar.DATE,i);
			if(cal.get(Calendar.MONTH) !=month-1){
				  break;
			}//달이 다음달로 넘어가지 않도록
		    if(hopping==0 && ((i-1)/7)==0){
				System.out.print(i+"\t");
			}else{
				if(cal.get(Calendar.DAY_OF_WEEK)==1){ //일요일이면
						System.out.println();//한 주아래로
				}
				System.out.print(i+"\t");
			}
	    }//for	
    }
}
