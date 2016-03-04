package kiea.priv.zzz.java.lang.Class;

public class Test04Main
{
	private static boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////
	
	public Test04Main()
	{
		if (flag) System.out.println("Constructor in");
		
		if (flag) System.out.println("1 >" + Test04Main.class);
		if (flag) System.out.println("2 >" + getClass());
		if (flag) System.out.println("3 >" + getClass().getName());
		
		if (flag) System.out.println("Constructor out");
	}
	
	public void execute()
	{
		if (flag) System.out.println("Method in");
		
		if (flag) System.out.println("1 >" + Test04Main.class);
		if (flag) System.out.println("2 >" + getClass());
		if (flag) System.out.println("3 >" + getClass().getName());
		
		if (flag) System.out.println("Method out");
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			Test04Main cls = new Test04Main();
			cls.execute();
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (flag) test01(args);
	}
}
