import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class ChatClientFrame extends StartingJFrame {
  private static final long serialVersionUID=122454214237L;//JAVA5
  JPanel contentPane;
  ChatClientJPanel cp;
  BorderLayout borderLayout1 = new BorderLayout();
  String ip="";
  int port =0;
  public ChatClientFrame() {
	  super();
	  this.setVisible(false);
	  this.validate();
      inits();
  }
  private void inits() {
    ChatClientWindow win=new ChatClientWindow();;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(new Dimension(600, 500));
    this.setTitle("Chat Client Frame");
    cp=new ChatClientJPanel(this);
	setMainJpanel(cp);
	this.setVisible(true);
	this.validate();
    this.addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent e){
          cp.disConnection();
          System.exit(0);
        }});
    }
}
