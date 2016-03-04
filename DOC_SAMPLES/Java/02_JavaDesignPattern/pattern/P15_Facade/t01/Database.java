package kiea.priv.zzz.book.JavaDesignPattern.pattern.P15_Facade.t01;

import java.util.Properties;

public class Database
{
	private Properties prop = null;
	
	private Database()
	{
		this.prop = new Properties();
		
		this.prop.setProperty("a1@youngjin.com", "Kim");
		this.prop.setProperty("b1@youngjin.com", "Lee");
		this.prop.setProperty("c1@youngjin.com", "Kang");
		this.prop.setProperty("d1@youngjin.com", "Pack");
	}
	
	private Properties getProp()
	{
		return this.prop;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static Database instance = null;
	
	private static synchronized Database getInstance()
	{
		if (instance == null) {
			instance = new Database();
		}
		
		return instance;
	}
	
	public static Properties getProperties()
	{
		return Database.getInstance().getProp();
	}
}
