package kiea.priv.zzz.book.JavaDesignPattern.pattern.P05_Singleton.t05;

public class TestMain extends Thread
{
	public TestMain(String name)
	{
		super(name);
	}
	
	public void run()
	{
		Singleton instance = Singleton.getInstance();
		System.out.println(getName() + " : instance = " + instance);
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		System.out.println("Start.");
		
		/*
		 * 정상적인 처리
		 */
		new TestMain("A").start();
		new TestMain("B").start();
		new TestMain("C").start();
		new TestMain("D").start();
		
		System.out.println("End.");
	}
}
