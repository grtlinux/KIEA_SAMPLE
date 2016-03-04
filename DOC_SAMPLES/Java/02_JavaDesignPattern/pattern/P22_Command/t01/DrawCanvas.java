package kiea.priv.zzz.book.JavaDesignPattern.pattern.P22_Command.t01;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class DrawCanvas extends Canvas implements Drawable
{
	private static final long serialVersionUID = 1L;
	
	private Color color = Color.RED;
	private int radius = 5;
	private MacroCommand history;
	
	public DrawCanvas(int width, int height, MacroCommand history)
	{
		setSize(width, height);
		setBackground(Color.white);
		this.history = history;
	}
	
	public void paint(Graphics g)
	{
		this.history.execute();
	}
	
	public void draw(int x, int y)
	{
		Graphics g = getGraphics();
		g.setColor(this.color);
		g.fillOval(x - this.radius, y - this.radius, this.radius * 2, this.radius * 2);
	}
}
