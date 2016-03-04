package kiea.priv.zzz.book.JavaDesignPattern.pattern.P04_FactoryMethod.t01;

public class TestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		Factory factory = new CardFactory();
		
		Product card1 = factory.create("홍길동");
		Product card2 = factory.create("이순신");
		Product card3 = factory.create("강감찬");
		
		card1.use();
		card2.use();
		card3.use();
	}
}
