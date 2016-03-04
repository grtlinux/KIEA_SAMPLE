import java.awt.*;
import javax.swing.*;
public class HCalendarMain {
  public static void main(String[] args) {
    StartingJFrame startFrame =new StartingJFrame();
	JPanel cp=new JPanel();//JFrame 넘기기
	HCalendar hcal=new HCalendar(cp);
	startFrame.setMainJpanel(cp);
	startFrame.setSize(new Dimension(550, 400));//JFrame 크기조정
	startFrame.validate();//JFrame 화면 조정
  }
}
