package kiea.z01.ztest.t01.Oracle_Derby.t01;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import au.com.bytecode.opencsv.CSVReader;
import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDriver;

public class Csv2DerbyTestMain
{
	private static final String JOBID = "AR010141209A3120";
	
	private static String strPath = "D:/KANG/WORK/DERBY_TEST/anal_data/" + JOBID;
	private static String strFileName = "A24_EQP_FDC02_DETAILS01.csv";

	private static OracleConnection oraConn = null;
	private static Statement oraStmt = null;
	private static ResultSet oraResultSet = null;
	
	private static Connection derbyConn = null;
	private static Statement derbyStmtCreate = null;
	private static Statement derbyStmtAlter = null;
	private static PreparedStatement derbyStmtInsert = null;
	private static ResultSet derbyResultSet = null;

	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * test01
	 *
	 */
	private static void test01()
	{
		if (Flag.TRUE) {
			/*
			 * Oracle 연결
			 */
			try {
				String strUser = "YMS_TEST";
				String strPassword = "test11gyms#";
				
				String strUrl = "jdbc:oracle:thin:@" +
						"(DESCRIPTION=" +
						"    (ADDRESS_LIST=(LOAD_BALANCE=ON)" +
						"        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.221)(PORT=1521))" +
						"        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.222)(PORT=1521))" +
						"        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.223)(PORT=1521))" +
						"        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.241)(PORT=1521))" +
						"        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.242)(PORT=1521))" +
						"        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.243)(PORT=1521))" +
						"    )" +
						"    (CONNECT_DATA =(SERVER=DEDICATED)" +
						"        (SERVICE_NAME=ymsdb)" +
						"    )" +
						")";
						
				
				OracleDriver oracleDriver = new OracleDriver();
				
				Properties prop = new Properties();
				prop.setProperty("user", strUser);
				prop.setProperty("password", strPassword);
				
				oraConn = (OracleConnection) oracleDriver.connect(strUrl, prop);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Flag.TRUE) {
			/*
			 * Derby 연결
			 */
			try {
				Properties props = new Properties();
				
				props.put("user", "app");
				props.put("password", "app");

				derbyConn = DriverManager.getConnection("jdbc:derby:anal/" + JOBID + ";create=true", props);
				derbyConn.setAutoCommit(false);
				
				derbyStmtCreate = derbyConn.createStatement();
				derbyStmtCreate.execute(""
						+ "create table                          \n"
						+ "    ANAL_PARAM                        \n"
						+ "(                                     \n"
						+ "    JOB_ID    VARCHAR(20)  not null,  \n"
						+ "    SEQ       DECIMAL      not null,  \n"
						+ "    PARAM_NM  VARCHAR(50),            \n"
						+ "    PARAM_VAL VARCHAR(200),           \n"
						+ "    REG_DT    DATE                    \n"
						+ ")                                     \n"
						);

			} catch (SQLException e) {
				if (!"X0Y32".equals(e.getSQLState()))
					e.printStackTrace();
				else
					System.out.println("이미 DDL문(CREATE)으로 생성되었습니다.");
			}
		}
		
		if (Flag.TRUE) {
			/*
			 * 처리
			 */
			try {
				if (Flag.TRUE) {
					/*
					 * Oracle 자료를 읽어 Derby에 Insert 한다.
					 *      JOB_ID    VARCHAR(20)  not null,
					 *      SEQ       NUMBER       not null,
					 *      PARAM_NM  VARCHAR(50),
					 *      PARAM_VAL VARCHAR(200),
					 *      REG_DT    DATE
					 */
					oraStmt = oraConn.createStatement();
					oraResultSet = oraStmt.executeQuery("select JOB_ID, SEQ, PARAM_NM, PARAM_VAL, REG_DT from ANAL_PARAM where job_id = '" + JOBID + "'");

					derbyStmtInsert = derbyConn.prepareStatement("INSERT INTO ANAL_PARAM (JOB_ID, SEQ, PARAM_NM, PARAM_VAL, REG_DT) VALUES (?,?,?,?,?)");
					
					for (int i=0; i < 1000 && oraResultSet.next(); i++) {
						String fld1 = oraResultSet.getString(1);
						int fld2 = oraResultSet.getInt(2);
						String fld3 = oraResultSet.getString(3);
						String fld4 = oraResultSet.getString(4);
						Date fld5 = oraResultSet.getDate(5);
						
						derbyStmtInsert.setString(1, fld1);
						derbyStmtInsert.setInt(2, fld2);
						derbyStmtInsert.setString(3, fld3);
						derbyStmtInsert.setString(4, fld4);
						derbyStmtInsert.setDate(5, fld5);
						derbyStmtInsert.executeUpdate();

						if (Flag.TRUE) System.out.println(String.format("[%s] [%s] [%s] [%s] [%s]", fld1, fld2, fld3, fld4, fld5));
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * Derby 자료를 읽는다.
					 */
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Flag.TRUE) {
			/*
			 * Derby close
			 */
			try {
				derbyConn.commit();
				if (derbyResultSet != null) derbyResultSet.close();
				if (derbyStmtInsert != null) derbyStmtInsert.close();
				if (derbyStmtCreate != null) derbyStmtCreate.close();
				if (derbyConn != null) derbyConn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			/*
			 * Derby shutdown
			 */
			try {
				DriverManager.getConnection("jdbc:derby:anal/" + JOBID + ";shutdown=true");
			} catch (SQLException e) {
				if (!"08006".equals(e.getSQLState()))
					e.printStackTrace();
			}
		}
		
		if (Flag.TRUE) {
			/*
			 * Oracle close
			 */
			try {
				if (oraResultSet != null) oraResultSet.close();
				if (oraStmt != null) oraStmt.close();
				if (oraConn != null) oraConn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("PROCESSING OK!!!!!");
	}
	
	private static void test02()
	{
		if (Flag.TRUE) {
			/*
			 * size = 19
			 * 01 LINE          VARCHAR,
			 * 02 PART          VARCHAR,
			 * 03 EQPMODEL      VARCHAR,
			 * 04 EQPID         VARCHAR,
			 * 05 MODULE_NAME   VARCHAR,
			 * 06 PROCID        VARCHAR,
			 * 07 PRODID        VARCHAR,
			 * 08 PPID          VARCHAR,
			 * 09 GLASSID       VARCHAR,
			 * 10 PROCESSSTEP   VARCHAR,
			 * 11 BEGINSTEP     VARCHAR,
			 * 12 SENSOR_NAME   VARCHAR,
			 * 13 PARAM         VARCHAR,
			 * 14 PARAM_VALUE   VARCHAR,
			 * 15 USL           VARCHAR,
			 * 16 LSL           VARCHAR,
			 * 17 BEGINTIME     VARCHAR,
			 * 18 CLUSTER_ID    VARCHAR,
			 * 19 GROUP_ID      VARCHAR,
			 */
		}
		
		if (Flag.TRUE) {
			System.out.println("------------------ CONNECTION DB and CREATE TABLE --------------------");

			/*
			 * Derby 연결
			 */
			try {
				Properties props = new Properties();
				
				props.put("user", "app");
				props.put("password", "app");

				derbyConn = DriverManager.getConnection("jdbc:derby:anal/" + JOBID + ";create=true", props);
				derbyConn.setAutoCommit(false);
				
				derbyStmtCreate = derbyConn.createStatement();
				derbyStmtCreate.execute(""
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
						+ "    PARAM_VALUE   VARCHAR(200)          ,  \n"
						+ "    USL           VARCHAR(200)          ,  \n"
						+ "    LSL           VARCHAR(200)          ,  \n"
						+ "    BEGINTIME     VARCHAR(200)          ,  \n"
						+ "    CLUSTER_ID    VARCHAR(200) NOT NULL ,  \n"
						+ "    GROUP_ID      VARCHAR(200) NOT NULL    \n"
						+ ")                           \n"
				);

			} catch (SQLException e) {
				if (!"X0Y32".equals(e.getSQLState()))
					e.printStackTrace();
				else
					System.out.println("이미 DDL문(CREATE)으로 생성되었습니다.");
			}
		}

		if (Flag.TRUE) {
			System.out.println("------------------ INSERT from SELECT --------------------");
			
			/*
			 * 처리
			 */
			try {
				CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(strPath + File.separatorChar + strFileName), "EUC-KR"));
				String[] line;
				
				derbyStmtInsert = derbyConn.prepareStatement("INSERT INTO A24_EQP_FDC02_DETAILS01 VALUES (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?)");

				final int LIMIT_IDX = (10 * 1000 * 1000);
				int i = 0;
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
						derbyStmtInsert.setString( 1, fld01);
						derbyStmtInsert.setString( 2, fld02);
						derbyStmtInsert.setString( 3, fld03);
						derbyStmtInsert.setString( 4, fld04);
						derbyStmtInsert.setString( 5, fld05);
						derbyStmtInsert.setString( 6, fld06);
						derbyStmtInsert.setString( 7, fld07);
						derbyStmtInsert.setString( 8, fld08);
						derbyStmtInsert.setString( 9, fld09);
						derbyStmtInsert.setString(10, fld10);
						derbyStmtInsert.setString(11, fld11);
						derbyStmtInsert.setString(12, fld12);
						derbyStmtInsert.setString(13, fld13);
						derbyStmtInsert.setString(14, fld14);
						derbyStmtInsert.setString(15, fld15);
						derbyStmtInsert.setString(16, fld16);
						derbyStmtInsert.setString(17, fld17);
						derbyStmtInsert.setString(18, fld18);
						derbyStmtInsert.setString(19, fld19);

						derbyStmtInsert.executeUpdate();
					}
					
					i ++;
				}
				
				reader.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!Flag.TRUE) {
			System.out.println("------------------ ALTER CONSTRAINT Primary Key --------------------");

			/*
			 * ALTER CONSTRAINT : unique
			 */
			try {
				derbyStmtAlter = derbyConn.createStatement();
				derbyStmtAlter.execute(""
						+ "ALTER TABLE                                   \n"
						+ "    A24_EQP_FDC02_DETAILS01                   \n"
						+ "ADD CONSTRAINT                                \n"
						+ "    A24_EQP_FDC02_DETAILS01_PK  Primary Key   \n"
						+ "(                                             \n"
						+ "    CLUSTER_ID   ,                            \n"
						+ "    GROUP_ID     ,                            \n"
						+ "    EQPID        ,                            \n"
						+ "    GLASSID      ,                            \n"
						+ "    SENSOR_NAME  ,                            \n"
						+ "    PARAM                                     \n"
						+ ")                                             \n"
						);

			} catch (SQLException e) {
				//if (!"X0Y32".equals(e.getSQLState()))
					e.printStackTrace();
				//else
				//	System.out.println("이미 DDL문(CREATE)으로 생성되었습니다.");
			}
		}
		
		if (Flag.TRUE) {
			System.out.println("------------------ CREATE INDEX --------------------");

			/*
			 * CREATE INDEX
			 */
			try {
				derbyStmtAlter = derbyConn.createStatement();
				derbyStmtAlter.execute(""
						+ "CREATE INDEX                       \n"
						+ "    A24_EQP_FDC02_DETAILS01_IDX01  \n"
						+ "ON                                 \n"
						+ "    A24_EQP_FDC02_DETAILS01        \n"
						+ "(                                  \n"
						+ "    CLUSTER_ID   ,                 \n"
						+ "    GROUP_ID     ,                 \n"
						+ "    EQPID        ,                 \n"
						+ "    GLASSID      ,                 \n"
						+ "    SENSOR_NAME  ,                 \n"
						+ "    PARAM                          \n"
						+ ")                                  \n"
						);

			} catch (SQLException e) {
				//if (!"X0Y32".equals(e.getSQLState()))
					e.printStackTrace();
				//else
				//	System.out.println("이미 DDL문(CREATE)으로 생성되었습니다.");
			}
		}
		
		if (Flag.TRUE) {
			System.out.println("------------------ CLOSE of Derby --------------------");

			/*
			 * Derby close
			 */
			try {
				derbyConn.commit();

				if (derbyResultSet != null) derbyResultSet.close();
				if (derbyStmtInsert != null) derbyStmtInsert.close();
				if (derbyStmtCreate != null) derbyStmtCreate.close();
				if (derbyConn != null) derbyConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			/*
			 * Derby shutdown
			 */
			try {
				DriverManager.getConnection("jdbc:derby:anal/" + JOBID + ";shutdown=true");
			} catch (SQLException e) {
				if (!"08006".equals(e.getSQLState()))
					e.printStackTrace();
			}
		}

		System.out.println("PROCESSING OK!!!!!");
	}

	/**
	 * 
	 * main
	 *
	 * @param args
	 */
	public static void main(String[] args)
	{
		long before, after;
		before = System.nanoTime();

		if (Flag.TRUE) {
			try {
				// DB SYSTEM HOME
				System.setProperty("derby.system.home", "D:/KANG/WORK/DERBY_DB_HOME");
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}

		if (!Flag.TRUE) test01();
		if (Flag.TRUE) test02();

		after = System.nanoTime();
		System.out.println("time to be execute = " + (after - before) / 1000 / 1000);  // milliseconds
	}
}
