import static java.lang.Integer.*;
import static java.lang.Double.*;
public class DeciToBin2Main
{
	public static void main(String[] args) 
	{

		int a=123;
		String lineNum="01234567890123456789012345678901";
		System.out.printf("                 %s%n",lineNum);
		System.out.printf("123 toBinary   : %s%n",toBinaryString(a));
		for(int i=0;i<33;i++){
			System.out.printf("%2d:right rotate: %s%n",i,
			                   toBinaryString(rotateRight(a,i)));
		}
		for(int i=0;i<33;i++){
			System.out.printf("%2d:left  rotate: %s%n",i,
			                   toBinaryString(rotateLeft(a,i)));
		}
		System.out.printf("%s%n",lineNum);
		
		System.out.printf("%s%n%s%n",toBinaryString(a),toBinaryString(reverseBytes(a)));
		
	}
}