public class   StaticLockPrintMain{
	public static void main(String[] args) {
		StaticLockPrint mr1=new StaticLockPrint();
		StaticLockPrint mr2=new StaticLockPrint();
		StaticLockPrint mr3=new StaticLockPrint();
		Thread t1=new Thread(mr1,"a");
		Thread t2=new Thread(mr2,"b");
		Thread t3=new Thread(mr3,"c");
		t1.start();  t2.start();  t3.start();
	}
}