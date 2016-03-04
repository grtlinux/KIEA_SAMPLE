package kiea.priv.zzz.book.JavaDesignPattern.pattern.P21_Proxy.t01;

public class PrinterProxy implements Printable
{
	private String name = null;
	private Printer real = null;
	
	public PrinterProxy() {}
	
	public PrinterProxy(String name)
	{
		this.name = name;
	}
	
	public synchronized void setPrinterName(String name)
	{
		if (real != null) {
			real.setPrinterName(name);
		}
		
		this.name = name;
	}
	
	public String getPrinterName()
	{
		return this.name;
	}
	
	public void print(String string)
	{
		realize();
		real.print(string);
	}
	
	private synchronized void realize()
	{
		if (real == null) {
			real = new Printer(this.name);
		}
	}
}
