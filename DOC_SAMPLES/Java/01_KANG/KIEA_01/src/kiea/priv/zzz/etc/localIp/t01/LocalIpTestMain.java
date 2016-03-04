package kiea.priv.zzz.etc.localIp.t01;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class LocalIpTestMain
{
	private static boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args)
	{
		if (flag) {
			/*
			 * 골라서 출력
			 */
			try {
				String ip = null;
				
				boolean isLoopBack = true;
				Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
				
				while (en.hasMoreElements()) {
					NetworkInterface ni = en.nextElement();
					if (ni.isLoopback())
						continue;
					
					Enumeration<InetAddress> inetAddresses = ni.getInetAddresses();
					while (inetAddresses.hasMoreElements()) {
						InetAddress ia = inetAddresses.nextElement();
						if (ia.getHostAddress() != null && ia.getHostAddress().indexOf(".") != -1) {
							ip = ia.getHostAddress();
							isLoopBack = false;
							break;
						}
					}
					
					if (!isLoopBack)
						break;
				}
				
				System.out.println("IP > " + ip);
				
			} catch (SocketException e) {
				e.printStackTrace();
			}
		}
		
		if (flag) {
			/*
			 * 모두출력
			 */
			try {
				Enumeration<NetworkInterface> nifs = NetworkInterface.getNetworkInterfaces();
				
				while (nifs.hasMoreElements()) {
					NetworkInterface nif = nifs.nextElement();
					
					Enumeration<InetAddress> inetAddress = nif.getInetAddresses();
					while (inetAddress.hasMoreElements()) {
						InetAddress ia = inetAddress.nextElement();
						
						if (flag) System.out.println();
						
						if (ia.isLoopbackAddress()) {
							if (flag) System.out.println(" * LOOPBACK address");
						}
						if (ia.isLinkLocalAddress()) {
							if (flag) System.out.println(" * LINK LOCAL address");
						}
						if (ia.isSiteLocalAddress()) {
							if (flag) System.out.println(" * SITE LOCAL address       -> " + ia.getHostAddress());
						}
						
						if (flag) System.out.println("> " + ia.getHostAddress());

						if (!flag && ia.getHostAddress() != null && ia.getHostAddress().indexOf(".") != -1) {
							if (!flag) System.out.println("> " + ia.getHostAddress() + " [" + ia.getHostName() + "]");
							if (flag) System.out.println("> " + ia.getHostAddress());
						}
					}
				}
				
			} catch (SocketException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test02(String[] args)
	{
		if (flag) {
			try {
				InetAddress ia = InetAddress.getLocalHost();
				System.out.println("Current Host Name       : " + ia.getHostName());
				System.out.println("Current Host IP Address : " + ia.getHostAddress());
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		
		if (flag) {
			try {
				InetAddress ia = InetAddress.getByName("java.sun.com");
				System.out.println("java.sun.com Host Name       : " + ia.getHostName());
				System.out.println("java.sun.com Host IP Address : " + ia.getHostAddress());
				System.out.println("java.sun.com => " + ia);
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		
		if (flag) {
			try {
				InetAddress[] ias = InetAddress.getAllByName("www.daum.net");
				
				for (InetAddress ia : ias) {
					System.out.println(">" + ia.getHostAddress());
				}
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		
		if (flag) {
			/*
			 * MAC Address : 오류. 틀림
			 */
			try {
				InetAddress ia = InetAddress.getLocalHost();
				if (flag) System.out.println("IP : " + ia.getHostAddress());
				
				NetworkInterface ni = NetworkInterface.getByInetAddress(ia);
				byte[] mac = ni.getHardwareAddress();
				int lenMac = mac.length;
				StringBuffer sb = new StringBuffer();
				for (int i=0; i < lenMac; i++) {
					if (i > 0)
						sb.append(":");
					sb.append(String.format("%02X", mac[i]));
				}
				
				if (flag) System.out.println("MAC : " + sb.toString());
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (SocketException e) {
				e.printStackTrace();
			}
		}
		
		if (flag) {
			/*
			 * 모두출력
			 */
			try {
				Enumeration<NetworkInterface> nifs = NetworkInterface.getNetworkInterfaces();
				
				while (nifs.hasMoreElements()) {
					NetworkInterface nif = nifs.nextElement();
					
					Enumeration<InetAddress> inetAddress = nif.getInetAddresses();
					while (inetAddress.hasMoreElements()) {
						InetAddress ia = inetAddress.nextElement();
						if (ia.isSiteLocalAddress()) {
							byte[] mac = nif.getHardwareAddress();
							StringBuffer sb = new StringBuffer();
							for (int i=0; i < mac.length; i++) {
								if (i > 0)
									sb.append(":");
								sb.append(String.format("%02X", mac[i]));
							}
							
							if (flag) System.out.println(" * SITE LOCAL address       -> " + ia.getHostAddress() + " [" + sb.toString() + "]");
						}
					}
				}
				
			} catch (SocketException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (flag) test01(args);
		if (flag) test02(args);
	}
}
