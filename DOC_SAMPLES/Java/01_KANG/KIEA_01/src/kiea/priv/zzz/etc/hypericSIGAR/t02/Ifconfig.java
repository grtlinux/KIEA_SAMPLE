package kiea.priv.zzz.etc.hypericSIGAR.t02;

import java.util.Arrays;
import java.util.Collection;

import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class Ifconfig extends SigarCommandBase
{
	private static boolean flag = true;
	
	public Ifconfig(Shell shell)
	{
		super(shell);
	}
	
	public Ifconfig()
	{
		super();
	}
	
	@Override
	public boolean validateArgs(String[] args)
	{
		return args.length <= 1;
	}
	
	@Override
	public String getSyntaxArgs()
	{
		return "[interface]";
	}
	
	@Override
	public String getUsageShort()
	{
		return "Network interface information";
	}
	
	@Override
	public boolean isPidCompleter()
	{
		return true;
	}
	
	@Override
	public Collection<?> getCompletions()
	{
		String[] ifNames;
		
		try {
			ifNames = this.proxy.getNetInterfaceList();
		} catch (SigarException e) {
			return super.getCompletions();
		}
		
		return Arrays.asList(ifNames);
	}
	
	@Override
	public void output(String[] args) throws SigarException
	{
		String[] ifNames;
		
		if (args.length == 1) {
			ifNames = args;
		} else {
			ifNames = this.proxy.getNetInterfaceList();
		}
		
		for (int i=0; i < ifNames.length; i++) {
			try {
				output(ifNames[i]);
			} catch (SigarException e) {
				println(ifNames[i] + "\t" + e.getMessage());
			}
			
			System.out.println("\n-------------------------------------------------------\n");
		}
	}
	
	private void output(String name) throws SigarException
	{
		NetInterfaceConfig ifconfig = this.sigar.getNetInterfaceConfig(name);
		long flags = ifconfig.getFlags();
		
		String hwaddr = "";
		if (!NetFlags.NULL_HWADDR.equals(ifconfig.getHwaddr())) {
			hwaddr = " HWaddr " + ifconfig.getHwaddr();
		}
		
		if (!ifconfig.getName().equals(ifconfig.getDescription())) {
			println(ifconfig.getDescription());
		}
		
		println(ifconfig.getName() + "\t" + "Link encap:" + ifconfig.getType() + hwaddr);
		
		String ptp = "";
		if ((flags & NetFlags.IFF_POINTOPOINT) > 0) {
			ptp = " P-t-P:" + ifconfig.getDestination();
		}
		
		String bcast = "";
		if ((flags & NetFlags.IFF_BROADCAST) > 0) {
			bcast = " Bcast:" + ifconfig.getBroadcast();
		}
		
		println("\t" + "inet addr:" + ifconfig.getAddress() + ptp + bcast + " Mask:" + ifconfig.getNetmask());
		
		println("\t" + NetFlags.getIfFlagsString(flags) + " MTU:" + ifconfig.getMtu() + "  Metric:" + ifconfig.getMetric());
		
		try {
			NetInterfaceStat ifstat = this.sigar.getNetInterfaceStat(name);
			
			println("\t"
					+ "TX packets:" + ifstat.getRxPackets()
					+ " errors:" + ifstat.getRxErrors()
					+ " dropped:" + ifstat.getRxDropped()
					+ " overruns:" + ifstat.getRxOverruns()
					+ " frame:" + ifstat.getRxFrame()
					);
			
			println("\t"
					+ "Tx packets:" + ifstat.getTxPackets()
					+ " errors:" + ifstat.getTxErrors()
					+ " dropped:" + ifstat.getTxDropped()
					+ " overruns:" + ifstat.getTxOverruns()
					+ " carrier:" + ifstat.getTxCarrier()
					);
			
			println("\t"
					+ "collisions:" + ifstat.getTxCollisions()
					);
			
			long rxBytes = ifstat.getRxBytes();
			long txBytes = ifstat.getTxBytes();
			
			println("\t" + "RX bytes:" + rxBytes + " (" + Sigar.formatSize(rxBytes) + ")" + "  " + "TX bytes:" + txBytes + " (" + Sigar.formatSize(txBytes) + ")");
			
		} catch (SigarException e) {
			// e.printStackTrace();
			// throw e;
		}
	}
	
	
	
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			try {
				new Ifconfig().processCommand(args);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (flag) test01(args);
		if (!flag) test01(new String[] { "eth2" });
	}
}
