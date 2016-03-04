package kiea.priv.zzz.etc.hypericSIGAR.t02;

import org.hyperic.sigar.DirUsage;
import org.hyperic.sigar.SigarException;

public class Du extends SigarCommandBase
{
	private static boolean flag = true;
	
	public Du(Shell shell)
	{
		super(shell);
	}
	
	public Du()
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
		return "[args]";
	}
	
	@Override
	public String getUsageShort()
	{
		return "Display usage for a directory recursively";
	}
	
	@Override
	public boolean isPidCompleter()
	{
		return true;
	}
	
	@Override
	public void output(String[] args) throws SigarException
	{
		String dir = args[0];
		DirUsage du = this.sigar.getDirUsage(dir);
		println(String.format("%,d", du.getDiskUsage()) + "\t" + dir);
	}
	
	
	
	
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				new Du().processCommand(args);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (flag) test01(new String[] { "D:/" });
	}
}
