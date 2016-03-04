package kiea.proj.sdc.anal.tools.tblchk.v01.step01;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.common.Connection;
import oracle.jdbc.OracleConnection;

public class QAF_ID_JOB_DIP_HISTORY_Main
{
	private OracleConnection conn = null;
	private Statement stmt = null;

	private QAF_ID_JOB_DIP_HISTORY_Main()
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
						+ "    yms_lya.qaf_id_job_dip_history    \n"
						+ "WHERE 1=1                             \n"
						+ "    AND ROWNUM <= 100                 \n"
						+ "    AND qaf_job_id LIKE 'LYAAU%s%%'   \n"
						//+ "    AND qaf_job_id LIKE 'LYAUR%s%%'   \n"
						+ "ORDER BY                              \n"
						+ "    qaf_job_id DESC                   \n"
						+ "    , qaf_job_dip_name                \n"
						+ ""
						, DateTime.getDate(6)
						);
					
				ResultSet resultSet = stmt.executeQuery(query);
				ResultSetMetaData metaData = resultSet.getMetaData();

				if (Flag.TRUE) {
					/* 
					 * meta data
					 * ---------- META DATA ----------
					 * 1) QAF_JOB_BU                     [java.lang.String]
					 * 2) QAF_JOB_ID                     [java.lang.String]
					 * 3) QAF_JOB_DIP_NAME               [java.lang.String]
					 * 4) QAF_JOB_DIP_VALUE              [java.lang.String]
					 * 5) QAF_JOB_DIP_CONDITION          [java.lang.String]
					 * 6) QAF_JOB_DIP_METHOD             [java.lang.String]
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
					Print.println("---------- QAF_ID_JOB_DIP_HISTORY -----------");
					
					for (int i=0; i < 50 && resultSet.next(); i++) {
						String jobId = resultSet.getString("QAF_JOB_ID");
						String dipName = resultSet.getString("QAF_JOB_DIP_NAME");
						String dipValue = resultSet.getString("QAF_JOB_DIP_VALUE");
						
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
					
				@SuppressWarnings("unused")
				int retNo = stmt.executeUpdate(query);
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
					
				@SuppressWarnings("unused")
				int retNo = stmt.executeUpdate(query);
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
						+ " DELETE FROM tbl WHERE id = 9 \n"
						+ ""
						);
					
				@SuppressWarnings("unused")
				int retNo = stmt.executeUpdate(query);
			} catch (Exception e) {
				e.printStackTrace();
				ret = false;
			}
		}
		
		return ret;
	}
	
	///////////////////////////////////////////////////////////////////////////

	private static QAF_ID_JOB_DIP_HISTORY_Main instance = null;
	
	public static QAF_ID_JOB_DIP_HISTORY_Main getInstance()
	{
		if (instance == null) {
			instance = new QAF_ID_JOB_DIP_HISTORY_Main();
		}
		
		return instance;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			QAF_ID_JOB_DIP_HISTORY_Main instance = QAF_ID_JOB_DIP_HISTORY_Main.getInstance();
			instance.select_01();
			instance.close();
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
