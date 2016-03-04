import javax.swing.*;
import java.awt.*;
public class Hello4Applet extends JApplet{
	private static final long serialVersionUID=122454214237L;//JAVA5
	public void init() {
	}
	public void start() {
	}
	public void paint(Graphics g){
		super.paint(g);//습관적으로 쓰자.
		g.drawString("안녕하세요!! JAVA를 즐깁시다.",50,50);
	}
}
