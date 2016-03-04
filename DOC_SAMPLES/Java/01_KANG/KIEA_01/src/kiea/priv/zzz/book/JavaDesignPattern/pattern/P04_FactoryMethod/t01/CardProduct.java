package kiea.priv.zzz.book.JavaDesignPattern.pattern.P04_FactoryMethod.t01;

public class CardProduct extends Product
{
	private String owner;
	
	public CardProduct(String owner)
	{
		this.owner = owner;
		System.out.println(this.owner + "의 카드를 만듭니다.");
	}
	
	public String getOwner()
	{
		return this.owner;
	}
	
	public void use()
	{
		System.out.println(this.owner + "의 카드를 사용합니다.");
	}
}
