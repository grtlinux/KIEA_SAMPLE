import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class StartingJFrame extends JFrame {
  private static final long serialVersionUID=122454214237L;//JAVA5
  private JPanel mainp;
  public StartingJFrame() {
	System.out.println(this.getClass().getName()+" Start!!");
    inits();//JFrme에 기본페널을 붙이기 그리고 중앙에 놓기
  }
  private void inits() {
	//--------------필수---------------//
    mainp = (JPanel) this.getContentPane();
    mainp.setLayout(new BorderLayout());//중앙을 사용하기 위해서
    this.setSize(new Dimension(400, 300));// this.setSize(400,300)//크기
    initFrame();//중앙에 붙이기
	//------------타이틀----------------//
	this.setTitle(this.getClass().getName());//JFrame에 이름 붙이기
  }
  public  void addListeners(){//JFrame에 관련된 리슨너
	//-------귀달기-------//
  }
  private void initFrame(){//중앙에 놓기
    Dimension monitorSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = this.getSize();
    if (frameSize.height > monitorSize.height) {
        frameSize.height = monitorSize.height;
      }
    if (frameSize.width > monitorSize.width) {
      frameSize.width = monitorSize.width;
    }
    int locationX=(monitorSize.width - frameSize.width) / 2;
    int locationY=(monitorSize.height - frameSize.height) / 2;
    this.setLocation(locationX, locationY);
    this.setVisible(true);
	enableEvents(AWTEvent.WINDOW_EVENT_MASK);//X로 끄기
  }
  public void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
	  System.out.println(this.getClass().getName()+" End!!");
      System.exit(1);//프로그램 끝내기
    }
  }
  public void setMainJpanel(javax.swing.JComponent c){
    mainp.add(c);
  }
}
