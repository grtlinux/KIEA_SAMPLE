import java.io.*;
import java.util.*;
public class ResultWrite {

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
	private static void log(String msg){
		System.out.println("Error-->:"+msg);
	}
	
	public void setTexts(String fname,String msg){
		FileWriter fw=null;
		PrintWriter pw=null;
		File ff=null;
		try{
			ff=new File(fname);
			fw=new FileWriter(ff,false);
			pw=new PrintWriter(fw,true);
			pw.write(msg);
		}catch(Exception ee){
			log(ee.getMessage());
		}finally{
			try{
				pw.close();
				fw.close();
			}catch(Exception eee){
				log(eee.getMessage());
			}
		}
	}

}
