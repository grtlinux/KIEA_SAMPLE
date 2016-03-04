package kiea.priv.zzz.book.JavaDesignPattern.pattern.P07_Builder.t02;

public class TextBuilder extends Builder
{
	private StringBuffer sb = new StringBuffer();
	
	public void buildTitle(String string)
	{
		sb.append("TEXT BUILDER..... : makeTitle : ");
		sb.append(string);
		sb.append("\n");
	}
	
	public void buildContent(String string)
	{
		sb.append("\tTEXT BUILDER..... : makeContent : ");
		sb.append(string);
		sb.append("\n");
	}
	
	public void buildItems(String[] arrStr)
	{
		for (String string : arrStr) {
			sb.append("\t\tHTML BUILDER..... : makeItems : ");
			sb.append(string);
			sb.append("\n");
		}
	}
	
	public String buildResult()
	{
		return sb.toString();
	}
}
