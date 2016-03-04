package kiea.priv.zzz.book.JavaDesignPattern.pattern.P15_Facade.t01;

import java.util.Properties;

public class PageMaker
{
	public static void makeWelcomePage(String mailto)
	{
		try {
			Properties prop = Database.getProperties();
			
			String name = prop.getProperty(mailto);
			
			HtmlWriter.print(mailto, name);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
