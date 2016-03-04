import javax.swing.*;
import java.awt.*;
public class IfLeapYearsApplet extends JApplet{
	private static final long serialVersionUID=122454214237L;//JAVA5
	public void init() {
	}
	public void start() {
	}
	public void paint(Graphics g){
		super.paint(g);//습관적으로 쓰자.
		for(int year=1998;year<2006;year++){
			boolean yearTF=false;
			if( (0 == (year % 4) && 0 != (year %100)) || 
				 0 == year%400 ){
				yearTF = true;
			}else{
				yearTF = false;
			}

			if(yearTF){
				g.drawString(year+"는 윤년입니다.",50,50+(year-1997)*20);
			}else{
				g.drawString(year+"는 윤년이 아닙니다.",50,50+(year-1997)*20);
			}
		}
	}
}
