package kiea.priv.zzz.book.JavaDesignPattern.pattern.P07_Builder.t01;

public class HtmlBuilder extends Builder
{
	private StringBuffer sb = new StringBuffer();
	
	public void makeTitle(String string)
	{
		sb.append("HTML BUILDER..... : makeTitle : ");
		sb.append(string);
		sb.append("\n");
	}
	
	public void makeContent(String string)
	{
		sb.append("\tHTML BUILDER..... : makeContent : ");
		sb.append(string);
		sb.append("\n");
	}
	
	public void makeItems(String[] arrStr)
	{
		for (String string : arrStr) {
			sb.append("\t\tHTML BUILDER..... : makeItems : ");
			sb.append(string);
			sb.append("\n");
		}
	}
	
	public String getResult()
	{
		return sb.toString();
	}
}
