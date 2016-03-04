package kiea.priv.zzz.etc.hypericSIGAR.t02;

import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarNotImplementedException;

public class ShowArgs extends SigarCommandBase
{
	private static boolean flag = true;
	
	public ShowArgs(Shell shell)
	{
		super(shell);
	}
	
	public ShowArgs()
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
		return "Show process command line arguments";
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
				println("pid = " + pids[i]);
				output(pids[i]);
			} catch (SigarException e) {
				println(e.getMessage());
			}
			println("\n-------------------------------------------\n");
		}
	}
	
	private void output(long pid) throws SigarException
	{
		String[] argv = this.proxy.getProcArgs(pid);
		
		try {
			String exe = this.proxy.getProcExe(pid).getName();
			println("exe = " + exe);
		} catch (SigarNotImplementedException e) {
		} catch (SigarException e) {
			println("exe = ???");
		}
		
		try {
			String cwd = this.proxy.getProcExe(pid).getCwd();
			println("cwd = " + cwd);
		} catch (SigarNotImplementedException e) {
		} catch (SigarException e) {
			println("cwd = ???");
		}
		
		for (int i=0; i < argv.length; i++) {
			println("    " + i + " => " + argv[i] + " <=");
		}
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				new ShowArgs().processCommand(args);
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
