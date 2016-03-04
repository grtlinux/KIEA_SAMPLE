import java.awt.*;
import javax.swing.*;
public class ButtonJPanel4 extends JPanel {
  private static final long serialVersionUID=122454245598L;//JAVA5
  JButton jButton1 = new JButton("Button1");
  JButton jButton2 = new JButton("Button2");
  JButton jButton3 = new JButton("Button3");
  JButton jButton4 = new JButton("Button4");
  JButton jButton5 = new JButton("Button5");
  JButton jButton6 = new JButton("Button6");
  JToggleButton jToggleButton1 = new JToggleButton("ToggleButton");
  public ButtonJPanel4() {  inits();    }
  void inits() {
    this.setLayout(new GridBagLayout());//GridBagLayout
    this.add(jButton1,    new GridBagConstraints(0, 0, 2, 1, 0.5, 0.0
        ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    this.add(jButton2,      new GridBagConstraints(0, 1, 1, 2, 0.0, 0.7
        ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    this.add(jButton4,  new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
        ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    this.add(jButton3,  new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
        ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    this.add(jButton5,         new GridBagConstraints(3, 0, 1, 3, 0.5, 0.0
        ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    this.add(jButton6,      new GridBagConstraints(0, 3, 1, 1, 0.0, 0.2
        ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    this.add(jToggleButton1,     new GridBagConstraints(1, 4, 3, 1, 0.0, 0.1
        ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
  }
}
