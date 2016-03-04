package kiea.priv.zzz.book.JavaDesignPattern.pattern.P05_Singleton.t02;

public class TestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		System.out.println("Start.");
		
		for (int i=0; i < 10; i++) {
			System.out.println(i + ") : " + TicketMaker.getInstance().getTicketNumber());
		}
		
		System.out.println("End.");
	}
}
