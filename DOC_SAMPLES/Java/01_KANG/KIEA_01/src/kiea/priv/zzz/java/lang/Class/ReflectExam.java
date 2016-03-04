package kiea.priv.zzz.java.lang.Class;

import java.lang.reflect.Method;

class TargetBean
{
	private String name;
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
}

public class ReflectExam
{
	private static boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
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
			try {
				Method[] methods = null;
				if (flag) {
					// TYPE-1
					methods = TargetBean.class.getMethods();
				} else {
					// TYPE-2
					Object obj = new TargetBean();
					Class<?> cls = obj.getClass();
					methods = cls.getMethods();
					
					// TYPE-3
					//methods = new TargetBean().getClass().getMethods();
				}
				for (Method method : methods) {
					System.out.println(">" + method.getName());
				}
				System.out.println("---------------------------------------------------");
				
				Method setMethod = null;
				Method getMethod = null;
				
				for (Method method : methods) {
					if ("setName".equals(method.getName())) {
						setMethod = method;
					} else if ("getName".equals(method.getName())) {
						getMethod = method;
					}
				}
				
				// create Object
				Class<?> cls = Class.forName("kiea.priv.zzz.java.lang.Class.TargetBean");
				TargetBean instance = (TargetBean) cls.newInstance();
				
				// setMethod
				setMethod.invoke(instance, "Hello Kang Seok");
				
				// getMethod
				String ret = (String) getMethod.invoke(instance);
				System.out.println("getMethod >" + ret);
				
				// instance.getName
				ret = instance.getName();
				System.out.println("getName >" + ret);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (flag) test01(args);
	}
}
