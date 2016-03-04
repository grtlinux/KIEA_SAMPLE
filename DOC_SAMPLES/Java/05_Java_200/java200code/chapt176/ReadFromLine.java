import java.io.*;
public class ReadFromLine {

	public static void main(String[] args) {
		try{
			InputStreamReader isr=new InputStreamReader(System.in);
			LineNumberReader br=new LineNumberReader(isr);
			StringBuffer sb=new StringBuffer();
			String s="";
			while((s=br.readLine())!=null){//ctrl+c
			     sb.append( br.getLineNumber()+" "+s+"\n");
			}
			System.out.println(sb.toString());
			br.close();
			isr.close();
		}catch(Exception ee){
			System.out.println(ee.toString());
		}
	}
}
