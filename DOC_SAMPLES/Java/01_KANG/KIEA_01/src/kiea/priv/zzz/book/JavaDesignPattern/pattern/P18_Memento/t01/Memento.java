package kiea.priv.zzz.book.JavaDesignPattern.pattern.P18_Memento.t01;

import java.util.Vector;

public class Memento
{
	private int money;
	private Vector<String> fruits;
	
	public Memento(int money)
	{
		this.money = money;
		this.fruits = new Vector<String>();
	}
	
	public int getMoney()
	{
		return this.money;
	}
	
	public Vector<String> getFruits()
	{
		return this.fruits;
	}
	
	public void addFruit(String fruit)
	{
		fruits.add(fruit);
	}
}
