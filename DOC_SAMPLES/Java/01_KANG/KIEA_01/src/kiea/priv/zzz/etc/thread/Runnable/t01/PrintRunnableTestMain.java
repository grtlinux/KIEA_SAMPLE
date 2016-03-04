package kiea.priv.zzz.etc.thread.Runnable.t01;

class PrintRunnable implements Runnable
{
	private String message;
	
	public PrintRunnable(String message)
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

public class PrintRunnableTestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		new Thread(new PrintRunnable("Good!")).start();
		new Thread(new PrintRunnable("Nice!")).start();
	}
}
