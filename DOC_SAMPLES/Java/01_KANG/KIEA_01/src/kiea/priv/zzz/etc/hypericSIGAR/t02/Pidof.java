package kiea.priv.zzz.etc.hypericSIGAR.t02;

import org.hyperic.sigar.SigarException;

public class Pidof extends SigarCommandBase
{
	private static boolean flag = true;

	public Pidof(Shell shell)
	{
		super(shell);
	}
	
	public Pidof()
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
		return "Find the process ID of a running program.";
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
			this.out.print(pids[i]);
			this.out.print(' ');
		}
		this.out.println();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				new Pidof().processCommand(args);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (!flag) test01(args);
		if (flag) test01(new String[] { "5852", "8024", "692", "123456", "1608" });
	}
}
