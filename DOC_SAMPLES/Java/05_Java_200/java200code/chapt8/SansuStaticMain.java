public class SansuStaticMain {
	
	public final static int MEN   = 3;
	public final static int WOMEN = 4;

	public static void main(String[] args) {
		int people1=WOMEN;
		switch(people1){
			case 3: System.out.println("남자");break;
			case 4: System.out.println("여자");break;
		}
		//int SansuStatic.WOMAN=4; 상수에 assign 못함
		int people2=SansuStatic.WOMAN;
		switch(people2){
			case 1: System.out.println("남자");break;
			case 2: System.out.println("여자");break;
		}
	}
}
