package kiea.priv.zzz.java.lang.Class;

import java.lang.reflect.Method;

interface Super
{
	
}

class Sub implements Super
{
	public int print()
	{
		System.out.println("Hello");
		return 1;
	}
}


public class Test02Main
{
	public static void main(String[] args)
	{
		
		try {
			Super obj = new Sub();
			Class<?> cls = obj.getClass();
			Method method = cls.getMethod("print", new Class[] {});
			
			Integer ret = (Integer) method.invoke(obj, new Object[] {});
			System.out.println(">" + ret);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
