package kiea.priv.zzz.etc.hypericSIGAR.t02;

import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.SigarException;

public class NetInfo extends SigarCommandBase
{
	private static String tobe = "DATE.2015.01.28 : 나중에 프로그램을 다시한번 코딩과 확인을 필요로 함. 반드시.";
	private static boolean flag = true;
	
	public NetInfo(Shell shell)
	{
		super(shell);
	}
	
	public NetInfo()
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
		return "Display network info";
	}
	
	@Override
	public void output(String[] args) throws SigarException
	{
		NetInterfaceConfig config = this.sigar.getNetInterfaceConfig(null);
		
		println("primary interface.........." + config.getName());
		println("primary ip address........." + config.getAddress());
		println("primary max address........" + config.getHwaddr());
		println("primary network............" + config.getNetmask());
		
		org.hyperic.sigar.NetInfo info = this.sigar.getNetInfo();
		println("host name.................." + info.getHostName());
		println("domain name................" + info.getDomainName());
		println("default gateway............" + info.getDefaultGateway());
		println("primary dns................" + info.getPrimaryDns());
		println("secondary dns.............." + info.getSecondaryDns());
	}
	
	
	
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				new NetInfo().processCommand(args);
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
