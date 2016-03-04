package kiea.priv.zzz.book.JavaDesignPattern.pattern.P01_Iterator.t01;

public class BookShelfIterator implements Iterator
{
	private BookShelfAggregate bookShelfAggregate;
	private int index;
	
	public BookShelfIterator(BookShelfAggregate bookShelfAggregate)
	{
		this.bookShelfAggregate = bookShelfAggregate;
		this.index = 0;
	}
	
	public boolean hasNext()
	{
		if (index < bookShelfAggregate.getLength())
			return true;
		else
			return false;
	}
	
	public Object next()
	{
		Book book = bookShelfAggregate.getBookAt(index);
		index ++;
		
		return book;
	}
}
