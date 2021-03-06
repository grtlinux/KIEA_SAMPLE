package kiea.z01.ztest.t01.Derby.t01.nserverdemo.t01;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;

public class NsSampleClientThread extends Thread
{
	protected int thread_id;
	protected Properties properties;
	protected PrintWriter pw;
	protected String dbUrl;

	NsSampleClientThread(int id,String dbUrl, Properties properties,PrintWriter pw) {
		this.thread_id=id;
		this.dbUrl = dbUrl;
		this.properties = properties;
		this.pw = pw;
	}

	public void run() {
		System.out.println("[NsSampleClientThread] Thread id - "+this.thread_id + "; started.");
		NsSampleWork w = new NsSampleWork(this.thread_id,dbUrl,properties,pw);
		w.doWork();  // do all the NsSampleWork
		pw.println("[NsSampleClientThread] Thread id - "+this.thread_id+"; finished all tasks.");
	}
}

class NsSampleWork
{
	protected int thread_id;
	protected String dbUrl;
	protected Properties properties;
	PrintWriter pw;
	PreparedStatement select = null;
	PreparedStatement insert = null;
	PreparedStatement delete = null;
	PreparedStatement update = null;
	PreparedStatement getMaxKey = null;

	public static int counter=0;
	static Integer lock = new Integer(0);
	
	NsSampleWork(int id, String dbURL, Properties properties, PrintWriter pw)
	{
		this.thread_id = id;
		this.dbUrl = dbURL;
		this.pw = pw;
		this.properties = properties;
	}
	
	public Connection getConnection(String dbRrl, Properties properties)
	{
		Connection conn = null;
		try {
			pw.println("[NsSampleWork] Thread id - "+thread_id + "; requests database connection, dbUrl ="+dbUrl);
			conn = DriverManager.getConnection(dbUrl, properties);
		} catch (Exception e) {
			System.out.println("[NsSampleWork] Thread id - "+ thread_id + "; failed to get database connection. Exception thrown:");
			e.printStackTrace();
		}
		return conn;
	}
	
	public void setIsolationLevel(Connection conn, int level)
	{
		try {
			conn.setTransactionIsolation(level);
		} catch (Exception e) {
			pw.println("[NsSampleWork] Thread id - "+ thread_id +"; setIsolationLevel failed. Exception thrown: ");
			e.printStackTrace();
		}
	}
	
	public void closeConnection(Connection conn)
	{
		try {
			if(conn != null)
				conn.close();
			pw.println("[NsSampleWork] Thread id - "+thread_id + "; closed connection to the database.");
		} catch (Exception e) {
			pw.println("[NsSampleWork] Thread id - "+thread_id + "; error when closing connection;"+ e);
			e.printStackTrace();
		}
	}
	
