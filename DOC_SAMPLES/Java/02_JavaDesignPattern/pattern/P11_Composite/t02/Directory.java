package kiea.priv.zzz.book.JavaDesignPattern.pattern.P11_Composite.t02;

import java.util.Vector;

public class Directory extends Entry
{
	private String name;
	private Vector<Entry> directory = new Vector<Entry>();
	
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
		for (Entry entry : directory) {
			size += entry.getSize();
		}
		return size;
	}
	
	public Entry add(Entry entry)
	{
		directory.add(entry);
		entry.parent = this;
		return this;
	}
	
	protected void printList(String prefix)
	{
		System.out.println(prefix + "/" + this);
		for (Entry entry : directory) {
			entry.printList(prefix + "/" + name);
		}
	}
}
