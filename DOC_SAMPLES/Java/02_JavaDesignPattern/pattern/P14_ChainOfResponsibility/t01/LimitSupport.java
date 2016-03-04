package kiea.priv.zzz.book.JavaDesignPattern.pattern.P14_ChainOfResponsibility.t01;

public class LimitSupport extends Support
{
	private int limit;
	
	public LimitSupport(String name, int limit)
	{
		super(name);
		this.limit = limit;
	}
	
	protected boolean resolve(Trouble trouble)
	{
		if (trouble.getNumber() < limit) {
			return true;
		} else {
			return false;
		}
	}
}
