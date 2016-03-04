package kiea.priv.zzz.book.JavaDesignPattern.pattern.P08_AbstractFactory.t01;

public class ListLink extends Link
{
	public ListLink(String caption, String url)
	{
		super(caption,url);
	}
	
	public String makeHTML()
	{
		return "  <li><a href=\"" + url + "\">" + caption + "</a></li>\n";
	}
}
