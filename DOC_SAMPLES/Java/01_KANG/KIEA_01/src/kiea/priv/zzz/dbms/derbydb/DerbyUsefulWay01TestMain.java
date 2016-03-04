package kiea.priv.zzz.dbms.derbydb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DerbyUsefulWay01TestMain
{
	private static final boolean flag = true;
	
	///////////////////////////////////////////////////////////////////////////
	
	private static final String strDriver = "org.apache.derby.jdbc.EmbeddedDriver";  // "org.apache.derby.jdbc.ClientDriver"
	private static final String strUrl = "jdbc:derby:tours/toursdb";
	
	private static Connection conn = null;
	private static PreparedStatement stmtInsert = null;
	private static PreparedStatement stmtUpdate = null;
	private static PreparedStatement stmtDelete = null;
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
				conn = DriverManager.getConnection(strUrl);
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
						+ "CREATE TABLE location  \n"
						+ "(                      \n"
						+ "    num   INT,         \n"
						+ "    addr  VARCHAR(40)  \n"
						+ ")                      \n"
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
	private static void insertTable() throws SQLException
	{
		if (flag) {
			/*
			 * insert table
			 */
			if (flag) System.out.println("## start INSERT TABLE ##");
			
			try {
				stmtInsert = conn.prepareStatement(""
						+ "INSERT INTO    \n"
						+ "    location   \n"
						+ "VALUES         \n"
						+ "(              \n"
						+ "    ?, ?       \n"
						+ ")              \n"
						);
				
				int cntInsert = 0;
				
				stmtInsert.setInt(1, 1001);
				stmtInsert.setString(2, "one thousand one");
				cntInsert += stmtInsert.executeUpdate();
				
				stmtInsert.setInt(1, 1002);
				stmtInsert.setString(2, "one thousand two");
				cntInsert += stmtInsert.executeUpdate();
				
				stmtInsert.setInt(1, 1003);
				stmtInsert.setString(2, "one thousand three");
				cntInsert += stmtInsert.executeUpdate();
				
				stmtInsert.setInt(1, 1004);
				stmtInsert.setString(2, "one thousand four");
				cntInsert += stmtInsert.executeUpdate();
				
				stmtInsert.setInt(1, 1005);
				stmtInsert.setString(2, "one thousand five");
				cntInsert += stmtInsert.executeUpdate();
				
				if (flag) System.out.println("\tInsert count = " + cntInsert);
				
			} catch (SQLException e) {
				throw e;
			} finally {
				try {
					if (stmtInsert != null) {
						stmtInsert.close();
						stmtInsert = null;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (flag) System.out.println("## end INSERT TABLE ##\n");
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	private static void updateTable() throws SQLException
	{
		if (flag) {
			/*
			 * update
			 */
			if (flag) System.out.println("## start UPDATE TABLE ##");

			try {
				stmtUpdate = conn.prepareStatement(""
						+ "UPDATE           \n"
						+ "    location     \n"
						+ "SET              \n"
						+ "    num = ?,     \n"
						+ "    addr = ?     \n"
						+ "WHERE 1=1        \n"
						+ "    AND num = ?  \n"
						);
				
				int cntUpdate = 0;
				
				stmtUpdate.setInt(1, 2001);
				stmtUpdate.setString(2, "two thousand one");
				stmtUpdate.setInt(3, 1001);
				cntUpdate += stmtUpdate.executeUpdate();
				
				stmtUpdate.setInt(1, 2002);
				stmtUpdate.setString(2, "two thousand two");
				stmtUpdate.setInt(3, 1002);
				cntUpdate += stmtUpdate.executeUpdate();
				
				stmtUpdate.setInt(1, 2003);
				stmtUpdate.setString(2, "two thousand three");
				stmtUpdate.setInt(3, 1003);
				cntUpdate += stmtUpdate.executeUpdate();
				
				stmtUpdate.setInt(1, 2004);
				stmtUpdate.setString(2, "two thousand four");
				stmtUpdate.setInt(3, 1004);
				cntUpdate += stmtUpdate.executeUpdate();
				
				stmtUpdate.setInt(1, 2005);
				stmtUpdate.setString(2, "two thousand five");
				stmtUpdate.setInt(3, 1005);
				cntUpdate += stmtUpdate.executeUpdate();
				
				if (flag) System.out.println("\tUpdate count = " + cntUpdate);
				
			} catch (SQLException e) {
				throw e;
			} finally {
				try {
					if (stmtUpdate != null) {
						stmtUpdate.close();
						stmtUpdate = null;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (flag) System.out.println("## end UPDATE TABLE ##\n");
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	private static void selectTable() throws SQLException
	{
		if (flag) {
			/*
			 * select
			 */
			if (flag) System.out.println("## start SELECT TABLE ##");

			try {
				String query = "SELECT * FROM location";
				
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
			
			if (flag) System.out.println("## end SELECT TABLE ##\n");
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	private static void deleteTable() throws SQLException
	{
		if (flag) {
			/*
			 * delete
			 */
			if (flag) System.out.println("## start DELETE TABLE ##");

			try {
				stmtDelete = conn.prepareStatement(""
						+ "DELETE FROM      \n"
						+ "    location     \n"
						+ "WHERE 1=1        \n"
						+ "    AND num = ?  \n"
						);
				
				int cntDelete = 0;
				
				stmtDelete.setInt(1, 2001);
				cntDelete += stmtDelete.executeUpdate();
				
				stmtDelete.setInt(1, 2002);
				cntDelete += stmtDelete.executeUpdate();
				
				stmtDelete.setInt(1, 2003);
				cntDelete += stmtDelete.executeUpdate();
				
				if (flag) System.out.println("\tDelete count = " + cntDelete);
				
			} catch (SQLException e) {
				throw e;
			} finally {
				try {
					if (stmtDelete != null) {
						stmtDelete.close();
						stmtDelete = null;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (flag) System.out.println("## end DELETE TABLE ##\n");
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	private static void truncateTable() throws SQLException
	{
		if (flag) {
			/*
			 * delete
			 */
			if (flag) System.out.println("## start TRUNCATE TABLE ##");

			try {
				stmt = conn.createStatement();
				
				boolean ret = stmt.execute("TRUNCATE TABLE location  \n");
				
				if (flag) System.out.println("\tTruncate boolean = " + ret);
				
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

			if (flag) System.out.println("## end TRUNCATE TABLE ##\n");
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	private static void dropTable() throws SQLException
	{
		if (flag) {
			/*
			 * drop table
			 */
			if (flag) System.out.println("## start DROP TABLE ##");
			
			try {
				stmt = conn.createStatement();
				
				boolean ret = stmt.execute("DROP TABLE location  \n");
				
				if (flag) System.out.println("\tDrop boolean = " + ret);

			} catch (SQLException e) {
				if (!"42Y55".equals(e.getSQLState()))
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
			
			if (flag) System.out.println("## end DROP TABLE ##\n");
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
	private static void disConnection() throws SQLException
	{
		close();
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
		
		if (flag) dropTable();

		if (flag) createTable();

		if (flag) insertTable();
		
		if (flag) updateTable();
		
		if (flag) selectTable();
		
		if (flag) deleteTable();
		
		if (flag) selectTable();

		if (flag) truncateTable();

		if (flag) dropTable();
		
		if (flag) disConnection();
		
		if (flag) close();
		
		if (flag) shutdown();
	}
	
	
	public static void main(String[] args) throws SQLException, Exception
	{
		if (flag) {
			try {
				// System.setProperty("derby.system.home", "D:/KANG/WORK/DerbyDB");
				System.setProperty("derby.system.home", "D:/KANG.20141230/WORK/_DerbyDB");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (flag) test01(args);
	}
}
