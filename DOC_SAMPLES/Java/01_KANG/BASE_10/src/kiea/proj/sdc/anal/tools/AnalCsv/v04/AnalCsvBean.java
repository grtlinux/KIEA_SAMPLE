package kiea.proj.sdc.anal.tools.AnalCsv.v04;

import java.sql.Statement;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.common.Connection04;
import kiea.proj.sdc.anal.util.StrUtil;
import oracle.jdbc.OracleConnection;

public class AnalCsvBean
{
	private AnalCsvType type = null;  // INPUT, UPDATE, DELETE
	private String table = null;
	private String[][] fields = null;
	private String where = null;
	
	public String getQuery()
	{
		String ret = toString();
		if (ret == null)
			return ret;
		
		return ret.replace("\t", " ").replace("\n", " ").trim();
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		
		if ("INSERT".equalsIgnoreCase(this.type.getName())) {
			if (fields.length <= 0)
				return null;
			
			/*
			 * INSERT INTO tbl (field) VALUES ( 123 )
			 * INSERT INTO tbl (FLD1, FLD2, FLD3) VALUES ( 'Hello', '123', 123 )
			 */
			sb.append("INSERT INTO " + this.table).append(" \n");
			
			sb.append("(").append(" \n");
			
			for (int idx=0; idx < fields.length; idx++) {
				if (idx == 0) {
					sb.append("\t" + fields[idx][0]).append(" \n");
				} else {
					sb.append("\t, " + fields[idx][0]).append(" \n");
				}
			}
			
			sb.append(") VALUES (").append(" \n");
			
			for (int idx=0; idx < fields.length; idx++) {
				if (idx == 0) {
					sb.append("\t" + fields[idx][1]).append(" \n");
				} else {
					sb.append("\t, " + fields[idx][1]).append(" \n");
				}
			}
			
			sb.append(")").append(" \n");
			sb.append(" \n");
			
		} else if ("UPDATE".equalsIgnoreCase(this.type.getName())) {
			if (fields.length <= 0)
				return null;

			if (Flag.TRUE) {
				/*
				 * UPDATE tbl SET  NAME = 'KANG', FLD2 = 123 WHERE  id = 3
				 */
				sb.append("UPDATE " + this.table + " SET").append(" \n");
				
				for (int idx=0; idx < fields.length; idx++) {
					if (idx == 0) {
						sb.append(String.format("\t%s = %s\n", fields[idx][0], fields[idx][1]));
					} else {
						sb.append(String.format("\t, %s = %s\n", fields[idx][0], fields[idx][1]));
					}
				}
				
				if (this.where != null) {
					sb.append("WHERE " + this.where).append(" \n");
				}
				
				sb.append(" \n");
			} else {
				/*
				 * UPDATE tbl SET (FLD1, FLD2, FLD3) = ('Hello', '123', 123) WHERE FLD1 = '111'
				 */
				sb.append("UPDATE " + this.table + " SET").append(" \n");
				
				sb.append("(").append(" \n");
				
				for (int idx=0; idx < fields.length; idx++) {
					if (idx == 0) {
						sb.append("\t" + fields[idx][0]).append(" \n");
					} else {
						sb.append("\t, " + fields[idx][0]).append(" \n");
					}
				}
				
				sb.append(") = (").append(" \n");
				
				for (int idx=0; idx < fields.length; idx++) {
					if (idx == 0) {
						sb.append("\t" + fields[idx][1]).append(" \n");
					} else {
						sb.append("\t, " + fields[idx][1]).append(" \n");
					}
				}
				
				sb.append(")").append(" \n");
				
				if (this.where != null) {
					sb.append("WHERE " + this.where).append(" \n");
				}
				
				sb.append(" \n");
			}

		} else if ("DELETE".equalsIgnoreCase(this.type.getName())) {
			/*
			 * DELETE FROM tbl WHERE id = 9
			 */
			sb.append("DELETE FROM " + this.table).append(" \n");
			
			if (this.where != null) {
				sb.append("WHERE " + this.where).append(" \n");
			}
			
			sb.append(" \n");

		} else if ("SELECT".equalsIgnoreCase(this.type.getName())) {
				if (fields.length <= 0)
					return null;
				
				/*
				 * SELECT FLD1, FLD2, FLD3 FROM tbl WHERE FLD1= '12345'
				 */
				sb.append("SELECT ").append(" \n");
				
				for (int idx=0; idx < fields.length; idx++) {
					if (idx == 0) {
						sb.append("\t" + fields[idx][0]).append(" \n");
					} else {
						sb.append("\t, " + fields[idx][0]).append(" \n");
					}
				}
				
				sb.append("FROM " + this.table).append(" \n");

				if (this.where != null) {
					sb.append("WHERE " + this.where).append(" \n");
				}
				sb.append(" \n");
				
		} else {
			return null;
		}
		
		return sb.toString();
	}
	
