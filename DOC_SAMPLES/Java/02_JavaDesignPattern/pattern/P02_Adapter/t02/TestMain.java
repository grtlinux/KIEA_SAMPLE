package kiea.priv.zzz.book.JavaDesignPattern.pattern.P02_Adapter.t02;

public class TestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		PrintA printA = new PrintBanner("Kang Seok");
		
		printA.showWeak();
		printA.showStrong();
	}
}
