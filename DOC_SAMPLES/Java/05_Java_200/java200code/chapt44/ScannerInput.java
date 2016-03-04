import java.util.Scanner;
public class ScannerInput {
	final static String help1=
		    "Please Input int type One number :";
	public static int readInt(){
		
		System.out.print(help1);
		Scanner input=new Scanner(System.in);
		return input.nextInt();
	}
}
