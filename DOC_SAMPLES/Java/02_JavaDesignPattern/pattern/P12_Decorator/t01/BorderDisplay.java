package kiea.priv.zzz.book.JavaDesignPattern.pattern.P12_Decorator.t01;

public abstract class BorderDisplay extends Display
{
	protected Display display;
	
	protected BorderDisplay(Display display)
	{
		this.display = display;
	}
}
