package kiea.priv.zzz.etc.hypericSIGAR.t02;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarLoader;

public class CpuInfo extends SigarCommandBase
{
	private static boolean flag = true;
	
	public boolean displayTimes = true;
	
	public CpuInfo(Shell shell)
	{
		super(shell);
	}
	
	public CpuInfo()
	{
		super();
	}
	
	@Override
	public boolean validateArgs(String[] args)
	{
		return true;
	}
	
	@Override
	public String getSyntaxArgs()
	{
		return "[args]";
	}
	
	@Override
	public String getUsageShort()
	{
		return "Display cup information";
	}
	
	@Override
	public boolean isPidCompleter()
	{
		return true;
	}
	
	@Override
	public void output(String[] args) throws SigarException
	{
		org.hyperic.sigar.CpuInfo[] infos = this.sigar.getCpuInfoList();
		
		org.hyperic.sigar.CpuInfo info = infos[0];
		long cacheSize = info.getCacheSize();
		println("Vendor.........." + info.getVendor());
		println("Model..........." + info.getModel());
		println("Mhz............." + info.getMhz());
		println("Total CPUs......" + info.getTotalCores());
		if ((info.getTotalCores() != info.getTotalSockets()) || (info.getCoresPerSocket() > info.getTotalCores())) {
			println("Physical CPUs..." + info.getTotalSockets());
			println("Cores per CPU..." + info.getCoresPerSocket());
		}
		
		if (cacheSize != Sigar.FIELD_NOTIMPL) {
			println("Cache size.........." + cacheSize);
		}
		println("");
		
		if (!this.displayTimes) {
			return;
		}
		
		CpuPerc[] cpus = this.sigar.getCpuPercList();
		
		for (int i=0; i < cpus.length; i++) {
			println("CPU " + i + " ..............................");
			output(cpus[i]);
		}
		
		println("Totals .............................");
		output(this.sigar.getCpuPerc());
	}
	
	private void output(CpuPerc cpu)
	{
		println("User Time........." + CpuPerc.format(cpu.getUser()));
		println("Sys Time.........." + CpuPerc.format(cpu.getSys()));
		println("Idle Time........." + CpuPerc.format(cpu.getIdle()));
		println("Wait Time........." + CpuPerc.format(cpu.getWait()));
		println("Nice Time........." + CpuPerc.format(cpu.getNice()));
		println("Combined.........." + CpuPerc.format(cpu.getCombined()));
		println("Irq Time.........." + CpuPerc.format(cpu.getIrq()));
		if (SigarLoader.IS_LINUX) {
			println("SoftIrq Time........" + CpuPerc.format(cpu.getSoftIrq()));
			println("Stolen Time........." + CpuPerc.format(cpu.getStolen()));
		}
		println("");
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				new CpuInfo().processCommand(args);
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
