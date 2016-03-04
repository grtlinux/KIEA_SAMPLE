package kiea.priv.zzz.pkg.au.com.bytecode.opencsv.TEST.t02_Derby2CSV;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.io.output.FileWriterWithEncoding;

import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.ResultSetHelper;
import au.com.bytecode.opencsv.ResultSetHelperService;

public class Airlines2CsvTestMain
{
	private static boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////
	
	private static final String strDriver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String strUrl = "jdbc:derby:tours/toursdb";

	private static Connection conn = null;
	@SuppressWarnings("unused")
	private static PreparedStatement stmtInsert = null;
	@SuppressWarnings("unused")
	private static PreparedStatement stmtUpdate = null;
	@SuppressWarnings("unused")
	private static PreparedStatement stmtDelete = null;
	private static Statement stmt = null;
	private static ResultSet resultSet = null;
	private static ResultSetMetaData meta = null;

	///////////////////////////////////////////////////////////////////////////

	private static void test01(String tableName, String pathName) throws SQLException, Exception
	{
		if (flag) {
			/*
			 * connection
			 */
			if (flag) System.out.println("## start CONNECTION ##");
			
			try {
				if (flag) System.out.println("\tLoading the Derby jdbc driver.....");
				Class.forName(strDriver).getInterfaces();
				
				if (flag) System.out.println("\tGetting Derby database connection .....");
				conn = DriverManager.getConnection(strUrl);
				if (flag) System.out.println("\tSuccessfully got the Derby database connection .....");
			} catch (SQLException e) {
				throw e;
			}
			
			if (flag) System.out.println("## end CONNECTION ##\n");
		}
		
		if (flag) {
			/*
			 * select
			 */
			if (flag) System.out.println("## start SELECT TABLE ##");

			try {
				String query = "SELECT * FROM " + tableName;
				
				stmt = conn.createStatement();
				
				if (flag) System.out.println("\tQUERY : " + query);
				resultSet = stmt.executeQuery(query);
				meta = resultSet.getMetaData();
				
				int numberOfColumns = meta.getColumnCount();
				
				if (flag) {
					/*
					 * META
					 */
					if (flag) System.out.println("\tFIELD INFORMATION ##");
					
					for (int i=0; i < numberOfColumns; i++) {
						int fldNo = i + 1;
						
						System.out.println("\t\t(" + fldNo + ") " + meta.getColumnName(fldNo) + " (" + meta.getColumnTypeName(fldNo) + ":" + meta.getColumnType(fldNo) + ")");
					}
				}
				
				if (!flag) {
					/*
					 * DATA
					 */
					if (flag) System.out.println("\tROW DATA ##");

					int rowIndex = 0;
					
					while (resultSet.next()) {
						StringBuffer sb = new StringBuffer();
						
						for (int i=0; i < numberOfColumns; i++) {
							int fldNo = i + 1;
							
							sb.append("[").append(resultSet.getString(fldNo)).append("]  ");
						}
						
						System.out.println("\t(" + rowIndex + ")  " + sb.toString());
						
						rowIndex ++;
					}
				}
				
				if (flag) {
					/*
					 * Derby ResultSet to CSV
					 */
					if (flag) System.out.println("\tROW DATA to CSV FILE ##");

					//resultSet.first();
					
					String fileName = pathName + "/" + tableName + ".csv";
					CSVWriter writer = new CSVWriter(new FileWriterWithEncoding(fileName, "EUC-KR"));  // default seperator
					writer.setResultService((ResultSetHelper) new ResultSetHelperService());
					writer.writeAll(resultSet, true);
					writer.close();
				}
				
			} catch (SQLException e) {
				throw e;
			} finally {
				try {
					if (resultSet != null) {
						resultSet.close();
						resultSet = null;
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
			}
			
			if (flag) System.out.println("## end SELECT TABLE ##\n");
		}
		
		if (flag) {
			/*
			 * close
			 */
			if (flag) System.out.println("## start CLOSE ##");

			try {
				conn.commit();
				
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (flag) System.out.println("## end CLOSE ##\n");
		}
		
		if (flag) {
			/*
			 * shutdown
			 */
			if (flag) System.out.println("## start SHUTDOWN ##");

			try {
				DriverManager.getConnection(strUrl + ";shutdown=true");
			} catch (SQLException e) {
				if (!"08006".equals(e.getSQLState()))
					throw e;
			}
		
			if (flag) System.out.println("## end SHUTDOWN ##\n");
		}
	}
	
	public static void main(String[] args) throws SQLException, Exception
	{
		if (flag) {
			// System.setProperty("derby.system.home", "D:/KANG/WORK/DerbyDB");
			System.setProperty("derby.system.home", "D:/KANG.20141230/WORK/_DerbyDB");
		}
		
		if (flag) test01("AIRLINES"          , "D:/TEMP");
		if (flag) test01("CITIES"            , "D:/TEMP");
		if (flag) test01("COUNTRIES"         , "D:/TEMP");
		if (flag) test01("FLIGHTAVAILABILITY", "D:/TEMP");
		if (flag) test01("FLIGHTS"           , "D:/TEMP");
		if (flag) test01("FLIGHTS_HISTORY"   , "D:/TEMP");
		if (flag) test01("MAPS"              , "D:/TEMP");
	}
}
