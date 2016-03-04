package kiea.priv.zzz.etc.hypericSIGAR.t02;

import java.util.Arrays;

import org.hyperic.sigar.SigarException;

public class SysInfo extends SigarCommandBase
{
	private static boolean flag = true;
	
	public SysInfo(Shell shell)
	{
		super(shell);
	}
	
	public SysInfo()
	{
		super();
	}
	
	public String getUsageShort()
	{
		return "Display system information";
	}
	
	public void output(String[] args) throws SigarException
	{
		// sigar/os info
		Version.printInfo(System.out);
		println("");
		
		// uptime
		new Uptime(this.shell).output(args);
		println("");
		
		// cpu info
//		CpuInfo cpuInfo = new CpuInfo(this.shell);
//		cpuInfo.displayTimes = false;
//		cpuInfo.output(args);
//		println("");
		
		// memory info
//		new Free(this.shell).output(args);
//		println("");
		
		println("File Systems.........." + Arrays.asList(this.sigar.getFileSystemList()));
		println("Network Interfaces...." + Arrays.asList(this.sigar.getNetInterfaceList()));
		println("");
		
		// system resource limits
		println("System resource liits");
		new Ulimit(this.shell).output(args);
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				new SysInfo().processCommand(args);
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
