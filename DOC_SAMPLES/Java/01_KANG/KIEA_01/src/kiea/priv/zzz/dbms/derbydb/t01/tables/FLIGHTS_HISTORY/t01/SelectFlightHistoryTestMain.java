package kiea.priv.zzz.dbms.derbydb.t01.tables.FLIGHTS_HISTORY.t01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectFlightHistoryTestMain
{
	private static final boolean flag = true;
	
	private static final String strDriver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String strUrl = "jdbc:derby:tours/toursdb";
	
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	private static ResultSetMetaData meta = null;
	private static String query = null;
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			query = "SELECT * FROM FLIGHTS_HISTORY";
		}
		
		if (flag) {
			/*
			 * connection and select
			 */
			try {
				if (flag) System.out.println("Loading the Derby jdbc driver.....");
				Class.forName(strDriver).getInterfaces();
				
				if (flag) System.out.println("Getting Derby database connection .....");
				conn = DriverManager.getConnection(strUrl);
				if (flag) System.out.println("Successfully got the Derby database connection .....");
				
				stmt = conn.createStatement();
				
				if (flag) System.out.println("## QUERY : " + query);
				rs = stmt.executeQuery(query);
				meta = rs.getMetaData();
				
				int numberOfColumns = meta.getColumnCount();
				
				if (flag) {
					if (flag) System.out.println("## FIELD INFORMATION ##");
					
					for (int i=0; i < numberOfColumns; i++) {
						int fldNo = i + 1;
						
						System.out.println("\t(" + fldNo + ") " + meta.getColumnName(fldNo) + " (" + meta.getColumnTypeName(fldNo) + ":" + meta.getColumnType(fldNo) + ")");
					}
				}
				
				if (flag) {
					if (flag) System.out.println("## ROW DATA ##");

					int rowIndex = 0;
					
					while (rs.next()) {
						StringBuffer sb = new StringBuffer();
						
						for (int i=0; i < numberOfColumns; i++) {
							int fldNo = i + 1;
							
							sb.append("[").append(rs.getString(fldNo)).append("]  ");
						}
						
						System.out.println("(" + rowIndex + ")  " + sb.toString());
						
						rowIndex ++;
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null) {
						rs.close();
						rs = null;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				try {
					if (stmt != null) {
						stmt.close();
						stmt = null;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				try {
					if (conn != null) {
						conn.close();
						conn = null;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		if (flag) {
			/*
			 * shutdown
			 */
			try {
				DriverManager.getConnection(strUrl + ";shutdown=true");
			} catch (SQLException e) {
				if (!"08006".equals(e.getSQLState()))
					throw e;
			}
		}
	}

	public static void main(String[] args) throws Exception
	{
		if (flag) {
			try {
				System.setProperty("derby.system.home", "D:/KANG/WORK/DerbyDB");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (flag) test01(args);
	}
}
