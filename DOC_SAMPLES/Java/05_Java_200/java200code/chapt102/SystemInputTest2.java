import java.io.IOException;
public class  SystemInputTest2
{
	public static void main(String[] args) {
		char cin='0';
		try{
			while(cin!='/'){
				do{
					cin=(char)System.in.read();//read -> return int 
					System.out.print(cin);                         
				}while(cin=='\n' | cin=='\r');
			}
		}catch(IOException e){//에외처리 read 때문에
			System.out.println(e.getMessage()); 
		}
	}
}
