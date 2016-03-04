package kiea.priv.zzz.etc.hypericSIGAR.t02;

import java.util.Arrays;
import java.util.List;

import org.hyperic.sigar.win32.Service;
import org.hyperic.sigar.win32.Win32Exception;

public class ServiceStatus
{
	private static boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void printService(String name) throws Win32Exception
	{
		/*
		 * 권한 설정이 필요하다고 생각함.
		 * 되는 것만 출력
		 */
		try {
			Service service = new Service(name);
			System.out.println(">" + service.getStatusString());
			service.close();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			List<String> listServices;
			
			if (args.length == 0) {
				
				listServices = Service.getServiceNames();
				
				if (flag) {
					// 확인출력
					for (String service : listServices) {
						System.out.println(">" + service);
					}
				}
			} else {
				listServices = Arrays.asList(args);
			}
			
			for (String service : listServices) {
				printService(service);
			}
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (flag) test01(args);
	}
}
