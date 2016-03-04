package kiea.priv.zzz.book.JavaDesignPattern.pattern.P02_Adapter.t01;

public class TestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		PrintA printA = new BannerPrint("Hello");
		PrintB printB = (PrintB) printA;
		
		printA.showWeak();
		printA.showStrong();
		
		printB.showParen();
		printB.showAster();
	}
}
