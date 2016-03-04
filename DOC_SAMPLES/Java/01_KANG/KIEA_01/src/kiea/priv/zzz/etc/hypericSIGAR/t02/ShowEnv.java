package kiea.priv.zzz.etc.hypericSIGAR.t02;

import java.util.Map;

import org.hyperic.sigar.SigarException;

public class ShowEnv extends SigarCommandBase
{
	private static boolean flag = true;
	
	public ShowEnv(Shell shell)
	{
		super(shell);
	}
	
	public ShowEnv()
	{
		super();
	}
	
	@Override
	public String getUsageShort()
	{
		return "Show process environment";
	}
	
	@Override
	protected boolean validateArgs(String[] args)
	{
		return true;
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
				println("pid=" + pids[i]);
				output(pids[i]);
			} catch (SigarException e) {
				println(e.getMessage());
			}
			
			println("\n---------------------------------------\n");
		}
	}
	
	private void output(long pid) throws SigarException
	{
		@SuppressWarnings("unchecked")
		Map<String, String> env = (Map<String, String>) this.proxy.getProcEnv(pid);
		
		for (Map.Entry<String, String> entry : env.entrySet()) {
			println(entry.getKey() + "=" + entry.getValue());
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				new ShowEnv().processCommand(args);
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
