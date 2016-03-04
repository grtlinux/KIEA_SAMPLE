package sdc.anal.lya.macro.A24.EQP_FDC02_DETAILS01.v05;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.common.Connection04;
import kiea.proj.sdc.anal.common.FileValue;
import kiea.proj.sdc.anal.common.Parameter;
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

public class EQP_FDC02_DETAILS01_MacroJob extends AbstractMacroJob
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
					ret = this.derbyStmt.execute(" DROP TABLE A24_EQP_FDC02_DETAILS01 ");
				} catch (SQLException e) {
					if (!"42Y55".equals(e.getSQLState()))
						throw e;
				}

				ret = this.derbyStmt.execute(""
						+ "CREATE TABLE                \n"
						+ "    A24_EQP_FDC02_DETAILS01 \n"
						+ "(                           \n"
						+ "    LINE          VARCHAR(20)           ,  \n"
						+ "    PART          VARCHAR(20)           ,  \n"
						+ "    EQPMODEL      VARCHAR(200)          ,  \n"
						+ "    EQPID         VARCHAR(20)  NOT NULL ,  \n"
						+ "    MODULE_NAME   VARCHAR(200)          ,  \n"
						
						+ "    PROCID        VARCHAR(50)           ,  \n"
						+ "    PRODID        VARCHAR(50)           ,  \n"
						+ "    PPID          VARCHAR(20)           ,  \n"
						+ "    GLASSID       VARCHAR(20)  NOT NULL ,  \n"
						+ "    PROCESSSTEP   VARCHAR(50)           ,  \n"
						
						+ "    BEGINSTEP     VARCHAR(50)           ,  \n"
						+ "    SENSOR_NAME   VARCHAR(200) NOT NULL ,  \n"
						+ "    PARAM         VARCHAR(10)  NOT NULL ,  \n"
						//+ "    PARAM_VALUE   VARCHAR(200)          ,  \n"
						+ "    PARAM_VALUE   DOUBLE PRECISION      ,  \n"
						//+ "    PARAM_VALUE   DECIMAL(20,2)         ,  \n"
						+ "    USL           VARCHAR(200)          ,  \n"
						
						+ "    LSL           VARCHAR(200)          ,  \n"
						+ "    BEGINTIME     VARCHAR(50)           ,  \n"
						+ "    CLUSTER_ID    VARCHAR(5)   NOT NULL ,  \n"
						+ "    GROUP_ID      VARCHAR(10)  NOT NULL ,  \n"
						+ "    BAD_RATIO     DOUBLE PRECISION         \n"
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

	private SO_SYS_CELLID_LIST2_CsvIo reader1 = null;
	private RP_SYS_WIP_SUMM1_CsvIo reader2 = null;
	private EQP_FDC02_DETAILS01_CsvIo writer1 = null;
	
	private List<SO_SYS_CELLID_LIST2_Bean> inList1  = null;
	private List<RP_SYS_WIP_SUMM1_Bean> inList2  = null;
	private List<EQP_FDC02_DETAILS01_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public EQP_FDC02_DETAILS01_MacroJob(String jobId)
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
			EQP_FDC02_DETAILS01_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(EQP_FDC02_DETAILS01_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(EQP_FDC02_DETAILS01_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(EQP_FDC02_DETAILS01_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(EQP_FDC02_DETAILS01_Main.dsName + ".csv") },
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
		
		if (Flag.TRUE) {
			try {
				reader1 = new SO_SYS_CELLID_LIST2_CsvIo(this.filePath);
				reader2 = new RP_SYS_WIP_SUMM1_CsvIo(this.filePath);
				writer1 = new EQP_FDC02_DETAILS01_CsvIo(this.filePath);
				
				inList1  = reader1.getList(true);
				inList2  = reader2.getList(true);
				
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
		
		Map<String, SO_SYS_CELLID_LIST2_Bean> mapGlass = new HashMap<String, SO_SYS_CELLID_LIST2_Bean>();
		
		if (Flag.TRUE) {
			/*
			 * GLASS의 ClusterId, Group 정보를 얻는다.
			 */
			for (SO_SYS_CELLID_LIST2_Bean bean : inList1) {
				mapGlass.put(bean.getGlassId(), bean);
			}
		}

		StringBuffer sbEqp = new StringBuffer();
		StringBuffer sbGlass = new StringBuffer();

		if (Flag.TRUE) {
			/*
			 * GLASS_LIST, EQP_LIST 를 얻는다.
			 */
			if (Flag.TRUE) {
				// GLASS_LIST를 얻는다.

				Set<String> setGLASS = new HashSet<String>();

				for (SO_SYS_CELLID_LIST2_Bean bean : inList1) {
					
					setGLASS.add(bean.getGlassId());
					if (setGLASS.size() >= 1000)
						break;
					
					if (!Flag.TRUE) Print.println("SO_SYS_CELLID_LIST2_Bean > %s", bean);
				}
				
				int i = 0;
				for (String glassId : setGLASS) {
					if (i > 0)
						sbGlass.append(",");
					
					sbGlass.append(StrUtil.quote(glassId));
					
					i++;
				}
				
				if (i == 0) {
					sbGlass.append("''");
				}

				if (!Flag.TRUE) {
					/*
					 * 확인출력
					 */
					Print.println("GLASS_LIST : %s", sbGlass.toString());
				}
			}
			
			if (Flag.TRUE) {
				// EQP_LIST를 얻는다.

				Set<String> setEQP = new HashSet<String>();
				
				for (RP_SYS_WIP_SUMM1_Bean bean : inList2) {
					
					setEQP.add(bean.getSuspiciousEqp());
					
					if (!Flag.TRUE) Print.println("RP_SYS_WIP_SUMM_Bean > %s", bean);
				}
				
				int i = 0;
				for (String eqpId : setEQP) {
					if (i > 0)
						sbEqp.append(",");
					
					sbEqp.append(StrUtil.quote(eqpId));
					
					i++;
				}
				
				if (i == 0) {
					sbEqp.append("''");
				}

				if (!Flag.TRUE) {
					/*
					 * 확인출력
					 */
					Print.println("EQP_LIST : %s", sbEqp.toString());
				}
			}
		}
		
		if (Flag.TRUE) {
			/*
			 * Oracle SELECT Query -> ResultSet -> Derby INSERT Query
			 */
			String query = null;
			
			query = String.format(""
					+ "SELECT /*+PARALLEL(2) ORDERED */                                         \n"
					+ "    T1.LINE                                                              \n"
					+ "    , T1.PART                                                            \n"
					+ "    , T1.EQPMODEL                                                        \n"
					+ "    , T1.EQPID                                                           \n"
					+ "    , T1.MODULE_NAME                                                     \n"
					+ "    , T1.PROCID                                                          \n"
					+ "    , T1.PRODID                                                          \n"
					+ "    , T1.PPID                                                            \n"
					+ "    , T1.GLASSID                                                         \n"
					+ "    , T1.PROCESSSTEP                                                     \n"
					+ "    , T1.BEGINSTEP                                                       \n"
					+ "    , T1.SENSOR_NAME                                                     \n"
					+ "    , T1.PARAM                                                           \n"
					+ "    , T1.PARAM_VALUE                                                     \n"
					+ "    , T1.USL                                                             \n"
					+ "    , T1.LSL                                                             \n"
					+ "    , T1.BEGINTIME                                                       \n"
					+ "FROM                                                                     \n"
					+ "    YMS_DW.DW_EQP_FDC_SUM T1                                             \n"
					+ "WHERE 1=1                                                                \n"
					//+ "    AND ROWNUM <= 10                                                     \n"
					+ "    AND T1.LINE        =  %s                                             \n"  // LINE
					+ "    AND T1.BEGINTIME >= TO_DATE(%s,'YYYYMMDDHH24MISS') - 7               \n"  // TODATETIME
					+ "    AND T1.BEGINTIME <= TO_DATE(%s,'YYYYMMDDHH24MISS')                   \n"  // TODATETIME
					+ "    AND T1.EQPID       IN ( %s )                                         \n"
					+ "    AND T1.GLASSID     IN ( %s )                                         \n"
					//+ "    AND T1.BEGINTIME >= SYSDATE - 1                                      \n"
					+ "    AND T1.PARAM       IN ('MEAN')                                       \n"
					+ ""
					, StrUtil.quote(Parameter.getInstance().getStrLine())
					, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
					, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
					, sbEqp.toString()
					, sbGlass.toString()
					);
			
			if (!Flag.TRUE) System.out.println("[" + query + "]");
			if (Flag.TRUE) Logger.info("[" + query + "]");
			
			try {
				this.oraConn = Connection04.getInstance().getConn();
				this.oraStmt = this.oraConn.createStatement();
				this.oraResultSet = this.oraStmt.executeQuery(query);
				
				this.derbyPreStmt = this.derbyConn.prepareStatement("INSERT INTO A24_EQP_FDC02_DETAILS01 VALUES (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?)");

				int cnt = 0;
				for (cnt=0; cnt >= 0 && this.oraResultSet.next(); cnt++) {
					
					this.derbyPreStmt.setString(  1, this.oraResultSet.getString("LINE"       ));
					this.derbyPreStmt.setString(  2, this.oraResultSet.getString("PART"       ));
					this.derbyPreStmt.setString(  3, this.oraResultSet.getString("EQPMODEL"   ));
					this.derbyPreStmt.setString(  4, this.oraResultSet.getString("EQPID"      ));
					this.derbyPreStmt.setString(  5, this.oraResultSet.getString("MODULE_NAME"));
					this.derbyPreStmt.setString(  6, this.oraResultSet.getString("PROCID"     ));
					this.derbyPreStmt.setString(  7, this.oraResultSet.getString("PRODID"     ));
					this.derbyPreStmt.setString(  8, this.oraResultSet.getString("PPID"       ));
					this.derbyPreStmt.setString(  9, this.oraResultSet.getString("GLASSID"    ));
					this.derbyPreStmt.setString( 10, this.oraResultSet.getString("PROCESSSTEP"));
					this.derbyPreStmt.setString( 11, this.oraResultSet.getString("BEGINSTEP"  ));
					this.derbyPreStmt.setString( 12, this.oraResultSet.getString("SENSOR_NAME"));
					this.derbyPreStmt.setString( 13, this.oraResultSet.getString("PARAM"      ));
					this.derbyPreStmt.setDouble( 14, this.oraResultSet.getDouble("PARAM_VALUE"));
					this.derbyPreStmt.setString( 15, this.oraResultSet.getString("USL"        ));
					this.derbyPreStmt.setString( 16, this.oraResultSet.getString("LSL"        ));
					this.derbyPreStmt.setString( 17, this.oraResultSet.getString("BEGINTIME"  ));
					
					SO_SYS_CELLID_LIST2_Bean bean = mapGlass.get(this.oraResultSet.getString("GLASSID"));
					if (bean != null) {
						this.derbyPreStmt.setString( 18, bean.getClusterId());
						this.derbyPreStmt.setString( 19, bean.getGroup    ());
						this.derbyPreStmt.setDouble( 20, Double.parseDouble(bean.getBadRatio ()));
					} else {
						this.derbyPreStmt.setString( 18, "");
						this.derbyPreStmt.setString( 19, "");
						this.derbyPreStmt.setDouble( 20, 0.0);
					}
					
					this.derbyPreStmt.executeUpdate();
				}

				// 자료 건수 기록
				EQP_FDC02_DETAILS01_Main.cntWList = cnt;

				Connection04.getInstance().close();
				// this.derbyConn.commit();
				
				if (Flag.TRUE) System.out.println("OK! = " + cnt);

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (this.oraResultSet != null) {
						this.oraResultSet.close();
						this.oraResultSet = null;
					}
					
					if (this.oraStmt != null) {
						this.oraStmt.close();
						this.oraStmt = null;
					}
					
					if (this.derbyPreStmt != null) {
						this.derbyPreStmt.close();
						this.derbyPreStmt = null;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
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
				
				EQP_FDC02_DETAILS01_Main.cntWList = outList1.size();

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
						+ "    LINE          AS LINE           ,  \n"
						+ "    PART          AS PART           ,  \n"
						+ "    EQPMODEL      AS EQPMODEL       ,  \n"
						+ "    EQPID         AS EQPID          ,  \n"
						+ "    MODULE_NAME   AS MODULE_NAME    ,  \n"
						+ "    PROCID        AS PROCID         ,  \n"
						+ "    PRODID        AS PRODID         ,  \n"
						+ "    PPID          AS PPID           ,  \n"
						+ "    GLASSID       AS GLASSID        ,  \n"
						+ "    PROCESSSTEP   AS PROCESSSTEP    ,  \n"
						+ "    BEGINSTEP     AS BEGINSTEP      ,  \n"
						+ "    SENSOR_NAME   AS SENSOR_NAME    ,  \n"
						+ "    PARAM         AS PARAM          ,  \n"
						+ "    PARAM_VALUE   AS PARAM_VALUE    ,  \n"
						+ "    USL           AS USL            ,  \n"
						+ "    LSL           AS LSL            ,  \n"
						+ "    BEGINTIME     AS BEGINTIME      ,  \n"
						+ "    CLUSTER_ID    AS CLUSTER_ID     ,  \n"
						+ "    GROUP_ID      AS GROUP_ID       ,  \n"
						//+ "    BAD_RATIO     AS BAD_RATIO         \n"
						+ "    BAD_RATIO     AS 불량_율           \n"
						+ "FROM                                   \n"
						+ "    A24_EQP_FDC02_DETAILS01            \n"
						+ "ORDER BY                               \n"
						+ "    CLUSTER_ID                      ,  \n"
						+ "    PART                            ,  \n"
						+ "    EQPID                           ,  \n"
						+ "    SENSOR_NAME                     ,  \n"
						+ "    PARAM_VALUE                     ,  \n"
						+ "    GROUP_ID                           \n"
				);
				
				this.derbyResultSet.last();
				int cnt = this.derbyResultSet.getRow();
				this.derbyResultSet.first();

				String fileName = this.filePath + FileValue.SEP + EQP_FDC02_DETAILS01_Main.dsName + ".csv";
				if (Flag.TRUE) new File(fileName).delete();
				CSVWriter writer = new CSVWriter(new FileWriterWithEncoding(fileName, "EUC-KR"));  // default seperator
				writer.setResultService((ResultSetHelper) new ResultSetHelperService());
				writer.writeAll(this.derbyResultSet, true);
				writer.close();

				EQP_FDC02_DETAILS01_Main.cntWList = cnt;

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
			EQP_FDC02_DETAILS01_Main.runMSec = (System.nanoTime() - EQP_FDC02_DETAILS01_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", EQP_FDC02_DETAILS01_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + EQP_FDC02_DETAILS01_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + EQP_FDC02_DETAILS01_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(EQP_FDC02_DETAILS01_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
