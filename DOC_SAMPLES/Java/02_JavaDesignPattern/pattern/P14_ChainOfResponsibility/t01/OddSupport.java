package kiea.priv.zzz.book.JavaDesignPattern.pattern.P14_ChainOfResponsibility.t01;

public class OddSupport extends Support
{
	public OddSupport(String name)
	{
		super(name);
	}
	
	protected boolean resolve(Trouble trouble)
	{
		if (trouble.getNumber() % 2 == 1) {
			return true;
		} else {
			return false;
		}
	}
}
