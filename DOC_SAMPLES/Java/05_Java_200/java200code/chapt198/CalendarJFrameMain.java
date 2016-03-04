import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class CalendarJFrameMain {
  public static void main(String[] args) {
    StartingJFrame startFrame =new StartingJFrame();
	CalendarJPanel cp=new CalendarJPanel(startFrame);//JFrame 넘기기
	startFrame.setMainJpanel(cp);
	startFrame.setSize(new Dimension(550, 400));//JFrame 크기조정
	startFrame.validate();//JFrame 화면 조정
  }
}
