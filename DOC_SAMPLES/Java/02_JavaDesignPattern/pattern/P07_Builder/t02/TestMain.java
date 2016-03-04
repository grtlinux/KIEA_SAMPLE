package kiea.priv.zzz.book.JavaDesignPattern.pattern.P07_Builder.t02;

public class TestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		Director director1 = new Director(new TextBuilder());
		Director director2 = new Director(new HtmlBuilder());
		
		System.out.println(director1.construct());
		System.out.println("---------------------------------------------");
		
		System.out.println(director2.construct());
	}
}
