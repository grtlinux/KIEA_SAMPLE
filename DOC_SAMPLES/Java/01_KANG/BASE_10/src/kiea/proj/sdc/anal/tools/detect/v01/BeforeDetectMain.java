package kiea.proj.sdc.anal.tools.detect.v01;

import java.sql.ResultSet;
import java.sql.Statement;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.kr.co.tain.util.Print;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.util.ServiceDetectorProperties;
import oracle.jdbc.OracleConnection;

public class BeforeDetectMain
{
	private ServiceDetectorProperties prop = null;
	private String rowNum = null;

	private OracleConnection conn = null;
	private Statement stmt = null;
	
	public BeforeDetectMain()
	{
		if (Flag.TRUE) {
			this.prop = ServiceDetectorProperties.getInstance();
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

	public void execute()
	{
		if (Flag.TRUE) {

			getDetectJobIdList();
			
			//getParamList();
			
			//insertParamList();
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

	private String getQuery()
	{
		String ret = null;
		
		if (Flag.TRUE) {
			ret = String.format("\n"
					+ "WITH TBL AS (                                         \n"
					+ "    SELECT                                            \n"
					+ "        job_id                                        \n"
					+ "    FROM                                              \n"
					+ "        yms_lya.syms_lya_job_master                   \n"
					+ "    WHERE 1=1                                         \n"
					+ "        AND job_title NOT LIKE '[얼룩%%'  /* 불량 */  \n"
					+ "        AND job_id LIKE 'LYAAU%s%%'       /* auto */  \n"
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
					, DateTime.getDate(6)
					, this.rowNum
					);
			if (Flag.TRUE) System.out.println("-------------------- LYA_AR [" + ret + "]");
		}
		
		return ret;
	}
	
	private String getQueryLYAAR()
	{
		String ret = null;
		
		if (Flag.TRUE) {
			ret = String.format("\n"
					+ "WITH TBL AS (                                         \n"
					+ "    SELECT                                            \n"
					+ "        job_id                                        \n"
					+ "    FROM                                              \n"
					+ "        yms_lya.syms_lya_job_master                   \n"
					+ "    WHERE 1=1                                         \n"
					+ "        AND job_title NOT LIKE '[얼룩%%'  /* 불량 */  \n"
					+ "        AND job_id LIKE 'LYAAU%s%%'       /* auto */  \n"
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
					, DateTime.getDate(6)
					, this.rowNum
					);
			if (Flag.TRUE) System.out.println("-------------------- LYA_AR [" + ret + "]");
		}
		
		return ret;
	}
	
	private String getQueryLYAUR()
	{
		String ret = null;
		
		if (Flag.TRUE) {
			ret = String.format("\n"
					+ "WITH TBL AS (                                         \n"
					+ "    SELECT                                            \n"
					+ "        job_id                                        \n"
					+ "    FROM                                              \n"
					+ "        yms_lya.syms_lya_job_master                   \n"
					+ "    WHERE 1=1                                         \n"
					+ "        AND job_title NOT LIKE '[얼룩%%'  /* 불량 */  \n"
					+ "        AND job_id LIKE 'LYAUR%s%%'     /* manual */  \n"
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
					, DateTime.getDate(6)
					, this.rowNum
					);
			if (Flag.TRUE) System.out.println("-------------------- LYA_UR [" + ret + "]");
		}
		
		return ret;
	}
	
	private String getQueryMURAAR()
	{
		String ret = null;
		
		if (Flag.TRUE) {
			ret = String.format("\n"
					+ "WITH TBL AS (                                         \n"
					+ "    SELECT                                            \n"
					+ "        job_id                                        \n"
					+ "    FROM                                              \n"
					+ "        yms_lya.syms_lya_job_master                   \n"
					+ "    WHERE 1=1                                         \n"
					+ "        AND job_title LIKE '[얼룩%%'      /* 얼룩 */  \n"
					+ "        AND job_id LIKE 'LYAAU%s%%'       /* auto */  \n"
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
					, DateTime.getDate(6)
					, this.rowNum
					);
			if (Flag.TRUE) System.out.println("-------------------- MURA_AR [" + ret + "]");
		}
		
		return ret;
	}
	
	private String getQueryMURAUR()
	{
		String ret = null;
		
		if (Flag.TRUE) {
			ret = String.format("\n"
					+ "WITH TBL AS (                                         \n"
					+ "    SELECT                                            \n"
					+ "        job_id                                        \n"
					+ "    FROM                                              \n"
					+ "        yms_lya.syms_lya_job_master                   \n"
					+ "    WHERE 1=1                                         \n"
					+ "        AND job_title LIKE '[얼룩%%'      /* 얼룩 */  \n"
					+ "        AND job_id LIKE 'LYAUR%s%%'     /* manual */  \n"
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
					, DateTime.getDate(6)
					, this.rowNum
					);
			if (Flag.TRUE) System.out.println("-------------------- MURA_UR [" + ret + "]");
		}
		
		return ret;
	}
	
	private String getDetectJobIdList()
	{
		String ret = null;
		
		if (Flag.TRUE) {
			try {
				
				if (!Flag.TRUE) {
					
					ResultSet rs = stmt.executeQuery(getQuery());
					
					for (int i=0; rs.next(); i++) {
						String jobId = rs.getString("JOB_ID");
						
						if (Flag.TRUE) Print.println("%4d) [%s]", i, jobId);
					}
				}
				
				if (Flag.TRUE) {
					
					ResultSet rs = stmt.executeQuery(getQueryLYAAR());
					
					for (int i=0; rs.next(); i++) {
						String jobId = rs.getString("JOB_ID");
						
						if (Flag.TRUE) Print.println("%4d) [%s]", i, jobId);
					}
				}
				
				if (Flag.TRUE) {
					
					ResultSet rs = stmt.executeQuery(getQueryLYAUR());
					
					for (int i=0; rs.next(); i++) {
						String jobId = rs.getString("JOB_ID");
						
						if (Flag.TRUE) Print.println("%4d) [%s]", i, jobId);
					}
				}
				
				if (Flag.TRUE) {
					
					ResultSet rs = stmt.executeQuery(getQueryMURAAR());
					
					for (int i=0; rs.next(); i++) {
						String jobId = rs.getString("JOB_ID");
						
						if (Flag.TRUE) Print.println("%4d) [%s]", i, jobId);
					}
				}
				
				if (Flag.TRUE) {
					
					ResultSet rs = stmt.executeQuery(getQueryMURAUR());
					
					for (int i=0; rs.next(); i++) {
						String jobId = rs.getString("JOB_ID");
						
						if (Flag.TRUE) Print.println("%4d) [%s]", i, jobId);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ret;
	}

	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			SystemProperties.setTesting("010");
		}
		
		if (Flag.TRUE) {
			new BeforeDetectMain().execute();
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
