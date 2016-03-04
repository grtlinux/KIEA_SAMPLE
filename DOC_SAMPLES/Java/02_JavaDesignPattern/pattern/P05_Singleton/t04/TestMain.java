package kiea.priv.zzz.book.JavaDesignPattern.pattern.P05_Singleton.t04;

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
		 * 일부러 오류를 발생시킴.
		 * 순차적인 실행이 아니기 때문에 instance 값이 다르다.
		 */
		new TestMain("A").start();
		new TestMain("B").start();
		new TestMain("C").start();
		new TestMain("D").start();
		
		System.out.println("End.");
	}
}
