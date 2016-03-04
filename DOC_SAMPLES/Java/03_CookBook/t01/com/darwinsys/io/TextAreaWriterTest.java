package kiea.priv.zzz.book.CookBook.t01.com.darwinsys.io;

import java.io.PrintWriter;

import javax.swing.JTextArea;

import junit.framework.TestCase;
import kiea.priv.zzz.book.CookBook.t01.demo.com.darwinsys.io.TextAreaWriter;

public class TextAreaWriterTest extends TestCase
{
	private static final String HELLO_WORLD = "Hello World";
	
	JTextArea ta = new JTextArea();
	
	public void testOne()
	{
		PrintWriter x = new PrintWriter(new TextAreaWriter(ta));
		x.print("Hello");
		x.print(' ');
		x.print("World");
		x.close();
		assertEquals(HELLO_WORLD, ta.getText());
	}
}
