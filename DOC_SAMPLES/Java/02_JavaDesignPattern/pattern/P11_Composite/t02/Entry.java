package kiea.priv.zzz.book.JavaDesignPattern.pattern.P11_Composite.t02;

public abstract class Entry
{
	public abstract String getName();
	public abstract int getSize();
	
	public Entry add(Entry entry) throws FileTreatmentException
	{
		throw new FileTreatmentException();
	}
	
	public void printList()
	{
		printList("");
	}
	
	protected abstract void printList(String prefix);
	
	public String toString()
	{
		return getName() + " (" + getSize() + ")";
	}
	
	protected Entry parent;
	
	public String getFullName()
	{
		StringBuffer fullName = new StringBuffer();
		
		Entry entry = this;
		do {
			fullName.insert(0, "/" + entry.getName());
			entry = entry.parent;
		} while (entry != null);
		
		return fullName.toString();
	}
}
