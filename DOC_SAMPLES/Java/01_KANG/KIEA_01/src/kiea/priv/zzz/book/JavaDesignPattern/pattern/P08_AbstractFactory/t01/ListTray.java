package kiea.priv.zzz.book.JavaDesignPattern.pattern.P08_AbstractFactory.t01;

public class ListTray extends Tray
{
	public ListTray(String caption)
	{
		super(caption);
	}
	
	public String makeHTML()
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append("<li>\n");
		sb.append(caption + "\n");
		sb.append("<ul>\n");
		
		for (Item item : tray) {
			sb.append(item.makeHTML());
		}
		
		sb.append("</ul>\n");
		sb.append("</li>\n");
		
		return sb.toString();
	}
}
