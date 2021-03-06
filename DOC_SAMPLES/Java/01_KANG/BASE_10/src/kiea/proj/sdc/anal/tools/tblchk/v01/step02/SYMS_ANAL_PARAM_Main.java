package kiea.proj.sdc.anal.tools.tblchk.v01.step02;

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
	
	public void select_00()
	{
		if (Flag.TRUE) {
			try {
				
				String query = String.format("\n"
						+ "WITH TBL AS (                            \n"
						+ "    SELECT                               \n"
						+ "        DISTINCT job_id                  \n"
						+ "    FROM                                 \n"
						+ "        syms_anal_param                  \n"
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
					Print.println("---------- SYMS_ANAL_PARAM ----------- 00");
					
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
						+ "    syms_anal_param                   \n"
						+ "WHERE 1=1                             \n"
						//+ "    AND ROWNUM <= 100                 \n"
						//+ "    AND job_id LIKE 'AR010%s%%'       \n"
						//+ "    AND job_id LIKE 'UR010%s%%'       \n"
						//+ "    AND job_id LIKE 'AR010%%'         \n"
						//+ "    AND job_id LIKE 'UR010%%'         \n"
						//+ "    AND job_id LIKE 'AR%%'         \n"
						//+ "    AND job_id LIKE 'UR%%'         \n"
						+ "ORDER BY                              \n"
						+ "    job_id DESC                       \n"
						+ "    , anal_job_dip_name               \n"
						+ ""
						, DateTime.getDate(6)
						);
					
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
					Print.println("---------- SYMS_ANAL_PARAM ----------- 01");
					
					for (int i=0; i < 1000 && resultSet.next(); i++) {
						String jobId = resultSet.getString("JOB_ID");
						String dipName = resultSet.getString("ANAL_JOB_DIP_NAME");
						String dipValue = resultSet.getString("ANAL_JOB_DIP_VALUE");
						
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
						+ " DELETE FROM syms_anal_param WHERE 1=1 \n"
						//+ " DELETE FROM syms_anal_param WHERE job_id LIKE 'UR%%' \n"
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
	///////////////////////////////////////////////////////////////////////////

	public boolean listJobId(String svcCode)
	{
		boolean ret = true;
		
		if (Flag.TRUE) {
			if (svcCode == null) {
				svcCode = "___";
			}
		}
		
		if (Flag.TRUE) {
			try {
				
				String query = String.format("\n"
						+ "SELECT                                \n"
						+ "    DISTINCT job_id                   \n"
						+ "FROM                                  \n"
						+ "    syms_anal_param                   \n"
						+ "WHERE 1=1                             \n"
						//+ "    AND ROWNUM <= 100                 \n"
						//+ "    AND job_id LIKE 'AR010%s%%'       \n"
						//+ "    AND job_id LIKE 'UR010%s%%'       \n"
						//+ "    AND job_id LIKE 'AR010%%'         \n"
						//+ "    AND job_id LIKE 'UR010%%'         \n"
						//+ "    AND job_id LIKE 'AR%%'            \n"
						//+ "    AND job_id LIKE 'UR%%'            \n"
						+ "    AND job_id LIKE '__%s%s%%'        \n"
						+ "ORDER BY                              \n"
						+ "    job_id DESC                       \n"
						+ "\n"
						, svcCode
						, DateTime.getDate(6)
						);
				if (Flag.TRUE) System.out.println("<<< QUERY >>>\n" + query);
				
				ResultSet resultSet = stmt.executeQuery(query);

				if (Flag.TRUE) {
					/*
					 * row data
					 */
					Print.println("---------- SYMS_ANAL_PARAM ----------- 01");
					
					for (int i=0; i < 10000 && resultSet.next(); i++) {
						String jobId = resultSet.getString("JOB_ID");

						if (Flag.TRUE) Print.println("%4d) [%16s]", i, jobId);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ret;
	}
	
	public boolean select(String svcCode)
	{
		boolean ret = true;
		
		if (Flag.TRUE) {
			if (svcCode == null) {
				svcCode = "___";
			}
		}
		
		if (Flag.TRUE) {
			try {
				
				String query = String.format("\n"
						+ "SELECT                                \n"
						+ "    *                                 \n"
						+ "FROM                                  \n"
						+ "    syms_anal_param                   \n"
						+ "WHERE 1=1                             \n"
						//+ "    AND ROWNUM <= 100                 \n"
						//+ "    AND job_id LIKE 'AR010%s%%'       \n"
						//+ "    AND job_id LIKE 'UR010%s%%'       \n"
						//+ "    AND job_id LIKE 'AR010%%'         \n"
						//+ "    AND job_id LIKE 'UR010%%'         \n"
						//+ "    AND job_id LIKE 'AR%%'            \n"
						//+ "    AND job_id LIKE 'UR%%'            \n"
						+ "    AND job_id LIKE '__%s%s%%'        \n"
						+ "ORDER BY                              \n"
						+ "    job_id DESC                       \n"
						+ "    , anal_job_dip_name               \n"
						+ "\n"
						, svcCode
						, DateTime.getDate(6)
						);
				if (Flag.TRUE) System.out.println("<<< QUERY >>>\n" + query);
				
				ResultSet resultSet = stmt.executeQuery(query);
				ResultSetMetaData metaData = resultSet.getMetaData();

				if (!Flag.TRUE) {
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
					Print.println("---------- SYMS_ANAL_PARAM ----------- 01");
					
					String oldJobId = ""; 
					for (int i=0; i < 10000 && resultSet.next(); i++) {
						String jobId = resultSet.getString("JOB_ID");
						String dipName = resultSet.getString("ANAL_JOB_DIP_NAME");
						String dipValue = resultSet.getString("ANAL_JOB_DIP_VALUE");
						
						if (!oldJobId.equals(jobId)) {
							oldJobId = jobId;
						} else {
							jobId = "";
						}
						
						if (Flag.TRUE) Print.println("%4d) [%16s] [%s] [%s]", i, jobId, dipName, dipValue);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return ret;
	}
	
	public boolean delete(String svcCode)
	{
		boolean ret = true;
		
		if (Flag.TRUE) {
			try {
				
				String query;
				
				if (svcCode != null) {
					query = String.format("\n"
							+ "DELETE                                \n"
							+ "FROM                                  \n"
							+ "    syms_anal_param                   \n"
							+ "WHERE 1=1                             \n"
							+ "    AND job_id LIKE '__%s%s%%'        \n"
							+ "\n"
							, svcCode
							, DateTime.getDate(6)
							);
				} else {
					query = String.format("\n"
							+ "DELETE                                \n"
							+ "FROM                                  \n"
							+ "    syms_anal_param                   \n"
							+ "WHERE 1=1                             \n"
							+ "\n"
							);
				}

				if (Flag.TRUE) System.out.println("<<< QUERY >>>\n" + query);
				
				int retNo = stmt.executeUpdate(query);
				System.out.println("DELETE ret : " + retNo);

			} catch (Exception e) {
				e.printStackTrace();
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
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	private static void test01_select_jobid()
	{
		if (Flag.TRUE) {
			SYMS_ANAL_PARAM_Main instance = SYMS_ANAL_PARAM_Main.getInstance();
			if (Flag.TRUE) instance.select_00();
			instance.close();
		}
	}

	private static void test01_select()
	{
		if (Flag.TRUE) {
			SYMS_ANAL_PARAM_Main instance = SYMS_ANAL_PARAM_Main.getInstance();
			if (Flag.TRUE) instance.select_01();
			instance.close();
		}
	}
	
	private static void test01_delete()
	{
		if (Flag.TRUE) {
			SYMS_ANAL_PARAM_Main instance = SYMS_ANAL_PARAM_Main.getInstance();
			if (Flag.TRUE) instance.select_01();
			if (Flag.TRUE) instance.delete_01();
			if (Flag.TRUE) instance.select_01();
			instance.close();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test02_listJobid(String svcCode)
	{
		if (Flag.TRUE) {
			SYMS_ANAL_PARAM_Main instance = SYMS_ANAL_PARAM_Main.getInstance();
			if (Flag.TRUE) instance.listJobId(svcCode);
			instance.close();
		}
	}
	
	private static void test02_select(String svcCode)
	{
		if (Flag.TRUE) {
			SYMS_ANAL_PARAM_Main instance = SYMS_ANAL_PARAM_Main.getInstance();
			if (Flag.TRUE) instance.select(svcCode);
			instance.close();
		}
	}
	
	private static void test02_delete(String svcCode)
	{
		if (Flag.TRUE) {
			SYMS_ANAL_PARAM_Main instance = SYMS_ANAL_PARAM_Main.getInstance();
			if (Flag.TRUE) instance.delete(svcCode);
			instance.close();
		}
	}

	private static void printUsage()
	{
		if (Flag.TRUE) {
			System.out.println(" * args sample....(ignorecase)");
			System.out.println("    JOBID [ALL|000]  : list all jobids");
			System.out.println("    JOBID [LYA|010]  : list lya jobids");
			System.out.println("    JOBID [MURA|020] : list mura jobids");
			System.out.println("    LIST [ALL|000]   : list all params");
			System.out.println("    LIST [LYA|010]   : list lya params");
			System.out.println("    LIST [MURA|020]  : list mura params");
			System.out.println("    DEL  [ALL|000]   : delete all params");
			System.out.println("    DEL  [LYA|010]   : delete lya params");
			System.out.println("    DEL  [MURA|020]  : delete mura params");
			System.exit(0);
		}
	}
	
	private static void test02(String[] args)
	{
		if (Flag.TRUE) {
			if (args.length == 0) {
				args = new String[] { "JOBID", "ALL", };  // default
			}
		}
		
		if (Flag.TRUE) {
			//args = new String[] { "JOBID", "ALL" , };  // list all jobids
			//args = new String[] { "JOBID", "000" , };  // list all jobids
			//args = new String[] { "JOBID", "LYA" , };  // list lya jobids
			//args = new String[] { "JOBID", "010" , };  // list lya jobids
			//args = new String[] { "JOBID", "MURA", };  // list mura jobids
			//args = new String[] { "JOBID", "020" , };  // list mura jobids

			//args = new String[] { "LIST", "ALL" , };  // list all params
			//args = new String[] { "LIST", "000" , };  // list all params
			//args = new String[] { "LIST", "LYA" , };  // list lya params
			//args = new String[] { "LIST", "010" , };  // list lya params
			//args = new String[] { "LIST", "MURA", };  // list mura params
			//args = new String[] { "LIST", "020" , };  // list mura params
			
			//args = new String[] { "DEL" , "ALL" , };  // delete all params
			//args = new String[] { "DEL" , "000" , };  // delete all params
			//args = new String[] { "DEL" , "LYA" , };  // delete lya params
			//args = new String[] { "DEL" , "010" , };  // delete lya params
			//args = new String[] { "DEL" , "MURA", };  // delete mura params
			//args = new String[] { "DEL" , "020" , };  // delete mura params
		}
		
		if (Flag.TRUE) {
			
			if (args.length != 2) {
				
				System.out.println("> args.length=" + args.length);
				for (int i=0; i < args.length; i++) {
					Print.println("\t(%d) [%s]", i, args[i]);
				}
				
				printUsage();
			}
		}
		
		if (Flag.TRUE) {
			if ("JOBID".equalsIgnoreCase(args[0])) {
				if ("ALL".equalsIgnoreCase(args[1]) || "000".equalsIgnoreCase(args[1])) {
					test02_listJobid(null);
				} else if ("LYA".equalsIgnoreCase(args[1]) || "010".equalsIgnoreCase(args[1])) {
					test02_listJobid("010");
				} else if ("MURA".equalsIgnoreCase(args[1]) || "020".equalsIgnoreCase(args[1])) {
					test02_listJobid("020");
				} else {
					printUsage();
				}
			} else if ("LIST".equalsIgnoreCase(args[0])) {
				if ("ALL".equalsIgnoreCase(args[1]) || "000".equalsIgnoreCase(args[1])) {
					test02_select(null);
				} else if ("LYA".equalsIgnoreCase(args[1]) || "010".equalsIgnoreCase(args[1])) {
					test02_select("010");
				} else if ("MURA".equalsIgnoreCase(args[1]) || "020".equalsIgnoreCase(args[1])) {
					test02_select("020");
				} else {
					printUsage();
				}
			} else if ("DEL".equalsIgnoreCase(args[0])) {
				if ("ALL".equalsIgnoreCase(args[1]) || "000".equalsIgnoreCase(args[1])) {
					test02_delete(null);
				} else if ("LYA".equalsIgnoreCase(args[1]) || "010".equalsIgnoreCase(args[1])) {
					test02_delete("010");
				} else if ("MURA".equalsIgnoreCase(args[1]) || "020".equalsIgnoreCase(args[1])) {
					test02_delete("020");
				} else {
					printUsage();
				}
			} else {
				printUsage();
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01_select_jobid();
		if (!Flag.TRUE) test01_select();
		if (!Flag.TRUE) test01_delete();
		
		if (Flag.TRUE) test02(args);
	}
}
