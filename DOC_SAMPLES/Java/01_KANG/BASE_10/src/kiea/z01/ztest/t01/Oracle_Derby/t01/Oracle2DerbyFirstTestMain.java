package kiea.z01.ztest.t01.Oracle_Derby.t01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import kiea.kr.co.tain.base.Flag;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDriver;

public class Oracle2DerbyFirstTestMain
{
	private static final String JOBID = "AR010141217A3272";
	
	private static OracleConnection oraConn = null;
	private static Statement oraStmt = null;
	private static ResultSet oraResultSet = null;
	
	private static Connection derbyConn = null;
	private static Statement derbyStmtCreate = null;
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
	
	/**
	 * 
	 * main
	 *
	 * @param args
	 */
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

		if (Flag.TRUE) test01();
	}
}
