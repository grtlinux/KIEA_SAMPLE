/* Copyright (c) 2008-2014, KangSeok
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the HSQL Development Group nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL HSQL DEVELOPMENT GROUP, HSQLDB.ORG,
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_WIP_PROC_RATIO.v01;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.io.AbstractOracleReader;
import kiea.proj.sdc.anal.util.StrUtil;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_WIP_PROC_RATIO_OracleReader.java
 *
 */
public class AIB_WIP_PROC_RATIO_OracleReader extends AbstractOracleReader
{
	private List<AIB_WIP_PROC_RATIO_ReadBean> list = new ArrayList<AIB_WIP_PROC_RATIO_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public AIB_WIP_PROC_RATIO_OracleReader()
	{
		if (Flag.TRUE) {
		}
	}

	/**
	 * 
	 * getQuery
	 *
	 * @return
	 */
	private String getQuery()
	{
		String query = null;
		
		query = String.format("" +
				"/* WIP_PROC_RATION */                                                                      \n" +
				"SELECT                                                                                     \n" +
				"    'EQP_ID' DIV_CODE,                                                                     \n" +
				"    IMPTSTEPGRPID,                                                                         \n" +
				"    IMPTEQPID,                                                                             \n" +
				"    PROCHOUR,                                                                              \n" +
				"    DEFECTCD ,                                                                             \n" +
				"    DECGRADECD,                                                                            \n" +
				"    DECQTY                                                                                 \n" +
				"FROM                                                                                       \n" +
				"    (                                                                                      \n" +
				"    SELECT /*+ ORDERED PARALLEL ( 2 ) */                                                   \n" +
				"        F.PROCHOUR,                                                                        \n" +
				"        F.INSPSTEPTYPE,                                                                    \n" +
				"        F.IMPTEQPID,                                                                       \n" +
				"        F.IMPTSTEPGRPID,                                                                   \n" +
				"        F.SITEID,                                                                          \n" +
				"        D.PRODGRPNAME,                                                                     \n" +
				"        F.DECGRADECD,                                                                      \n" +
				"        F.DEFECTCD,                                                                        \n" +
				"        F.DECQTY                                                                           \n" +
				"    FROM                                                                                   \n" +
				"        yms_dm.F_IMPTDEFECTEQP F,                                                          \n" +
				"        yms_dm.D_PRODUCT D                                                                 \n" +
				"    /* WHERE F.PROCHOUR >= &FROM_ETL_HOUR.*/                                               \n" +
				"    WHERE                                                                                  \n" +
				"        F.PROCHOUR >= TO_CHAR(TO_DATE(%s, 'YYYYMMDDHH24') - 7, 'YYYYMMDDHH24')             \n" +  // TO_ETL_HOUR
				"        AND F.PROCHOUR     <= %s   /* 2012070122 */                                        \n" +  // TO_ETL_HOUR
				"        AND F.SITEID       =  %s                                                           \n" +  // LINE
				"        AND F.INSPSTEPTYPE IN ( %s )                                                       \n" +  // SUB_AREA_CODE
				"        AND D.PRODGRPNAME  IN ( %s )                                                       \n" +  // USER_GROUP_CODE
				"        AND F.PRODTYPE     IN ( %s )                                                       \n" +  // PRODUCT_TYPE
				"        AND F.PRODID       =  D.PRODID                                                     \n" +
				"    )                                                                                      \n" +
				"/*WHERE DECGRADECD = 'RJ'*/                                                                \n" +
				"WHERE DECQTY > 0                                                                           \n" +
				"UNION ALL                                                                                  \n" +
				"SELECT                                                                                     \n" +
				"    'UNIT_ID' DIV_CODE,                                                                    \n" +
				"    IMPTSTEPGRPID,                                                                         \n" +
				"    IMPTEQPUNITID,                                                                         \n" +
				"    PROCHOUR,                                                                              \n" +
				"    DEFECTCD ,                                                                             \n" +
				"    DECGRADECD,                                                                            \n" +
				"    DECQTY                                                                                 \n" +
				"FROM                                                                                       \n" +
				"    (                                                                                      \n" +
				"    SELECT /*+ ORDERED PARALLEL ( 2 ) */                                                   \n" +
				"        F.PROCHOUR,                                                                        \n" +
				"        F.INSPSTEPTYPE,                                                                    \n" +
				"        F.IMPTEQPUNITID,                                                                   \n" +
				"        F.IMPTSTEPGRPID,                                                                   \n" +
				"        F.SITEID,                                                                          \n" +
				"        D.PRODGRPNAME,                                                                     \n" +
				"        F.DECGRADECD,                                                                      \n" +
				"        F.DEFECTCD,                                                                        \n" +
				"        F.DECQTY                                                                           \n" +
				"    FROM                                                                                   \n" +
				"        yms_dm.F_IMPTDEFECTUNIT F,                                                         \n" +
				"        yms_dm.D_PRODUCT D                                                                 \n" +
				"    /* WHERE F.PROCHOUR >= &FROM_ETL_HOUR.*/                                               \n" +
				"    WHERE                                                                                  \n" +
				"        F.PROCHOUR >= TO_CHAR(TO_DATE(%s, 'YYYYMMDDHH24') - 7, 'YYYYMMDDHH24')             \n" +  // TO_ETL_HOUR
				"        AND F.PROCHOUR     <= %s   /* 2012070122 */                                        \n" +  // TO_ETL_HOUR
				"        AND F.SITEID       =  %s                                                           \n" +  // LINE
				"        AND F.INSPSTEPTYPE IN ( %s )                                                       \n" +  // SUB_AREA_CODE
				"        AND D.PRODGRPNAME  IN ( %s )                                                       \n" +  // USER_GROUP_CODE
				"        AND F.PRODTYPE     IN ( %s )                                                       \n" +  // PRODUCT_TYPE
				"        AND F.PRODID       =  D.PRODID                                                     \n" +
				"    )                                                                                      \n" +
				"/*WHERE DECGRADECD = 'RJ'*/                                                                \n" +
				"WHERE DECQTY > 0                                                                           \n" +
				"UNION ALL                                                                                  \n" +
				"SELECT                                                                                     \n" +
				"    'SLOT_ID' DIV_CODE,                                                                    \n" +
				"    IMPTSTEPGRPID,                                                                         \n" +
				"    IMPTEQPSLOTID,                                                                         \n" +
				"    PROCHOUR,                                                                              \n" +
				"    DEFECTCD ,                                                                             \n" +
				"    DECGRADECD,                                                                            \n" +
				"    DECQTY                                                                                 \n" +
				"FROM                                                                                       \n" +
				"    (                                                                                      \n" +
				"    SELECT /*+ ORDERED PARALLEL ( 2 ) */                                                   \n" +
				"        F.PROCHOUR,                                                                        \n" +
				"        F.INSPSTEPTYPE,                                                                    \n" +
				"        F.IMPTEQPSLOTID,                                                                   \n" +
				"        F.IMPTSTEPGRPID,                                                                   \n" +
				"        F.SITEID,                                                                          \n" +
				"        D.PRODGRPNAME,                                                                     \n" +
				"        F.DECGRADECD,                                                                      \n" +
				"        F.DEFECTCD,                                                                        \n" +
				"        F.DECQTY                                                                           \n" +
				"    FROM                                                                                   \n" +
				"        yms_dm.F_IMPTDEFECTSLOT F,                                                         \n" +
				"        yms_dm.D_PRODUCT D                                                                 \n" +
				"    /* WHERE F.PROCHOUR >= &FROM_ETL_HOUR.*/                                               \n" +
				"    WHERE                                                                                  \n" +
				"        F.PROCHOUR >= TO_CHAR(TO_DATE(%s, 'YYYYMMDDHH24') - 7, 'YYYYMMDDHH24')             \n" +  // TO_ETL_HOUR
				"        AND F.PROCHOUR     <= %s   /* 2012070122 */                                        \n" +  // TO_ETL_HOUR
				"        AND F.SITEID       =  %s                                                           \n" +  // LINE
				"        AND F.INSPSTEPTYPE IN ( %s )                                                       \n" +  // SUB_AREA_CODE
				"        AND D.PRODGRPNAME  IN ( %s )                                                       \n" +  // USER_GROUP_CODE
				"        AND F.PRODTYPE     IN ( %s )                                                       \n" +  // PRODUCT_TYPE
				"        AND F.PRODID       =  D.PRODID                                                     \n" +
				"    )                                                                                      \n" +
				"/*WHERE DECGRADECD = 'RJ'*/                                                                \n" +
				"WHERE DECQTY > 0                                                                           \n" +
				""
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime().substring(0, 10))
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime().substring(0, 10))
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrProductType())

				, StrUtil.quote(Parameter.getInstance().getStrToDateTime().substring(0, 10))
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime().substring(0, 10))
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrProductType())

				, StrUtil.quote(Parameter.getInstance().getStrToDateTime().substring(0, 10))
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime().substring(0, 10))
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrProductType())
				);

		return query;
	}

	/**
	 * getList
	 */
	@Override
	public List<AIB_WIP_PROC_RATIO_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<AIB_WIP_PROC_RATIO_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<AIB_WIP_PROC_RATIO_ReadBean>();
				return list;
			}
			
			try {
				String query = null;
				
				query = getQuery();
				
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				
				OracleConnection conn = Connection.getOracleConnection();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i < 1000000 && rs.next(); i++) {
						if (!Flag.TRUE) {
							System.out.println(String.format("%3d) --------------------------------------", i));
							System.out.println(String.format("    DIV_CODE      = [%s]", rs.getString("DIV_CODE"     )));
							System.out.println(String.format("    IMPTSTEPGRPID = [%s]", rs.getString("IMPTSTEPGRPID")));
							System.out.println(String.format("    IMPTEQPID     = [%s]", rs.getString("IMPTEQPID"    )));
							System.out.println(String.format("    PROCHOUR      = [%s]", rs.getString("PROCHOUR"     )));
							System.out.println(String.format("    DEFECTCD      = [%s]", rs.getString("DEFECTCD"     )));
							System.out.println(String.format("    DECGRADECD    = [%s]", rs.getString("DECGRADECD"   )));
							System.out.println(String.format("    DECQTY        = [%s]", rs.getString("DECQTY"       )));
						}
						
						if (Flag.TRUE) {
							AIB_WIP_PROC_RATIO_ReadBean bean = new AIB_WIP_PROC_RATIO_ReadBean();

							bean.setDivCode      (rs.getString("DIV_CODE"     ));
							bean.setImptStepGrpId(rs.getString("IMPTSTEPGRPID"));
							bean.setImptEqpId    (rs.getString("IMPTEQPID"    ));
							bean.setProcHour     (rs.getString("PROCHOUR"     ));
							bean.setDefectCd     (rs.getString("DEFECTCD"     ));
							bean.setDecGradeCd   (rs.getString("DECGRADECD"   ));
							bean.setDecQty       (rs.getString("DECQTY"       ));
							
							list.add(bean);
						}
					}
					
					if (!Flag.TRUE) {
						System.out.println();
						ResultSetMetaData md = rs.getMetaData();
						for (int i=1; i <= md.getColumnCount(); i++) {
							System.out.println(String.format("%d) [%s] [%s] [%s] [%s]", i, md.getColumnName(i), md.getColumnLabel(i), md.getColumnClassName(i), md.getCatalogName(i)));
						}
					}
				}
				
				conn.close();
				if (!Flag.TRUE) System.out.println("OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			Parameter.getInstance(5);
			AIB_WIP_PROC_RATIO_OracleReader reader = new AIB_WIP_PROC_RATIO_OracleReader();
			
			List<AIB_WIP_PROC_RATIO_ReadBean> lst = reader.getList(true);
			for (AIB_WIP_PROC_RATIO_ReadBean bean : lst) {
				System.out.println(bean);
			}
			
			System.out.println("the read ok !!! (" + lst.size() + ")");
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
