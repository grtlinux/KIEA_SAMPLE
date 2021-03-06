package kiea.proj.sdc.anal.tools.table.SYMS_ANAL_PARAM.v01;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.common.Connection;
import oracle.jdbc.OracleConnection;

public class SYMS_ANAL_PARAM_Main
{
	private OracleConnection conn = null;
	private Statement stmt = null;

	private SYMS_ANAL_PARAM_Main()
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
				
				String query = "";
				
				if (!Flag.TRUE) {
					/*
					 * 운영
					 */
					query = String.format("\n"
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
				} else {
					/*
					 * 테스트
					 */
					query = String.format("\n"
							+ "SELECT                                \n"
							+ "    *                                 \n"
							+ "FROM                                  \n"
							+ "    syms_anal_param                   \n"
							+ "WHERE 1=1                             \n"
							//+ "    AND ROWNUM <= 100                 \n"
							//+ "    AND job_id LIKE 'AR___%s%%'       \n"
							+ "    AND job_id LIKE 'AR010%s%%'       \n"
							+ "ORDER BY                              \n"
							+ "    job_id DESC                       \n"
							//+ "        create_date DESC              \n"
							+ ""
							, DateTime.getDate(6)
							);
				}
					
				ResultSet resultSet = stmt.executeQuery(query);
				ResultSetMetaData metaData = resultSet.getMetaData();

				if (Flag.TRUE) {
					/* 
					 * meta data
					 * ---------- META DATA ----------
					 *  1) BIZ_UNIT                       [java.lang.String]
					 *  2) JOB_ID                         [java.lang.String]
					 *  3) ANAL_JOB_DIP_NAME              [java.lang.String]
					 *  4) ANAL_JOB_DIP_VALUE             [java.lang.String]
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
					
					for (int i=0; i < 30 && resultSet.next(); i++) {
						String fld1 = resultSet.getString("BIZ_UNIT");
						String fld2 = resultSet.getString("JOB_ID");
						String fld3 = resultSet.getString("ANAL_JOB_DIP_NAME");
						String fld4 = resultSet.getString("ANAL_JOB_DIP_VALUE");
						
						if (Flag.TRUE) Print.println("%4d) [%s] [%s] [%s] [%s]", i, fld1, fld2, fld3, fld4);
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

	private static SYMS_ANAL_PARAM_Main instance = null;
	
	public static SYMS_ANAL_PARAM_Main getInstance()
	{
		if (instance == null) {
			instance = new SYMS_ANAL_PARAM_Main();
		}
		
		return instance;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			SYMS_ANAL_PARAM_Main instance = SYMS_ANAL_PARAM_Main.getInstance();
			
			if (Flag.TRUE) instance.select_01();
			
			instance.close();
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
