package kiea.proj.sdc.anal.tools.yms.SYMS.v02;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.tools.yms.SYMS.v01.SYMS_LYA_JOB_MASTER_Main;
import oracle.jdbc.OracleConnection;

public class SYMS_LYA_JOB_MASTER_TestMain
{
	private OracleConnection conn = null;
	private Statement stmt = null;

	private SYMS_LYA_JOB_MASTER_TestMain()
	{
		if (Flag.TRUE) {
			try {
				this.conn = Connection.getOracleConnection();
				this.stmt = conn.createStatement();
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}
	
	public void close()
	{
		if (Flag.TRUE) {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void select_01()
	{
		if (Flag.TRUE) {
			try {
				
				String query = String.format("\n"
						+ "SELECT                                \n"
						+ "    *                                 \n"
						+ "FROM                                  \n"
						+ "    yms_lya.syms_lya_job_master       \n"
						+ "WHERE 1=1                             \n"
						+ "    AND ROWNUM <= 100                 \n"
						+ "    AND job_id LIKE 'LYAAU%s%%'       \n"
						//+ "    AND job_id LIKE 'LYAUR%s%%'       \n"
						+ "ORDER BY                              \n"
						+ "    job_id DESC                       \n"
						//+ "        create_date DESC              \n"
						+ ""
						, DateTime.getDate(6)
						);
					
				ResultSet resultSet = stmt.executeQuery(query);
				ResultSetMetaData metaData = resultSet.getMetaData();

				if (Flag.TRUE) {
					/* 
					 * meta data
					 * ---------- META DATA ----------
					 *  1) BIZ_UNIT                       [java.lang.String]
					 *  2) JOB_ID                         [java.lang.String]
					 *  3) JOB_TITLE                      [java.lang.String]
					 *  4) ANAL_EVENT_TYPE                [java.lang.String]
					 *  5) USER_ID                        [java.lang.String]
					 *  6) USER_NM                        [java.lang.String]
					 *  7) DEPT_CD                        [java.lang.String]
					 *  8) DEPT_NM                        [java.lang.String]
					 *  9) LINE_ID                        [java.lang.String]
					 * 10) INSPECT_AREA                   [java.lang.String]
					 * 11) INSPECT_STEP                   [java.lang.String]
					 * 12) JOB_TYPE                       [java.lang.String]
					 * 13) PERIOD_TYPE                    [java.lang.String]
					 * 14) FROM_DATE                      [java.sql.Timestamp]
					 * 15) TO_DATE                        [java.sql.Timestamp]
					 * 16) REPORT_CNT                     [java.math.BigDecimal]
					 * 17) ANAL_REQ_STATUS                [java.lang.String]
					 * 18) CREATE_DATE                    [java.sql.Timestamp]
					 * 19) CLSEVENTID                     [java.lang.String]
					 * 20) USL                            [java.math.BigDecimal]
					 * 21) SPC_MODI_DATE                  [java.sql.Timestamp]
					 * 22) USER_MAIL                      [java.lang.String]
					 * 23) USER_CLUSTER                   [java.lang.String]
					 */
					System.out.println("---------- META DATA ----------");

					for (int i=1; i <= metaData.getColumnCount(); i++) {
						//System.out.println(String.format("\t%2d) %-30s %-30s [%s]", i, metaData.getColumnName(i), metaData.getColumnLabel(i), metaData.getColumnClassName(i)));
						System.out.println(String.format("\t%2d) %-30s [%s]", i, metaData.getColumnName(i), metaData.getColumnClassName(i)));
					}

					System.out.println();
				}

				if (Flag.TRUE) {
					/*
					 * row data
					 */
					Print.println("---------- SYMS_LYA_JOB_MASTER -----------");
					
					for (int i=0; i < 50 && resultSet.next(); i++) {
						String jobId = resultSet.getString("JOB_ID");
						String jobTitle = resultSet.getString("JOB_TITLE");
						String createDate = resultSet.getString("CREATE_DATE");
						
						if (Flag.TRUE) Print.println("%4d) [%s] [%s] [%s]", i, jobId, createDate, jobTitle);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	///////////////////////////////////////////////////////////////////////////

	private static SYMS_LYA_JOB_MASTER_TestMain instance = null;
	
	public static SYMS_LYA_JOB_MASTER_TestMain getInstance()
	{
		if (instance == null) {
			instance = new SYMS_LYA_JOB_MASTER_TestMain();
		}
		
		return instance;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			SYMS_LYA_JOB_MASTER_Main instance = SYMS_LYA_JOB_MASTER_Main.getInstance();
			instance.select_01();
			instance.close();
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
