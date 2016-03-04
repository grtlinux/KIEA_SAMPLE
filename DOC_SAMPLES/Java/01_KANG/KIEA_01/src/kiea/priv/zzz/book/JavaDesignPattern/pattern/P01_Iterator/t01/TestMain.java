package kiea.priv.zzz.book.JavaDesignPattern.pattern.P01_Iterator.t01;

public class TestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		BookShelfAggregate bookShelfAggregate = new BookShelfAggregate(4);
		
		bookShelfAggregate.appendBook(new Book("Around the World in 80 Days"));
		bookShelfAggregate.appendBook(new Book("Bible"));
		bookShelfAggregate.appendBook(new Book("Cinderella"));
		bookShelfAggregate.appendBook(new Book("Daddy-Long-Legs"));
		bookShelfAggregate.appendBook(new Book("East of Eden"));
		bookShelfAggregate.appendBook(new Book("Frankenstein"));
		bookShelfAggregate.appendBook(new Book("Guliver's Travels"));
		bookShelfAggregate.appendBook(new Book("Hamlet"));
		
		Iterator iter = bookShelfAggregate.iterator();
		while (iter.hasNext()) {
			Book book = (Book) iter.next();
			System.out.println(book);
		}
	}
}
