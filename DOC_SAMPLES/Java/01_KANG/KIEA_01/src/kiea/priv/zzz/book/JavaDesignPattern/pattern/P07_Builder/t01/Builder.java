package kiea.priv.zzz.book.JavaDesignPattern.pattern.P07_Builder.t01;

public abstract class Builder
{
	public abstract void makeTitle(String string);
	public abstract void makeContent(String string);
	public abstract void makeItems(String[] arrStr);
	public abstract String getResult();
}
