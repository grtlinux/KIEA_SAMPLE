import java.io.*;
import java.util.*;
public class LottoTextRead {

	public int position(String s1,String s2){
		return s1.indexOf(s2); 
	}//
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
	}//
	
	public String readTexts(String fname){
		FileReader fr=null;
		BufferedReader br=null;
		InputStreamReader isr=null;
		File ff=null;
		
		StringBuffer bf=new StringBuffer();
		try{
			if(isExist(fname)){
				ff=new File(fname);
				fr=new FileReader(ff);
				br=new BufferedReader(fr);
			}
			String msg="";
			while((msg=br.readLine())!=null){
				bf.append(msg+"\n");
				//bf.append(msg.replace('[',' ').trim()+"\n");
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
		return bf.toString();
	}//
	public int readText(String str){
		int num=0;
		str=str.replace('[',' ').trim();
		StringTokenizer st=new StringTokenizer(str,"|]");
		String msg=st.nextToken().trim();
		try{
			num=Integer.parseInt(msg.trim());
		}catch(Exception ee){
			log(ee.getMessage());
		}
		return num;
	}//
	public String readLineAt(String fname,int at){
		FileReader fr=null;
		BufferedReader br=null;
		InputStreamReader isr=null;
		File ff=null;
		String rmsg="";
		StringBuffer bf=new StringBuffer();
		try{
			if(isExist(fname)){
				ff=new File(fname);
				fr=new FileReader(ff);
				br=new BufferedReader(fr);
			}
			String msg="";
			while((msg=br.readLine())!=null){
				if(this.position(msg,at+"|]")!=-1
				&& readText(msg)==at){
					StringTokenizer st=new StringTokenizer(msg,"|]");
					st.nextToken();
					if(st.hasMoreTokens()){
						rmsg=st.nextToken();
						break;
					}else{
						rmsg="[||]";
					}
				}
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
		return rmsg;
	}//
	public int readLineAt(String fname,String at){
		FileReader fr=null;
		BufferedReader br=null;
		InputStreamReader isr=null;
		File ff=null;
		int line=0;
		//String rmsg="";
		StringBuffer bf=new StringBuffer();
		try{
			if(isExist(fname)){
				ff=new File(fname);
				fr=new FileReader(ff);
				br=new BufferedReader(fr);
			}
			String msg="";
			while((msg=br.readLine())!=null){
				if(this.position(msg,at)!=-1){
					StringTokenizer st=new StringTokenizer(msg,"|]");
					if(st.hasMoreTokens()){
						try{
							String temp=(st.nextToken().trim()).replace('[',' ').trim();
							line=Integer.parseInt(temp);
							break;
						}catch(Exception es){
							throw new Exception(es.getMessage());
						}
					}
				}
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
		return line;
	}//
	
	private int strLength(String str){
		return str.length();
	}//

	
	private static void log(String msg){
		System.out.println("Error-->:"+msg);
	}//
	
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
	}//
	public int howManyToken(String str,String dele){
		int temp=0;
		StringTokenizer st=new StringTokenizer(str,dele);
		temp=st.countTokens();
		return temp;
	}//
	public String[] getValues(String str,String dele)
	throws Exception{
		
		int tokenNum=howManyToken(str,dele);
		if(tokenNum<=0){
			throw new Exception("토큰이 없다.");
		}
		StringTokenizer st=new StringTokenizer(str,dele);
		String [] ss=new String[tokenNum];
		int iter=ss.length;
		for(int i=0;i<iter;i++){
			ss[i]=st.nextToken();
		}
		return ss;
	}//
	public String getValue(String str,String dele)
	throws Exception{
	
		int tokenNum=howManyToken(str,dele);
		if(tokenNum<=0){
			throw new Exception("토큰이 없다.");
		}
		StringTokenizer st=new StringTokenizer(str,dele);
		String  ss=st.nextToken().trim();
		return ss;
	}//
	public String getLottoData(String str,String dele){
		String [] ss=null;
		String [] sts=null;
		try{
			ss=getValues(str,dele);
			sts=getValues(ss[2],"<");
		}catch(Exception ee){
			System.out.println(ee.getMessage());
		}
		return sts[0];
	}//
	public String [] resultValues(String fname,int at){
		String [] ss=new String[4];
		for(int i=0;i<4;i++){
			String temp=readLineAt(fname,(at+i));
			ss[i]=getLottoData(temp,">");
		}
		return ss;
		
	}//
	public LottoVO getLottoVO(String [] aa)
	throws Exception{
		LottoVO vo=null;
		 int count=0;
		 String day="";
		 int []num=new int[6];
		 int num7=0;
		 try{
			StringTokenizer st=new StringTokenizer(aa[2],".");
			for(int i=0;st.hasMoreTokens();i++){
				num[i]=Integer.parseInt(st.nextToken().trim());
			}
			
			count=Integer.parseInt(aa[0].trim());
			day=aa[1];
			num7=Integer.parseInt(aa[3].trim());
			vo=new LottoVO();
			vo.setCount(count);
			vo.setDay(day);
			vo.setNum1(num[0]);
			vo.setNum2(num[1]);
			vo.setNum3(num[2]);
			vo.setNum4(num[3]);
			vo.setNum5(num[4]);
			vo.setNum6(num[5]);
			vo.setNum7(num7);
		 }catch(Exception ee){
		 	throw new Exception("타입이 이상");
		 }
		return vo;
	}//
	
	public static void print(String [] aa){
		int count=aa.length;
		for(int i=0;i<count;i++){
			System.out.print(aa[i]+" : ");
		}
		System.out.println();
	}
	
}
