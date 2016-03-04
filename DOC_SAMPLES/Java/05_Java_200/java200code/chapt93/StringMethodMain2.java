public class StringMethodMain2 {

	public static void main(String[] args) {
		String b="Hello, I love you.";
		//6 대소문자로 
		String d=b.toUpperCase();
		System.out.println(d);
		String e=b.toLowerCase();
		System.out.println(e);
		//7 양쪽 공백제거
		System.out.println("  안녕하세요   ".trim());
		//8 String<--> int
		int istr=Integer.parseInt("37");
		String stri=String.valueOf(istr);
		String stri2=istr+"";
		//9 대소문 관계없이 비교하기
		System.out.println("hello".compareToIgnoreCase("Hello"));
		System.out.println("Hello".compareToIgnoreCase("hell"));
		//10 +과 같은 concatenation
		stri=stri.concat("hello");
		System.out.println(stri);
		//11 문자열 <-->char 배열
		char [] chs=b.toCharArray(); //문자열을 char 배열로
		String copys1=String.copyValueOf(chs);//char 배열을 문자열로
		String copys2=new String(chs);//char 배열을 문자열로
		for(char ch : chs){
			System.out.printf("%c ",ch);
		}
		//12 String format
		String ss=String.format("%s %d %c %f","Hellos", 78, 'c', 8.6);
		System.out.println(ss);
		//13 문자를 포함하고 있는가?
		System.out.println("hello".contains("el"));
	}
}
