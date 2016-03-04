package kiea.proj.sdc.anal.tools.yms.SYMS.v01;

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
	///////////////////////////////////////////////////////////////////////////

	/*
	 * ---------- SYMS_ANAL_PARAM : META DATA ----------
	 * 1) BIZ_UNIT                       [java.lang.String]
	 * 2) JOB_ID                         [java.lang.String]
	 * 3) ANAL_JOB_DIP_NAME              [java.lang.String]
	 * 4) ANAL_JOB_DIP_VALUE             [java.lang.String]
	 */

	private static void test01()
	{
		if (Flag.TRUE) {
			if (Flag.TRUE) {
				try {
					String[][] tableNames = {
							{ "SYMS_ANAL_PARAM", "SYMS_ANAL_PARAM에 사용되는 코드인자 테이블", },
					};
					
					OracleConnection conn = Connection.getOracleConnection();
					Statement stmt = conn.createStatement();
					
					for (String[] tableName : tableNames) {
						String query;
						query = String.format("SELECT * FROM %s WHERE job_id LIKE 'AR010%%' ORDER BY job_id DESC, anal_job_dip_name", tableName[0]);
						//query = String.format("SELECT * FROM YMS_LYA.%s WHERE ROWNUM <= 1", tableName[0]);
						
						ResultSet resultSet = stmt.executeQuery(query);
						ResultSetMetaData metaData = resultSet.getMetaData();
						
						System.out.println(String.format("\n### TABLE INFO :  < %s > : %s \n", tableName[0].trim(), tableName[1]));
						
						if (Flag.TRUE) {
							/* 
							 * meta data
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
							System.out.println("---------- ROW DATA ----------");
							
							for (int i=0; i < 100 && resultSet.next(); i++) {
								
								StringBuffer sb = new StringBuffer();
								sb.append(String.format("(%6d)   ", i));
								
								for (int col=1; col <= metaData.getColumnCount(); col++) {
									sb.append("\t").append(resultSet.getString(col));
								}
								
								System.out.println(sb.toString());
							}

							System.out.println();
						}
					}
					
					conn.close();
					if (Flag.TRUE) System.out.println("##### OK!");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void test02_JobId()
	{
		if (Flag.TRUE) {
			try {
				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();
				
				String query;
				query = String.format("\n"
						+ "WITH TBL AS (                         \n"
						+ "    SELECT                            \n"
						+ "        DISTINCT job_id               \n"
						+ "    FROM                              \n"
						+ "        syms_anal_param               \n"
						+ "    WHERE 1=1                         \n"
						+ "        AND job_id like 'AR0201A%%'     \n"
						//+ "        AND job_id like '%%%sA%%'     \n"
						+ ")                                     \n"
						+ "SELECT                                \n"
						+ "    job_id                            \n"
						+ "FROM                                  \n"
						+ "    TBL                               \n"
						+ "WHERE 1=1                             \n"
						//+ "    AND ROWNUM <= 1                   \n"
						+ "ORDER BY                              \n"
						+ "    job_id DESC                       \n"
						+ ""
						, DateTime.getDate(6)
						);
					
				ResultSet rs = stmt.executeQuery(query);
					
				if (Flag.TRUE) {
					Print.println("---------- SYMS_ANAL_PARAM -----------");
					
					for (int i=0; rs.next(); i++) {
						String jobId = rs.getString("JOB_ID");
						
						if (Flag.TRUE) Print.println("%4d) [%s]", i, jobId);
					}
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
   5) [AR0201A410083046]
   6) [AR0201A410083045]
   7) [AR0201A410083044]
   8) [AR0201A410083034]
   9) [AR0201A410083033]
  10) [AR0201A410083028]
  11) [AR0201A410083027]
  12) [AR0201A410083026]
  13) [AR0201A410083025]
  14) [AR0201A410083024]
	*/
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (Flag.TRUE) test02_JobId();  // SYMS_ANAL_PARAM 모든 JOBID 확인
	}
}
