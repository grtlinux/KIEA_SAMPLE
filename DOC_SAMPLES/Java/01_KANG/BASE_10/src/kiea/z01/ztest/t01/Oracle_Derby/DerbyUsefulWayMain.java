package kiea.z01.ztest.t01.Oracle_Derby;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import au.com.bytecode.opencsv.CSVReader;

public class DerbyUsefulWayMain
{
//	private static OracleConnection oraConn = null;
//	private static Statement oraStmt = null;
//	private static ResultSet oraResultSet = null;
	
	private static String strPathFileName = "D:/KANG/WORK/DERBY_TEST/anal_data/AR010141209A3120/A24_EQP_FDC02_DETAILS01.csv";

	private static Connection derbyConn = null;
	private static Statement derbyStmt = null;
	private static PreparedStatement derbyPreStmt = null;
	private static ResultSet derbyResultSet = null;
	private static ResultSetMetaData derbyMetaData = null;

	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * @throws SQLException
	 */
	private static void derbyConnect() throws SQLException
	{
		if (Flag.TRUE) {
			long time0 = System.nanoTime();
			if (Flag.TRUE) System.out.println("## start DERBY CONNECTION");
			
			try {
				Properties props = new Properties();
				props.put("user", "app");
				props.put("password", "app");

				// derbyConn = DriverManager.getConnection("jdbc:derby:anal/AR010141209A3120;create=true", props);
				derbyConn = DriverManager.getConnection("jdbc:derby:anal/AR;create=true", props);
				derbyConn.setAutoCommit(false);

			} catch (SQLException e) {
				throw e;
			}
			
			if (Flag.TRUE) System.out.println("## end DERBY CONNECTION ==> " + ((System.nanoTime() - time0) / 1000 / 1000) + " milliseconds");
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	private static void derbyCreateTable() throws SQLException
	{
		if (Flag.TRUE) {
			long time0 = System.nanoTime();
			if (Flag.TRUE) System.out.println("## start DERBY CREATE TABLE");
			
			try {
				derbyStmt = derbyConn.createStatement();
				boolean ret = derbyStmt.execute(""
						+ "CREATE TABLE                \n"
						+ "    A24_EQP_FDC02_DETAILS01 \n"
						+ "(                           \n"
						+ "    LINE          VARCHAR(200)          ,  \n"
						+ "    PART          VARCHAR(200)          ,  \n"
						+ "    EQPMODEL      VARCHAR(200)          ,  \n"
						+ "    EQPID         VARCHAR(200) NOT NULL ,  \n"
						+ "    MODULE_NAME   VARCHAR(200)          ,  \n"
						+ "    PROCID        VARCHAR(200)          ,  \n"
						+ "    PRODID        VARCHAR(200)          ,  \n"
						+ "    PPID          VARCHAR(200)          ,  \n"
						+ "    GLASSID       VARCHAR(200) NOT NULL ,  \n"
						+ "    PROCESSSTEP   VARCHAR(200)          ,  \n"
						+ "    BEGINSTEP     VARCHAR(200)          ,  \n"
						+ "    SENSOR_NAME   VARCHAR(200) NOT NULL ,  \n"
						+ "    PARAM         VARCHAR(200) NOT NULL ,  \n"
						//+ "    PARAM_VALUE   VARCHAR(200)          ,  \n"
						+ "    PARAM_VALUE   DOUBLE PRECISION      ,  \n"
						//+ "    PARAM_VALUE   DECIMAL(20,2)         ,  \n"
						+ "    USL           VARCHAR(200)          ,  \n"
						+ "    LSL           VARCHAR(200)          ,  \n"
						+ "    BEGINTIME     VARCHAR(200)          ,  \n"
						+ "    CLUSTER_ID    VARCHAR(200) NOT NULL ,  \n"
						+ "    GROUP_ID      VARCHAR(200) NOT NULL    \n"
						+ ")                           \n"
				);

				if (Flag.TRUE) System.out.println("\t create table ret = " + ret);
				
			} catch (SQLException e) {
				if (!"X0Y32".equals(e.getSQLState()))
					throw e;
				else
					if (Flag.TRUE) System.out.println("이미 DDL문(CREATE)으로 생성되었습니다.");
			} finally {
				try {
					if (derbyStmt != null)
						derbyStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (Flag.TRUE) System.out.println("## end DERBY CREATE TABLE ==> " + ((System.nanoTime() - time0) / 1000 / 1000) + " milliseconds");
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	private static void derbyInsertTableFromCSV() throws SQLException, Exception
	{
		if (Flag.TRUE) {
			long time0 = System.nanoTime();
			if (Flag.TRUE) System.out.println("## start DERBY INSERT TABLE");
			
			final int LIMIT_IDX = (10 * 1000 * 1000);
			int i = 0;

			try {
				CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(strPathFileName), "EUC-KR"));
				reader.readNext(); // read header
				String[] line;
				
				derbyPreStmt = derbyConn.prepareStatement("INSERT INTO A24_EQP_FDC02_DETAILS01 VALUES (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?)");

				while ((line = reader.readNext()) != null) {
					
					if (i > LIMIT_IDX)
						break;
					
					String fld01 = line[ 0];
					String fld02 = line[ 1];
					String fld03 = line[ 2];
					String fld04 = line[ 3];
					String fld05 = line[ 4];
					String fld06 = line[ 5];
					String fld07 = line[ 6];
					String fld08 = line[ 7];
					String fld09 = line[ 8];
					String fld10 = line[ 9];
					String fld11 = line[10];
					String fld12 = line[11];
					String fld13 = line[12];
					String fld14 = line[13];
					String fld15 = line[14];
					String fld16 = line[15];
					String fld17 = line[16];
					String fld18 = line[17];
					String fld19 = line[18];

					if (!Flag.TRUE) {
						Print.println("[ %d ]", i);
						System.out.println("    01 LINE        : [" + fld01 + "]");
						System.out.println("    02 PART        : [" + fld02 + "]");
						System.out.println("    03 EQPMODEL    : [" + fld03 + "]");
						System.out.println("    04 EQPID       : [" + fld04 + "]");
						System.out.println("    05 MODULE_NAME : [" + fld05 + "]");
						System.out.println("    06 PROCID      : [" + fld06 + "]");
						System.out.println("    07 PRODID      : [" + fld07 + "]");
						System.out.println("    08 PPID        : [" + fld08 + "]");
						System.out.println("    09 GLASSID     : [" + fld09 + "]");
						System.out.println("    10 PROCESSSTEP : [" + fld10 + "]");
						System.out.println("    11 BEGINSTEP   : [" + fld11 + "]");
						System.out.println("    12 SENSOR_NAME : [" + fld12 + "]");
						System.out.println("    13 PARAM       : [" + fld13 + "]");
						System.out.println("    14 PARAM_VALUE : [" + fld14 + "]");
						System.out.println("    15 USL         : [" + fld15 + "]");
						System.out.println("    16 LSL         : [" + fld16 + "]");
						System.out.println("    17 BEGINTIME   : [" + fld17 + "]");
						System.out.println("    18 CLUSTER_ID  : [" + fld18 + "]");
						System.out.println("    19 GROUP_ID    : [" + fld19 + "]");
						System.out.println();
					}

					if (Flag.TRUE) {
						derbyPreStmt.setString( 1, fld01);
						derbyPreStmt.setString( 2, fld02);
						derbyPreStmt.setString( 3, fld03);
						derbyPreStmt.setString( 4, fld04);
						derbyPreStmt.setString( 5, fld05);
						derbyPreStmt.setString( 6, fld06);
						derbyPreStmt.setString( 7, fld07);
						derbyPreStmt.setString( 8, fld08);
						derbyPreStmt.setString( 9, fld09);
						derbyPreStmt.setString(10, fld10);
						derbyPreStmt.setString(11, fld11);
						derbyPreStmt.setString(12, fld12);
						derbyPreStmt.setString(13, fld13);
						derbyPreStmt.setDouble(14, Double.parseDouble(fld14));
						derbyPreStmt.setString(15, fld15);
						derbyPreStmt.setString(16, fld16);
						derbyPreStmt.setString(17, fld17);
						derbyPreStmt.setString(18, fld18);
						derbyPreStmt.setString(19, fld19);

						derbyPreStmt.executeUpdate();
					}
					
					i ++;
				}
				
				reader.close();

				if (Flag.TRUE) System.out.println("\t insert table count = " + i);
				
			} catch (SQLException e) {
				throw e;
			} catch (Exception e) {
				throw e;
			} finally {
				try {
					if (derbyPreStmt != null)
						derbyPreStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (Flag.TRUE) System.out.println("## end DERBY INSERT TABLE ==> " + ((System.nanoTime() - time0) / 1000 / 1000) + " milliseconds");
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	private static void derbyUpdateTable() throws SQLException
	{
		if (Flag.TRUE) {
			long time0 = System.nanoTime();
			if (Flag.TRUE) System.out.println("## start DERBY UPDATE TABLE");
			
			try {
				
				derbyPreStmt = derbyConn.prepareStatement("UPDATE A24_EQP_FDC02_DETAILS01 SET BEGINSTEP = ? WHERE BEGINSTEP = ? ");

				derbyPreStmt.setString(1, "-");
				derbyPreStmt.setString(2, "N/A");

				int ret = derbyPreStmt.executeUpdate();

				if (Flag.TRUE) System.out.println("\t update table count = " + ret);
				
			} catch (SQLException e) {
				throw e;
			} finally {
				try {
					if (derbyPreStmt != null)
						derbyPreStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (Flag.TRUE) System.out.println("## end DERBY UPDATE TABLE : " + ((System.nanoTime() - time0) / 1000 / 1000) + " milliseconds");
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	private static void derbySelectTable() throws SQLException
	{
		if (Flag.TRUE) {
			long time0 = System.nanoTime();
			if (Flag.TRUE) System.out.println("## start DERBY SELECT TABLE");
			
			try {
				derbyStmt = derbyConn.createStatement();
				derbyResultSet = derbyStmt.executeQuery(""
                        + "SELECT                        \n"
                        + "    *                         \n"
                        + "FROM                          \n"
                        + "    A24_EQP_FDC02_DETAILS01   \n"
                        + "WHERE 1=1                     \n"
                        + "ORDER BY                      \n"
                        + "    PART,                     \n"
                        + "    EQPiD,                    \n"
                        + "    GLASSiD,                  \n"
                        + "    PARAM_VALUE               \n"
				);
				
				derbyMetaData = derbyResultSet.getMetaData();

				int cntFields = derbyMetaData.getColumnCount();
				
				if (Flag.TRUE) {
					for (int i=1; i <= cntFields; i++) {
						if (Flag.TRUE) System.out.println(String.format("%2d) [%-30s] [%s] [%s]"
								, i, derbyMetaData.getColumnName(i), derbyMetaData.getColumnLabel(i), derbyMetaData.getColumnClassName(i)));
					}
				}
				
				if (Flag.TRUE) {
					for (int i=0; i < 100 && derbyResultSet.next(); i++) {
						
						StringBuffer sb = new StringBuffer();
						sb.append(String.format("(%4d)   ", i));
						
						for (int col=1; col <= cntFields; col++) {
							sb.append("  [").append(derbyResultSet.getString(col)).append("]");
						}
						
						if (Flag.TRUE) System.out.println(sb.toString());
					}
				}

			} catch (SQLException e) {
				throw e;
			} finally {
				try {
					if (derbyResultSet != null)
						derbyResultSet.close();

					if (derbyStmt != null)
						derbyStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (Flag.TRUE) System.out.println("## end DERBY SELECT TABLE ==> " + ((System.nanoTime() - time0) / 1000 / 1000) + " milliseconds");
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	private static void derbyDeleteTable() throws SQLException
	{
		if (Flag.TRUE) {
			long time0 = System.nanoTime();
			if (Flag.TRUE) System.out.println("## start DERBY DELETE TABLE");
			
			try {
				
				derbyPreStmt = derbyConn.prepareStatement("DELETE FROM A24_EQP_FDC02_DETAILS01 WHERE GROUP_ID = ? ");

				derbyPreStmt.setString(1, "GOOD");

				int ret = derbyPreStmt.executeUpdate();

				if (Flag.TRUE) System.out.println("\t delete table count = " + ret);

			} catch (SQLException e) {
				throw e;
			} finally {
				try {
					if (derbyPreStmt != null)
						derbyPreStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (Flag.TRUE) System.out.println("## end DERBY DELETE TABLE ==> " + ((System.nanoTime() - time0) / 1000 / 1000) + " milliseconds");
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	private static void derbyTruncateTable() throws SQLException
	{
		if (Flag.TRUE) {
			long time0 = System.nanoTime();
			if (Flag.TRUE) System.out.println("## start DERBY TRUNCATE TABLE");
			
			try {
				
				derbyStmt = derbyConn.createStatement();

				boolean ret = derbyStmt.execute("TRUNCATE TABLE A24_EQP_FDC02_DETAILS01 ");

				if (Flag.TRUE) System.out.println("\t truncate table ret = " + ret);

			} catch (SQLException e) {
				throw e;
			} finally {
				try {
					if (derbyStmt != null)
						derbyStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (Flag.TRUE) System.out.println("## end DERBY TRUNCATE TABLE ==> " + ((System.nanoTime() - time0) / 1000 / 1000) + " milliseconds");
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	private static void derbyDropTable() throws SQLException
	{
		if (Flag.TRUE) {
			long time0 = System.nanoTime();
			if (Flag.TRUE) System.out.println("## start DERBY DROP TABLE");
			
			try {
				derbyStmt = derbyConn.createStatement();
				boolean ret = derbyStmt.execute(""
						+ "DROP TABLE                  \n"
						+ "    A24_EQP_FDC02_DETAILS01 \n"
				);

				if (Flag.TRUE) System.out.println("\t create table ret = " + ret);

			} catch (SQLException e) {
				throw e;
			} finally {
				try {
					if (derbyStmt != null)
						derbyStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (Flag.TRUE) System.out.println("## end DERBY DROP TABLE ==> " + ((System.nanoTime() - time0) / 1000 / 1000) + " milliseconds");
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	private static void derbyCommit() throws SQLException
	{
		if (Flag.TRUE) {
			long time0 = System.nanoTime();
			if (Flag.TRUE) System.out.println("## start DERBY COMMIT");
			
			try {
				
				derbyConn.commit();

			} catch (SQLException e) {
				throw e;
			}
			
			if (Flag.TRUE) System.out.println("## end DERBY COMMIT ==> " + ((System.nanoTime() - time0) / 1000 / 1000) + " milliseconds");
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	private static void derbyDisconnect() throws SQLException
	{
		if (Flag.TRUE) {
			long time0 = System.nanoTime();
			if (Flag.TRUE) System.out.println("## start DERBY DISCONNECT");
			
			try {
				
				if (derbyConn != null)
					derbyConn.close();

			} catch (SQLException e) {
				throw e;
			}
			
			if (Flag.TRUE) System.out.println("## end DERBY DISCONNECT ==> " + ((System.nanoTime() - time0) / 1000 / 1000) + " milliseconds");
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	private static void derbyShutdown() throws SQLException
	{
		if (Flag.TRUE) {
			long time0 = System.nanoTime();
			if (Flag.TRUE) System.out.println("## start DERBY SHUTDOWN");
			
			try {
				// DriverManager.getConnection("jdbc:derby:anal/AR010141209A3120;shutdown=true");
				DriverManager.getConnection("jdbc:derby:anal/AR;shutdown=true");
			} catch (SQLException e) {
				if (!"08006".equals(e.getSQLState()))
					throw e;
			}
			
			if (Flag.TRUE) System.out.println("## end DERBY SHUTDOWN ==> " + ((System.nanoTime() - time0) / 1000 / 1000) + " milliseconds");
		}
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	private static void derbyRemoveFolder() throws Exception
	{
		if (Flag.TRUE) {
			long time0 = System.nanoTime();
			if (Flag.TRUE) System.out.println("## start REMOVE FOLDER");
			
			try {
				/*
				 * TODO : 2014.12.22
				 * remove JOBID folder AR010141209A3120
				 */
				if (Flag.TRUE) System.out.println("\t remove : " + System.getProperty("derby.system.home") + "/anal");
				
			} catch (Exception e) {
				throw e;
			}
			
			if (Flag.TRUE) System.out.println("## end REMOVE FOLDER ==> " + ((System.nanoTime() - time0) / 1000 / 1000) + " milliseconds");
		}
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	private static void derbyJarFolder() throws Exception
	{
		if (Flag.TRUE) {
			long time0 = System.nanoTime();
			if (Flag.TRUE) System.out.println("## start JAR FOLDER");
			
			try {
				/*
				 * TODO : 2014.12.22
				 * jar JOBID folder AR010141209A3120
				 */
				if (Flag.TRUE) System.out.println("\t jar : " + System.getProperty("derby.system.home") + "/anal");
				
			} catch (Exception e) {
				throw e;
			}
			
			if (Flag.TRUE) System.out.println("## end JAR FOLDER ==> " + ((System.nanoTime() - time0) / 1000 / 1000) + " milliseconds");
		}
	}
	
	/**
	 * 
	 * @param args
	 * @throws SQLException
	 * @throws Exception
	 */
	private static void test01(String[] args) throws SQLException, Exception
	{
		if (Flag.TRUE) derbyConnect();
		
		if (Flag.TRUE) derbyCreateTable();
		
		if (Flag.TRUE) derbyInsertTableFromCSV();
		if (!Flag.TRUE) derbyUpdateTable();
		if (!Flag.TRUE) derbySelectTable();
		
		if (!Flag.TRUE) derbyDeleteTable();
		if (!Flag.TRUE) derbyTruncateTable();
		if (!Flag.TRUE) derbyDropTable();
		
		if (Flag.TRUE) derbyCommit();
		if (Flag.TRUE) derbyDisconnect();
		
		if (Flag.TRUE) derbyShutdown();
		
		if (Flag.TRUE) derbyRemoveFolder();
		if (Flag.TRUE) derbyJarFolder();
	}
	
	/**
	 * 
	 * @param args
	 * @throws SQLException
	 * @throws Exception
	 */
	private static void test02(String[] args) throws SQLException, Exception
	{
		if (Flag.TRUE) derbyConnect();

		if (Flag.TRUE) derbyCreateTable();
		
		if (Flag.TRUE) derbyInsertTableFromCSV();
		if (Flag.TRUE) derbySelectTable();
		
		if (Flag.TRUE) derbyUpdateTable();
		if (Flag.TRUE) derbySelectTable();
		
		if (Flag.TRUE) derbyDeleteTable();
		if (Flag.TRUE) derbySelectTable();
		
		if (Flag.TRUE) derbyTruncateTable();
		if (Flag.TRUE) derbySelectTable();

		if (Flag.TRUE) derbyDropTable();
		
		if (Flag.TRUE) derbyCommit();
		if (Flag.TRUE) derbyDisconnect();
		
		if (Flag.TRUE) derbyShutdown();
		
		if (Flag.TRUE) derbyRemoveFolder();
		if (Flag.TRUE) derbyJarFolder();
	}

	/**
	 * 
	 * @param args
	 * @throws SQLException
	 * @throws Exception
	 */
	public static void main(String[] args) throws SQLException, Exception
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

		if (Flag.TRUE) test01(args);
		if (!Flag.TRUE) test02(args);
	}
}
