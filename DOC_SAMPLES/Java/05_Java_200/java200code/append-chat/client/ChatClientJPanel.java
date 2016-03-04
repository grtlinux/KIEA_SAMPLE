import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.net.*;
import java.awt.event.*;
import javax.swing.event.*;
public class ChatClientJPanel extends JPanel implements javax.swing.event.ListSelectionListener{
  private static final long serialVersionUID=122454214237L;//JAVA5
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel np = new JPanel();
  JPanel cp = new JPanel();
  JPanel ep = new JPanel();
  JPanel sp = new JPanel();
  JLabel stateLb = new JLabel();
  JPanel cnp = new JPanel();
  JLabel chatLb = new JLabel("Chatting Message", JLabel.CENTER);
  JLabel userLb = new JLabel("User Informations", JLabel.CENTER);
  JPanel ccp = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea chatTextArea = new JTextArea();
  JPanel ecp = new JPanel();
  JPanel esp = new JPanel();
  JScrollPane jScrollPane2 = new JScrollPane();
  JList userList = new JList();
  JPanel essp = new JPanel();
  JLabel userCountLb = new JLabel();
  JLabel whoContacLb = new JLabel();
  JTextField sendTf = new JTextField(40);
  JButton sendButton = new JButton();
  Socket socket;
  ObjectInputStream     ois;
  ObjectOutputStream    oos;
  Thread            t;
  ChatClientThread cct;
  String name;
  String ip;
  int port;
  JFrame jframe;
  public JList getUserList(){
    return userList;
  }
  public JLabel getUserCountLb(){
    return userCountLb;
  }
  public JTextArea getChatTextArea(){
    return chatTextArea;
  }
  public ChatClientJPanel(JFrame jframe) {
    this.jframe=jframe;
    inits();
  }
  private void inits()  {
    this.setLayout(new BorderLayout());
    np.setLayout(new BorderLayout());
    stateLb.setFont(new java.awt.Font("Arial", 1, 15));
    stateLb.setForeground(Color.black);
    stateLb.setBorder(new TitledBorder(""));
    stateLb.setText("Connect to the Server-   User ID : ");
    cnp.setLayout(new BorderLayout());
    cp.setLayout(new BorderLayout());
    chatLb.setFont(new java.awt.Font("Dialog", 1, 20));
    chatLb.setAlignmentY((float) 0.5);
    chatLb.setBorder(new TitledBorder(""));
    ep.setLayout(new BorderLayout());
    userLb.setAlignmentY((float) 0.5);
    userLb.setBorder(new TitledBorder(""));
    userLb.setFont(new java.awt.Font("Dialog", 1, 20));
    ccp.setLayout(new BorderLayout());
    chatTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
    chatTextArea.setEditable(false);
    chatTextArea.setText("");
    jScrollPane1.setForeground(Color.black);
    jScrollPane1.setBorder(new TitledBorder(""));
    ecp.setLayout(new BorderLayout());
    esp.setLayout(new BorderLayout());
    essp.setLayout(new BorderLayout());
    userCountLb.setFont(new java.awt.Font("Arial", 1, 13));
    userCountLb.setText("Current User Counts: 0                 ");
    whoContacLb.setFont(new java.awt.Font("Arial", 1, 13));
    whoContacLb.setPreferredSize(new Dimension(69, 30));
    whoContacLb.setText("No Select");
    sendTf.setBorder(BorderFactory.createLineBorder(Color.black));
    sendTf.setToolTipText("Please input message");
    sendTf.setText("");
    sendButton.setText("Send Message");
    ep.setPreferredSize(new Dimension(258, 196));
    ecp.setBorder(new TitledBorder(""));
    ecp.setPreferredSize(new Dimension(200, 100));
    sp.setBorder(BorderFactory.createEtchedBorder());
    esp.setBorder(new TitledBorder(""));
    ccp.setBorder(new TitledBorder(""));
    userList.setBorder(BorderFactory.createLineBorder(Color.black));
    userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    cp.add(cnp,  BorderLayout.NORTH);
    cnp.add(chatLb,  BorderLayout.NORTH);
    cp.add(ccp, BorderLayout.CENTER);
    ep.add(userLb, BorderLayout.NORTH);
    ep.add(ecp,  BorderLayout.CENTER);
    ep.add(esp,  BorderLayout.SOUTH);
    sp.add(sendTf, null);
    np.add(stateLb,  BorderLayout.CENTER);
    ccp.add(jScrollPane1,  BorderLayout.CENTER);
    jScrollPane1.getViewport().add(chatTextArea, null);
    ecp.add(jScrollPane2,  BorderLayout.CENTER);
    jScrollPane2.getViewport().add(userList, null);
    esp.add(essp, BorderLayout.WEST);
    essp.add(userCountLb,  BorderLayout.SOUTH);
    essp.add(whoContacLb, BorderLayout.NORTH);
    sp.add(sendButton, null);
	userList.addListSelectionListener(this);
    sendButton.addActionListener(new Handler());
    sendTf.addActionListener(new Handler());
    setServerIP();
    this.add(np, BorderLayout.NORTH);
    this.add(cp,  BorderLayout.CENTER);
    this.add(ep,  BorderLayout.EAST);
    this.add(sp,  BorderLayout.SOUTH);
  }
  //서버쪽과 연결하는 메서드
   public void connection(String serverName,int port) throws IOException{
     socket = new Socket(serverName, port);
     System.out.println("Connect to "+serverName);

     oos = new ObjectOutputStream(socket.getOutputStream());
     ois = new ObjectInputStream(socket.getInputStream());

     System.out.println("Server Ready : Writing");
     Message mess = new Message(name, "님이 접속하셨습니다.", Message.INITCONTACT);
     oos.writeObject(mess);

     System.out.println("Server Ready : Reading");
     cct = new ChatClientThread(ois, this);
     t = new Thread(cct);
     t.start();
   }
   public void disConnection() {
     try{
       Message d=new Message(name,"님이 접속종료.",Message.END);
       oos.writeObject(d);
      }catch(IOException e){}
      t=null;
  }
  public void setIpPort(String ip, int port,String name){
    this.ip = ip;
    this.port = port;
    this.name=name;
  }
  public void setServerIP(){
    ChattingEnteranceDialog di=
    new ChattingEnteranceDialog(jframe,this,"Welcom Dialog", true);
    di.setVisible(true);
    try{
       connection(ip,5200);
     }catch(IOException e1){
       System.out.println("connection 중 Exception이 발생했습니다.");
    }
     this.stateLb.setText("Connection to "+ip+" ,  UserID: "+name);
  }
  public class Handler implements ActionListener{
       public void actionPerformed(ActionEvent e){
         if (e.getSource() == sendButton || e.getSource() == sendTf) {
           try {
             oos.writeObject(new Message(name, sendTf.getText(), Message.PROGRESS));
           }
           catch (IOException e2) {
             System.out.println("Communication IOEXception Occurs.");
           }
           sendTf.setText("");
         } //if
      }//actionperformed
  }
 public  void valueChanged(ListSelectionEvent e) {
     whoContacLb.setText((String)userList.getSelectedValue());
  }
}