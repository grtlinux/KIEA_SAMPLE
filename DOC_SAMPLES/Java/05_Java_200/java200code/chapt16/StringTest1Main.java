public class StringTest1Main {

	public static void main(String[] args) {
		//String Concatenation
		String sname="";
		sname+="Antony ";
		sname+="Sedas ";
		sname+=6+"th";
		//String Substring
		System.out.println("1 :            01234567890123456789");
		System.out.println("2 :Name      : "+sname);
		sname=sname.substring(2);
		System.out.println("3 :substring : "+sname);
		sname=sname.substring(2,5);
		System.out.println("4 :substring : "+sname);	
		//call by value
		String sChange="hyonny%love";
		System.out.print("5 :main: Befor : ");
		System.out.println("Hot Live  : "+sChange);
		changeIn(sChange);
		System.out.print("7 :main: After : ");
		System.out.println("Hot Live  : "+sChange);
		
		System.out.print("8 : ,<>/%^&*+-=() 이 있다면 true : ");
		System.out.println(hasDelemeter('<'));

	}//main
	
	//call by value test
	public static void changeIn(String str){
		str=str.replace('o','z');
		str+="hello";
		System.out.print("6 :method changeIn: ");
		System.out.println("Heart "+str);
	}//coffeeIn
	
	//,<>/%^&*+-=() 문자가 있다면 true
	public static boolean hasDelemeter(char c){
	 	boolean hasD=false;
	 	if(" ,<>/%^&*+-=()".indexOf(c)!=-1){
			 hasD=true;
		}
	 	return hasD;
	}//분리문자
}
