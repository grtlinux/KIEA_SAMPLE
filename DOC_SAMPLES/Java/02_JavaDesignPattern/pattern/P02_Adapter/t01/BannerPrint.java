package kiea.priv.zzz.book.JavaDesignPattern.pattern.P02_Adapter.t01;

public class BannerPrint extends Banner implements PrintA, PrintB
{
	public BannerPrint(String message)
	{
		super(message);
	}
	
	public void showWeak()
	{
		showWithParen();
	}
	
	public void showStrong()
	{
		showWithAster();
	}
	
	public void showParen()
	{
		showWithParen();
	}
	
	public void showAster()
	{
		showWithAster();
	}
}