	public void prepareStmts(Connection conn)
	{
		try {
			select = conn.prepareStatement("select t_int,  t_char, t_float,t_key from SAMPLETBL where t_key = ?");
			insert = conn.prepareStatement("insert into SAMPLETBL (t_int, t_char,t_float,t_key) values (?,?,?,?)");
			update = conn.prepareStatement(" update SAMPLETBL set t_int = ? where t_key = ?");
			delete = conn.prepareStatement("delete from SAMPLETBL where t_key = ?");
			getMaxKey = conn.prepareStatement("select max(t_key) from SAMPLETBL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int doSelectOperation(long selectWhat)
	{
		int numRowsSelected = 0;
		ResultSet rs = null;

		try {
			select.setLong(1,selectWhat);
			rs = select.executeQuery();

			while (rs.next()) {
				numRowsSelected++;
		
				int intVal = rs.getInt(1);
				String strVal = rs.getString(2);
				float floatVal = rs.getFloat(3);
				long longVal = rs.getLong(4); 	//t_key column
		
				pw.println("[NsSampleWork] Thread id - "+ thread_id +" selected "+numRowsSelected +" row ["+ intVal + "," + strVal +","+ floatVal +","+ longVal +"]");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
						rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return numRowsSelected;
	}
	
	public void doWork()
	{
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection(dbUrl,properties);
	
			if(conn == null)
				throw new Exception("Failed to obtain connection!");
	
			conn.setAutoCommit(true);
	
			// Setting isolation level to read uncommitted, since this is a sample application.
			// Please set the isolation level depending on the requirements of your application
			setIsolationLevel(conn,Connection.TRANSACTION_READ_UNCOMMITTED);
	
			prepareStmts(conn);
	
			// Perform the DML operations
			for (int i=0; i<NsSample.ITERATIONS; i++) {
				// Choose between either a select or any one of (insert or update or delete ) operation
				int choice = (int) (Math.random() * 100) % 2;
				switch (choice) {
					case 0: { //select a row
						rs = getMaxKey.executeQuery(); //gets max t_key value
						long selectWhere = 0;
						if(rs.next()) {
							selectWhere = rs.getLong(1);
						}
						@SuppressWarnings("unused")
						int numSelected = doSelectOperation(selectWhere);
						break;
						}
		
					case 1: { //do an insert, update or delete
						doIUDOperation();
						break;
						}
				} //end of switch()
			}//enf of for()
	
		} catch(Exception e) {
			pw.println("[NsSampleWork] Thread id - "+ thread_id + "; error when performing dml operations; ");
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
		
				closeConnection(conn);
				cleanup();
			} catch(Exception ee) {
				pw.println("[NsSampleWork] Thread id - " + thread_id+"; error when cleaning up connection, resultset; exception is ");
				ee.printStackTrace();
			}
		}
	}
	
	public void cleanup()
	{
		try{
			if(select != null)
				select.close();
			if(insert != null)
				insert.close();
			if(delete != null)
				delete.close();
			if(update != null)
				update.close();
			if(getMaxKey != null)
				getMaxKey.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doIUDOperation()
	{
		int decide = (int) (Math.random() * 100) % 3;
		ResultSet rs = null;

		try {
			switch (decide) {
				case 0: { //insert
					int numInsert = insertRow(insert);
					pw.println("[NsSampleWork] Thread id - "+thread_id+"; inserted "+numInsert+" row.");
					break;
					}
			
				case 1: { //update
					rs = getMaxKey.executeQuery();
					long updateRow=0;
					if(rs.next())
						updateRow = rs.getLong(1);
					int numUpdate = updateRow(update,updateRow);
					System.out.println("[NsSampleWork] Thread id - "+thread_id+"; updated "+numUpdate+" row with t_key = " + updateRow);
					break;
					}
			
				case 2: { //delete
					rs = getMaxKey.executeQuery();
					long deleteRow =0;
					if(rs.next())
						deleteRow = rs.getLong(1);
					int numDelete = deleteRow(delete,deleteRow);
					System.out.println("[NsSampleWork] Thread id - "+thread_id+"; deleted "+numDelete+" row with t_key = " + deleteRow);
					break;
					}
			}//end of switch()
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void checkAndCreateSchema(Connection conn, PrintWriter pw)
	{
		Statement stmt = null;
		ResultSet rs = null;

		try	{
			conn.setAutoCommit(true);
		} catch (SQLException  se) {
			pw.println("[NsSampleWork] Error when setting autocommit on connection; exception thrown: ");
			se.printStackTrace();
		}
	
		// Check for existence of schema by quering the catalog systables
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select tablename from sys.systables " + " where tablename = 'SAMPLETBL'");
			if (rs.next()) {
				pw.println("[NsSampleWork] Table 'SAMPLETBL' already exists; no need to create schema again.");
				return;
			}
		} catch (SQLException  se) {
			pw.println("[NsSampleWork] Unable to query the metadata for existence of table SAMPLETBL; exception is "+se);
			pw.println("[NsSampleWork] Exiting the application.");
			se.printStackTrace();
			System.exit(1);
		}
	
		// Create the necessary table and indexes
		try {
			pw.println("[NsSampleWork] Begin creating table - SAMPLETBL and necessary indexes. ");
			stmt.execute("create table SAMPLETBL (" +
			"t_int int," +
			"t_char char(15),"+
			"t_float float," +
			"t_key bigint )");
			stmt.execute("create index t_char_idx on SAMPLETBL ( t_char)");
			stmt.execute("create index t_float_idx on SAMPLETBL ( t_float)");
			stmt.execute("create index t_key_idx on SAMPLETBL ( t_key )" );
		} catch (Exception  e) {
			pw.println("[NsSampleWork] Error when creating schema; exception is " + e.toString());
			pw.println("[NsSampleWork] Exiting the application.");
			e.printStackTrace();
			System.exit(1);
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void loadSchema(Connection conn, int rowsToInsert, PrintWriter pw)
	{
		int insertsRemaining = rowsToInsert;
		PreparedStatement ps=null;

		try {
			ps = conn.prepareStatement("insert into SAMPLETBL (t_int, t_char,t_float,t_key) values (?,?,?,?)");
			// Insert one row at a time
			while (insertsRemaining-- >= 0)	{
				int numInserts = insertRow(ps);
				if (numInserts != 1)
					pw.println("[NsSampleWork] Failed to insert row.");
			}
		} catch (Exception e) {
			pw.println("[NsSampleWork] Error when loading schema; exception is "+ e);
			e.printStackTrace();
		} finally {
			try {
				if(ps != null)
					ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static int insertRow(PreparedStatement ps)
	{
		int rowsAdded = 0;
		try	{
			// Generate random values for the datatypes in the sample table
			Random rand = new Random();
			int intVal = Math.abs(rand.nextInt()%1000);
	
			String charVal = "Derby";
	
			synchronized(lock) {
				charVal += counter;
				counter++;
			}
	
			// Set parameter values
			ps.setInt(1, intVal);
			ps.setString(2,charVal);
			ps.setFloat(3, rand.nextFloat()*(float)Math.pow(10,Math.abs(rand.nextInt()%30)));
			ps.setLong(4,rand.nextLong()%10000);
			rowsAdded = ps.executeUpdate();
			return rowsAdded;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static int updateRow(PreparedStatement ps, long updateWhere)
	{
		try	{
			int val=0;
			synchronized(lock) {
				val = counter++;
			}
			ps.setInt(1,val);
			ps.setLong(2,updateWhere);
			return(ps.executeUpdate());
		} catch (SQLException se) {
			se.printStackTrace();
			return 0;
		}
	}
	
	public static int deleteRow(PreparedStatement ps, long deleteRow)
	{
		int rowsDeleted = 0;
		try	{
			ps.setLong(1, deleteRow);
			rowsDeleted = ps.executeUpdate();
			return rowsDeleted;
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
