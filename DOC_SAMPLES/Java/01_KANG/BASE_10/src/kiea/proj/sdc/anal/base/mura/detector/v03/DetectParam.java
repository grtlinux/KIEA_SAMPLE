package kiea.proj.sdc.anal.base.mura.detector.v03;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleConnection;
import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.kr.co.tain.util.Print;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.util.ServiceDetectorProperties;
import kiea.proj.sdc.anal.util.StrUtil;

public class DetectParam implements ParamImpl
{
	private ServiceDetectorProperties prop = null;
	private String svcCode = null;
	private String rowNum = null;
	private String type = null;
	
	private OracleConnection conn = null;
	private Statement stmt = null;
	
	///////////////////////////////////////////////////////////////////////////

	public ParamInfo get()
	{
		return null;
	}
	
	///////////////////////////////////////////////////////////////////////////

	/**
	 * constructor
	 */
	public DetectParam()
	{
		if (Flag.TRUE) {
			this.prop = ServiceDetectorProperties.getInstance();
			this.svcCode = this.prop.get("anal.service.code");
			this.rowNum = this.prop.get("anal.service.rownum");
		}
		
		if (Flag.TRUE) {
			try {
				
				this.conn = Connection.getOracleConnection();
				this.stmt = conn.createStatement();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * execute
	 *
	 */
	public void execute()
	{
		if (Flag.TRUE) {
			String[] types = { "AR", "UR" };
			
			for (String type : types) {
				this.type = type;
				
				String maxJobId = getMaxJobId();
				if (!Flag.TRUE) maxJobId = String.format("%s%s%sA0000", this.type, this.svcCode, DateTime.getDate(6)); // 테스트용
				
				if (!Flag.TRUE) Print.println("MAX_JOBID> %s", maxJobId);

				List<String[]> list = getList(maxJobId);
				
				if (list.size() > 0) {
					insertList(list);
				}
			}
		}

		/*
		 * connection close
		 */
		if (Flag.TRUE) {
			try {
				conn.close();
				if (!Flag.TRUE) System.out.println("##### OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * getMaxJobId
	 *
	 * @return
	 */
	private String getMaxJobId()
	{
		String ret = null;
		
		if (Flag.TRUE) {
			try {
				
				String query;
				query = String.format("\n"
						+ "WITH TBL AS (                         \n"
						+ "    SELECT                            \n"
						+ "        job_id                        \n"
						+ "    FROM                              \n"
						+ "        anal_jobid                    \n"
						+ "    WHERE 1=1                         \n"
						+ "        AND job_id like '%s%s%sA%%'   \n"
						+ "    ORDER BY                          \n"
						+ "        job_id DESC                   \n"
						+ ")                                     \n"
						+ "SELECT                                \n"
						+ "    job_id                            \n"
						+ "FROM                                  \n"
						+ "    TBL                               \n"
						+ "WHERE 1=1                             \n"
						+ "    AND ROWNUM <= 1                   \n"
						+ ""
						, this.type
						, this.svcCode
						, DateTime.getDate(6)
						);
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				
				if (Flag.TRUE) {
					
					ResultSet rs = stmt.executeQuery(query);
					if (rs.next()) {
						ret = rs.getString("JOB_ID");
					} else {
						ret = String.format("%s%s%sA0000", this.type, this.svcCode, DateTime.getDate(6));
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ret;
	}
	
	/**
	 * 
	 * getList
	 *
	 * @param maxJobId
	 * @return
	 */
	private List<String[]> getList(String maxJobId)
	{
		List<String[]> list = new ArrayList<String[]>();
		
		if (Flag.TRUE) {
			try {

				String query;
				query = String.format("\n"
						+ "SELECT                                           \n"
						+ "    job_id,                                      \n"
						+ "    anal_job_dip_name,                           \n"
						+ "    anal_job_dip_value                           \n"
						+ "FROM                                             \n"
						+ "    syms_anal_param                              \n"
						+ "WHERE                                            \n"
						+ "    job_id in (                                  \n"
						+ "        SELECT                                   \n"
						+ "            job_id                               \n"
						+ "        FROM                                     \n"
						+ "        (                                        \n"
						+ "            SELECT                               \n"
						+ "                DISTINCT job_id                  \n"
						+ "            FROM                                 \n"
						+ "                syms_anal_param                  \n"
						+ "            WHERE 1=1                            \n"
						+ "                AND job_id LIKE '%s%s%%'         \n"
						+ "                AND job_id > %s                  \n"
						+ "            ORDER BY                             \n"
						+ "                job_id DESC                      \n"
						+ "        )                                        \n"
						+ "        WHERE 1=1                                \n"
						+ "            AND ROWNUM <= %s                     \n"
						+ "    )                                            \n"
						+ "ORDER BY                                         \n"
						+ "    job_id                                       \n"
						+ ""
						, this.type
						, this.svcCode
						, StrUtil.quote(maxJobId)
						, this.rowNum
						);
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				
				if (Flag.TRUE) {

					ResultSet rs = stmt.executeQuery(query);

					for (int i=0; rs.next(); i++) {
						String jobId = rs.getString("JOB_ID");
						String paramNm = rs.getString("ANAL_JOB_DIP_NAME");
						String paramVal = rs.getString("ANAL_JOB_DIP_VALUE");
						
						if (!Flag.TRUE) Print.println("%4d) [%s] [%s] [%s]", i, jobId, paramNm, paramVal);
						
						list.add(new String[] { jobId, paramNm, paramVal } );
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!Flag.TRUE) {
			/*
			 * list 확인
			 */
			String jobId = "";
			for (String[] items : list) {
				if (!jobId.equals(items[0])) {
					Print.println("[%s] [%-20s] [%-50s]", items[0], items[1], items[2]);
					jobId = items[0];
				} else {
					Print.println(" %16s  [%-20s] [%-50s]", "", items[1], items[2]);
				}
			}
		}

		return list;
	}
	
	/**
	 * 
	 * insertList
	 *
	 * @param list
	 */
	private void insertList(List<String[]> list)
	{
		if (Flag.TRUE) {
			try {
				
				String jobId = "";
				int seq = 0;
				String query;
				
				for (String[] items : list) {
					if (!Flag.TRUE) Print.println("[%s] [%s] [%s]", items[0], items[1], items[2]);
					
					if (Flag.TRUE) {
						if (!jobId.equals(items[0])) {
							query = String.format(""
									+ "INSERT INTO anal_jobid (job_id, status, reg_dt) VALUES ( %s, 'WAIT', SYSDATE) "
									, StrUtil.quote(items[0])
									);
							stmt.executeUpdate(query);

							jobId = items[0];
							seq = 0;
						}
					}
					
					if (Flag.TRUE) {
						query = String.format(""
								+ "INSERT INTO anal_param (job_id, seq, param_nm, param_val, reg_dt) "
								+ "VALUES ( %s, %d, %s, %s, SYSDATE ) "
								, StrUtil.quote(items[0])
								, ++ seq
								, StrUtil.quote(items[1])
								, StrUtil.quote(items[2])
								);
						stmt.executeUpdate(query);
					}
				}
				
				if (Flag.TRUE) {
					query = String.format("UPDATE anal_jobid SET status='READY' WHERE status='WAIT' ");
					stmt.executeUpdate(query);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			SystemProperties.setTesting("010");
		}
		
		if (Flag.TRUE) {
			new DetectParam().execute();
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
