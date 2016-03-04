package sdc.anal.lya.macro.A24.EQP_FDC02_SUM01.v05;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.FileValue;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

import org.apache.commons.io.output.FileWriterWithEncoding;

import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.ResultSetHelper;
import au.com.bytecode.opencsv.ResultSetHelperService;

public class EQP_FDC02_SUM01_MacroJob extends AbstractMacroJob
{
	protected Connection oraConn = null;
	protected Statement oraStmt = null;
	protected PreparedStatement oraPreStmt = null;
	protected ResultSet oraResultSet = null;
	protected ResultSetMetaData oraMetaData = null;
	
	protected Connection derbyConn = null;
	protected Statement derbyStmt = null;
	protected PreparedStatement derbyPreStmt = null;
	protected ResultSet derbyResultSet = null;
	protected ResultSetMetaData derbyMetaData = null;

	/**
	 * 
	 * @throws SQLException
	 */
	private void derbyConnect() throws SQLException
	{
		if (Flag.TRUE) {
			try {
				// DB SYSTEM HOME
				String home = System.getProperty("derby.system.home");
				if (home == null)
					System.setProperty("derby.system.home", "D:/KANG/WORK/DERBY_DB_HOME");
				//System.setProperty("derby.system.home", "D:/ANAL/anal/LYA/data/DerbyDB");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Flag.TRUE) {
			long time0 = System.nanoTime();
			if (!Flag.TRUE) System.out.println("## start DERBY CONNECTION");
			
			try {
				Properties props = new Properties();
				props.put("user", "app");
				props.put("password", "app");

				// derbyConn = DriverManager.getConnection("jdbc:derby:anal/AR010141209A3120;create=true", props);
				this.derbyConn = DriverManager.getConnection("jdbc:derby:anal/" + this.jobId + ";create=true", props);
				this.derbyConn.setAutoCommit(false);

			} catch (SQLException e) {
				throw e;
			}
			
			if (!Flag.TRUE) System.out.println("## end DERBY CONNECTION ==> " + ((System.nanoTime() - time0) / 1000 / 1000) + " milliseconds");
		}
	}
	
	/**
	 * 
	 * @throws SQLException
	 */
	private void derbyCreateTable() throws SQLException
	{
		if (Flag.TRUE) {
			long time0 = System.nanoTime();
			if (!Flag.TRUE) System.out.println("## start DERBY CREATE TABLE");
			
			try {
				this.derbyStmt = this.derbyConn.createStatement();
				boolean ret = false;
				
				try {
					ret = this.derbyStmt.execute(" DROP TABLE A24_EQP_FDC02_SUM01 ");
				} catch (SQLException e) {
					if (!"42Y55".equals(e.getSQLState()))
						throw e;
				}
				
				ret = this.derbyStmt.execute(""
						+ "CREATE TABLE                \n"
						+ "    A24_EQP_FDC02_SUM01     \n"
						+ "(                           \n"
						+ "    CLUSTER_ID   VARCHAR(5)   NOT NULL  , \n"
						+ "    PART         VARCHAR(20)            , \n"
						+ "    EQPID        VARCHAR(20)  NOT NULL  , \n"
						+ "    SENSOR_NAME  VARCHAR(200) NOT NULL  , \n"
						+ "    CNT          INTEGER                , \n"
						+ "    MIN_VAL      DOUBLE PRECISION       , \n"
						+ "    MAX_VAL      DOUBLE PRECISION       , \n"
						+ "    GOOD_CNT     INTEGER                , \n"
						+ "    BAD_CNT      INTEGER                , \n"
						+ "    GOOD_SUM     DOUBLE PRECISION       , \n"
						+ "    BAD_SUM      DOUBLE PRECISION       , \n"
						+ "    GOOD_AVG     DOUBLE PRECISION       , \n"
						+ "    BAD_AVG      DOUBLE PRECISION       , \n"
						+ "    FDC_BG       DOUBLE PRECISION       , \n"
						+ "    RANK         INTEGER                  \n"
						+ ")\n"
				);

				if (!Flag.TRUE) System.out.println("\t create table ret = " + ret);
				
			} catch (SQLException e) {
				if (!"X0Y32".equals(e.getSQLState()))
					throw e;
				else
					if (!Flag.TRUE) System.out.println("이미 DDL문(CREATE)으로 생성되었습니다.");
			} finally {
				try {
					if (this.derbyStmt != null) {
						this.derbyStmt.close();
						this.derbyStmt = null;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (!Flag.TRUE) System.out.println("## end DERBY CREATE TABLE ==> " + ((System.nanoTime() - time0) / 1000 / 1000) + " milliseconds");
		}
	}

	/**
	 * 
	 * @throws SQLException
	 */
	private void derbyDisconnect() throws SQLException
	{
		if (Flag.TRUE) {
			long time0 = System.nanoTime();
			if (!Flag.TRUE) System.out.println("## start DERBY DISCONNECT");
			
			try {
				
				if (this.derbyConn != null)
					this.derbyConn.close();

			} catch (SQLException e) {
				throw e;
			}
			
			if (!Flag.TRUE) System.out.println("## end DERBY DISCONNECT ==> " + ((System.nanoTime() - time0) / 1000 / 1000) + " milliseconds");
		}
	}

	/**
	 * 
	 * @throws SQLException
	 */
	private void derbyShutdown() throws SQLException
	{
		if (Flag.TRUE) {
			long time0 = System.nanoTime();
			if (!Flag.TRUE) System.out.println("## start DERBY SHUTDOWN");
			
			try {
				// DriverManager.getConnection("jdbc:derby:anal/AR010141209A3120;shutdown=true");
				DriverManager.getConnection("jdbc:derby:anal/" + this.jobId + ";shutdown=true");
			} catch (SQLException e) {
				if (!"08006".equals(e.getSQLState()))
					throw e;
			}
			
			if (!Flag.TRUE) System.out.println("## end DERBY SHUTDOWN ==> " + ((System.nanoTime() - time0) / 1000 / 1000) + " milliseconds");
		}
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	private EQP_FDC02_DETAILS01_CsvIo reader1 = null;
	private EQP_FDC02_SUM01_CsvIo writer1 = null;
	
	@SuppressWarnings("unused")
	private List<EQP_FDC02_DETAILS01_Bean> inList1  = null;
	private List<EQP_FDC02_SUM01_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public EQP_FDC02_SUM01_MacroJob(String jobId)
	{
		this.jobId = jobId;
		
		this.filePath = FileUtil.makeDataDir(BasePath.getInstance().getDataPath(), StrUtil.getDateFromJobId(this.jobId), this.jobId);
	}
	
	/**
	 * generateDataSet
	 */
	public void generateDataSet()
	{
	}

	/**
	 * beforeMacroJob
	 */
	public void beforeMacroJob()
	{
		if (Flag.TRUE) Logger.info("beforeMacroJob : " + this.getClass());
		
		if (Flag.TRUE) {
			/*
			 * 시간기록 시작
			 */
			EQP_FDC02_SUM01_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(EQP_FDC02_SUM01_Main.dsName)));

			bean.sendToOracle();

			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
		}

		if (Flag.TRUE) {
			/*
			 * INSERT
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.INSERT);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "JOB_ID     ", StrUtil.quote(jobId) },
					{ "CMD_CODE   ", StrUtil.quote(EQP_FDC02_SUM01_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(EQP_FDC02_SUM01_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(EQP_FDC02_SUM01_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			/*
			 * TODO : 2014.12.23 : Derby JavaDB 적용
			 */
			try {
				if (Flag.TRUE) derbyConnect();
				if (Flag.TRUE) derbyCreateTable();
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		
		if (!Flag.TRUE) {
			try {
				reader1 = new EQP_FDC02_DETAILS01_CsvIo(this.filePath);
				writer1 = new EQP_FDC02_SUM01_CsvIo(this.filePath);
				
				inList1  = reader1.getList(true);
				outList1 = writer1.getList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * macroJob
	 */
	public void macroJob()
	{
		if (Flag.TRUE) Logger.info("macroJob : " + this.getClass());

		if (Flag.TRUE) {
			try {
				/*
				 * Job
				 */
				this.derbyStmt = this.derbyConn.createStatement();
				boolean ret = this.derbyStmt.execute(""
						+ "INSERT INTO                                                                           \n"
						+ "    A24_EQP_FDC02_SUM01                                                               \n"
						+ "(                                                                                     \n"
						+ "    CLUSTER_ID   ,                                                                    \n"
						+ "    PART         ,                                                                    \n"
						+ "    EQPID        ,                                                                    \n"
						+ "    SENSOR_NAME  ,                                                                    \n"
						+ "    CNT          ,                                                                    \n"
						+ "    MIN_VAL      ,                                                                    \n"
						+ "    MAX_VAL      ,                                                                    \n"
						+ "    GOOD_CNT     ,                                                                    \n"
						+ "    BAD_CNT      ,                                                                    \n"
						+ "    GOOD_SUM     ,                                                                    \n"
						+ "    BAD_SUM      ,                                                                    \n"
						+ "    GOOD_AVG     ,                                                                    \n"
						+ "    BAD_AVG      ,                                                                    \n"
						+ "    FDC_BG       ,                                                                    \n"
						+ "    RANK                                                                              \n"
						+ ")                                                                                     \n"
						+ "SELECT                                                                                \n"
						+ "    A.CLUSTER_ID,                                                                     \n"
						+ "    A.PART,                                                                           \n"
						+ "    A.EQPID,                                                                          \n"
						+ "    A.SENSOR_NAME,                                                                    \n"
						+ "    A.CNT,                                                                            \n"
						+ "    A.MIN_VAL,                                                                        \n"
						+ "    A.MAX_VAL,                                                                        \n"
						+ "    A.GOOD_CNT,                                                                       \n"
						+ "    A.BAD_CNT,                                                                        \n"
						+ "    A.GOOD_SUM,                                                                       \n"
						+ "    A.BAD_SUM,                                                                        \n"
						+ "    (A.GOOD_SUM - A.MIN_VAL * A.GOOD_CNT)                                             \n"
						+ "        / ((A.MAX_VAL - A.MIN_VAL) * A.GOOD_CNT * A.GOOD_CNT) AS GOOD_AVG,            \n"
						+ "    (A.BAD_SUM  - A.MIN_VAL * A.BAD_CNT )                                             \n"
						+ "        / ((A.MAX_VAL - A.MIN_VAL) * A.BAD_CNT  * A.BAD_CNT ) AS BAD_AVG,             \n"
						+ "    ABS((A.BAD_SUM  - A.MIN_VAL * A.BAD_CNT )                                         \n"
						+ "        / ((A.MAX_VAL - A.MIN_VAL) * A.BAD_CNT  * A.BAD_CNT )                         \n"
						+ "        - (A.GOOD_SUM - A.MIN_VAL * A.GOOD_CNT)                                       \n"
						+ "        / ((A.MAX_VAL - A.MIN_VAL) * A.GOOD_CNT * A.GOOD_CNT)) AS FDC_BG,             \n"
						+ "    1 AS RANK                                                                         \n"
						+ "FROM                                                                                  \n"
						+ "    (                                                                                 \n"
						+ "        SELECT                                                                        \n"
						+ "            A.CLUSTER_ID,                                                             \n"
						+ "            A.PART,                                                                   \n"
						+ "            A.EQPID,                                                                  \n"
						+ "            A.SENSOR_NAME,                                                            \n"
						+ "            COUNT(*) AS CNT,                                                          \n"
						+ "            MIN(PARAM_VALUE) AS MIN_VAL,                                              \n"
						+ "            MAX(PARAM_VALUE) AS MAX_VAL,                                              \n"
						+ "            SUM(CASE GROUP_ID WHEN 'GOOD' THEN 1 ELSE 0 END) AS GOOD_CNT,             \n"
						+ "            SUM(CASE GROUP_ID WHEN 'BAD'  THEN 1 ELSE 0 END) AS BAD_CNT,              \n"
						+ "            SUM(CASE GROUP_ID WHEN 'GOOD' THEN PARAM_VALUE ELSE 0 END) AS GOOD_SUM,   \n"
						+ "            SUM(CASE GROUP_ID WHEN 'BAD'  THEN PARAM_VALUE ELSE 0 END) AS BAD_SUM     \n"
						+ "        FROM                                                                          \n"
						+ "            A24_EQP_FDC02_DETAILS01 A                                                 \n"
						+ "        WHERE 1=1                                                                     \n"
						+ "        GROUP BY                                                                      \n"
						+ "            A.CLUSTER_ID,                                                             \n"
						+ "            A.PART,                                                                   \n"
						+ "            A.EQPID,                                                                  \n"
						+ "            A.SENSOR_NAME                                                             \n"
						+ "    ) A                                                                               \n"
						+ "WHERE 1=1                                                                             \n"
						+ "    AND A.GOOD_CNT > 0                                                                \n"
						+ "    AND A.BAD_CNT > 0                                                                 \n"
						+ "    --AND A.MIN_VAL <> A.MAX_VAL                                                      \n"
						+ "    AND ABS(A.MIN_VAL - A.MAX_VAL) > 0.0000001                                        \n"
						+ "ORDER BY 14 DESC                                                                      \n"
						//+ "FETCH FIRST 20 ROWS ONLY                                                              \n"
				);
				
				if (!Flag.TRUE) System.out.println("\t insert from select ret = " + ret);
				
				// 자료 건수 기록
				//EQP_FDC02_SUM01_Main.cntWList = cnt;

				//this.derbyConn.commit();

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (this.derbyStmt != null) {
						this.derbyStmt.close();
						this.derbyStmt = null;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * afterMacroJob
	 */
	public void afterMacroJob()
	{
		if (Flag.TRUE) Logger.info("afterMacroJob : " + this.getClass());
		
		if (!Flag.TRUE) {
			/*
			 * NOT USED
			 */
			try {
				EQP_FDC02_SUM01_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * Derby SELECT Query -> ResultSet -> CSV 파일
			 * ORDER BY CLUSTER_ID, PART, EQP, SENSOR_NAME, PARAM_VALUE, GROUP_ID
			 */
			try {
				this.derbyStmt = this.derbyConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				this.derbyResultSet = this.derbyStmt.executeQuery(""
						+ "SELECT                                 \n"
						+ "    CLUSTER_ID    AS CLUSTER_ID     ,  \n"
						+ "    PART          AS PART           ,  \n"
						+ "    EQPID         AS EQPID          ,  \n"
						+ "    SENSOR_NAME   AS SENSOR_NAME    ,  \n"
						+ "    CNT           AS CNT            ,  \n"
						+ "    MIN_VAL       AS MIN_VAL        ,  \n"
						+ "    MAX_VAL       AS MAX_VAL        ,  \n"
						+ "    GOOD_CNT      AS GOOD_CNT       ,  \n"
						+ "    BAD_CNT       AS BAD_CNT        ,  \n"
						+ "    GOOD_SUM      AS GOOD_SUM       ,  \n"
						+ "    BAD_SUM       AS BAD_SUM        ,  \n"
						+ "    GOOD_AVG      AS GOOD_AVG       ,  \n"
						+ "    BAD_AVG       AS BAD_AVG        ,  \n"
						+ "    FDC_BG        AS FDC_BG         ,  \n"
						+ "    RANK          AS RANK              \n"
						+ "FROM                                   \n"
						+ "    A24_EQP_FDC02_SUM01                \n"
						+ "ORDER BY                               \n"
						//+ "    CLUSTER_ID                      ,  \n"
						//+ "    PART                            ,  \n"
						//+ "    EQPID                           ,  \n"
						//+ "    SENSOR_NAME                     ,  \n"
						+ "    FDC_BG                             \n"
						+ "FETCH FIRST 20 ROWS ONLY               \n"
				);
				
				this.derbyResultSet.last();
				int cnt = this.derbyResultSet.getRow();
				this.derbyResultSet.first();

				String fileName = this.filePath + FileValue.SEP + EQP_FDC02_SUM01_Main.dsName + ".csv";
				if (Flag.TRUE) new File(fileName).delete();
				CSVWriter writer = new CSVWriter(new FileWriterWithEncoding(fileName, "EUC-KR"));  // default seperator
				writer.setResultService((ResultSetHelper) new ResultSetHelperService());
				writer.writeAll(this.derbyResultSet, true);
				writer.close();

				EQP_FDC02_SUM01_Main.cntWList = cnt;

				if (Flag.TRUE) System.out.println("CSV OK! = " + cnt);

				// this.derbyConn.commit();

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (this.derbyResultSet != null) {
						this.derbyResultSet.close();
						this.derbyResultSet = null;
					}
					
					if (this.derbyStmt != null) {
						this.derbyStmt.close();
						this.derbyStmt = null;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if (Flag.TRUE) {
			/*
			 * 종료
			 */
			try {
				this.derbyConn.commit();

				derbyDisconnect();
				derbyShutdown();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			EQP_FDC02_SUM01_Main.runMSec = (System.nanoTime() - EQP_FDC02_SUM01_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", EQP_FDC02_SUM01_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + EQP_FDC02_SUM01_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + EQP_FDC02_SUM01_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(EQP_FDC02_SUM01_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
