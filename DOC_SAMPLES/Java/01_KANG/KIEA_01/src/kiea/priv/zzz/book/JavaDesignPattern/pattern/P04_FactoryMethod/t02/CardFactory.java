package kiea.priv.zzz.book.JavaDesignPattern.pattern.P04_FactoryMethod.t02;

import java.util.Hashtable;

public class CardFactory extends Factory
{
	private Hashtable<String, Integer> database = new Hashtable<String, Integer>();
	private int serial = 123;
	
	protected synchronized Product createProduct(String owner)
	{
		return new CardProduct(owner, this.serial ++);
	}
	
	protected void registProduct(Product product)
	{
		CardProduct cardProduct = (CardProduct) product;
		database.put(cardProduct.getOwner(), cardProduct.getSerial());
	}
	
	public Hashtable<String, Integer> getDatabase()
	{
		return this.database;
	}
}
