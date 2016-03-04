package kiea.priv.zzz.book.CookBook.t01.com.darwinsys.swingui;

import java.awt.Frame;
import java.awt.Label;

import kiea.priv.zzz.book.CookBook.t01.demo.com.darwinsys.swingui.WindowCloser;

public class WindowCloserDemo
{
	public static void main(String[] args)
	{
		Frame f = new Frame("Close Me");
		f.add(new Label("Try Titlebar Close", Label.CENTER));
		f.setSize(100, 100);
		f.setVisible(true);
		f.addWindowListener(new WindowCloser(f, true));
	}
}
