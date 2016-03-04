package kiea.priv.zzz.etc.hypericSIGAR.t02;

import org.hyperic.sigar.ProcExe;
import org.hyperic.sigar.ProcFd;
import org.hyperic.sigar.ProcState;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarPermissionDeniedException;

public class ProcFileInfo extends SigarCommandBase
{
	private static boolean flag = true;

	public ProcFileInfo(Shell shell)
	{
		super(shell);
	}
	
	public ProcFileInfo()
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
		return "Display process file info";
	}
	
	@Override
	public boolean isPidCompleter()
	{
		return true;
	}
	
	@Override
	public void output(String[] args) throws SigarException
	{
		long[] pids = this.shell.findPids(args);
		
		for (int i=0; i < pids.length; i++) {
			try {
				output(pids[i]);
			} catch (SigarPermissionDeniedException e) {
				println(this.shell.getUserDeniedMessage(pids[i]));
			} catch (SigarException e) {
				println("(" + e.getMessage() + "0");
			}
			println("\n---------------------------------------------\n");
		}
	}
	
	private void output(long pid) throws SigarException
	{
		println("pid = " + pid);
		
		try {
			ProcFd fd = sigar.getProcFd(pid);
			println("open file descriptors = " + fd.getTotal());
		} catch (SigarException e) {
			//e.printStackTrace();
		}
		
		ProcState state = sigar.getProcState(pid);
		println("ProcState.name = " + state.getName());
		
		ProcExe exe = sigar.getProcExe(pid);
		String name = exe.getName();
		if (name.length() == 0) {
			name = "unknown";
		}
		println("name = " + name);
		
		println("cwd = " + exe.getCwd());
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				new ProcFileInfo().processCommand(args);
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
