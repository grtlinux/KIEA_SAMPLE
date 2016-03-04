package kiea.priv.zzz.book.JavaDesignPattern.pattern.P04_FactoryMethod.t01;

public abstract class Factory
{
	public final Product create(String owner)
	{
		Product product = createProduct(owner);
		registProduct(product);
		return product;
	}
	
	protected abstract Product createProduct(String owner);
	protected abstract void registProduct(Product product);
}
