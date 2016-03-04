package kiea.z01.ztest.t01.Derby.t01.sample.t01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import kiea.kr.co.tain.base.Flag;

public class SampleAppTestMain
{
	private String framework = "embedded";
	private String protocol = "jdbc:derby:";
	
	void go(String[] args)
	{
		parseArguments(args);
		
		System.out.println("SimpleAppTestMain starting in " + this.framework + " mode.");
		
		Connection conn = null;
		ArrayList<Statement> statements = new ArrayList<Statement>();
		PreparedStatement psInsert;
		PreparedStatement psUpdate;
		Statement s;
		ResultSet rs = null;
		
		try {
			Properties props = new Properties();
			
			props.put("user", "app");
			props.put("password", "app");
			
			String dbName = "derbyDB";
			
			conn = DriverManager.getConnection(this.protocol + dbName + ";create=true", props);
			System.out.println("Connected to and created database " + dbName);
			
			conn.setAutoCommit(false);
			
			s = conn.createStatement();
			statements.add(s);
			
			s.execute("create table location(num int, addr varchar(40))");
			System.out.println("Created table location");
			
			psInsert = conn.prepareStatement("insert into location values (?, ?)");
			statements.add(psInsert);
			
			psInsert.setInt(1, 1956);
			psInsert.setString(2, "Webster St.");
			psInsert.executeUpdate();
			System.out.println("Inserted 1956 Webster.");
			
			psInsert.setInt(1, 1910);
			psInsert.setString(2, "Union St.");
			psInsert.executeUpdate();
			System.out.println("Inserted 1910 Union.");
			
			psUpdate = conn.prepareStatement("update location set num=?, addr=? where num=?");
			statements.add(psUpdate);
			
			psUpdate.setInt(1, 180);
			psUpdate.setString(2, "Grand Ave.");
			psUpdate.setInt(3, 1956);
			psUpdate.executeUpdate();
			System.out.println("Updated 1956 Webster to 180 Grand");
			
			psUpdate.setInt(1, 300);
			psUpdate.setString(2, "Lakeshore Ave.");
			psUpdate.setInt(3, 180);
			psUpdate.executeUpdate();
			System.out.println("Updated 180 Grand to 300 Lakeshore");
			
			rs = s.executeQuery("select num, addr from location order by num");
			
			int number;
			boolean failure = false;
			
			if (!rs.next()) {
				failure = true;
				reportFailure("No rows in ResultSet");
			}
			
			if ((number = rs.getInt(1)) != 300) {
				failure = true;
				reportFailure("Wrong row returned. expected num=300. got " + number);
			}
			
			if (!rs.next()) {
				failure = true;
				reportFailure("Too few rows");
			}
			
			if ((number = rs.getInt(1)) != 1910) {
				failure = true;
				reportFailure("Wrong row returned. expected num=1910. got " + number);
			}
			
			if (rs.next()) {
				failure = true;
				reportFailure("Too many rows");
			}
			
			if (!failure) {
				System.out.println("Verified the rows");
			}
			
			s.execute("drop table location");
			System.out.println("Dropped table location");
			
			conn.commit();
			System.out.println("Committed the transaction");
			
			if (this.framework.equalsIgnoreCase("embedded")) {
				try {
					DriverManager.getConnection("jdbc:derby:;shutdown=true");
				} catch (SQLException e) {
					if (( (e.getErrorCode() == 50000) && ("XJ015".equals(e.getSQLState()) ))) {
						System.out.println("Derby shut down normally");
					} else {
						System.err.println("Derby did not dhut down normally");
						printSQLException(e);
					}
				}
			}
		} catch (SQLException e) {
			printSQLException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			
			int i=0;
			while (!statements.isEmpty()) {
				Statement st = (Statement) statements.remove(i);
				try {
					if (st != null) {
						st.close();
						st = null;
					}
				} catch (SQLException e) {
					printSQLException(e);
				}
			}
			
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
		}
	}
	
	private void reportFailure(String message)
	{
		System.err.println("\nData verification failed:");
		System.err.println("\t" + message);
	}
	
	public static void printSQLException(SQLException e)
	{
		while (e != null) {
			System.err.println("\n----- SQLException -----");
			System.err.println("  SQL State  : " + e.getSQLState());
			System.err.println("  Error Code : " + e.getErrorCode());
			System.err.println("  Message    : " + e.getMessage());
			
			e = e.getNextException();
		}
	}
	
	private void parseArguments(String[] args)
	{
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("derbyclient")) {
				this.framework = "derbyclient";
				this.protocol = "jdbc:derby://localhost:1527/";
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) {
			try {
				// DB SYSTEM HOME
				System.setProperty("derby.system.home", "D:/KANG/WORK/DERBY_DB_HOME");
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}

		new SampleAppTestMain().go(args);
		System.out.println("SimpleAppTestMain finished");
	}
}
