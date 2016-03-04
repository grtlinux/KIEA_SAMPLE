import java.awt.*;
import javax.swing.*;
public class ButtonJPanel6 extends JPanel {
  private static final long serialVersionUID=122454214598L;//JAVA5
  JButton jButton1 = new JButton("Button1");
  JButton jButton2 = new JButton("Button2");
  JButton jButton3 = new JButton("Button3");
  JButton jButton4 = new JButton("Button4");
  JButton jButton5 = new JButton("Button5");
  JButton jButton6 = new JButton("Button6");
  JToggleButton jButton7 = new JToggleButton("ToggleButton");
  public ButtonJPanel6() {  inits();    }
  void inits() {
    this.setLayout(new GridBagLayout());//GridBagLayout
	Builder.build(this,jButton1,Builder.set(0, 0, 2, 1, 0.5, 0.0,"C","B"));
	Builder.build(this,jButton2,Builder.set(0, 1, 1, 2, 0.0, 0.7,"C","B"));
	Builder.build(this,jButton4,Builder.set(2, 1, 1, 1, 0.0, 0.0,"C","N",20,10,20,10));
	Builder.build(this,jButton3,Builder.set(1, 2, 1, 1, 0.0, 0.0,"C","B",10,10,10,10));
	Builder.build(this,jButton5,Builder.set(3, 0, 1, 3, 0.5, 0.0,"C","B"));
	Builder.build(this,jButton6,Builder.set(0, 3, 1, 1, 0.0, 0.2,"C","N",0,0,0,0,50,0));
	Builder.build(this,jButton7,Builder.set(1, 4, 3, 1, 0.0, 0.1,"C","B",0,5,0,5));;
  }
}
