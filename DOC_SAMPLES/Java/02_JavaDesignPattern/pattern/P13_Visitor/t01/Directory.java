package kiea.priv.zzz.book.JavaDesignPattern.pattern.P13_Visitor.t01;

import java.util.Vector;

public class Directory extends Entry
{
	private String name;
	private Vector<Entry> dir = new Vector<Entry>();
	
	public Directory(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getSize()
	{
		int size = 0;
		for (Entry entry : dir) {
			size += entry.getSize();
		}
		return size;
	}
	
	public Entry add(Entry entry)
	{
		this.dir.add(entry);
		return this;
	}
	
//	public Iterator<Entry> iterator()
//	{
//		return dir.iterator();
//	}
	
	public Vector<Entry> getList()
	{
		return dir;
	}
	
	public void accept(Visitor visitor)
	{
		visitor.visit(this);
	}
}
