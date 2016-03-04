package kiea.priv.zzz.etc.hypericSIGAR.t02;

import java.util.List;

import kiea.priv.zzz.etc.hypericSIGAR.t01.Ps;
import kiea.priv.zzz.etc.hypericSIGAR.t01.Uptime;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.ProcCpu;
import org.hyperic.sigar.ProcStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;

public class Top
{
	private static boolean flag = true;
	
	private static final int SLEEP_TIME = 1000 * 5;
	
	private static final String HEADER = "PID\tUSER\tSTIME\tSIZE\tRSS\tSHARE\tSTATE\tTIME\tCPU\tCOMMAND";
	
	private static String toString(ProcStat stat)
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append(stat.getTotal()   ).append(" process: ");
		sb.append(stat.getSleeping()).append(" sleeping, ");
		sb.append(stat.getRunning() ).append(" running, ");
		sb.append(stat.getZombie()  ).append(" zombie, ");
		sb.append(stat.getStopped() ).append(" stopped...").append(stat.getThreads()).append(" threads");
		
		return sb.toString();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				
				Sigar sigarImpl = new Sigar();
				SigarProxy sigar = SigarProxyCache.newInstance(sigarImpl, SLEEP_TIME);
				
				while (true) {
					Shell.clearScreen();
					
					System.out.println(Uptime.getInfo(sigar));
					System.out.println(toString(sigar.getProcStat()));
					System.out.println(sigar.getCpuPerc());
					System.out.println(sigar.getMem());
					System.out.println(sigar.getSwap());
					System.out.println();
					System.out.println(HEADER);
					
					long[] pids = Shell.getPids(sigar, args);
					
					for (int i=0; i < pids.length; i++) {
						long pid = pids[i];
						
						String cpuPerc = "?";
						
						List<String> info;
						
						try {
							info = Ps.getInfo(sigar, pid);
						} catch (SigarException e) {
							continue;
						}
						
						try {
							ProcCpu cpu = sigar.getProcCpu(pid);
							cpuPerc = CpuPerc.format(cpu.getPercent());
						} catch (SigarException e) {
							// TODO: handle exception
						}
						
						info.add(info.size()-1, cpuPerc);
						
						System.out.println(Ps.join(info));
					}
					
					try {
						Thread.sleep(SLEEP_TIME);
					} catch (InterruptedException e) {}
					
					SigarProxyCache.clear(sigar);
				}
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
