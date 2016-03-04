import java.io.*;
public class ReadAndWriteFromFileMain 
{
	public static void main(String[] args) 
	{
		String fname="aaa.txt";
		ReadAndWriteFromFile baw=new ReadAndWriteFromFile();
		try{
			baw.readnwrite(fname,false);//clear
			//baw.readnwrite("aaa.txt",true);//append
			baw.readFile(fname);
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
}
