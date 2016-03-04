import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
public class TextAreaJPanel3 extends JPanel {
  private static final long serialVersionUID=1224564214537L;//JAVA5
  JPanel northp = new JPanel();
  JPanel eastp = new JPanel();
  JPanel cp = new JPanel();
  JPanel ecp = new JPanel();
  JLabel titlelb = new JLabel("",JLabel.CENTER);
  JLabel jLabel1 = new JLabel("",JLabel.CENTER);
  JButton jButton3 = new JButton("Add");
  JButton jButton4 = new JButton("Copy");
  JButton jButton2 = new JButton("Clear");
  JButton jButton1 = new JButton("Label Change");
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jTextArea1 = new JTextArea(10,60);
  JTextField jTextField1 = new JTextField(60);
  public TextAreaJPanel3() {//성성자
      inits();
  }
  public void inits(){
    this.setLayout(new BorderLayout());
    this.add(northp, BorderLayout.NORTH);
    this.add(eastp,  BorderLayout.EAST);
    this.add(cp,  BorderLayout.CENTER);
    northp.setLayout(new BorderLayout());
    eastp.setLayout(new BorderLayout());
    cp.setLayout(new BorderLayout());
    cp.setBorder(new TitledBorder(""));
    eastp.setBorder(new TitledBorder(""));
    jLabel1.setFont(new java.awt.Font("Arial", 1, 15));
    jLabel1.setToolTipText("JLabel Tooltip tests");
    jLabel1.setText("Input Texts");
    titlelb.setFont(new java.awt.Font("Arial", Font.BOLD, 20));
    titlelb.setBorder(new TitledBorder("Title"));
    titlelb.setText("TextArea Tests");
    northp.add(titlelb);
    eastp.add(ecp,  BorderLayout.CENTER);
    ecp.setPreferredSize(new Dimension(100, 35));
    ecp.add(jButton3 );
    ecp.add(jButton4, null);//FlowLayout
    ecp.add(jButton2);//FlowLayout
    ecp.add(jButton1);//FlowLayout
    cp.add(jScrollPane1,  "Center");
    cp.add(jTextField1,  "South");
    cp.add(jLabel1, "North");
    jScrollPane1.getViewport().add(jTextArea1);
    jTextArea1.setWrapStyleWord(true);
    jTextArea1.setLineWrap(true);
    this.jTextArea1.setCaretColor(new Color(255,0,0));
	this.jTextArea1.setSelectedTextColor(new Color(0,0,255));
    addListener();//
  }
  public void addListener(){//리슨너 달기
    jButton3.addActionListener(new MyEventHandling());//ActionListener
    jButton4.addActionListener(new MyEventHandling());//ActionListener
	jButton2.addMouseListener(new MyMouseHandling());//MouseAdapter
    jTextField1.addKeyListener(new MyKeyHandling());//KeyAdapter
	jButton1.addMouseMotionListener(new MyMotionHandling());//MouseMotionAdapter
    jTextField1.addMouseMotionListener(new MyMotionHandling());//MouseMotionAdapter
    jTextArea1.addMouseMotionListener(new MyMotionHandling());//MouseMotionAdapter
  }
  public class MyEventHandling implements  ActionListener{
	public void actionPerformed(ActionEvent e){ //ActionListener
		if(e.getSource()==jButton3){
			 jLabel1.setText(jTextArea1.getSelectedText());
		}else if(e.getSource()==jButton4){
			 jTextArea1.append(jTextField1.getText()+"\n");
			 jTextField1.setText("");
		}
	}//actionPerformed
  }//MyEventHandling
  public class MyKeyHandling extends  KeyAdapter{
	public void keyPressed(KeyEvent e) {//KeyListener
		if(e.getSource()==jTextField1){
			if(KeyEvent.VK_ENTER==e.getKeyCode()){//10 enter
			  jTextArea1.append(jTextField1.getText()+"\n");
			  jTextField1.setText("");
			}
		}
    }//keyPressed
  }//MyKeyHandling
  public class MyMouseHandling extends  MouseAdapter{
	public void mouseClicked(MouseEvent e)  {//MouseListener
		if(e.getSource()==jButton2){
			    jTextArea1.setText("");
		}
	}//
  }//MyMouseHandling
  public class MyMotionHandling extends  MouseMotionAdapter{
	public void mouseDragged(MouseEvent e)  {//MouseMotionListener
		if(e.getSource()==jTextField1){
			jLabel1.setText(jTextField1.getSelectedText()+"\n");
		}
	}//
	public void mouseMoved(MouseEvent e)    {//MouseMotionListener
		if(e.getSource()==jTextArea1){
			jLabel1.setText("Number of Characters: "+jTextArea1.getCaretPosition());
		}else if(e.getSource()==jButton1){
			jLabel1.setText("(x, y)=("+e.getX()+", "+e.getY()+")");
		}
	}//
  }//MyMotionHandling
}//class 

