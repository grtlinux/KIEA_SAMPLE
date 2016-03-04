package kiea.priv.zzz.etc.hypericSIGAR.t02;

import java.util.Arrays;

import org.hyperic.sigar.ResourceLimit;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.jmx.SigarInvokerJMX;

public class Ulimit extends SigarCommandBase
{
	private static boolean flag = true;
	
	private SigarInvokerJMX invoker;
	private String mode;
	
	public Ulimit(Shell shell)
	{
		super(shell);
	}
	
	public Ulimit()
	{
		super();
	}
	
	public String getUsageShort()
	{
		return "Display system resource limits";
	}
	
	protected boolean validateArgs(String[] args)
	{
		return true;
	}
	
	private static String format(long val)
	{
		if (val == ResourceLimit.INFINITY()) {
			return "unlimited";
		} else {
			return String.valueOf(val);
		}
	}
	
	private String getValue(String attr) throws SigarException
	{
		Long val = (Long) this.invoker.invoke(attr + this.mode);
		return format(val.longValue());
	}
	
	public void output(String[] args) throws SigarException
	{
		if (flag) System.out.println(">" + Arrays.asList(args));
		
		this.mode = "Cur";
		this.invoker = SigarInvokerJMX.getInstance(this.proxy, "Type=ResourceLimit");
		
		for (int i=0; i < args.length; i++) {
			String arg = args[i];
			if (arg.equals("-H")) {
				this.mode = "Max";
			} else if (arg.equals("-S")) {
				this.mode = "Cur";
			} else {
				throw new SigarException("Unknown argument: " + arg);
			}
		}
		System.out.println("core file size........" + getValue("Core"));
		System.out.println("data seg size........." + getValue("Data"));
		System.out.println("file size............." + getValue("FileSize"));
		System.out.println("pipe size............." + getValue("PipeSize"));
		System.out.println("max memory size......." + getValue("Memory"));
		System.out.println("open files............" + getValue("OpenFiles"));
		System.out.println("stack size............" + getValue("Stack"));
		System.out.println("cpu time.............." + getValue("Cpu"));
		System.out.println("max user processes...." + getValue("Processes"));
		System.out.println("virtual memory........" + getValue("VirtualMemory"));
		System.out.println("-----------------------------------------------------");
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				new Ulimit().processCommand(args);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (!flag) test01(args);
		if (flag) test01(new String[] { "-H" });
		if (flag) test01(new String[] { "-S" });
	}
}
