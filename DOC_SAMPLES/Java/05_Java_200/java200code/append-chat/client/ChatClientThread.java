import    java.io.*;
import    java.util.*;
import javax.swing.*;
public class ChatClientThread implements Runnable{
  ObjectInputStream  ois;
  ChatClientJPanel cc;
  Message mess;
  boolean exit=false;
  DefaultListModel model;
  public ChatClientThread(ObjectInputStream ois,ChatClientJPanel cc){
   this.ois=ois;
   this.cc=cc;
   model=new DefaultListModel();
   model.removeAllElements();
   cc.getUserList().setModel(model);
  }
  public void run(){
    while(!exit){
       try{
         mess=(Message)ois.readObject();
       }catch(Exception e){
         System.out.println(this.getClass().toString()+" IOEXception");
       }
       int state=mess.getState();
       String name=mess.getName();
       if(state==Message.INITCONTACT){
          model.removeAllElements();
          Vector userName=mess.getUserName();
          cc.getUserList().removeAll();
          for(int i=0;i<userName.size();i++){
            model.addElement((String)userName.elementAt(i));
            cc.getUserList().setModel(model);
          }
          cc.getUserCountLb().setText("Current "+model.size()+" Connects.");
       }else if(state==Message.END){
          cc.getUserList().removeAll();
          model.removeElement(name);
          cc.getUserList().setModel(model);
          cc.getUserCountLb().setText("Current "+model.size()+" Connects.");
        }
        cc.getChatTextArea().append("["+name+"]"+mess.getMessage()+"\n");
      }
      try{
          ois.close();
      }catch(IOException e){
        System.out.println(this.getClass().toString()+" Run Exception");
      }
    }
}






