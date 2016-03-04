package kiea.priv.zzz.book.JavaDesignPattern.pattern.P08_AbstractFactory.t01;

public class TestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		String className = "kiea.priv.zzz.book.JavaDesignPattern.pattern.P08_AbstractFactory.t01.ListFactory";
		//String className = "ListFactory";
		
		Factory factory = Factory.getFactory(className);
		
		Link joins = factory.createLink("중앙일보", "http://www.joins.com/");
		Link hani = factory.createLink("한겨레 신문", "http://www.hani.co.kr/");

		Link us_yahoo = factory.createLink("Yahoo!", "http://www.yahoo.com/");
		Link kr_yahoo = factory.createLink("Yahoo!Korea", "http://www.yahoo.co.kr");

		Link excite = factory.createLink("Excite", "http://www.excite.com/");
		Link google = factory.createLink("Google", "http://www.google.com/");

		Tray trayNews = factory.createTray("신문");
		trayNews.add(joins);
		trayNews.add(hani);
		
		Tray trayYahoo = factory.createTray("Yahoo");
		trayYahoo.add(us_yahoo);
		trayYahoo.add(kr_yahoo);

		Tray traySearch = factory.createTray("서치 엔진");
		traySearch.add(trayYahoo);
		traySearch.add(excite);
		traySearch.add(google);

		Page page = factory.createPage("LinkPage", "홍길동");
		page.add(trayNews);
		page.add(traySearch);
		
		page.output();
	}
}
