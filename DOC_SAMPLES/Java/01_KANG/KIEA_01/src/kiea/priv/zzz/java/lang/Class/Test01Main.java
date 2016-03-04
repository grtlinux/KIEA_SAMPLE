package kiea.priv.zzz.java.lang.Class;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class MethodTest
{
	public MethodTest()
	{
		methodTest();
	}
	
	public List<Integer> getList()
	{
		List<Integer> list = new ArrayList<Integer>();
		
		list.add(new Integer(1));
		list.add(new Integer(2));
		list.add(new Integer(3));
		
		return list;
	}
	
	private void methodTest()
	{
		try {
			Method method = this.getClass().getMethod("getList", new Class[] {});
			
			@SuppressWarnings("unchecked")
			List<Integer> list = (List<Integer>) method.invoke(this, new Object[] {});
			
			for (Integer integer : list) {
				System.out.println(">" + integer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class Test01Main
{
	public static void main(String[] args)
	{
		new MethodTest();
	}
}
