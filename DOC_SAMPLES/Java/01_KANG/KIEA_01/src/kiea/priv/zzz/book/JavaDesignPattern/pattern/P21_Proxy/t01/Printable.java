package kiea.priv.zzz.book.JavaDesignPattern.pattern.P21_Proxy.t01;

public interface Printable
{
	public abstract void setPrinterName(String name);
	public abstract String getPrinterName();
	public abstract void print(String string);
}
