import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import javax.swing.border.*;
public class ChatClientWindow extends JWindow {
  private static final long serialVersionUID=122454214237L;//JAVA5
  JPanel jPanel1 = new JPanel();
  ImageIcon icon;
  BorderLayout borderLayout1 = new BorderLayout();
  JLabel jLabel1 = new JLabel();
  JProgressBar jProgressBar1 = new JProgressBar();
  public ChatClientWindow() {
      inits();
  }
  public void setTime(){
    this.setVisible(true);
    try {
      Thread.sleep(3000);//3초간 보여준다.
    }
    catch (InterruptedException ex) {
    }
    this.setVisible(false);
    this.dispose();
  }
  private void inits()  {
    String imageNums="image/image.jpg";
    icon = new ImageIcon(ChatClientWindow.class.getResource(imageNums));
    jPanel1.setLayout(new BorderLayout());
    jLabel1.setIcon(icon);
    jProgressBar1.setBorder(BorderFactory.createLineBorder(Color.black));
    jProgressBar1.setPreferredSize(new Dimension(300, 20));
    jProgressBar1.setStringPainted(true);
    this.getContentPane().add(jPanel1,  BorderLayout.CENTER);
    jPanel1.add(jLabel1, BorderLayout.CENTER);
    jPanel1.add(jProgressBar1,  BorderLayout.SOUTH);
    this.setSize(300,202+20);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = this.getSize();
    this.setLocation((screenSize.width - frameSize.width) / 2,
          (screenSize.height - frameSize.height) / 2);
    MyThread thread =new MyThread();
    thread.start();
    setTime();
  }
  public class MyThread extends Thread{//Nested class
    int i=0;
    public void run(){
      while(true){
        if(i<101){
          jProgressBar1.setValue(i); //
          i += 5;
        }
        try {
          Thread.sleep(3000 / 20);
          if (i > 100) {
            break;
          }
        }catch (InterruptedException ite) {}
      }//while
    }//run
  }///thread
}
