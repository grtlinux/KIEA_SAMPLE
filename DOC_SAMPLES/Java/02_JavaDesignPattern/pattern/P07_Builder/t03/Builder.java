package kiea.priv.zzz.book.JavaDesignPattern.pattern.P07_Builder.t03;

public abstract class Builder
{
	public abstract void makeTitle(String title);
	public abstract void makeString(String string);
	public abstract void makeItems(String[] items);
	public abstract Object getResult();
}
