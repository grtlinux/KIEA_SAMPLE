package kiea.priv.zzz.java.lang.Class;

import java.lang.reflect.Method;

class UseMethod
{
	public int add(int a, int b)
	{
		System.out.println("Addition....");
		return a + b;
	}
	
	public int add2(int a, int b, int c, double d, float e)
	{
		System.out.println("Addition-2..." + c + "," + d + "," + e);
		return a + b;
	}
	
	public String arrString(String[] arrStr)
	{
		StringBuffer sb = new StringBuffer();
		
		for (String str : arrStr) {
			sb.append(str);
			sb.append(',');
		}
		
		return sb.toString();
	}
}

public class Test03Main
{
	private static boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		Class<?> cls = Class.forName("kiea.priv.zzz.java.lang.Class.UseMethod");
		Class<?> argTypes[] = new Class[2];
		argTypes[0] = Integer.TYPE;
		argTypes[1] = Integer.TYPE;
		Method method = cls.getMethod("add", argTypes);
		
		UseMethod obj = new UseMethod();
		Object argList[] = new Object[2];
		argList[0] = new Integer(27);
		argList[1] = new Integer(47);
		Object ret = method.invoke(obj, argList);
		
		Integer retVal = (Integer) ret;
		
		System.out.println(">" + retVal);
		System.out.println("\n-----------------------------------------------\n");
	}
	
	private static void test02(String[] args) throws Exception
	{
		Class<?> cls = Class.forName("kiea.priv.zzz.java.lang.Class.UseMethod");
		Class<?> argTypes[] = new Class[2];
		argTypes[0] = Integer.TYPE;
		argTypes[1] = Integer.TYPE;
		Method method = cls.getMethod("add", argTypes);
		
		Object argList[] = new Object[2];
		argList[0] = new Integer(27);
		argList[1] = new Integer(47);
		Object ret = method.invoke(new UseMethod(), argList);
		
		Integer retVal = (Integer) ret;
		
		System.out.println(">" + retVal);
		System.out.println("\n-----------------------------------------------\n");
	}
	
	private static void test03(String[] args) throws Exception
	{
		Class<?> cls = Class.forName("kiea.priv.zzz.java.lang.Class.UseMethod");
		Class<?> argTypes[] = new Class[5];
		argTypes[0] = Integer.TYPE;
		argTypes[1] = Integer.TYPE;
		argTypes[2] = int.class;
		argTypes[3] = double.class;
		argTypes[4] = float.class;
		
		Method method = cls.getMethod("add2", argTypes);
		
		Object argList[] = new Object[] {
			new Integer(27),
			new Integer(47),
			123,
			234.56,
			10.3f,
		};
		Object ret = method.invoke(new UseMethod(), argList);
		
		Integer retVal = (Integer) ret;
		
		System.out.println(">" + retVal);
		System.out.println("\n-----------------------------------------------\n");
	}
	
	private static void test04(String[] args) throws Exception
	{
		Class<?> cls = Class.forName("kiea.priv.zzz.java.lang.Class.UseMethod");
		Method method = cls.getMethod("add", new Class[] { Integer.TYPE, Integer.TYPE });
		
		Integer ret = (Integer) method.invoke(new UseMethod(), new Object[] { new Integer(27), new Integer(57), });
		
		System.out.println(">" + ret);
		System.out.println("\n-----------------------------------------------\n");
	}
	
	/*
	 * Object obj = [Class].newInstance();
	 * Class<?> cls = obj.getClass();
	 * cls.getMethod([methodName], [parameterClass]).invoke(obj, [parameterValue]);
	 * 
	 * parameterClass
	 *     Class[] argType = new Class[] { int.class, double.class };
	 *     
	 * parameterValue
	 *     Object[] argValue = new Object[] { 123, 1234.56 };
	 */
	private static void test05(String[] args) throws Exception
	{
		Class<?> cls = Class.forName("kiea.priv.zzz.java.lang.Class.UseMethod");
		// Method method = cls.getMethod("arrString", String[].class);
		// Method method = cls.getMethod("arrString", new Class[] { String[].class });
		Method method = cls.getDeclaredMethod("arrString", new Class[] { String[].class });
		
		// String ret = (String) method.invoke(cls.newInstance(), new Object[] { "One", "Two", "Three" });                 // ERROR : IllegalArgumentException : wrong number of arguments
		// String ret = (String) method.invoke(cls.newInstance(), new String[] { "One", "Two", "Three" });                 // ERROR : IllegalArgumentException : wrong number of arguments
		
		String[] arg = { "One", "Two", "Three" };
		String ret = (String) method.invoke(cls.newInstance(), new Object[] { arg });                                      // CORRECT 
		//String ret = (String) method.invoke(cls.newInstance(), new Object[] { new String[] {"One", "Two", "Three"} });   // CORRECT

		System.out.println(">" + ret);
		System.out.println("\n-----------------------------------------------\n");
	}
	
	public static void main(String[] args) throws Exception
	{
		if (flag) test01(args);
		if (flag) test02(args);
		if (flag) test03(args);
		if (flag) test04(args);
		if (flag) test05(args);
	}
}
