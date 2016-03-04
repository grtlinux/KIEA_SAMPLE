package kiea.priv.zzz.book.JavaDesignPattern.pattern.P14_ChainOfResponsibility.t01;

public class SpecialSupport extends Support
{
	private int number;
	
	public SpecialSupport(String name, int number)
	{
		super(name);
		this.number = number;
	}
	
	protected boolean resolve(Trouble trouble)
	{
		if (trouble.getNumber() == this.number) {
			return true;
		} else {
			return false;
		}
	}
}
