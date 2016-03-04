package kiea.priv.zzz.book.JavaDesignPattern.pattern.P01_Iterator.t01;

public class Book
{
	private String name = "";
	
	public Book(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String toString()
	{
		return "BOOK = " + this.name;
	}
}
