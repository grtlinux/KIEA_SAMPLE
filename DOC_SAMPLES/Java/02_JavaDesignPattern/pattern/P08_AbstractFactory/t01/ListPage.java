package kiea.priv.zzz.book.JavaDesignPattern.pattern.P08_AbstractFactory.t01;

public class ListPage extends Page
{
	public ListPage(String title, String author)
	{
		super(title, author);
	}
	
	public String makeHTML()
	{
		StringBuffer sb = new StringBuffer();

		sb.append("<html><head><title>" + title + "</title></head>\n");
		sb.append("<body>\n");
		sb.append("<h1>" + title + "</h1>\n");
		sb.append("<ul>\n");
		
		for (Item item : content) {
			sb.append(item.makeHTML());
		}
		
		sb.append("</ul>\n");
		sb.append("<hr><address>" + author + "</address>\n");
		sb.append("</body></html>\n");
		
		return sb.toString();
	}
}
