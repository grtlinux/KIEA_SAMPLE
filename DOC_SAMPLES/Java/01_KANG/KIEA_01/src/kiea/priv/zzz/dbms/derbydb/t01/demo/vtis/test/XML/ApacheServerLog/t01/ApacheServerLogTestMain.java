package kiea.priv.zzz.dbms.derbydb.t01.demo.vtis.test.XML.ApacheServerLog.t01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ApacheServerLogTestMain
{
	private static final boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////
	
	private static final String strDriver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String strUrl = "jdbc:derby:memory:kang";
	
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	private static ResultSetMetaData meta = null;

	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	private static void connection() throws SQLException, Exception
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
				conn = DriverManager.getConnection(strUrl + ";create=true");
				if (flag) System.out.println("\tSuccessfully got the Derby database connection .....");
			} catch (SQLException e) {
				throw e;
			}
			
			if (flag) System.out.println("## end CONNECTION ##\n");
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	private static void createTable() throws SQLException
	{
		if (flag) {
			/*
			 * create table
			 */
			if (flag) System.out.println("## start CREATE TABLE ##");
			
			try {
				stmt = conn.createStatement();
				
				stmt.execute(""
						+ "create function apacheNaturalLogFile(fileURL varchar(32672)) returns table                                  \n"
						+ "(                                                                                                           \n"
						+ "    IP         varchar(100),                                                                                \n"
						+ "    accessDate timestamp,                                                                                   \n"
						+ "    request    clob,                                                                                        \n"
						+ "    statusCode int,                                                                                         \n"
						+ "    fileSize   int,                                                                                         \n"
						+ "    referrer   varchar(200),                                                                                \n"
						+ "    userAgent  clob                                                                                         \n"
						+ ")                                                                                                           \n"
						+ "language java                                                                                               \n"
						+ "parameter style derby_jdbc_result_set                                                                       \n"
	                    + "no sql                                                                                                      \n"
                        + "external name 'kiea.kr.com.tain.ztest.Derby.t01.demo.vtis.example.ApacheServerLogVTI.apacheNaturalLogFile'  \n"
						);
			} catch (SQLException e) {
				throw e;
			} finally {
				try {
					if (stmt != null) {
						stmt.close();
						stmt = null;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (flag) System.out.println("## end CREATE TABLE ##\n");
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	private static void selectTable_01() throws SQLException
	{
		if (flag) {
			/*
			 * select
			 */
			if (flag) System.out.println("## start SELECT TABLE - 01 ##");

			try {
				String query = "\n"
						+ "select   \n"
						+ "    s.*  \n"
						+ "from     \n"
						+ "    table(apacheNaturalLogFile('D:/KANG/WORK/workspace(64)/DERBYDB/src/kiea/kr/com/tain/ztest/Derby/t01/demo/vtis/DATA_FILES/ApacheServerLog.xml')) s \n"
						;
				
				stmt = conn.createStatement();
				
				if (flag) System.out.println("\tQUERY : " + query);
				rs = stmt.executeQuery(query);
				meta = rs.getMetaData();
				
				int numberOfColumns = meta.getColumnCount();
				
				if (flag) {
					if (flag) System.out.println("\tFIELD INFORMATION ##");
					
					for (int i=0; i < numberOfColumns; i++) {
						int fldNo = i + 1;
						
						System.out.println("\t\t(" + fldNo + ") " + meta.getColumnName(fldNo) + " (" + meta.getColumnTypeName(fldNo) + ":" + meta.getColumnType(fldNo) + ")");
					}
				}
				
				if (flag) {
					if (flag) System.out.println("\tROW DATA ##");

					int rowIndex = 0;
					
					while (rs.next()) {
						StringBuffer sb = new StringBuffer();
						
						for (int i=0; i < numberOfColumns; i++) {
							int fldNo = i + 1;
							
							sb.append("[").append(rs.getString(fldNo)).append("]  ");
						}
						
						System.out.println("\t(" + rowIndex + ")  " + sb.toString());
						
						rowIndex ++;
					}
				}
			} catch (SQLException e) {
				throw e;
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
			}
			
			if (flag) System.out.println("## end SELECT TABLE - 01 ##\n");
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	private static void selectTable_02() throws SQLException
	{
		if (flag) {
			/*
			 * select
			 */
			if (flag) System.out.println("## start SELECT TABLE - 02 ##");

			try {
				String query = "\n"
						+ "select   \n"
						+ "    s.*  \n"
						+ "from     \n"
						+ "    table(apacheNaturalLogFile('D:/KANG/WORK/workspace(64)/DERBYDB/src/kiea/kr/com/tain/ztest/Derby/t01/demo/vtis/DATA_FILES/ApacheServerLog.xml')) s \n"
						+ "where    \n"
						+ "    s.statusCode = 206 \n"
						;
				
				stmt = conn.createStatement();
				
				if (flag) System.out.println("\tQUERY : " + query);
				rs = stmt.executeQuery(query);
				meta = rs.getMetaData();
				
				int numberOfColumns = meta.getColumnCount();
				
				if (flag) {
					if (flag) System.out.println("\tFIELD INFORMATION ##");
					
					for (int i=0; i < numberOfColumns; i++) {
						int fldNo = i + 1;
						
						System.out.println("\t\t(" + fldNo + ") " + meta.getColumnName(fldNo) + " (" + meta.getColumnTypeName(fldNo) + ":" + meta.getColumnType(fldNo) + ")");
					}
				}
				
				if (flag) {
					if (flag) System.out.println("\tROW DATA ##");

					int rowIndex = 0;
					
					while (rs.next()) {
						StringBuffer sb = new StringBuffer();
						
						for (int i=0; i < numberOfColumns; i++) {
							int fldNo = i + 1;
							
							sb.append("[").append(rs.getString(fldNo)).append("]  ");
						}
						
						System.out.println("\t(" + rowIndex + ")  " + sb.toString());
						
						rowIndex ++;
					}
				}
			} catch (SQLException e) {
				throw e;
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
			}
			
			if (flag) System.out.println("## end SELECT TABLE - 02 ##\n");
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	private static void selectTable_03() throws SQLException
	{
		if (flag) {
			/*
			 * select
			 */
			if (flag) System.out.println("## start SELECT TABLE - 03 ##");

			try {
				String query = "\n"
						+ "select   \n"
						+ "    s.*  \n"
						+ "from     \n"
						+ "    table(apacheNaturalLogFile('D:/KANG/WORK/workspace(64)/DERBYDB/src/kiea/kr/com/tain/ztest/Derby/t01/demo/vtis/DATA_FILES/ApacheServerLog.xml')) s \n"
						+ "where    \n"
						+ "    IP like '208%'  \n"
						;
				
				stmt = conn.createStatement();
				
				if (flag) System.out.println("\tQUERY : " + query);
				rs = stmt.executeQuery(query);
				meta = rs.getMetaData();
				
				int numberOfColumns = meta.getColumnCount();
				
				if (flag) {
					if (flag) System.out.println("\tFIELD INFORMATION ##");
					
					for (int i=0; i < numberOfColumns; i++) {
						int fldNo = i + 1;
						
						System.out.println("\t\t(" + fldNo + ") " + meta.getColumnName(fldNo) + " (" + meta.getColumnTypeName(fldNo) + ":" + meta.getColumnType(fldNo) + ")");
					}
				}
				
				if (flag) {
					if (flag) System.out.println("\tROW DATA ##");

					int rowIndex = 0;
					
					while (rs.next()) {
						StringBuffer sb = new StringBuffer();
						
						for (int i=0; i < numberOfColumns; i++) {
							int fldNo = i + 1;
							
							sb.append("[").append(rs.getString(fldNo)).append("]  ");
						}
						
						System.out.println("\t(" + rowIndex + ")  " + sb.toString());
						
						rowIndex ++;
					}
				}
			} catch (SQLException e) {
				throw e;
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
			}
			
			if (flag) System.out.println("## end SELECT TABLE - 03 ##\n");
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	private static void selectTable_04() throws SQLException
	{
		if (flag) {
			/*
			 * select
			 */
			if (flag) System.out.println("## start SELECT TABLE - 04 ##");

			try {
				String query = "\n"
						+ "select   \n"
						+ "    s.*  \n"
						+ "from     \n"
						+ "    table(apacheNaturalLogFile('D:/KANG/WORK/workspace(64)/DERBYDB/src/kiea/kr/com/tain/ztest/Derby/t01/demo/vtis/DATA_FILES/ApacheServerLog.xml')) s \n"
						+ "where    \n"
						+ "    accessDate between timestamp('2002-07-01 08:40:56.0') and timestamp('2002-07-01 08:42:56.0') \n"
						;
				
				stmt = conn.createStatement();
				
				if (flag) System.out.println("\tQUERY : " + query);
				rs = stmt.executeQuery(query);
				meta = rs.getMetaData();
				
				int numberOfColumns = meta.getColumnCount();
				
				if (flag) {
					if (flag) System.out.println("\tFIELD INFORMATION ##");
					
					for (int i=0; i < numberOfColumns; i++) {
						int fldNo = i + 1;
						
						System.out.println("\t\t(" + fldNo + ") " + meta.getColumnName(fldNo) + " (" + meta.getColumnTypeName(fldNo) + ":" + meta.getColumnType(fldNo) + ")");
					}
				}
				
				if (flag) {
					if (flag) System.out.println("\tROW DATA ##");

					int rowIndex = 0;
					
					while (rs.next()) {
						StringBuffer sb = new StringBuffer();
						
						for (int i=0; i < numberOfColumns; i++) {
							int fldNo = i + 1;
							
							sb.append("[").append(rs.getString(fldNo)).append("]  ");
						}
						
						System.out.println("\t(" + rowIndex + ")  " + sb.toString());
						
						rowIndex ++;
					}
				}
			} catch (SQLException e) {
				throw e;
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
			}
			
			if (flag) System.out.println("## end SELECT TABLE - 04 ##\n");
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	private static void close() throws SQLException
	{
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
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	private static void shutdown() throws SQLException
	{
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
	
	///////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 * @param args
	 * @throws SQLException
	 * @throws Exception
	 */
	private static void test01(String[] args) throws SQLException, Exception
	{
		if (flag) connection();
		
		if (flag) createTable();

		if (flag) selectTable_01();
		
		if (flag) selectTable_02();

		if (flag) selectTable_03();

		if (flag) selectTable_04();
		
		if (flag) close();
		
		if (flag) shutdown();
	}
	
	
	public static void main(String[] args) throws SQLException, Exception
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
