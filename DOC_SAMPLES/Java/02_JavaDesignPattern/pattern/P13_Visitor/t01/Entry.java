package kiea.priv.zzz.book.JavaDesignPattern.pattern.P13_Visitor.t01;

import java.util.Vector;

public abstract class Entry implements Acceptor
{
	public abstract String getName();
	public abstract int getSize();
	
	public Entry add(Entry entry) throws FileTreatmentException
	{
		throw new FileTreatmentException();
	}
	
//	public Iterator<Entry> iterator() throws FileTreatmentException
//	{
//		throw new FileTreatmentException();
//	}
	
	public Vector<Entry> getList() throws FileTreatmentException
	{
		throw new FileTreatmentException();
	}
	
	public String toString()
	{
		return getName() + " (" + getSize() + ")";
	}
}
