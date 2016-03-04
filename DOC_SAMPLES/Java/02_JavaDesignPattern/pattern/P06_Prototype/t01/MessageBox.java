package kiea.priv.zzz.book.JavaDesignPattern.pattern.P06_Prototype.t01;

public class MessageBox implements Product
{
	private char decoChar;
	
	public MessageBox(char decoChar)
	{
		this.decoChar = decoChar;
	}
	
	public void use(String string)
	{
		int length = string.getBytes().length;
		for (int i=0; i < length + 4; i++) {
			System.out.print(this.decoChar);
		}
		System.out.println();
		
		System.out.println(this.decoChar + " " + string + " " + this.decoChar);
		
		for (int i=0; i < length + 4; i++) {
			System.out.print(this.decoChar);
		}
		System.out.println();
	}
	
	public Product createClone()
	{
		Product product = null;
		
		try {
			product = (Product) clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return product;
	}
}
