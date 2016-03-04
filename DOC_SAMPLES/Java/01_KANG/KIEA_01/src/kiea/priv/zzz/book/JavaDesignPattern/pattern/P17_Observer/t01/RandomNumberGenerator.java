package kiea.priv.zzz.book.JavaDesignPattern.pattern.P17_Observer.t01;

import java.util.Random;

public class RandomNumberGenerator extends NumberGenerator
{
	private Random random = new Random();
	private int number;
	
	public int getNumber()
	{
		return this.number;
	}
	
	public void execute()
	{
		for (int i=0; i < 20; i++) {
			number = random.nextInt(50);
			notifyObservers();
		}
	}
}
