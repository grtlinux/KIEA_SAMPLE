package kiea.priv.zzz.etc.hypericSIGAR.t02;

import java.util.Arrays;

import org.hyperic.sigar.CpuTimer;
import org.hyperic.sigar.SigarException;

public class Time extends SigarCommandBase
{
	private static boolean flag = true;
	
	public Time(Shell shell)
	{
		super(shell);
	}
	
	public Time()
	{
		super();
	}
	
	public String getUsageShort()
	{
		return "Dislplay Time command";
	}
	
	public String getSyntaxArgs()
	{
		return "[command] [.....]";
	}
	
	protected boolean validateArgs(String[] args)
	{
		return args.length >= 1;
	}
	
	public void output(String[] args) throws SigarException
	{
		if (flag) System.out.println(">" + Arrays.asList(args));
		
		boolean isInteractive = this.shell.isInteractive();
		// turn off paging.
		this.shell.setInteractive(false);
		CpuTimer cpu = new CpuTimer(this.sigar);
		
		int num;
		
		if (Character.isDigit(args[0].charAt(0))) {
			num = Integer.parseInt(args[0]);
			String[] xargs = new String[args.length - 1];
			System.arraycopy(args, 1, xargs, 0, xargs.length);
			args = xargs;
		} else {
			num = 1;
		}
		
		cpu.start();
		
		try {
			for (int i=0; i < num; i++) {
				this.shell.handleCommand("time " + args[0], args);  // important
			}
		} catch (Exception e) {
			// e.printStackTrace();
			// throw e;
		} finally {
			this.shell.setInteractive(isInteractive);
		}
		cpu.stop();
		cpu.list(System.out);
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				new Time().processCommand(args);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (!flag) test01(args);
		if (flag) test01(new String[] { "cmd", "2" });
	}
}
