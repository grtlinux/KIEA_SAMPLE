package kiea.priv.zzz.etc.hypericSIGAR.t02;

import org.hyperic.sigar.SigarException;

public class Kill extends SigarCommandBase
{
	private static boolean flag = true;
	
	public Kill(Shell shell)
	{
		super(shell);
	}
	
	public Kill()
	{
		super();
	}
	
	@Override
	public boolean validateArgs(String[] args)
	{
		return args.length == 1 || args.length == 2;
	}
	
	@Override
	public String getSyntaxArgs()
	{
		return "[signal <query|pid>";
	}
	
	@Override
	public String getUsageShort()
	{
		return "Send siganl to a process";
	}
	
	@Override
	public boolean isPidCompleter()
	{
		return true;
	}
	
	@Override
	public void output(String[] args) throws SigarException
	{
		String signal = "SIGTERM";
		long[] pids;
		String query;
		
		if (args.length == 2) {
			signal = args[0];
			query = args[1];
		} else {
			query = args[0];
		}
		
		pids = this.shell.findPids(new String[] { query });
		
		for (int i=0; i < pids.length; i++) {
			println("kill " + signal + " " + pids[i]);
			this.sigar.kill(pids[i], signal);
		}
	}
	
	
	
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				new Kill().processCommand(args);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (!flag) test01(args);
		if (!flag) test01(new String[] { "720" });
		if (flag) test01(new String[] { "Exe.Name.ct=cmd.exe" });
	}
}
