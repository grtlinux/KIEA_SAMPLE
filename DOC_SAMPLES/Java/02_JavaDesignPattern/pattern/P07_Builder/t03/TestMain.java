package kiea.priv.zzz.book.JavaDesignPattern.pattern.P07_Builder.t03;

import javax.swing.JFrame;

public class TestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		Director director = new Director(new FrameBuilder());
		JFrame frame = (JFrame) director.construct();
		frame.setVisible(true);
	}
}
