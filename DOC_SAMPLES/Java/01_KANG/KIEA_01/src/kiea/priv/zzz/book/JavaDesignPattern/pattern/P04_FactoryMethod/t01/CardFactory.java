package kiea.priv.zzz.book.JavaDesignPattern.pattern.P04_FactoryMethod.t01;

import java.util.Vector;

public class CardFactory extends Factory
{
	private Vector<String> owners = new Vector<String>();
	
	protected Product createProduct(String owner)
	{
		return new CardProduct(owner);
	}
	
	protected void registProduct(Product product)
	{
		owners.add(((CardProduct) product).getOwner());
	}
	
	public Vector<String> getOwners()
	{
		return owners;
	}
}
