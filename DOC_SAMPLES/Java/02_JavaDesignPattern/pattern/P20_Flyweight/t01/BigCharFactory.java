package kiea.priv.zzz.book.JavaDesignPattern.pattern.P20_Flyweight.t01;

import java.util.Hashtable;

public class BigCharFactory
{
	private Hashtable<String, BigChar> pool = new Hashtable<String, BigChar>();
	
	private BigCharFactory() {}
	
	private static BigCharFactory instance = new BigCharFactory();
	
	public static BigCharFactory getInstance()
	{
		return instance;
	}
	
	public synchronized BigChar getBigChar(char charName)
	{
		BigChar bc = pool.get("" + charName);
		if (bc == null) {
			bc = new BigChar(charName);
			pool.put("" + charName, bc);
		}
		return bc;
	}
}
