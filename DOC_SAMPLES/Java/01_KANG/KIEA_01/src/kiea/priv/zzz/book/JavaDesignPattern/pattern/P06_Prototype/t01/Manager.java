package kiea.priv.zzz.book.JavaDesignPattern.pattern.P06_Prototype.t01;

import java.util.Hashtable;

public class Manager
{
	private Hashtable<String, Product> showCase = new Hashtable<String, Product>();
	
	public void register(String name, Product proto)
	{
		showCase.put(name, proto);
	}
	
	public Product create(String protoName)
	{
		Product product = (Product) showCase.get(protoName);
		return product.createClone();
	}
}
