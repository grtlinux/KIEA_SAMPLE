package kiea.priv.zzz.book.JavaDesignPattern.pattern.P15_Facade.t01;

public class HtmlWriter
{
	public HtmlWriter() {}
	
	public static void print(String mailto, String name)
	{
		System.out.println("> " + mailto + ", " + name);
	}
}
