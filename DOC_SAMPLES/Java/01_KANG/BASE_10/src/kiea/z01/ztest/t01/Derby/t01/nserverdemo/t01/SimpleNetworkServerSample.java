package kiea.z01.ztest.t01.Derby.t01.nserverdemo.t01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.derby.drda.NetworkServerControl;

public class SimpleNetworkServerSample
{
	private static String DBNAME = "NSSimpleDB";
	
	public static void main(String[] args) throws Exception
	{
		Connection embeddedConn = null;
		
		try {
			startNetworkServer();
		} catch (Exception e) {
			System.out.println("Failed to start NetworkServer: " + e);
			System.exit(1);
		}
		
		try {
			embeddedConn = getEmbeddedConnection(DBNAME, "create=true");
			System.out.println("Got an embedded connection");
			
			System.out.println("Testing embedded connection by executing a sample query");
			test(embeddedConn);
			
			String howToConnect = ijUsage();
			System.out.println(howToConnect);
			
			waitForExit();
			
		} catch (SQLException e) {
			System.out.println("Failure making connection: " + e);
			e.printStackTrace();
		} finally {
			if (embeddedConn != null)
				embeddedConn.close();
			
			try {
				DriverManager.getConnection("jdbc:derby:;shutdown=true");
			} catch (SQLException e2) {
				// ignore
			}
		}
	}
	
	public static void startNetworkServer() throws Exception
	{
		startWithProperty();
		waitForStart();
	}
	
	private static void startWithProperty() throws Exception
	{
		System.out.println("Starting Network Server");
		System.setProperty("derby.drda.startNetworkServer","true");

		Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
	}
	
	@SuppressWarnings("static-access")
	private static void waitForStart() throws Exception
	{
		org.apache.derby.drda.NetworkServerControl server = null;

		server = new NetworkServerControl();

		System.out.println("Testing if Network Server is up and running!");
		for (int i = 0; i < 10 ; i ++) {
			try {
				Thread.currentThread().sleep(5000);
				server.ping();
			} catch (Exception e) {
				System.out.println("Try #" + i + " " +e.toString());
				if (i == 9 ) {
					System.out.println("Giving up trying to connect to Network Server!");
					throw e;
				}
			}
		}
		System.out.println("Derby Network Server now running");
	}
	
	public static Connection getEmbeddedConnection(String database, String attributes) throws Exception
	{
		String dbUrl = "jdbc:derby:"+database +";"+attributes;
		Connection conn = DriverManager.getConnection(dbUrl);
		return conn;
	}
	
	public static void test(Connection conn) throws Exception
	{
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// To test our connection, we will try to do a select from the system catalog tables
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from sys.systables");
			while(rs.next())
				System.out.println("number of rows in sys.systables = "+ rs.getInt(1));

		} catch(SQLException sqle) {
			System.out.println("SQLException when querying on the database connection; "+ sqle);
			throw sqle;
	  	} finally {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
	 	}
	}
	
	private static void waitForExit() throws Exception
	{
		System.out.println("Clients can continue to connect: ");
 		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
 		System.out.println("Press [Enter] to stop Server");
 		in.readLine();
	}
	
	private static String ijUsage()
	{
		String ijUsage = "\nWhile my app is busy with embedded work, ";
		ijUsage += "ij might connect like this:\n\n";
		ijUsage += "\t$ java -Dij.user=me -Dij.password=pw -Dij.protocol=jdbc:derby://localhost:1527/ org.apache.derby.tools.ij\n";
		ijUsage += "\tij> connect '" + DBNAME + "';\n\n";

		return ijUsage;
	}
}
