package kiea.priv.zzz.book.JavaDesignPattern.pattern.P05_Singleton.t02;

public class TicketMaker
{
	private static TicketMaker instance = new TicketMaker();
	
	private int ticketNum = 1000;
	
	private TicketMaker()
	{
		System.out.println("! TicketMaker 인스턴스가 생성되었습니다.");
	}
	
	public static TicketMaker getInstance()
	{
		return instance;
	}
	
	public synchronized int getTicketNumber()
	{
		return this.ticketNum ++;
	}
}
