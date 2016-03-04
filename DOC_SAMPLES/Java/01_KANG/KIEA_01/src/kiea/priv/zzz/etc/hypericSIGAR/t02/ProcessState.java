package kiea.priv.zzz.etc.hypericSIGAR.t02;

import org.hyperic.sigar.ProcState;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class ProcessState
{
	private static boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////
	
	private static String getStateString(char state)
	{
		switch (state) {
		case ProcState.SLEEP:
			return "Sleeping";
		case ProcState.RUN:
			return "Running";
		case ProcState.STOP:
			return "Suspended";
		case ProcState.ZOMBIE:
			return "Zombie";
		case ProcState.IDLE:
			return "Idle";
		default:
			return String.valueOf(state);
		}
	}
	
	private static void test01(String[] args) throws SigarException
	{
		if (flag) {
			try {
				String pid = "$$"; // default to this process
				
				if (args.length == 1) {
					pid = args[0];
				}
				
				Sigar sigar = new Sigar();
				
				ProcState procState = sigar.getProcState(pid);
				
				System.out.println(pid + ">" + procState.getName() + ":" +procState.getPpid() + ":" + getStateString(procState.getState()));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (!flag) test01(args);
		
		if (flag) {
			for (String pid : new String[] { "11", "4684", "$$", "2848", "3960", "4700" } ) {
				test01(new String[] { pid });
			}
		}
	}
}
