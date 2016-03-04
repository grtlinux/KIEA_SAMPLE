package kiea.priv.zzz.etc.hypericSIGAR.t02;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.MultiProcCpu;
import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class MultiPs extends SigarCommandBase
{
	private static boolean flag = true;
	
	public MultiPs(Shell shell)
	{
		super(shell);
	}
	
	public MultiPs()
	{
		super();
	}
	
	@Override
	public boolean validateArgs(String[] args)
	{
		return args.length == 1;
	}
	
	@Override
	public String getSyntaxArgs()
	{
		return "query";
	}
	
	@Override
	public String getUsageShort()
	{
		return "Show multi process status";
	}
	
	@Override
	public boolean isPidCompleter()
	{
		return true;
	}
	
	@Override
	public void output(String[] args) throws SigarException
	{
		String query = args[0];
		
		MultiProcCpu cpu = this.sigar.getMultiProcCpu(query);
		println("Number of processes: " + cpu.getProcesses());
		println("Cpu usage: " + CpuPerc.format(cpu.getPercent()));
		println("Cpu time: " + Ps.getCpuTime(cpu.getTotal()));
		
		ProcMem mem = this.sigar.getMultiProcMem(query);
		println("Size: " + Sigar.formatSize(mem.getSize()));
		println("Resident: " + Sigar.formatSize(mem.getResident()));
		println("Share: " + Sigar.formatSize(mem.getShare()));
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				new MultiPs().processCommand(args);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * SIGAR' query is searched in PTQL document at the web site https://support.hyperic.com/display/SIGAR/Home
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		if (!flag) test01(args);
		if (flag) test01(new String[] { "Exe.Name.ct=Program Files" });
	}
}
