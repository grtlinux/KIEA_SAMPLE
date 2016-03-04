package kiea.proj.sdc.anal.tools.AnalCsv.v01;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.common.DefValue;
import kiea.proj.sdc.anal.util.AnalProperties;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.ServiceInetdProperties;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class AnalCsvServer
{
	private static final String PORT = "12010";
	
	private ServiceInetdProperties prop = null;
	private String logFile = null;
	private String strPort = null;
	private String[] allowIp = null;

	private static ServerSocket serverSocket = null;
	
	public AnalCsvServer()
	{
		if (Flag.TRUE) {
			this.prop = ServiceInetdProperties.getInstance();
			this.logFile = this.prop.get("anal.sendquery.log.file");
			this.strPort = this.prop.get("anal.sendquery.listen.port");
			
			this.allowIp = this.prop.get("anal.sendquery.allow.host").split(",");
			for (int i=0; i < this.allowIp.length; i++)
				this.allowIp[i] = this.allowIp[i].trim();
		}
		
		if (Flag.TRUE) {
			/*
			 * 반드시 먼저 선언해야 할 것
			 * 1. base path : ..../qaf 까지
			 * 2. 사용할 로그정보
			 */
			BasePath.getInstance();

			try {
				Logger.getInstance(AnalProperties.getInstance().getServiceBasePath(), null, this.logFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void execute()
	{
		if (Flag.TRUE) {

			try {
				/*
				 * Create ServerSocket
				 */
				serverSocket = new ServerSocket(Integer.parseInt(this.strPort));
				Logger.info("STATUS : ServerSocket Listening, port is %s", this.strPort);
				
				while (true) {
					/*
					 * listen and wait for a connection
					 */
					Socket socket = serverSocket.accept();
					String address = socket.getInetAddress().getHostAddress();
					
					if (Flag.TRUE) {
						boolean flgDeny = true;
						
						for (String ip : allowIp) {
							if (address.equals(ip)) {
								flgDeny = false;
								break;
							}
						}
						
						if (flgDeny) {
							socket.close();

							Logger.info("STATUS : deny the address [%s], and close the socket...", address);
							if (!Flag.TRUE) Print.println("STATUS : deny the address [%s], and close the socket...", address);
							continue;
						}
					}
					
					Logger.info("STATUS : event of connection from manager...[%s]", address);
					if (!Flag.TRUE) Print.println("STATUS : event of connection from manager...[%s]", address);

					if (Flag.TRUE) {
						/*
						 * if a connection, create CommandThread and do the job...
						 */
						SendToOracle cmdThread = new SendToOracle(socket);
						cmdThread.start();
					} else {
						if (Flag.TRUE) {
							/*
							 * TODO : 2014.10.07 : FOR TESTING
							 * 
							 * InetAddress inet = InetAddress.getByName("naver.com");
							 * InetAddress inet = InetAddress.getByName("125.209.222.141");
							 * System.out.println(inet.getHostAddress());  // 125.209.222.141
							 * 
							 * InetAddress[] list = InetAddress.getAllByName("naver.com");
							 * for (InetAddress inet : list)
							 *     System.out.println(inet.getHostAddress());
							 * 
							 */
							InetAddress inet = socket.getInetAddress();
							Print.println("inet.getCanonicalHostName() = %s", inet.getCanonicalHostName()); // KangSeok-PC
							Print.println("inet.getHostAddress      () = %s", inet.getHostAddress      ()); // 11.94.177.34
							Print.println("inet.getHostName         () = %s", inet.getHostName         ()); // KangSeok-PC

							byte[] num = inet.getAddress();
							Print.println("%d.%d.%d.%d", num[0] & 0xFF, num[1] & 0xFF, num[2] & 0xFF, num[3] & 0xFF);
						}
						
						socket.close();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				try {
					if (serverSocket != null)
						serverSocket.close();
					
				} catch (Exception e2) {}
			}
			
			Logger.exit();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * 서버소켓을 생성하여 manager로부터의 실행요청을 받아
	 * cmdThread를 생성하여 처리하는 단일 프로세스를 구성한다.
	 * 
	 * TODO : 2014.07.29 : 접근 IP를 통제한다.
	 */
	private static void test01()
	{
		if (Flag.TRUE) {
			/*
			 * 반드시 먼저 선언해야 할 것
			 * 1. base path : ..../qaf 까지
			 * 2. 사용할 로그정보
			 */
			BasePath.getInstance();

			try {
				Logger.getInstance(AnalProperties.getInstance().getServiceBasePath(), null, DefValue.inetdLogName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Flag.TRUE) {

			try {
				/*
				 * Create ServerSocket
				 */
				serverSocket = new ServerSocket(Integer.parseInt(PORT));
				Logger.info("STATUS : ServerSocket Listening, port is %s", PORT);
				
				while (true) {
					/*
					 * listen and wait for a connection
					 */
					Socket socket = serverSocket.accept();
					Logger.info("STATUS : event of connection from manager...");
					
					/*
					 * if a connection, create CommandThread and do the job...
					 */
					SendToOracle cmdThread = new SendToOracle(socket);
					cmdThread.start();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				try {
					if (serverSocket != null)
						serverSocket.close();
					
				} catch (Exception e2) {}
			}
			
			Logger.exit();
		}
	}
	
	private static void test02()
	{
		if (!Flag.TRUE) {
			SystemProperties.setTesting("010");
		}
		
		if (Flag.TRUE) {
			new AnalCsvServer().execute();
		}
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (Flag.TRUE) test02();
	}
}
