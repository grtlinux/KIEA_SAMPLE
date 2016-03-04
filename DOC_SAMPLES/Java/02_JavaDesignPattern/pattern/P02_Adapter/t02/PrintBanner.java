package kiea.priv.zzz.book.JavaDesignPattern.pattern.P02_Adapter.t02;

public class PrintBanner extends PrintA
{
	private Banner banner = null;
	
	public PrintBanner(String message)
	{
		this.banner = new Banner(message);
	}
	
	public void showWeak()
	{
		this.banner.showWithParen();
	}
	
	public void showStrong()
	{
		this.banner.showWithAster();
	}
}
