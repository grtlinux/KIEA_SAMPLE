package kiea.priv.zzz.etc.management.t01;

import java.lang.management.ManagementFactory;
//import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;

import com.sun.management.OperatingSystemMXBean;

/*
 * Preferences > Java > Compiler > Errors/Warnings
 *     Deprecated and restricted API
 *         Forbidden reference (access rules) : Error -> Warning
 */
@SuppressWarnings("restriction")
public class OperatingSystemMXBean01TestMain
{
	private static boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				OperatingSystemMXBean opBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
				RuntimeMXBean runBean = ManagementFactory.getRuntimeMXBean();
				
				//double beforeProcessTime = opBean.getSystemLoadAverage();
				double beforeProcessTime = opBean.getProcessCpuTime();
				long beforeUptime = runBean.getUptime();
				if (flag) System.out.println("BEFORE PT:" + beforeProcessTime + ", UT:" + beforeUptime);
				
				long nCpus = opBean.getAvailableProcessors();
				if (flag) System.out.println("N_CPU:" + nCpus);
				for (int i=0; i < 100000000; i++) {
					nCpus = opBean.getAvailableProcessors();
				}
				
				//double afterProcessTime = opBean.getSystemLoadAverage();
				double afterProcessTime = opBean.getProcessCpuTime();
				long afterUptime = runBean.getUptime();
				if (flag) System.out.println("AFTER PT:" + afterProcessTime + ", UT:" + afterUptime);
				
				float cal = (float) ((afterProcessTime - beforeProcessTime) / ((afterUptime - beforeUptime) * nCpus * 10000f));
				float usage = Math.min(99f, cal);
				
				
				if (flag) System.out.println("\nCAL:" + cal + ", USAGE:" + usage);
				
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
