import  java.io.*;
import  java.net.*;
import  java.util.*;
public class ChatServer{
   Vector		buffer=new Vector(5,3);
   //Vector<Message> buffer=new Vector<Message>(5,3);
   ServerSocket			serverSocket;
   Socket				socket;
   ObjectInputStream	ois;
   ObjectOutputStream   oos;
   public void service(){
	   System.out.println("start Server service...");
     try{
        System.out.println("접속 준비중");
        serverSocket=new ServerSocket(5200);
		serverSocket.getReuseAddress() ;//ServerSocket port 바로 다시사용
        }catch(IOException e){
        System.out.println("서비스 준비중에 IOException 발생.");
     }
    while(true){
      try{
           socket=serverSocket.accept();
           System.out.println(socket.getInetAddress()+"가 붙었습니다.");
           ois=new ObjectInputStream(socket.getInputStream());
           oos=new ObjectOutputStream(socket.getOutputStream());
           String ip=socket.getInetAddress().toString();
           Thread t=new Thread(new ChatServerThread(buffer,ois,oos));
           t.start();
         }catch(IOException e){
           System.out.println("IOException이 발생했습니다.");
      }
    }
  }
}

