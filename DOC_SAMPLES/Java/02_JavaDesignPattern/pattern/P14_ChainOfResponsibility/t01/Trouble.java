package kiea.priv.zzz.book.JavaDesignPattern.pattern.P14_ChainOfResponsibility.t01;

public class Trouble
{
	private int number;
	
	public Trouble(int number)
	{
		this.number = number;
	}
	
	public int getNumber()
	{
		return this.number;
	}
	
	public String toString()
	{
		return "[Trouble " + this.number + "]";
	}
}