	public boolean sendToOracle()
	{
		boolean ret = false;
		
		if (Flag.TRUE) {
			OracleConnection conn = null;
			Statement stmt = null;
			int cntUpdate = 0;

			try {
				conn = Connection04.getInstance().getConn();
				stmt = conn.createStatement();
				
				cntUpdate = stmt.executeUpdate(getQuery());
				if (cntUpdate > 0)
					ret = true;
				
				Connection04.getInstance().close();

			} catch (Exception e) {
				e.printStackTrace();
				// System.exit(-1);
			}
		}
		
		return ret;
	}
	
	///////////////////////////////////////////////////////////////////////////

	public AnalCsvType getType()
	{
		return type;
	}
	public void setType(AnalCsvType type)
	{
		this.type = type;
	}
	public String getTable()
	{
		return table;
	}
	public void setTable(String table)
	{
		this.table = table;
	}
	public String[][] getFields()
	{
		return fields;
	}
	public void setFields(String[][] fields)
	{
		this.fields = fields;
	}
	public String getWhere()
	{
		return where;
	}
	public void setWhere(String strWhere)
	{
		this.where = strWhere;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.INSERT);
			bean.setTable("ABC");
			bean.setFields(new String[][] {
					{ "FLD1", StrUtil.quote("Hello") },
					{ "FLD2", StrUtil.quote("강석") },
					{ "FLD3", "1234" },
			});
			bean.setWhere("FLD1 = '12345'");
			
			Print.println("[%s][%s]", bean, bean.getQuery());
		}
		
		if (Flag.TRUE) {
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ABC");
			bean.setFields(new String[][] {
					{ "FLD1", StrUtil.quote("Hello") },
					{ "FLD2", StrUtil.quote("강석") },
					{ "FLD3", "1234" },
			});
			bean.setWhere("FLD1 = '12345'");
			
			Print.println("[%s][%s]", bean, bean.getQuery());
		}

		if (Flag.TRUE) {
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ABC");
			bean.setFields(new String[][] {
					{ "FLD1", StrUtil.quote("Hello") },
					{ "FLD2", StrUtil.quote("강석") },
					{ "FLD3", "1234" },
			});
			bean.setWhere("FLD1 = '12345'");
			
			Print.println("[%s][%s]", bean, bean.getQuery());
		}
		
		if (Flag.TRUE) {
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.SELECT);
			bean.setTable("ABC");
			bean.setFields(new String[][] {
					{ "FLD1", },
					{ "FLD2", },
					{ "FLD3", },
			});
			bean.setWhere("FLD1 = '12345'");
			
			Print.println("[%s][%s]", bean, bean.getQuery());
		}
	}
	
	private static void test02()
	{
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

		if (Flag.TRUE) {
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.INSERT);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "JOB_ID     ", StrUtil.quote("AR010YYMMDDA9999") },
					{ "CMD_CODE   ", StrUtil.quote("CMD_CODE") },
					{ "PROG_NM    ", StrUtil.quote("PROG_NAME") },
					{ "CSV_NM     ", StrUtil.quote("CSV_NAME") },
					{ "MAIN_CLASS ", StrUtil.quote("Main Class") },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			
			Print.println("[%s][%s]", bean, bean.getQuery());
			
			if (bean.sendToOracle() == true) {
				System.out.println("INSERT Query OK!!!");
			} else {
				System.out.println("INSERT Query FAIL");
			}
		}
		
		try { Thread.sleep(5000); } catch (Exception e) {}
		
		if (!Flag.TRUE) {
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "123456789" },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "1234500" },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere("JOB_ID = 'AR010YYMMDDA9999'");
			
			Print.println("[%s][%s]", bean, bean.getQuery());
			
			if (bean.sendToOracle() == true) {
				System.out.println("UPDATE Query OK!!!");
			} else {
				System.out.println("UPDATE Query FAIL");
			}
		}
		
		if (Flag.TRUE) {
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere("JOB_ID = 'AR010YYMMDDA9999'");
			
			if (bean.sendToOracle() == true) {
				System.out.println("DELETE Query OK!!!");
			} else {
				System.out.println("DELETE Query FAIL");
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (Flag.TRUE) test02();
	}
}
