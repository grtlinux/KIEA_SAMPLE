import java.io.IOException;
public class  SystemInputTest1
{
	public static void main(String[] args) {
		char cin='/';
		try{
			cin=(char)System.in.read();  // read -> return int 
			System.out.println(cin);
			System.out.println((int)cin);  // A 65,  a 97  
		}catch(IOException e){
			System.out.println(e.getMessage()); 
		}
	}
}
