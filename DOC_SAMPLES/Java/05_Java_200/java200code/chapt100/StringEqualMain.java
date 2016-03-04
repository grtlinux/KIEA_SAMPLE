public class StringEqualMain {
	public static void main(String[] args) {
		//String literal
		String st1="Hello Jungbo!";
		String st2="Hello Jungbo!";
		String st3="Go Jungbo!";
		//String Object
		String sr1=new String("Hello Jungbo!");
		String sr2=new String("Hello Jungbo!");
		String sr3=new String("GO GO Jungbo!");
		String sr4=sr1;
		
		System.out.println("1  st1==st2 :"+(st1==st2));
		System.out.println("2  st1.equals(st2) :"+(st1.equals(st2)));	
		System.out.println("3  st1==st3 : "+(st1==st3));
		System.out.println("4  st1.equals(st3) : "+(st1.equals(st3)));
		System.out.println("5  sr1==sr2 : "+(sr1==sr2));
		System.out.println("6  sr1.equals(sr2) : "+(sr1.equals(sr2)));
		System.out.println("7  sr1==sr3 : "+(sr1==sr3));
		System.out.println("8  sr1.equals(sr3) : "+(sr1.equals(sr3)));
		System.out.println("9  st1==sr1 : "+(st1==sr1));
		System.out.println("10 st1.equals(sr1) : "+(st1.equals(st2)));
		System.out.println("11 sr1==sr4 : "+(sr1==sr4));
		System.out.println("12 sr1.equals(sr4) : "+(sr1.equals(sr4)));
	}
}
