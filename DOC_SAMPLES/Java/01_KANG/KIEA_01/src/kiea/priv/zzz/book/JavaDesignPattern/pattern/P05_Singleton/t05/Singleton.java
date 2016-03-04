package kiea.priv.zzz.book.JavaDesignPattern.pattern.P05_Singleton.t05;

public class Singleton
{
	private static Singleton instance = null;
	
	private Singleton()
	{
		System.out.println("!인스턴스를 생성합니다.");
		slowdown();
		System.out.println("\t!인스턴스를 생성했습니다.");
	}
	
	private void slowdown()
	{
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
	}
	
	public static synchronized Singleton getInstance()
	{
		/*
		 * synchronized로 동시 호출되는 처리를 막음.
		 */
		if (Singleton.instance == null) {
			Singleton.instance = new Singleton();
		}
		
		return Singleton.instance;
	}
}
