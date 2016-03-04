import static java.lang.Integer.*;
import static java.lang.Double.*;
public class DeciToBinMain
{
	public static void main(String[] args) 
	{
		int a=69;
		//Integer.Integer
		System.out.println("1 : 69 to 2柳过: "+Integer.toBinaryString(a));
		System.out.println("2 : 69 to 2柳过: "+(Integer.toBinaryString(-a)));

        //Integer.toOctalString(int i) 
		System.out.println("3 : 69 to 8柳过: "+(Integer.toOctalString(a)));
		System.out.println("4 : 69 to 8柳过: "+(Integer.toOctalString(-a)));

		//Integer.toHexString(int i) 
		System.out.println("5 : 69 to 16柳过: "+(Integer.toHexString(a)));
		System.out.println("6 : 69 to 16柳过: "+(Integer.toHexString(-a)));

		//Double.toHexString(double i) 
		System.out.println("7 : 65.65 to 16柳过: "+(Double.toHexString(65.65)));
		System.out.println("8 : 65.65 to 16柳过: "+(Double.toHexString(-65.65)));
		//JAVA5
		Integer ide=Integer.decode("1");
		System.out.println("9 : "+ide.intValue());
		System.out.println("10: 69 reverse   "+Integer.reverseBytes(a));
		
		System.out.printf("                 01234567890123456789012345678901%n");
		System.out.printf("11:binary      : %s%n",(toBinaryString(a)));
		System.out.printf("12:reverse     : %s%n",toBinaryString(reverseBytes(a)));
		System.out.printf("13:left rotate : %s%n",toBinaryString(rotateLeft(a,3)));
		System.out.printf("14:right rotate: %s%n",
			                                      toBinaryString(rotateRight(a,3)));
	}
}
