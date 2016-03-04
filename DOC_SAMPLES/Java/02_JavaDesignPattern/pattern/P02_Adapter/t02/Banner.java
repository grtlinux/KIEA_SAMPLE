package kiea.priv.zzz.book.JavaDesignPattern.pattern.P02_Adapter.t02;

public class Banner
{
	private String message;
	
	public Banner(String message)
	{
		this.message = message;
	}
	
	public void showWithParen()
	{
		System.out.println("(" + message + ")");
	}
	
	public void showWithAster()
	{
		System.out.println("*" + message + "*");
	}
}
