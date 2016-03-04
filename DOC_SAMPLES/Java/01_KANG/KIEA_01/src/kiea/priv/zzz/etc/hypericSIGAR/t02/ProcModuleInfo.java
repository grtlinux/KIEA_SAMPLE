package kiea.priv.zzz.etc.hypericSIGAR.t02;

import java.util.List;

import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarNotImplementedException;

public class ProcModuleInfo extends SigarCommandBase
{
	private static boolean flag = true;
	
	public ProcModuleInfo(Shell shell)
	{
		super(shell);
	}
	
	public ProcModuleInfo()
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
		return "Display process module info";
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
			} catch (SigarException e) {
				println("(" + e.getMessage() + ")");
			}
			println("\n---------------------------------------------------\n");
		}
	}
	
	private void output(long pid) throws SigarException
	{
		println("pid = " + pid);
		
		try {
			@SuppressWarnings("unchecked")
			List<String> modules = this.sigar.getProcModules(pid);
			
			for (int i=0; i < modules.size(); i++) {
				println(i + " = " + modules.get(i));
			}
		} catch (SigarNotImplementedException e) {
			throw e;
		} catch (SigarException e) {
			println("[" + e.getMessage() + "]");
		}
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				new ProcModuleInfo().processCommand(args);
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
