public class  EscapeTest
{
	public static void main(String[] args) 
	{
		System.out.printf("0        1         2         \n");
		System.out.printf("123456789012345678901234567890\n");
		System.out.printf("\t!\t!\n"); //8Ä­ Á¡ÇÁ
		System.out.printf("\t!\t!"); //8Ä­ Á¡ÇÁ
		System.out.printf("1234\b\b?"); //1Ä­ ¹é
		System.out.printf("\n123456789\r?"); //¸Ç¾Õ
		System.out.printf("%n"); //\n  Java5
		System.out.printf("%%"); //%   Java5
		System.out.printf("\n\'¾È³çÇÏ¼¼¿ä?\'"); //'
		System.out.printf("\n\"¾È³çÇÏ¼¼¿ä?\""); //'
		System.out.printf("\n\\¾È³çÇÏ¼¼¿ä?\\"); //\
	}
}
