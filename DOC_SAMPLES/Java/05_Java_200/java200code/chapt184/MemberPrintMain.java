public class   MemberPrintMain{
	public static void main(String[] args) {
		MemberPrint mr1=new MemberPrint();
		Thread t1=new Thread(mr1,"a");
		Thread t2=new Thread(mr1,"b");
		Thread t3=new Thread(mr1,"c");
		t1.start();  t2.start();  t3.start();
	}
}