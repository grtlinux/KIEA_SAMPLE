package kiea.priv.zzz.book.JavaDesignPattern.pattern.P17_Observer.t01;

public class DigitObserver implements Observer
{
	public void update(NumberGenerator generator)
	{
		System.out.println("DigitObserver : " + generator.getNumber());
		
		try { Thread.sleep(100); } catch (InterruptedException e) {}
	}
}
