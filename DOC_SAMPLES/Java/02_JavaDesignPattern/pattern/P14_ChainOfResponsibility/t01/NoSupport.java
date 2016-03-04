package kiea.priv.zzz.book.JavaDesignPattern.pattern.P14_ChainOfResponsibility.t01;

public class NoSupport extends Support
{
	public NoSupport(String name)
	{
		super(name);
	}
	
	protected boolean resolve(Trouble trouble)
	{
		return false;
	}
}
