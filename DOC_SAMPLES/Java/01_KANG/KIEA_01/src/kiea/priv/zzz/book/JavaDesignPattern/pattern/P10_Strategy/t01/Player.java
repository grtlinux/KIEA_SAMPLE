package kiea.priv.zzz.book.JavaDesignPattern.pattern.P10_Strategy.t01;

public class Player
{
	private String name;
	private Strategy strategy;
	
	private int gameCount;
	private int winCount;
	private int loseCount;
	
	public Player(String name, Strategy strategy)
	{
		this.name = name;
		this.strategy = strategy;
	}
	
	public Hand nextHand()
	{
		return strategy.nextHand();
	}
	
	public void win()
	{
		strategy.study(true);
		winCount++;
		gameCount++;
	}
	
	public void lose()
	{
		strategy.study(false);
		loseCount++;
		gameCount++;
	}
	
	public void even()
	{
		gameCount++;
	}
	
	public String toString()
	{
		return "[" + name + ":" + gameCount + " games, " + winCount + " win, "	+ loseCount + " lose]";
	}
}
