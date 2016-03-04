package kiea.priv.zzz.book.JavaDesignPattern.pattern.P03_TemplateMethod.t01;

public class CharDisplay extends AbstractDisplay
{
	private final int CNT = 10;
	private char ch;
	
	public CharDisplay(char ch)
	{
		this.ch = ch;
	}
	
	public void open()
	{
		System.out.print("<<");
	}
	
	public void print()
	{
		for (int i=0; i < CNT; i++) {
			System.out.print(ch);
		}
		
		System.out.print(" ");
	}
	
	public void close()
	{
		System.out.println(">>");
	}
}
