package kiea.priv.zzz.book.JavaDesignPattern.pattern.P08_AbstractFactory.t01;

import java.util.Vector;

public abstract class Tray extends Item
{
	protected Vector<Item> tray = new Vector<Item>();
	
	public Tray(String caption)
	{
		super(caption);
	}
	
	public void add(Item item)
	{
		tray.add(item);
	}
}
