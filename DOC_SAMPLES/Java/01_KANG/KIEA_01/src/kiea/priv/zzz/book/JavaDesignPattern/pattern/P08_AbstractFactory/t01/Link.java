package kiea.priv.zzz.book.JavaDesignPattern.pattern.P08_AbstractFactory.t01;

public abstract class Link extends Item
{
	protected String url;
	
	public Link(String caption, String url)
	{
		super(caption);
		this.url = url;
	}
}
