package kiea.priv.zzz.book.JavaDesignPattern.pattern.P04_FactoryMethod.t02;

public class CardProduct extends Product
{
	private String owner;
	private int serial;
	
	public CardProduct(String owner, int serial)
	{
		this.owner = owner;
		this.serial = serial;
		System.out.println(this.owner + " (" + this.serial + ") 의 카드를 만듭니다.");
	}
	
	public void use()
	{
		System.out.println(this.owner + " (" + this.serial + ") 의 카드를 사용합니다.");
	}
	
	public String getOwner()
	{
		return this.owner;
	}
	
	public int getSerial()
	{
		return this.serial;
	}
}
