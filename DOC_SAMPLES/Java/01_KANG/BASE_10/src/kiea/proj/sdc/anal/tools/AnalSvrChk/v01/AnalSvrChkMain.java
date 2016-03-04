package kiea.proj.sdc.anal.tools.AnalSvrChk.v01;

import java.net.Socket;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.util.ServiceInetdProperties;

public class AnalSvrChkMain
{
	private static final String TYPE = "PRIMARY";
	private static final String HOST = "127.0.0.1";
	private static final String PORT = "0";
	
	private ServiceInetdProperties prop = null;
	private String strType = null;
	private String strHost = null;
	private String strPort = null;
	
	private AnalSvrChkMain()
	{
		if (Flag.TRUE) {
			this.prop = ServiceInetdProperties.getInstance();
			this.strType = this.prop.get("anal.server.type", TYPE);
			this.strHost = this.prop.get("anal.server.primary.host", HOST);
			this.strPort = this.prop.get("anal.server.primary.port", PORT);
			
			if (!Flag.TRUE) Print.println("ANAL_SVR_CHK : [%s][%s][%s]", this.strType, this.strHost, this.strPort);
		}
	}
	
	/**
	 * 
	 * 1) return = 0 : 실행하지 않는다.
	 *     type = S(SECONDARY)
	 *     AND 접속chk가 정상
	 * 
	 * 2) return = 1 : 실행한다.
	 *     type = P(PRIMARY)
	 *     OR
	 *     type = S(SECONDARY)
	 *     AND 접속chk가 안된다.
	 *     
	 * 
	 * @return
	 */
	public int getReturnCode()
	{
		int ret = 0;
		
		if (Flag.TRUE) {
			String msg = null;
			long time1 = System.nanoTime();

			if ("P".equalsIgnoreCase(this.strType.substring(0, 1))) {
				ret = 1;
				msg = "PRIMARY SYSTEM";
				try { Thread.sleep(10); } catch (InterruptedException e) {};
			} else {

				try {
					Socket socket = new Socket(strHost, Integer.parseInt(this.strPort));
					socket.close();
					ret = 0;
					msg = "Connection OK !!! ";
					
				} catch (Exception e) {
					// e.printStackTrace();
					ret = 1;
					msg = e.getMessage();
				}
				
			}
			
			long time2 = System.nanoTime();

			if (Flag.TRUE) Print.println("ANAL_SVR_CHK RET=%d <= %s : [%s:%s:%s] (%d ms)", ret, msg, this.strType, this.strHost, this.strPort, (time2 - time1)/1000/1000);
		}
		
		return ret;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static AnalSvrChkMain instance = null;
	
	/**
	 * Singleton
	 * 
	 * @return
	 */
	public static synchronized AnalSvrChkMain getInstance()
	{
		if (instance == null) {
			instance = new AnalSvrChkMain();
		}
		
		return instance;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args)
	{
		if (!Flag.TRUE) {
			SystemProperties.setTesting("010");
		}

		if (Flag.TRUE) {
			int ret = AnalSvrChkMain.getInstance().getReturnCode();
			if (!Flag.TRUE) Print.println("RET=" + ret);
			System.exit(ret);
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01(args);
	}
}
