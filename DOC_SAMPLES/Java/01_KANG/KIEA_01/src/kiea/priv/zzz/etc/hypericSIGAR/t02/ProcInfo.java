package kiea.priv.zzz.etc.hypericSIGAR.t02;

import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarPermissionDeniedException;

public class ProcInfo extends SigarCommandBase
{
	private static boolean flag = true;

	private boolean isSingleProcess;
	
	public ProcInfo(Shell shell)
	{
		super(shell);
	}
	
	public ProcInfo()
	{
		super();
	}
	
	@Override
	public boolean validateArgs(String[] args)
	{
		return true;
	}
	
	@Override
	public String getUsageShort()
	{
		return "Display all process info";
	}
	
	@Override
	public boolean isPidCompleter()
	{
		return true;
	}
	
	@Override
	public void output(String[] args) throws SigarException
	{
		this.isSingleProcess = false;
		
		if ((args.length != 0) && args[0].startsWith("-s")) {
			this.isSingleProcess = true;
		}
		
		if (this.isSingleProcess) {
			for (int i=1; i < args.length; i++) {
				try {
					output(args[i]);
				} catch (SigarException e) {
					println("(" + e.getMessage() + ")");
				}
				println("\n---------------------------------------\n");
			}
		} else {
			long[] pids = this.shell.findPids(args);
			
			for (int i=0; i < pids.length; i++) {
				try {
					output(String.valueOf(pids[i]));
				} catch (SigarPermissionDeniedException e) {
					println(this.shell.getUserDeniedMessage(pids[i]));
				} catch (SigarException e) {
					println("(" + e.getMessage() + ")");
				}
				println("\n---------------------------------------\n");
			}
		}
	}
	
	private void output(String pid) throws SigarException
	{
		println("pid = " + pid);
		
		try {
			println("state = " + sigar.getProcState(pid));
		} catch (SigarException e) {
			if (this.isSingleProcess) {
				println(e.getMessage());
			}
		}
		
		try {
			println("mem = " + this.sigar.getProcMem(pid));
		} catch (SigarException e) {}

		try {
			println("cpu = " + this.sigar.getProcCpu(pid));
		} catch (SigarException e) {}

		try {
			println("cred = " + this.sigar.getProcCred(pid));
		} catch (SigarException e) {}

		try {
			println("credname = " + this.sigar.getProcCredName(pid));
		} catch (SigarException e) {}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				new ProcInfo().processCommand(args);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (!flag) test01(args);
		if (flag) test01(new String[] { "5852", "8024", "692" });
	}
}
