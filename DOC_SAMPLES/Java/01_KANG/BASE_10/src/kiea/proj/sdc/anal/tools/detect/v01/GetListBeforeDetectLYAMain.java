package kiea.proj.sdc.anal.tools.detect.v01;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.kr.co.tain.util.Print;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.util.ServiceDetectorProperties;
import kiea.proj.sdc.anal.util.StrUtil;
import oracle.jdbc.OracleConnection;

public class GetListBeforeDetectLYAMain
{
	private String[][] typeList = { 
			{ "AU", "AR" },   /* auto   */ 
			{ "UR", "UR" },   /* manual */
		};
	private ServiceDetectorProperties prop = null;
	private String svcCode = null;
	private String rowNum = null;

	private String[] type = null;

	private OracleConnection conn = null;
	private Statement stmt = null;

	public GetListBeforeDetectLYAMain()
	{
		if (Flag.TRUE) {
			this.prop = ServiceDetectorProperties.getInstance();
			this.svcCode = this.prop.get("anal.service.code");
			this.rowNum = this.prop.get("anal.service.rownum");
			if (!Flag.TRUE) Print.println("[SVC:%s] [ROW:%s]", this.svcCode, this.rowNum);
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

	public void execute()
	{
		if (Flag.TRUE) {

			for (String[] type : typeList) {
				this.type = type;
				
				String maxQafJobId = getMaxQafJobId();
				
				List<String> jobIdList = getDetectJobIdList(maxQafJobId);
				
				if (jobIdList.size() > 0) {
					/*
					 * 자료가 있을 때만 처리하도록
					 */
					List<String[]> paramList = getDetectParamList(jobIdList);
					
					insertParamList(paramList);
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
	
	private String getMaxQafJobId()
	{
		String ret = null;
		
		if (Flag.TRUE) {
			try {
				String query = "";
				query = String.format("\n"
						+ "WITH TBL AS (                         \n"
						+ "    SELECT                            \n"
						+ "        job_id                        \n"
						+ "    FROM                              \n"
						+ "        syms_anal_param               \n"
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
						, this.type[1]
						, this.svcCode
						, DateTime.getDate(6)
						);
				if (!Flag.TRUE) System.out.println("-------------------- QUERY [" + query + "]");
				
				if (Flag.TRUE) {
					String jobId, maxQafJobId;
					
					ResultSet rs = stmt.executeQuery(query);
					if (rs.next()) {
						jobId = rs.getString("JOB_ID");
						maxQafJobId = "LYA" + this.type[0] + jobId.substring(5, 11) + jobId.substring(12);
					} else {
						jobId = String.format("%s%s%sA0000", this.type[1], this.svcCode, DateTime.getDate(6));
						maxQafJobId = "LYA" + this.type[0] + jobId.substring(5, 11) + jobId.substring(12);
					}
					
					if (!Flag.TRUE) Print.println("> [%s] -> [%s]", jobId, maxQafJobId);
					
					ret = maxQafJobId;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return ret;
	}

	private List<String> getDetectJobIdList(String maxQafJobId)
	{
		List<String> ret = new ArrayList<String>();
		
		if (Flag.TRUE) {
			try {
				String query = "";
				query = String.format("\n"
						+ "WITH TBL AS (                                         \n"
						+ "    SELECT                                            \n"
						+ "        job_id                                        \n"
						+ "    FROM                                              \n"
						+ "        yms_lya.syms_lya_job_master                   \n"
						+ "    WHERE 1=1                                         \n"
						+ "        AND job_title NOT LIKE '[얼룩%%'  /* 불량 */  \n"
						+ "        AND job_id LIKE 'LYA%s%s%%'                   \n"
						+ "        AND job_id > %s                               \n"
						+ "    ORDER BY                                          \n"
						//+ "        create_date DESC                              \n"
						+ "        job_id DESC                                   \n"
						+ ")                                                     \n"
						+ "SELECT                                                \n"
						+ "    job_id                                            \n"
						+ "FROM                                                  \n"
						+ "    TBL                                               \n"
						+ "WHERE 1=1                                             \n"
						+ "    AND ROWNUM <= %s                                  \n"
						, this.type[0]
						, DateTime.getDate(6)
						, StrUtil.quote(maxQafJobId)
						, this.rowNum
						);
				if (!Flag.TRUE) System.out.println("-------------------- QUERY [" + query + "]");
				
				ResultSet rs = stmt.executeQuery(query);
				
				for (int i=0; rs.next(); i++) {
					String jobId = rs.getString("JOB_ID");
					
					if (Flag.TRUE) Print.println("%4d) [%s]", i, jobId);
					ret.add(jobId);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ret;
	}

	private List<String[]> getDetectParamList(List<String> jobIdList)
	{
		List<String[]> ret = new ArrayList<String[]>();
		
		if (Flag.TRUE) {
			String inString = null;
			
			if (Flag.TRUE) {
				/*
				 * 쿼리 in 에 포함될 문자열을 만든다. 
				 */
				StringBuffer sb = new StringBuffer();
				
				for (int i=0; i < jobIdList.size(); i++) {
					if (i > 0)
						sb.append(",");
					sb.append(StrUtil.quote(jobIdList.get(i)));
				}
				
				inString = sb.toString();
				if (!Flag.TRUE) System.out.println("[" + inString + "]");
			}
			
			if (Flag.TRUE) {
				try {
					String query = "";
					query = String.format("\n"
							+ "SELECT                                \n"
							+ "    qaf_job_bu                        \n"
							+ "    , qaf_job_id                      \n"
							+ "    , qaf_job_dip_name                \n"
							+ "    , qaf_job_dip_value               \n"
							+ "FROM                                  \n"
							+ "    yms_lya.qaf_id_job_dip_history    \n"
							+ "WHERE 1=1                             \n"
							+ "    AND qaf_job_id IN (               \n"
							+ "        %s                            \n"
							+ "    )                                 \n"
							+ "ORDER BY                              \n"
							+ "    qaf_job_id                        \n"
							+ ""
							, inString
							);
					if (!Flag.TRUE) System.out.println("-------------------- QUERY [" + query + "]");
					
					ResultSet rs = stmt.executeQuery(query);
					
					for (int i=0; rs.next(); i++) {
						String bizUnit = rs.getString("QAF_JOB_BU");
						String qafJobId = rs.getString("QAF_JOB_ID");
						String jobId = this.type[1] + this.svcCode + qafJobId.substring(5, 11) + "A" + qafJobId.substring(11);
						String paramNm = rs.getString("QAF_JOB_DIP_NAME");
						String paramVal = rs.getString("QAF_JOB_DIP_VALUE");
						
						if (!Flag.TRUE) Print.println("%4d) [%s] [%s] [%s] [%s] [%s]", i, bizUnit, qafJobId, jobId, paramNm, paramVal);
						
						ret.add(new String[] { bizUnit, jobId, paramNm, paramVal });
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return ret;
	}
	
	private boolean insertParamList(List<String[]> paramList)
	{
		boolean ret = true;
		
		if (!Flag.TRUE) {
			/*
			 * 출력
			 */
			for (String[] params : paramList) {
				if (Flag.TRUE) Print.println("[%s] [%s] [%s] [%s]", params[0], params[1], params[2], params[3]);
			}
		}
		
		if (Flag.TRUE) {
			try {
				
				String query;
				
				for (String[] params : paramList) {
					if (Flag.TRUE) {
						query = String.format(""
								+ "INSERT INTO syms_anal_param (biz_unit, job_id, anal_job_dip_name, anal_job_dip_value) "
								+ "VALUES ( %s, %s, %s, %s) "
								, StrUtil.quote(params[0])
								, StrUtil.quote(params[1])
								, StrUtil.quote(params[2])
								, StrUtil.quote(params[3])
								);
						if (!Flag.TRUE) Print.println("QUERY> %s", query);
						
						stmt.executeUpdate(query);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ret;
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
			new GetListBeforeDetectLYAMain().execute();
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
