import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
public class ChattingEnteranceDialog extends JDialog  implements ActionListener{
  private static final long serialVersionUID=122454214237L;//JAVA5
  JPanel panel1 = new JPanel();
  JPanel jPanel1 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JLabel jLabel2 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JButton jButton1 = new JButton();
  JLabel jLabel3 = new JLabel();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  JFrame jframe;
  JPanel jpanel;
  JLabel jLabel4 = new JLabel();
  JTextField jTextField3 = new JTextField();
  public ChattingEnteranceDialog(JFrame jframe, JPanel jpanel,
	             String title, boolean modal) {
    super(jframe, title, modal);
    this.jframe=jframe;
    this.jpanel=jpanel;
    inits();
    pack();
  }
  public ChattingEnteranceDialog() {
    this(null, null,"", false);
  }
  private void inits()  {
    panel1.setLayout(new BorderLayout());
    jPanel1.setLayout(new GridBagLayout());
    jLabel1.setText("Server IP");
    jLabel2.setText("Server port");
    jTextField2.setPreferredSize(new Dimension(100, 21));
    jTextField2.setText("5200");
    jTextField1.setPreferredSize(new Dimension(100, 21));
    jTextField1.setText("127.0.0.1");
    jButton1.setText("Connection");
    jButton1.addActionListener(this);
    jLabel3.setBorder(new TitledBorder(""));
    jLabel3.setText("Welcome to the HYO Chatting!");
    jPanel1.setBorder(BorderFactory.createRaisedBevelBorder());
    this.setDefaultCloseOperation(3);//EXIT_ON_CLOSE
    this.setModal(true);
    this.setResizable(false);
    jLabel4.setText("UserID");
    jTextField3.setText("");
    jTextField3.setPreferredSize(new Dimension(100, 21));
    getContentPane().add(panel1);
    panel1.add(jPanel1,  BorderLayout.CENTER);
    jPanel1.add(jLabel1,       new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER,
            GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
    jPanel1.add(jTextField1,   new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jLabel2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
            new Insets(5, 5, 5, 5), 0, 0));
    jPanel1.add(jTextField2,  new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton1,   new GridBagConstraints(0, 4, 3, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
            new Insets(0, 5, 5, 5), 0, 0));
    jPanel1.add(jLabel3,    new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jLabel4, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jTextField3, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(0, 0, 0, 0), 0, 0));
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension frameSize = this.getSize();
     this.setLocation((screenSize.width - frameSize.width) / 2,
             (screenSize.height - frameSize.height) / 2);
  }
  public void actionPerformed(ActionEvent e) {
    try{
      int port =Integer.parseInt(this.jTextField2.getText());
      ((ChatClientJPanel)jpanel).setIpPort(
                  jTextField1.getText(),port,jTextField3.getText());
      this.setVisible(false);
      this.dispose();
    }catch(Exception ee){
      javax.swing.JOptionPane.showMessageDialog(jframe,"타입이 잘못입력됨");
    }
  }
}
