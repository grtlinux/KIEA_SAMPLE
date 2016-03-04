package kiea.priv.zzz.book.JavaDesignPattern.pattern.P06_Prototype.t01;

public class UnderlinePen implements Product
{
	private char ulChar;
	
	public UnderlinePen(char ulChar)
	{
		this.ulChar = ulChar;
	}
	
	public void use(String string)
	{
		int length = string.getBytes().length;
		
		System.out.println("\"" + string + "\"");
		for (int i=0; i < length + 2; i++) {
			System.out.print(this.ulChar);
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
