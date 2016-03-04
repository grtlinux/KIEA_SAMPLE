package kiea.priv.zzz.book.JavaDesignPattern.pattern.P21_Proxy.t01;

public class TestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		Printable printable;
		
		printable = new PrinterProxy("Alice");
		System.out.println("현재의 이름은 " + printable.getPrinterName() + " 입니다.");
		
		printable.setPrinterName("Bob");
		System.out.println("현재의 이름은 " + printable.getPrinterName() + " 입니다.");
		
		printable.print("Hello, world");
	}
}
