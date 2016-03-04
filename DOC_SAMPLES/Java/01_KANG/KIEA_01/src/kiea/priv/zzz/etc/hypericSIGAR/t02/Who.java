package kiea.priv.zzz.etc.hypericSIGAR.t02;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hyperic.sigar.SigarException;

public class Who extends SigarCommandBase
{
	private static boolean flag = true;
	
	public Who(Shell shell)
	{
		super(shell);
	}
	
	public Who()
	{
		super();
	}
	
	public String getUsageShort()
	{
		return "Show who is logged on";
	}
	
	private String getTime(long time)
	{
		if (time == 0) {
			return "unknown";
		}
		return new SimpleDateFormat("MMM dd HH:mm").format(new Date(time));
	}
	
	public void output(String[] args) throws SigarException
	{
		org.hyperic.sigar.Who[] who = this.sigar.getWhoList();
		if (flag) System.out.println("> who.length = " + who.length);
		for (int i=0; i < who.length; i++) {
			String host = who[i].getHost();
			if (host.length() != 0) {
				host = "(" + host + ")";
			}
			
			printf(new String[] {
				who[i].getUser(),
				who[i].getDevice(),
				getTime(who[i].getTime() * 1000),
				host
			});
			
			if (flag) System.out.println(">" + who[i].getUser()
					+ ", " + who[i].getDevice()
					+ ", " + getTime(who[i].getTime() * 1000)
					+ ", " + host);
		}
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				new Who().processCommand(args);
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
