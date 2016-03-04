import java.io.*;
import java.util.*;
public class KisulFileReader {
	
	private Vector list=new Vector(10,10);
	
	public boolean isExist(String fname){
		boolean isE=false;
		File f=null;
		try{
			f=new File(fname);
			if(f.exists()){
				isE=true;
			}
		}catch(Exception ee){
			log(ee.getMessage());
		}
		return isE;
	}
	public int listSize(){
		return list.size();
	}
	public void readTexts(String fname){
		list.removeAllElements();
		FileReader fr=null;
		BufferedReader br=null;
		InputStreamReader isr=null;
		File ff=null;
		try{
			if(isExist(fname)){
				ff=new File(fname);
				fr=new FileReader(ff);
				br=new BufferedReader(fr);
			}
			String msg="";
			while((msg=br.readLine())!=null){
				list.addElement(msg);
			}
				
		}catch(Exception ee){
			log(ee.getMessage());
		}finally{
			try{
				br.close();
				fr.close();
			}catch(Exception eee){
				log(eee.getMessage());
			}
		}
	}
	public String readText(String fname){
		String msg="";
		FileReader fr=null;
		BufferedReader br=null;
		InputStreamReader isr=null;
		File ff=null;
		try{
			if(isExist(fname)){
				ff=new File(fname);
				fr=new FileReader(ff);
				br=new BufferedReader(fr);
			}
			msg=br.readLine();
			//while((msg=br.readLine())!=null){
				//msg+="\n";
			//}
			
		}catch(Exception ee){
			log(ee.getMessage());
		}finally{
			try{
				br.close();
				fr.close();
			}catch(Exception eee){
				log(eee.getMessage());
			}
		}
		return msg;
	}
	private int strLength(String str){
		return str.length();
	}
	public String getText(int i){
		if(i+1>list.size()){
		 return "";
		}
		return (String)list.elementAt(i);
	}
	
	private static void log(String msg){
		System.out.println("Error-->:"+msg);
	}
	public Vector getAllLines(){
		return list;
	}
}
