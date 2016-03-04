package kiea.proj.sdc.anal.tools.tblchk.v01.step04;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.common.Connection;
import oracle.jdbc.OracleConnection;

public class ANAL_CSV_Main
{
	private OracleConnection conn = null;
	private Statement stmt = null;

	private ANAL_CSV_Main()
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
	
	public void select_00()
	{
		if (Flag.TRUE) {
			try {
				
				String query = String.format("\n"
						+ "WITH TBL AS (                            \n"
						+ "    SELECT                               \n"
						+ "        DISTINCT job_id                  \n"
						+ "    FROM                                 \n"
						+ "        anal_csv                         \n"
						+ "    WHERE 1=1                            \n"
						//+ "        AND ( 1=0                        \n"
						//+ "            OR job_id like 'AR010%sA%%'  \n"
						//+ "            OR job_id like 'UR010%sA%%'  \n"
						//+ "            OR job_id like 'AR020%sA%%'  \n"
						//+ "            OR job_id like 'UR020%sA%%'  \n"
						//+ "        )                                \n"
						+ "    ORDER BY                             \n"
						+ "        job_id DESC                      \n"
						+ ")                                        \n"
						+ "SELECT                                   \n"
						+ "    job_id                               \n"
						+ "FROM                                     \n"
						+ "    TBL                                  \n"
						+ "WHERE 1=1                                \n"
						//+ "    AND ROWNUM <= 1                      \n"
						+ ""
						, DateTime.getDate(6)
						);
					
				ResultSet resultSet = stmt.executeQuery(query);

				if (Flag.TRUE) {
					/*
					 * row data
					 */
					Print.println("---------- ANAL_CSV -----------00");
					
					for (int i=0; i < 50 && resultSet.next(); i++) {
						String jobId = resultSet.getString("JOB_ID");
						
						if (Flag.TRUE) Print.println("%4d) [%s]", i, jobId);
					}
				}

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
						+ "    anal_csv                          \n"
						+ "WHERE 1=1                             \n"
						+ "    AND ROWNUM <= 100                 \n"
						//+ "    AND job_id LIKE 'AR010%s%%'       \n"
						//+ "    AND job_id LIKE 'UR010%s%%'       \n"
						//+ "    AND job_id LIKE 'AR010%%'         \n"
						//+ "    AND job_id LIKE 'UR010%%'         \n"
						//+ "    AND job_id LIKE 'AR%%'         \n"
						//+ "    AND job_id LIKE 'UR%%'         \n"
						//+ "ORDER BY                              \n"
						//+ "    job_id DESC                       \n"
						//+ "    , anal_job_dip_name               \n"
						+ ""
						, DateTime.getDate(6)
						);
					
				ResultSet resultSet = stmt.executeQuery(query);
				ResultSetMetaData metaData = resultSet.getMetaData();

				if (Flag.TRUE) {
					/* 
					 * meta data
					 * ---------- META DATA ----------
					 * 1) JOB_ID                         [java.lang.String]
					 * 2) SEQ                            [java.math.BigDecimal]
					 * 3) PARAM_NM                       [java.lang.String]
					 * 4) PARAM_VAL                      [java.lang.String]
					 * 5) REG_DT                         [java.sql.Timestamp]
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
					Print.println("---------- ANAL_CSV -----------01");
					
					for (int i=0; i < 50 && resultSet.next(); i++) {
						String jobId = resultSet.getString("JOB_ID");
						String dipName = resultSet.getString("PARAM_NM");
						String dipValue = resultSet.getString("PARAM_VAL");
						
						if (Flag.TRUE) Print.println("%4d) [%s] [%s] [%s]", i, jobId, dipName, dipValue);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean insert_01()
	{
		boolean ret = true;
		
		if (Flag.TRUE) {
			try {
				String query = String.format("\n"
						+ "INSERT INTO tbl (field) VALUES ( 123 ) \n"
						+ ""
						);
					
				int retNo = stmt.executeUpdate(query);
				System.out.println("INSERT ret : " + retNo);
			} catch (Exception e) {
				e.printStackTrace();
				ret = false;
			}
		}
		
		return ret;
	}
	
	public boolean update_01()
	{
		boolean ret = true;
		
		if (Flag.TRUE) {
			try {
				String query = String.format("\n"
						+ " UPDATE tbl SET  NAME = 'KANG' WHERE  id = 3 \n"
						+ ""
						);
					
				int retNo = stmt.executeUpdate(query);
				System.out.println("UPDATE ret : " + retNo);
			} catch (Exception e) {
				e.printStackTrace();
				ret = false;
			}
		}
		
		return ret;
	}
	
	public boolean delete_01()
	{
		boolean ret = true;
		
		if (Flag.TRUE) {
			try {
				String query = String.format("\n"
						+ " DELETE FROM anal_csv WHERE 1=1 \n"
						// + " DELETE FROM anal_param WHERE job_id LIKE 'AR%%' \n"
						+ ""
						);
					
				int retNo = stmt.executeUpdate(query);
				System.out.println("DELETE ret : " + retNo);
			} catch (Exception e) {
				e.printStackTrace();
				ret = false;
			}
		}
		
		return ret;
	}
	
	///////////////////////////////////////////////////////////////////////////

	private static ANAL_CSV_Main instance = null;
	
	public static ANAL_CSV_Main getInstance()
	{
		if (instance == null) {
			instance = new ANAL_CSV_Main();
		}
		
		return instance;
	}
	
	///////////////////////////////////////////////////////////////////////////

	private static void test01_select_jobid()
	{
		if (Flag.TRUE) {
			ANAL_CSV_Main instance = ANAL_CSV_Main.getInstance();
			if (Flag.TRUE) instance.select_00();
			instance.close();
		}
	}

	private static void test01_select()
	{
		if (Flag.TRUE) {
			ANAL_CSV_Main instance = ANAL_CSV_Main.getInstance();
			if (Flag.TRUE) instance.select_01();
			instance.close();
		}
	}
	
	private static void test01_delete()
	{
		if (Flag.TRUE) {
			ANAL_CSV_Main instance = ANAL_CSV_Main.getInstance();
			if (Flag.TRUE) instance.select_01();
			if (Flag.TRUE) instance.delete_01();
			if (Flag.TRUE) instance.select_01();
			instance.close();
		}
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01_select_jobid();
		if (Flag.TRUE) test01_select();
		if (!Flag.TRUE) test01_delete();
	}
}
