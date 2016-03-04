package kiea.proj.sdc.anal.tools.table.ANAL_CSV.v01;

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
				} else if (!Flag.TRUE) {
					/*
					 * 테스트
					 */
					query = String.format("\n"
							+ "SELECT                                \n"
							+ "    *                                 \n"
							+ "FROM                                  \n"
							+ "    anal_csv                          \n"
							+ "WHERE 1=1                             \n"
							+ "    AND job_id LIKE 'AR020%s%%'       \n"
							+ "ORDER BY                              \n"
							+ "    job_id DESC                       \n"
							+ "    , s_time                          \n"

							//+ "    AND ROWNUM <= 100                 \n"
							//+ "    AND cmd_code in ( 'A01', 'A90')   \n"
							//+ "    AND job_id LIKE 'AR___%s%%'       \n"
							//+ "    AND job_id LIKE 'AR010%s%%'       \n"
							//+ "    AND job_id LIKE 'AR020%s%%'       \n"
							//+ "    AND job_id  >= 'AR010141119A3224'       \n"
							//+ "    AND job_id IN ('AR010141119A3224')       \n"
							//+ "    AND job_id IN ('AR010141119A3224','AR010141117A5242')       \n"
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
							+ "    anal_csv                          \n"
							+ "WHERE 1=1                             \n"
							//+ "    AND job_id LIKE 'AR010%s%%'       \n"
							//+ "    AND job_id = 'AR010141125A3226'   \n"
							+ "    AND job_id = 'AR020141201A3271'   \n"
							+ "ORDER BY                              \n"
							+ "    job_id DESC                       \n"
							+ "    , s_time                          \n"
							+ "    , e_time DESC                     \n"

							//+ "    AND ROWNUM <= 100                 \n"
							//+ "    AND job_id LIKE 'AR___%s%%'       \n"
							//+ "    AND job_id = 'AR010141125A3226'   \n"
							//+ "    AND job_id  >= 'AR010141119A3224'       \n"
							//+ "    AND job_id IN ('AR020141120A3080')       \n"
							//+ "    AND job_id IN ('AR010141119A3224')       \n"
							//+ "    AND job_id IN ('AR010141119A3331','AR010141119A3333')       \n"
							//+ "    AND job_id BETWEEN 'AR010141124A3156' AND 'AR010141124A3166'  \n"
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
					 *  1) JOB_ID                         [java.lang.String]
					 *  2) CMD_CODE                       [java.lang.String]
					 *  3) PROG_NM                        [java.lang.String]
					 *  4) CSV_NM                         [java.lang.String]
					 *  5) MAIN_CLASS                     [java.lang.String]
					 *  6) LIST_CNT                       [java.math.BigDecimal]
					 *  7) S_TIME                         [java.sql.Timestamp]
					 *  8) E_TIME                         [java.sql.Timestamp]
					 *  9) R_MSEC                         [java.lang.String]
					 * 10) CSV_STATUS                     [java.lang.String]
					 * 11) LOG_MESSAGE                    [java.lang.String]
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
					Print.println("---------- ANAL_CSV -----------");
					
					String oldJobId = "";
					
					for (int i=0; i < 10000 && resultSet.next(); i++) {
						String fld1 = resultSet.getString("JOB_ID");
						String fld2 = resultSet.getString("PROG_NM");
						String fld3 = resultSet.getString("CSV_NM");
						String fld4 = resultSet.getString("LIST_CNT");
						String fld5 = resultSet.getString("S_TIME");
						String fld6 = resultSet.getString("E_TIME");
						String fld7 = resultSet.getString("R_MSEC");
						String fld8 = resultSet.getString("CSV_STATUS");
						String fld9 = resultSet.getString("LOG_MESSAGE");
						
						if (!fld1.equals(oldJobId)) {
							Print.println();
							oldJobId = fld1;
						}
						
						if (Flag.TRUE) Print.println("%4d) [%s] [%-40s] [%-40s] [%10s] [%s] [%s] [%10s] [%s] [%s]", i, fld1, fld2, fld3, fld4, fld5, fld6, fld7, fld8, fld9);
					}
				}

				if (!Flag.TRUE) {
					/*
					 * row data
					 */
					Print.println("---------- ANAL_CSV -----------");

					for (int i=0; i < 30000 && resultSet.next(); i++) {
						
						StringBuffer sb = new StringBuffer();
						sb.append(String.format("(%4d)   ", i));
						
						for (int col=1; col <= metaData.getColumnCount(); col++) {
							if (col == 5)  // Mail Class
								continue;
							
							sb.append("\t").append(resultSet.getString(col));
						}
						
						System.out.println(sb.toString());
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
						+ "DELETE FROM                           \n"
						+ "    anal_csv                          \n"
						+ "WHERE 1=1                             \n"
						//+ "    AND job_id = 'AR010141110A3144'   \n"
						+ "    AND job_id = 'AR020141021A3300'   \n"
						//+ "    AND job_id BETWEEN 'AR010141125A3226' AND 'AR010141125A3231'  \n"
						//+ " DELETE FROM anal_csv WHERE job_id = '' \n"
						//+ "    AND job_id BETWEEN 'AR010141124A3156' AND 'AR010141124A3166'  \n"
						//+ "    AND job_id <= 'AR010141121A9999'  \n"
						//+ "    AND job_id >= 'AR010141121A3214'  \n"
						//+ "    AND job_id  = 'AR010141121A3222'  \n"
						+ ""
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
	
	private static void test01()
	{
		if (Flag.TRUE) {
			ANAL_CSV_Main instance = ANAL_CSV_Main.getInstance();
			
			if (!Flag.TRUE) instance.delete_01();
			if (Flag.TRUE) instance.select_01();
			
			instance.close();
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
