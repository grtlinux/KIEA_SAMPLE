package kiea.priv.zzz.book.JavaDesignPattern.pattern.P22_Command.t01;

import java.awt.Point;

public class DrawCommand implements Command
{
	protected Drawable drawable;
	private Point position;
	
	public DrawCommand(Drawable drawable, Point position)
	{
		this.drawable = drawable;
		this.position = position;
	}
	
	public void execute()
	{
		this.drawable.draw(this.position.x, this.position.y);
	}
}
