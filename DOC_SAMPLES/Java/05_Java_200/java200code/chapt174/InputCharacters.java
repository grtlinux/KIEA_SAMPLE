import java.io.*;
public class InputCharacters {

	public static void main(String[] args) {
		try{
			InputStreamReader in=new InputStreamReader(System.in);
			StringBuffer sb=new StringBuffer();
			StringBuffer sb2=new StringBuffer();
			int ch;
	        while ((ch = in.read()) != -1) {
	          if (ch != '\n') {//한줄단위로 받기 위해서
	        	  sb.append((char)ch);
	          } else {//여러줄을 붙이기 위해서
	          	sb2.append(sb.toString()+"\n");
	          	sb = new StringBuffer();//
	          }
	        }
			System.out.println(sb2.toString());
			in.close();
		}catch(Exception ee){
			System.out.println(ee.toString());
		}
	}
}
