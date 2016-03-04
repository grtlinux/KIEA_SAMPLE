import java.net.*;
import java.io.*;

public class LottoHttp {
	//국민은행에 붙는 소스는 매우 길다. 그래서 
	//public static String urls="http://lotto.mygo.co.kr/index.php?cmd=sub_product1&code=1";
	public static String urls="http://www.lotto645.com/Modules/lotto645/LottoWinList.jsp";
	
	public int whereLine(String ss){
		int count=0;
		String s="";
		//InputStreamReader isr=null;
		BufferedReader br=null;
		//StringBuffer sb=new StringBuffer();
		try{
			//isr=new InputStreamReader(conn.getInputStream());
			//br=new BufferedReader(isr);
			br=getReadLotto(urls);
			while((s=br.readLine())!=null){
				if((s.trim()).equals(ss)){
					break;
				}
				//sb.append(s.trim()+"\n");
				count++;
			}
			//s=sb.toString();
		}catch(Exception ee){
			System.out.println(ee.getMessage());
		}
		return count;
	}
	public String whereAt(int num){
		
		String s="";
		//InputStreamReader isr=null;
		BufferedReader br=null;
		//StringBuffer sb=new StringBuffer();
		try{
			//isr=new InputStreamReader(conn.getInputStream());
			//br=new BufferedReader(isr);
			br=getReadLotto(urls);
			for(int i=0;i<num;i++){
				br.readLine();
			}
			s=br.readLine();
			//s=sb.toString();
		}catch(Exception ee){
			System.out.println(ee.getMessage());
		}
		return s.trim();
	}
	
	public String doGet(){
		//URL url=null;
		String s="";
		try{
			//url=new URL(urls);
			//URLConnection conn=url.openConnection();
			//InputStreamReader isr=new InputStreamReader(conn.getInputStream());
			s=this.getHTTP(urls);
		}catch(Exception ee){
			System.out.println(ee.getMessage());
		}
		return s;
	}
	//public String getHTTP(URLConnection conn){
	public String getHTTP(String ss){
		String s="";
		int count=0;
		//InputStreamReader isr=null;
		BufferedReader br=null;
		StringBuffer sb=new StringBuffer();
		try{
			//isr=new InputStreamReader(conn.getInputStream());
			//br=new BufferedReader(isr);
			br=getReadLotto(ss);
			while((s=br.readLine())!=null){
				sb.append(count+++"|]"+s.trim()+"\n");
			}
			br.close();
			s=sb.toString();
		}catch(Exception ee){
			System.out.println(ee.getMessage());
		}
		return s;
	}
	public BufferedReader getReadLotto(String s){
		URL url=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		try{
			url=new URL(s);
			URLConnection conn=url.openConnection();
			isr=new InputStreamReader(conn.getInputStream());
			br=new BufferedReader(isr);
		}catch(Exception ee){
			System.out.println(ee.getMessage());
		}
		return br;
	}
}


LottoHttp