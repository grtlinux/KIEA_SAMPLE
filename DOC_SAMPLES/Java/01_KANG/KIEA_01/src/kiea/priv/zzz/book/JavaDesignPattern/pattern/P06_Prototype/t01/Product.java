package kiea.priv.zzz.book.JavaDesignPattern.pattern.P06_Prototype.t01;

public interface Product extends Cloneable
{
	public abstract void use(String string);
	public abstract Product createClone();
}
