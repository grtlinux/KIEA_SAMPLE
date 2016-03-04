package kiea.priv.zzz.etc.management.t01;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;

public class GarbageCollectorMXBeanTestMain
{
	private static boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				GarbageCollectorMXBean bean = ManagementFactory.getGarbageCollectorMXBeans().get(0);
				
				new GarbageCollectorMXBeanTestMain();
				
				Runtime.getRuntime().gc();  // Garbage Collector
				
				if (flag) System.out.println("Collection Count : " + bean.getCollectionCount());
				if (flag) System.out.println("Collection Time  : " + bean.getCollectionTime());
				
				new GarbageCollectorMXBeanTestMain();
				new GarbageCollectorMXBeanTestMain();
				
				Runtime.getRuntime().gc();  // Garbage Collector
				
				if (flag) System.out.println("Collection Count : " + bean.getCollectionCount());
				if (flag) System.out.println("Collection Time  : " + bean.getCollectionTime());
				
				new GarbageCollectorMXBeanTestMain();
				new GarbageCollectorMXBeanTestMain();
				new GarbageCollectorMXBeanTestMain();
				
				Runtime.getRuntime().gc();  // Garbage Collector
				
				if (flag) System.out.println("Collection Count : " + bean.getCollectionCount());
				if (flag) System.out.println("Collection Time  : " + bean.getCollectionTime());
				
				System.out.println();
				for (String string : bean.getMemoryPoolNames()) {
					System.out.println(string);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (flag) test01(args);
	}
}
