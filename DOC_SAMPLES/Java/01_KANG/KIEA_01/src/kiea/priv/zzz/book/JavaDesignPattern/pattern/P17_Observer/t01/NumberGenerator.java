package kiea.priv.zzz.book.JavaDesignPattern.pattern.P17_Observer.t01;

import java.util.Vector;

public abstract class NumberGenerator
{
	private Vector<Observer> observers = new Vector<Observer>();
	
	public void addObserver(Observer observer)
	{
		observers.add(observer);
	}
	
	public void deleteObserver(Observer observer)
	{
		observers.remove(observer);
	}
	
	public void notifyObservers()
	{
		for (Observer observer : observers) {
			observer.update(this);
		}
	}
	
	public abstract int getNumber();
	public abstract void execute();
}
