package kiea.priv.zzz.etc.thread.Thread.t01;

public class ThreadTestMain extends Thread
{
	public void run()
	{
		for (int i=0; i < 100; i++) {
			System.out.println(i + ") Nice Thread !");
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		Thread thread = new ThreadTestMain();
		thread.start();
		
		for (int i=0; i < 100; i++) {
			System.out.println(i + ") Good !");
		}
	}
}
