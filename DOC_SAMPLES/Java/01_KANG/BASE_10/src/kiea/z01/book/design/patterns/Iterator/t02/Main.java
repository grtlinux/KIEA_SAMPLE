/* Copyright (c) 2008-2014, KangSeok
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the HSQL Development Group nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL HSQL DEVELOPMENT GROUP, HSQLDB.ORG,
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package kiea.z01.book.design.patterns.Iterator.t02;

/**
 * @author KangSeok
 * @date 2014. 9. 11.
 * @file_name Main.java
 *
 */
public class Main
{
	public static void main(String[] args)
	{
		String[] bookList = {
				"Around the world in 80 days",
				"Bible",
				"Cinderella",
				"Daddy-Long-Legs",
				"East of Edan",
				"Frankenstein",
				"Gulliver's Travels",
				"Hamlet",
				
				"Iterator",
				"Adapter",
				"Template Method",
				"Factory Method",
				"Singleton",
				
				"Prototype",
				"Builder",
				"Abstract Factory",
				"Bridge",
				"Strategy",
				
				"Composite",
				"Decorator",
				"Viewer",
				"Chain of Responsibility",
				"Facade",
				
				"Mediator",
				"Observer",
				"Memento",
				"State",
				"Flyweight",
				
				"Proxy",
				"Command",
				"Interpreter",
		};
		
		BookShelf bookShelf = new BookShelf(4);
		
		for (String bookName : bookList) {
			bookShelf.appendBook(new Book(bookName));
		}
		
		Iterator<Book> iterBook = bookShelf.iterator();
		while (iterBook.hasNext()) {
			Book book = iterBook.next();
			System.out.println(book);
		}
	}
}
