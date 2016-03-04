package kiea.priv.zzz.book.JavaDesignPattern.pattern.P03_TemplateMethod.t01;

public class StringDisplay extends AbstractDisplay
{
	private String message;
	private String strLine;
	
	public StringDisplay(String message)
	{
		this.message = message;
		
		int len = getLength(this.message);
		
		StringBuffer sb = new StringBuffer();
		sb.append("+-");
		for (int i=0; i < len; i++) {
			sb.append("-");
		}
		sb.append("-+");
		
		strLine = sb.toString();
	}
	
	public void open()
	{
		System.out.println(this.strLine);
	}
	
	public void print()
	{
		System.out.println("| " + this.message + " |");
	}
	
	public void close()
	{
		System.out.println(this.strLine);
	}
}
