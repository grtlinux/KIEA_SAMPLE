package kiea.priv.zzz.etc.hypericSIGAR.t02;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hyperic.sigar.ProcCredName;
import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.ProcState;
import org.hyperic.sigar.ProcTime;
import org.hyperic.sigar.ProcUtil;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarProxy;

public class Ps extends SigarCommandBase
{
	private static boolean flag = true;

	public Ps(Shell shell)
	{
		super(shell);
	}
	
	public Ps()
	{
		super();
	}
	
	protected boolean validateArgs(String[] args)
	{
		return true;
	}
	
	public String getSyntaxArgs()
	{
		return "[pid|query]";
	}
	
	public String getUsageShort()
	{
		return "Show process status";
	}
	
	public boolean isPidCompleter()
	{
		return true;
	}
	
	public void output(String[] args) throws SigarException
	{
		long[] pids;
		if (args.length == 0) {
			pids = this.proxy.getProcList();
		} else {
			pids = this.shell.findPids(args);
		}
		
		for (int i=0; i < pids.length; i++) {
			long pid = pids[i];
			try {
				output(pid);
			} catch (SigarException e) {
				this.err.println("Exception getting process info for " + pid + ": " + e.getMessage());
			}
		}
	}
	
	public void output(long pid) throws SigarException
	{
		println(join(getInfo(this.proxy, pid)));
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public static String join(List<String> info)
	{
		StringBuffer buf = new StringBuffer();
		Iterator<String> it = info.iterator();
		boolean hasNext = it.hasNext();
		while (hasNext) {
			buf.append((String) it.next());
			hasNext = it.hasNext();
			if (hasNext)
				buf.append("\t");
		}
		
		return buf.toString();
	}
	
	@SuppressWarnings("deprecation")
	public static List<String> getInfo(SigarProxy sigar, long pid) throws SigarException
	{
		ProcState state = sigar.getProcState(pid);
		ProcTime time = null;
		String unknown = "???";
		
		List<String> info = new ArrayList<String>();
		info.add(String.valueOf(pid));
		
		try {
			ProcCredName cred = sigar.getProcCredName(pid);
			info.add(cred.getUser());
		} catch (SigarException e) {
			info.add(unknown);
		}
		
		try {
			time = sigar.getProcTime(pid);
			info.add(getStartTime(time.getStartTime()));
		} catch (SigarException e) {
			info.add(unknown);
		}
		
		try {
			ProcMem mem = sigar.getProcMem(pid);
			info.add(Sigar.formatSize(mem.getSize()));
			info.add(Sigar.formatSize(mem.getRss()));
			info.add(Sigar.formatSize(mem.getShare()));
		} catch (SigarException e) {
			info.add(unknown);
		}
		
		info.add(String.valueOf(state.getState()));
		
		if (time != null) {
			info.add(getCpuTime(time));
		} else {
			info.add(unknown);
		}
		
		String name = ProcUtil.getDescription(sigar, pid);
		info.add(name);
		
		//name = ProcUtil.getJavaMainClass(sigar, pid);
		//info.add(name);

		return info;
	}
	
	public static String getCpuTime(long total)
	{
		long t = total / 1000;
		return t/60 + ":" + t%60;
	}
	
	public static String getCpuTime(ProcTime time)
	{
		return getCpuTime(time.getTotal());
	}
	
	private static String getStartTime(long time)
	{
		if (time == 0) {
			return "00:00";
		}
		long timeNow = System.currentTimeMillis();
		String fmt = "MMMd";
		
		if ((timeNow - time) < ((60*60*24) * 1000)) {
			fmt = "HH:mm";
		}
		
		return new SimpleDateFormat(fmt).format(new Date(time));
	}
	

	
	
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				new Ps().processCommand(args);
			} catch (Exception e) {
				// e.printStackTrace();
				throw e;
			}
		}
	}
	
	private static void test02(String[] args) throws Exception
	{
		if (flag) {
			try {
				Sigar sigar = new Sigar();
				
				String[] arrStr = sigar.getProcArgs(3816);
				for (String str : arrStr) {
					System.out.println(">" + str);
				}
				
			} catch (SigarException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println();
		
		if (flag) {
			try {
				Sigar sigar = new Sigar();
				
				String[] arrStr = sigar.getProcArgs(7636);
				for (String str : arrStr) {
					System.out.println(">" + str);
				}
				
			} catch (SigarException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (!flag) test01(args);
		if (!flag) test01(new String[] { "6660" });
		
		if (flag) test02(args);
	}
}
