import java.util.Scanner;
public class Scanner2Chapt39 {

	public static void main(String[] args) {
		
		String str=readString();
		char [] charStr=str.toCharArray();
		int count=charStr.length;
		System.out.println("글자수:  "+count);
		for(int i=0;i<count;i++){
			System.out.print(charStr[i]+" : ");
		}
		System.out.println();
		int num=readInt();  //숫자를 입력
		System.out.println("입력된 수 : "+num);
	}
	public static int readInt(){
		Scanner input=new Scanner(System.in);
		return input.nextInt();
	}
	public static double readDouble(){
		Scanner input=new Scanner(System.in);
		return input.nextDouble();
	}
	public static String readString(){
		Scanner input=new Scanner(System.in);
		return input.nextLine();
	}
}
