package kiea.proj.sdc.anal.base.lcmg.detector.v03;

import java.sql.Statement;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.util.StrUtil;
import oracle.jdbc.OracleConnection;

public class InsertParam
{
	private ParamInfo info = null;
	private OracleConnection conn = null;
	private Statement stmt = null;
	
	public InsertParam(ParamInfo info)
	{
		if (Flag.TRUE) {
			try {
				
				this.info = info;
				this.conn = Connection.getOracleConnection();
				this.stmt = this.conn.createStatement();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private boolean queryDeleteParam()
	{
		boolean ret = false;
		
		if (Flag.TRUE) {
			try {
				/*
				 * DELETE ANAL_PARAM data already exist.
				 */

				String format = "DELETE FROM ANAL_PARAM WHERE JOB_ID = %s ";
				
				String jobId = info.getJobId();
				String query = String.format(format, StrUtil.quote(jobId));
				if (!Flag.TRUE) System.out.println("QUERY:" + query);
					
				int cnt = stmt.executeUpdate(query);
				ret = true;
				if (!Flag.TRUE) Print.println("UPDATE OK! [cnt=%d]", cnt);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ret;
	}
	
	private boolean queryInsertParam()
	{
		boolean ret = false;
		
		if (Flag.TRUE) {
			try {
				/*
				 * INSERT ANAL_PARAM
				 */
				String format = "INSERT INTO ANAL_PARAM (JOB_ID, SEQ, PARAM_NM, PARAM_VAL, REG_DT ) "
						+ "VALUES ( %s, %d, %s, %s, SYSDATE )";
				
				String jobId = info.getJobId();
				int seq = 0;
				for (String[] items : info.getVectorParams()) {

					seq++;
					String query = String.format(format, StrUtil.quote(jobId), seq, StrUtil.quote(items[0]), StrUtil.quote(items[1]));
					if (!Flag.TRUE) System.out.println("QUERY:" + query);
					
					int cnt = stmt.executeUpdate(query);
					if (cnt != 0) {
						ret = true;
						if (!Flag.TRUE) Print.println("UPDATE OK! [cnt=%d]", cnt);
					} else {
						ret = false;
						if (!Flag.TRUE) Print.println("UPDATE FAIL! [cnt=%d]", cnt);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ret;
	}
	
	private boolean queryDeleteJobId()
	{
		boolean ret = false;
		
		if (Flag.TRUE) {
			try {
				/*
				 * DELETE ANAL_JOBID data already exist.
				 */
				String format = "DELETE FROM ANAL_JOBID WHERE JOB_ID = %s ";
				
				String jobId = info.getJobId();
				String query = String.format(format, StrUtil.quote(jobId));
				if (!Flag.TRUE) System.out.println("QUERY:" + query);
					
				int cnt = stmt.executeUpdate(query);
				ret = true;
				if (!Flag.TRUE) Print.println("UPDATE OK! [cnt=%d]", cnt);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ret;
	}
	
	private boolean queryInsertJobId()
	{
		boolean ret = false;
		
		if (Flag.TRUE) {
			try {
				/*
				 * INSERT ANAL_JOBID
				 */
				String format = "INSERT INTO ANAL_JOBID (JOB_ID, STATUS, REG_DT ) "
						+ "VALUES ( %s, 'READY', SYSDATE )";
				
				String jobId = info.getJobId();
				String query = String.format(format, StrUtil.quote(jobId));
				if (!Flag.TRUE) System.out.println("QUERY:" + query);
				
				int cnt = stmt.executeUpdate(query);
				if (cnt != 0) {
					ret = true;
					if (!Flag.TRUE) Print.println("UPDATE OK! [cnt=%d]", cnt);
				} else {
					ret = false;
					if (!Flag.TRUE) Print.println("UPDATE FAIL! [cnt=%d]", cnt);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ret;
	}
	
	public void execute()
	{
		if (Flag.TRUE) {
			try {
				if (!queryDeleteParam()) {
					if (Flag.TRUE) Print.println("FAIL : DELETE PARAM");
					conn.rollback();
				} else if (!queryInsertParam()) {
					if (Flag.TRUE) Print.println("FAIL : INSERT PARAM");
					conn.rollback();
				} else if (!queryDeleteJobId()) {
					if (Flag.TRUE) Print.println("FAIL : DELETE JOBID");
					conn.rollback();
				} else if (!queryInsertJobId()) {
					if (Flag.TRUE) Print.println("FAIL : INSERT JOBID");
					conn.rollback();
				} else {
					conn.commit();
					if (!Flag.TRUE) System.out.println("OK!");
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try { conn.close(); } catch (Exception e) {}
			}
		}
	}
}
