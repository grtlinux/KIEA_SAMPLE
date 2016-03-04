package kiea.priv.zzz.book.JavaDesignPattern.pattern.P08_AbstractFactory.t01;

public abstract class Item
{
	protected String caption;
	
	public Item(String caption)
	{
		this.caption = caption;
	}
	
	public abstract String makeHTML();
}
