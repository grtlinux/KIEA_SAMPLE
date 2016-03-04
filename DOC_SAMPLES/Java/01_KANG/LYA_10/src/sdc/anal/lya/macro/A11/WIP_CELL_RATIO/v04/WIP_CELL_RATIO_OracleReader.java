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

package sdc.anal.lya.macro.A11.WIP_CELL_RATIO.v04;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.common.Connection04;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.io.AbstractOracleReader;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_WIP_CELL_RATIO_OracleReader.java
 *
 */
public class WIP_CELL_RATIO_OracleReader extends AbstractOracleReader
{
	private List<WIP_CELL_RATIO_ReadBean> list = new ArrayList<WIP_CELL_RATIO_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public WIP_CELL_RATIO_OracleReader()
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
				"/* WIP_CELL_RATIO */                                               \n" +
				"SELECT                                                             \n" +
				"    'EQP_ID' div_code,                                             \n" +
				"    imptstepgrpid,                                                 \n" +
				"    impteqpid,                                                     \n" +
				"    prochour trck_out_hour,                                        \n" +
				"    defectcd,                                                      \n" +
				"    decgradecd,                                                    \n" +
				"    decqty                                /* TOTCNT*/              \n" +
				"FROM                                                               \n" +
				"    (                                                              \n" +
				"    SELECT /*+ ORDERED PARALLEL(2) */                              \n" +
				"        f.insphour,                                                \n" +
				"        f.prochour,                                                \n" +
				"        f.inspsteptype,                                            \n" +
				"        f.impteqpid,                                               \n" +
				"        f.imptstepgrpid,                                           \n" +
				"        f.siteid,                                                  \n" +
				"        d.prodgrpname,                                             \n" +
				"        f.decgradecd,                                              \n" +
				"        f.defectcd,                                                \n" +
				"        f.decqty                                                   \n" +
				"        /* SUM(F.DECQTY) OVER (PARTITION BY F.INSPHOUR, */         \n" +
				"        /* F.PROCHOUR,F.IMPTEQPID, F.IMPTSTEPGRPID,     */         \n" +
				"        /* F.INSPSTEPTYPE,F.SITEID,D.PRODGRPNAME) TOTCNT*/         \n" +
				"    FROM                                                           \n" +
				"        yms_dm.f_inspimptdefecteqp f,                              \n" +
				"        yms_dm.d_product d                                         \n" +
				"    WHERE 1 = 1                                                    \n" +
				"        AND f.insphour      >= %s                                  \n" +  // FROM_ETL_HOUR
				"        AND f.insphour      <= %s                                  \n" +  // TO_ETL_HOUR
				"        AND f.siteid        =  %s /*7B => L7BFAB 으로 변경 */      \n" +  // LINE
				"        AND f.inspsteptype  IN (%s)                                \n" +  // SUB_AREA_CODE
				"        AND d.prodgrpname   IN (%s)                                \n" +  // USER_GROUP_CODE
				"        AND f.prodtype      IN (%s)                                \n" +  // PRODUCT_TYPE
				"        AND f.prodid        =  d.prodid                            \n" +
				"    )                                                              \n" +
				"/*WHERE DECGRADECD = 'RJ'*/                                        \n" +
				"WHERE  decqty > 0                                                  \n" +
				"UNION ALL                                                          \n" +
				"SELECT                                                             \n" +
				"    'UNIT_ID' div_code,                                            \n" +
				"    imptstepgrpid,                                                 \n" +
				"    impteqpunitid,                                                 \n" +
				"    prochour trck_out_hour,                                        \n" +
				"    defectcd,                                                      \n" +
				"    decgradecd,                                                    \n" +
				"    decqty        /* TOTCNT*/                                      \n" +
				"FROM                                                               \n" +
				"    (                                                              \n" +
				"    SELECT /*+ ORDERED PARALLEL(2) */                              \n" +
				"        f.insphour,                                                \n" +
				"        f.prochour,                                                \n" +
				"        f.inspsteptype,                                            \n" +
				"        f.impteqpunitid,                                           \n" +
				"        f.imptstepgrpid,                                           \n" +
				"        f.siteid,                                                  \n" +
				"        d.prodgrpname,                                             \n" +
				"        f.decgradecd,                                              \n" +
				"        f.defectcd,                                                \n" +
				"        f.decqty                                                   \n" +
				"        /* SUM(F.DECQTY) OVER (PARTITION BY F.INSPHOUR, */         \n" +
				"        /* F.PROCHOUR,F.IMPTEQPUNITID, F.IMPTSTEPGRPID, */         \n" +
				"        /* F.INSPSTEPTYPE,F.SITEID,D.PRODGRPNAME) TOTCNT*/         \n" +
				"    FROM                                                           \n" +
				"        yms_dm.f_inspimptdefectunit f,                             \n" +
				"        yms_dm.d_product d                                         \n" +
				"    WHERE 1 = 1                                                    \n" +
				"        AND f.insphour      >= %s                                  \n" +  // FROM_ETL_HOUR
				"        AND f.insphour      <= %s                                  \n" +  // TO_ETL_HOUR
				"        AND f.siteid        =  %s /*7B => L7BFAB 으로 변경 */      \n" +  // LINE
				"        AND f.inspsteptype  IN (%s)                                \n" +  // SUB_AREA_CODE
				"        AND d.prodgrpname   IN (%s)                                \n" +  // USER_GROUP_CODE
				"        AND f.prodtype      IN (%s)                                \n" +  // PRODUCT_TYPE
				"        AND f.prodid        =  d.prodid                            \n" +
				"    )                                                              \n" +
				"/*WHERE DECGRADECD = 'RJ'*/                                        \n" +
				"WHERE  decqty > 0                                                  \n" +
				"UNION ALL                                                          \n" +
				"SELECT                                                             \n" +
				"    'SLOT_ID' div_code,                                            \n" +
				"    imptstepgrpid,                                                 \n" +
				"    impteqpslotid,                                                 \n" +
				"    prochour trck_out_hour,                                        \n" +
				"    defectcd,                                                      \n" +
				"    decgradecd,                                                    \n" +
				"    decqty        /* TOTCNT*/                                      \n" +
				"FROM                                                               \n" +
				"    (                                                              \n" +
				"    SELECT /*+ ORDERED PARALLEL(2) */                              \n" +
				"        f.insphour,                                                \n" +
				"        f.prochour,                                                \n" +
				"        f.inspsteptype,                                            \n" +
				"        f.impteqpslotid,                                           \n" +
				"        f.imptstepgrpid,                                           \n" +
				"        f.siteid,                                                  \n" +
				"        d.prodgrpname,                                             \n" +
				"        f.decgradecd,                                              \n" +
				"        f.defectcd,                                                \n" +
				"        f.decqty                                                   \n" +
				"        /* SUM(F.DECQTY) OVER (PARTITION BY F.INSPHOUR, */         \n" +
				"        /* F.PROCHOUR,F.IMPTEQPSLOTID, F.IMPTSTEPGRPID, */         \n" +
				"        /* F.INSPSTEPTYPE,F.SITEID,D.PRODGRPNAME) TOTCNT*/         \n" +
				"    FROM                                                           \n" +
				"        yms_dm.f_inspimptdefectslot f,                             \n" +
				"        yms_dm.d_product d                                         \n" +
				"    WHERE 1 = 1                                                    \n" +
				"        AND f.insphour      >= %s                                  \n" +  // FROM_ETL_HOUR
				"        AND f.insphour      <= %s                                  \n" +  // TO_ETL_HOUR
				"        AND f.siteid        =  %s /*7B => L7BFAB 으로 변경 */      \n" +  // LINE
				"        AND f.inspsteptype  IN (%s)                                \n" +  // SUB_AREA_CODE
				"        AND d.prodgrpname   IN (%s)                                \n" +  // USER_GROUP_CODE
				"        AND f.prodtype      IN (%s)                                \n" +  // PRODUCT_TYPE
				"        AND f.prodid        =  d.prodid                            \n" +
				"    )                                                              \n" +
				"/*WHERE DECGRADECD = 'RJ'*/                                        \n" +
				"WHERE  decqty > 0                                                  \n" +
				""
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrProductType())

				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrProductType())

				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
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
	public List<WIP_CELL_RATIO_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<WIP_CELL_RATIO_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<WIP_CELL_RATIO_ReadBean>();
				return list;
			}
			
			try {
				String query = null;
				
				query = getQuery();
				
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				
				OracleConnection conn = Connection04.getInstance().getConn();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i < 1000000 && rs.next(); i++) {
						if (!Flag.TRUE) {
							System.out.println(String.format("%3d) --------------------------------------", i));
							System.out.println(String.format("    DIV_CODE      = [%s]", rs.getString("DIV_CODE"     )));
							System.out.println(String.format("    IMPTSTEPGRPID = [%s]", rs.getString("IMPTSTEPGRPID")));
							System.out.println(String.format("    IMPTEQPID     = [%s]", rs.getString("IMPTEQPID"    )));
							System.out.println(String.format("    TRCK_OUT_HOUR = [%s]", rs.getString("TRCK_OUT_HOUR")));
							System.out.println(String.format("    DEFECTCD      = [%s]", rs.getString("DEFECTCD"     )));
							System.out.println(String.format("    DECGRADECD    = [%s]", rs.getString("DECGRADECD"   )));
							System.out.println(String.format("    DECQTY        = [%s]", rs.getString("DECQTY"       )));
						}
						
						if (Flag.TRUE) {
							WIP_CELL_RATIO_ReadBean bean = new WIP_CELL_RATIO_ReadBean();

							bean.setDivCode      (rs.getString("DIV_CODE"     ));
							bean.setImptStepGrpId(rs.getString("IMPTSTEPGRPID"));
							bean.setImptEqpId    (rs.getString("IMPTEQPID"    ));
							bean.setTrckOutHour  (rs.getString("TRCK_OUT_HOUR"));
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
				
				Connection04.getInstance().close();
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
			SystemProperties.setTesting("010");
			BasePath.getInstance();
			try {
				Logger.getInstance(WIP_CELL_RATIO_Main.jobId, WIP_CELL_RATIO_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(WIP_CELL_RATIO_Main.jobId);
			WIP_CELL_RATIO_OracleReader reader = new WIP_CELL_RATIO_OracleReader();
			
			List<WIP_CELL_RATIO_ReadBean> lst = reader.getList(true);
			for (WIP_CELL_RATIO_ReadBean bean : lst) {
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
