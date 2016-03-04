package kiea.priv.zzz.book.JavaDesignPattern.pattern.P03_TemplateMethod.t01;

public class TestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		AbstractDisplay strDisplay = new StringDisplay("Kang Seok");
		AbstractDisplay chDisplay = new CharDisplay('*');
		
		strDisplay.display();
		
		System.out.println();
		
		chDisplay.display();
	}
}
