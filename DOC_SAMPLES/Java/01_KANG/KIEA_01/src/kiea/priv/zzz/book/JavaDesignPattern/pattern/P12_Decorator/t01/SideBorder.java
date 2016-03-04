package kiea.priv.zzz.book.JavaDesignPattern.pattern.P12_Decorator.t01;

public class SideBorder extends BorderDisplay
{
	private char borderChar;
	
	public SideBorder(Display display, char borderChar)
	{
		super(display);
		this.borderChar = borderChar;
	}
	
	public int getColumns()
	{
		return 1 + display.getColumns() + 1;
	}
	
	public int getRows()
	{
		return display.getRows();
	}
	
	public String getRowText(int row)
	{
		return borderChar + display.getRowText(row) + borderChar;
	}
}
