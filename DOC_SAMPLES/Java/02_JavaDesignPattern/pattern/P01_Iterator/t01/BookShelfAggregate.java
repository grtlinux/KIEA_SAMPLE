package kiea.priv.zzz.book.JavaDesignPattern.pattern.P01_Iterator.t01;

import java.util.Vector;

public class BookShelfAggregate implements Aggregate
{
	private Vector<Book> books;
	
	public BookShelfAggregate(int initSize)
	{
		this.books = new Vector<Book>(initSize);
	}
	
	public void appendBook(Book book)
	{
		this.books.add(book);
	}
	
	public Book getBookAt(int index)
	{
		return books.get(index);
	}
	
	public int getLength()
	{
		return books.size();
	}
	
	public Iterator iterator()
	{
		return new BookShelfIterator(this);
	}
}
