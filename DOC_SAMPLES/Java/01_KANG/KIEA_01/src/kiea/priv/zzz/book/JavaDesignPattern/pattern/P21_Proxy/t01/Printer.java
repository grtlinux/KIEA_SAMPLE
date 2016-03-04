package kiea.priv.zzz.book.JavaDesignPattern.pattern.P21_Proxy.t01;

public class Printer implements Printable
{
	private String name;
	
	public Printer()
	{
		heavyJob("Printer의 인스턴스를 생성중..");
	}
	
	public Printer(String name)
	{
		this.name = name;
		heavyJob("Printer의 인스턴스(" + this.name + ")를 생성중...");
	}
	
	public void setPrinterName(String name)
	{
		this.name = name;
	}
	
	public String getPrinterName()
	{
		return this.name;
	}
	
	public void print(String string)
	{
		System.out.println("---- " + this.name + " ----");
		System.out.println(string);
	}
	
	private void heavyJob(String msg)
	{
		System.out.print(msg);
		
		try { Thread.sleep(1000); } catch (InterruptedException e) {}

		System.out.println("완료");
	}
}
