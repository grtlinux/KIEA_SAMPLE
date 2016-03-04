package kiea.priv.zzz.etc.hypericSIGAR.t02;

import org.hyperic.sigar.Mem;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;

public class Free extends SigarCommandBase
{
	private static boolean flag = true;
	
	public Free(Shell shell)
	{
		super(shell);
	}
	
	public Free()
	{
		super();
	}
	
	@Override
	public boolean validateArgs(String[] args)
	{
		return true;
	}
	
	@Override
	public String getSyntaxArgs()
	{
		return "[args]";
	}
	
	@Override
	public String getUsageShort()
	{
		return "Display information about free and used memory";
	}
	
	@Override
	public boolean isPidCompleter()
	{
		return true;
	}
	
	@Override
	public void output(String[] args) throws SigarException
	{
		Mem mem = this.sigar.getMem();
		Swap swap = this.sigar.getSwap();
		
		Object[] header = new Object[] { "total", "used", "free" };
		
		Object[] memRow = new Object[] {
			format(mem.getTotal()),
			format(mem.getUsed()),
			format(mem.getFree()),
		};
		
		Object[] actualRow = new Object[] {
			format(mem.getActualUsed()),
			format(mem.getActualFree()),
		};
		
		Object[] swapRow = new Object[] {
			format(swap.getTotal()),
			format(swap.getUsed()),
			format(swap.getFree()),
		};
		
		printf("%18s %10s %10s", header);
		
		printf("Mem:    %10ld %10ld %10ld", memRow);
		
		// e.g. linux
		if ((mem.getUsed() != mem.getActualUsed()) || (mem.getFree() != mem.getActualFree())) {
			printf("-/+ buffers/cache: " + "%10ld %10d", actualRow);
		}
		
		printf("Swap:   %10ld %10ld %10ld", swapRow);
		printf("RAM:    %10ls", new Object[] { mem.getRam() + "MG" });
	}
	
	private static Long format(long value)
	{
		return new Long(value / 1024);
	}
	
	
	
	
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				new Free().processCommand(args);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (flag) test01(args);
	}
}
