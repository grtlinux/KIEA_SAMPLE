package kiea.z01.ztest.t01.Derby.t01.nserverdemo.t01;

import java.io.PrintWriter;
import java.net.InetAddress;

import org.apache.derby.drda.NetworkServerControl;

public class NetworkServerUtil
{
	private int portNum;
	private NetworkServerControl serverControl;
	private PrintWriter pw;
	
	public NetworkServerUtil(int port, PrintWriter pw)
	{
		this.portNum = port;
		this.pw = pw;
		
		try {
			serverControl = new NetworkServerControl(InetAddress.getByName("localhost"), this.portNum);
			pw.println("Derby Network Server created");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void trace(boolean onoff)
	{
		try {
			serverControl.trace(onoff);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testForConnection() throws Exception
	{
		serverControl.ping();
	}
	
	public void shutdown()
	{
		try {
			serverControl.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start()
	{
		try {
			serverControl.start(pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
