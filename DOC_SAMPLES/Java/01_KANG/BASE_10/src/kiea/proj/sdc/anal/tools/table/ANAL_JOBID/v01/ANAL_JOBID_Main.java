package kiea.proj.sdc.anal.tools.table.ANAL_JOBID.v01;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.common.Connection;
import oracle.jdbc.OracleConnection;

public class ANAL_JOBID_Main
{
	private OracleConnection conn = null;
	private Statement stmt = null;

	private ANAL_JOBID_Main()
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
							+ "    anal_jobid                        \n"
							+ "WHERE 1=1                             \n"
							+ "    AND ROWNUM <= 500                 \n"
							//+ "    AND status NOT IN ('FINISH')      \n"
							+ "    AND job_id  = 'AR010141202A3097'  \n"
							+ "ORDER BY                              \n"
							+ "    job_id DESC                       \n"
							
							//+ "    AND job_id LIKE 'AR0_0%s%%'       \n"
							//+ "    AND status NOT IN ('FINISH')      \n"
							//+ "    AND job_id >= 'AR010141128A4000'  \n"
							//+ "    AND job_id LIKE 'AR___%s%%'       \n"
							//+ "    AND job_id LIKE 'AR020%%'       \n"
							//+ "    AND job_id LIKE 'AR010141121A%%'  \n"
							//+ "    AND job_id = 'AR020141021A3300'   \n"
							//+ "    AND job_id IN ('AR010141119A3224')       \n"
							//+ "    AND job_id IN ('AR010141119A3224','AR010141117A5242')       \n"
							//+ "    AND job_id BETWEEN 'AR010141124A3156' AND 'AR010141124A3166'  \n"
							//+ "    AND job_id <= 'AR010141121A9999'  \n"
							//+ "    AND job_id >= 'AR010141121A3214'  \n"
							//+ "    AND status IN ('READY','RUNNING') \n"
							//+ "        create_date DESC              \n"
							+ ""
							, DateTime.getDate(6)
							);
				}
					
				if (Flag.TRUE) Print.println("QUERY : %s", query);

				ResultSet resultSet = stmt.executeQuery(query);
				ResultSetMetaData metaData = resultSet.getMetaData();

				if (Flag.TRUE) {
					/* 
					 * meta data
					 * ---------- META DATA ----------
					 *  1) JOB_ID                         [java.lang.String]
					 *  2) STATUS                         [java.lang.String]
					 *  3) REG_DT                         [java.sql.Timestamp]
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
					Print.println("---------- ANAL_JOBID -----------");
					
					for (int i=0; i < 50 && resultSet.next(); i++) {
						String fld1 = resultSet.getString("JOB_ID");
						String fld2 = resultSet.getString("STATUS");
						String fld3 = resultSet.getString("REG_DT");
						
						if (Flag.TRUE) Print.println("%4d) [%s] [%s] [%s]", i, fld1, fld2, fld3);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void select_02()
	{
		if (Flag.TRUE) {
			try {
				
				String query = "";
				
				if (!Flag.TRUE) {
					/*
					 * 운영
					 */
					query = String.format("\n"
							+"select job_id , job_title, anal_req_status, create_date \n"
							+"from yms_lya.syms_lya_job_master                        \n"
							+"where 1=1                                               \n"
							+"and job_id like 'LYAAU141201%%'                         \n"
							+"and job_title like '[얼룩%%'                            \n"
							+"and job_title not like '%%-%%'                          \n"
							+"order by job_id desc                                    \n"
							+ ""
							, DateTime.getDate(6)
							);
				} else if (Flag.TRUE) {
					query = String.format("\n"
							+"select job_id , job_title, anal_req_status, create_date \n"
							+" ,substr(clseventid,instr(clseventid,'_',1)+1,instr(clseventid,'_',1,3)-1) as val									  \n"
							+"from yms_lya.syms_lya_job_master                        \n"
							+"where 1=1                                               \n"
							+"and job_id like '%%141201%%'                         \n"
							+"and job_title like '[얼룩%%'                            \n"
							+"and job_title not like '%%-%%'                          \n"
//							+"and job_id ='AR020141201A3271'                          \n"
							+"order by job_id desc                                    \n"
							+ ""
							, DateTime.getDate(6)
							);
				}
					
				if (Flag.TRUE) Print.println("QUERY : %s", query);

				ResultSet resultSet = stmt.executeQuery(query);
				ResultSetMetaData metaData = resultSet.getMetaData();

				if (Flag.TRUE) {
					/* 
					 * meta data
					 * ---------- META DATA ----------
					 *  1) JOB_ID                         [java.lang.String]
					 *  2) STATUS                         [java.lang.String]
					 *  3) REG_DT                         [java.sql.Timestamp]
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
					Print.println("---------- ANAL_JOBID -----------");
					
					for (int i=0; i < 50 && resultSet.next(); i++) {
						String fld1 = resultSet.getString("JOB_ID");
						String fld2 = resultSet.getString("JOB_TITLE");
						String fld3 = resultSet.getString("ANAL_REQ_STATUS");
						String fld4 = resultSet.getString("CREATE_DATE");
						String fld5 = resultSet.getString("VAL");
						
						if (Flag.TRUE) Print.println("%4d) [%s] [%s] [%s] [%s] [%s]", i, fld1, fld2, fld3, fld4, fld5);
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
						+ "UPDATE                                \n"
						+ "    anal_jobid                        \n"
						+ "SET                                   \n"
						+ "    STATUS = 'READY'                  \n"
						+ "WHERE 1=1                             \n"
						+ "    AND job_id  = 'AR010141202A3097'  \n"
						//+ "    AND job_id BETWEEN 'AR010141125A3226' AND 'AR010141125A3231'  \n"
						+ ""
						//+ "    AND job_id = 'AR010141121A3222'  \n"
						//+ "    AND job_id BETWEEN 'AR010141124A3156' AND 'AR010141124A3166'  \n"
						//+ "    AND job_id <= 'AR010141121A9999'  \n"
						//+ "    AND job_id >= 'AR010141121A3214'  \n"
						);
					
				@SuppressWarnings("unused")
				int retNo = stmt.executeUpdate(query);
				
				if (Flag.TRUE) Print.println("QUERY : %s", query);
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

	private static ANAL_JOBID_Main instance = null;
	
	public static ANAL_JOBID_Main getInstance()
	{
		if (instance == null) {
			instance = new ANAL_JOBID_Main();
		}
		
		return instance;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			ANAL_JOBID_Main instance = ANAL_JOBID_Main.getInstance();
			
			if (!Flag.TRUE) instance.update_01();
			if (Flag.TRUE) instance.select_01();
			
			instance.close();
		}
	}
	
	private static void test02()
	{
		if (Flag.TRUE) {
			ANAL_JOBID_Main instance = ANAL_JOBID_Main.getInstance();
			
			if (Flag.TRUE) instance.select_02();
			
			instance.close();
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
		if (!Flag.TRUE) test02();
	}
}
