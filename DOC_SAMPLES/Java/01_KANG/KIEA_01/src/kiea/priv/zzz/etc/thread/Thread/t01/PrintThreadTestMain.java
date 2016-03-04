package kiea.priv.zzz.etc.thread.Thread.t01;

class PrintThread extends Thread
{
	private String message;
	
	public PrintThread(String message)
	{
		this.message = message;
	}
	
	public void run()
	{
		for (int i=0; i < 100; i++) {
			System.out.println(message);
		}
	}
}

public class PrintThreadTestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		new PrintThread("Good!").start();
		new PrintThread("Nice!").start();
	}
}
