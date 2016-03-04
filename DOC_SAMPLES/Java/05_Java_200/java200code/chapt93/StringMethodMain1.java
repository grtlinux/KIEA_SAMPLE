public class StringMethodMain1 {

	public static void main(String[] args) {
		//1 String immutable
		String st="hello";
		System.out.println(st+st.hashCode());
		st.replace('h','H');  //immutable hello
		System.out.println(st+st.hashCode());
		st=st.replace('h','H');//st에 할당을 받아야 한다. 
		System.out.println(st+st.hashCode());
		//2 String의 위치  method index는 0부터
		String test1="Hello my nick name is totoro.\n";
		       test1+="I,m 29 years old.\n";
		       test1+="I'm a good boy.";
		System.out.println("이글의 길이 : "+test1.length());
		//System.out.println("첫 번째 .위치  : "+test1.indexOf('H'));//-1이면 없다.
		System.out.println("첫 번째 .위치  : "+test1.indexOf('.'));//-1이면 없다.
		System.out.println("마지막째 .위치  : "+test1.lastIndexOf('.'));
		System.out.println("첫 번째 years위치  : "+test1.indexOf("years"));
		//3 위치에 있는 한글자씩  부르기
		for(int i=0;i<test1.length();i++){
			System.out.print(test1.charAt(i));
		}
		//4 원하는 문자열  배열 
		String [] a =test1.split("\n");
		System.out.println(a.length);
		System.out.println(a[0]);
		//5 문자열 나누기 index는 0부터 시작한다. 
		String b=test1.substring(4,8);
		String c=test1.substring(0,5);
		String bb=test1.substring(4);
		System.out.println(b); // 4번째 문자부터 (8-1)번째 문자까지
		System.out.println(c); // 0번째 문자부터 (5-1)번째 문자까지
		System.out.println(bb); //0번째 문자부터 (4-1)번째 문자까지
	}
}
