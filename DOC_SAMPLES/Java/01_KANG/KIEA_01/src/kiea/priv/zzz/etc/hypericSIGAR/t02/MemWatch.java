package kiea.priv.zzz.etc.hypericSIGAR.t02;

import org.hyperic.sigar.ProcMem;
import org.hyperic.sigar.Sigar;

public class MemWatch
{
	private static boolean flag = true;
	
	private static final int SLEEP_TIME = 1000 * 2;
	
	///////////////////////////////////////////////////////////////////////////
	
	private static StringBuffer diff(ProcMem last, ProcMem cur)
	{
		StringBuffer sb = new StringBuffer();
		
		long diff = cur.getSize() - last.getSize();
		if (diff != 0) {
			sb.append("size=" + diff);
		}
		
		diff = cur.getResident() - last.getResident();
		if (diff != 0) {
			sb.append(", resident=" + diff);
		}
		
		diff = cur.getShare() - last.getShare();
		if (diff != 0) {
			sb.append(", share=" + diff);
		}
		
		return sb;
	}
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			Sigar sigar = new Sigar();
			
			if (args.length != 1) {
				throw new Exception("Usage : MemWatch pid");
			}
			
			long pid = Long.parseLong(args[0]);
			long lastTime = System.currentTimeMillis();
			
			ProcMem last = sigar.getProcMem(pid);
			
			while (true) {
				ProcMem curr = sigar.getProcMem(pid);
				
				StringBuffer sb = diff(last, curr);
				
				if (sb.length() == 0) {
					System.out.println("no change (size=" + Sigar.formatSize(curr.getSize()) + ")");
				} else {
					long currTime = System.currentTimeMillis();
					long timeDiff = currTime - lastTime;
					lastTime = currTime;
					sb.append(" after " + timeDiff + " ms");
					System.out.println(sb);
				}
				
				last = curr;
				
				try {
					Thread.sleep(SLEEP_TIME);
				} catch (InterruptedException e) {
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (!flag) test01(args);
		if (flag) test01(new String[] { "4684" });
	}
}
