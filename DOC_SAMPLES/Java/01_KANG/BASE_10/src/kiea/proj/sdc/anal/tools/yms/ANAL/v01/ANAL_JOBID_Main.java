package kiea.proj.sdc.anal.tools.yms.ANAL.v01;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Connection;
import oracle.jdbc.OracleConnection;

public class ANAL_JOBID_Main
{
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			try {
				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();
				
				String query;
				query = String.format("SELECT * FROM ANAL_JOBID ORDER BY job_id desc");
					
				ResultSet resultSet = stmt.executeQuery(query);
				ResultSetMetaData metaData = resultSet.getMetaData();
					
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
					
					for (int i=0; i < 15 && resultSet.next(); i++) {
						
						StringBuffer sb = new StringBuffer();
						sb.append(String.format("(%6d)   ", i));
						
						for (int col=1; col <= metaData.getColumnCount(); col++) {
							sb.append("\t").append(resultSet.getString(col));
						}
						
						System.out.println(sb.toString());
					}

					System.out.println();
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test02_update_01()
	{
		OracleConnection conn = null;
		Statement stmt = null;

		if (Flag.TRUE) {
			try {
				conn = Connection.getOracleConnection();
				stmt = conn.createStatement();
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}

		if (Flag.TRUE) {
			try {
				String query = String.format("\n"
						+ " UPDATE ANAL_JOBID SET  STATUS = 'READY' WHERE  JOB_ID = 'AR010141117A3222' \n"
						+ ""
						);
					
				@SuppressWarnings("unused")
				int retNo = stmt.executeUpdate(query);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Flag.TRUE) {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (Flag.TRUE) test02_update_01();
	}
}
