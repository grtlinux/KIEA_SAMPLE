public class SwitchTestChap34 {

	final static int valuA=1; 
	final static int valuB=2; 
	final static int valuC=3; 
	final static int valuD=4;  
	final static int valuE=5;  

	public static void main(String[] args) 
	{
		int a=valuC;
		int b=valuD;
		long c=5L; //switch case presision 에러
		switch(a){  //byte, shor, int, char 가능
			case 1 : System.out.println("1");break;
			case 2 : System.out.println("2");break;
			case 3 : System.out.println("3");break;
			case 4 : System.out.println("4");break;
			default : System.out.println("5");break;
		}
		switch(b){ //byte, shor, int, char 가능
			case valuA : System.out.println("1");
			case valuB : System.out.println("2");
			case valuC : System.out.println("3");
			case valuD : System.out.println("4");
			case valuE : System.out.println("5");break;
			default : System.out.println("default");break;
		}
	}//main
}
