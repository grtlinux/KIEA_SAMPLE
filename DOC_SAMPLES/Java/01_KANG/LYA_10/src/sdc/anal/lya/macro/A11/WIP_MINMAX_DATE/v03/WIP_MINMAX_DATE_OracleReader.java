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

package sdc.anal.lya.macro.A11.WIP_MINMAX_DATE.v03;

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
 * @file_name AIB_WIP_MINMAX_DATE_OracleReader.java
 *
 */
public class WIP_MINMAX_DATE_OracleReader extends AbstractOracleReader
{
	private List<WIP_MINMAX_DATE_ReadBean> list = new ArrayList<WIP_MINMAX_DATE_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public WIP_MINMAX_DATE_OracleReader()
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
				"/* WIP_MINMAX_DT */                                                            \n" +
				"SELECT                                                                         \n" +
				"    T1.IMPTSITEID                                 AS LINE_CODE                 \n" +
				"    , T1.PRODGRPNAME                              AS USER_GROUP_CODE           \n" +
				"    , T1.AREA                                     AS AREA                      \n" +
				"    , TO_DATE(MIN(T1.PROCHOUR),'YYYYMMDDHH24')    AS TRACKIN_MIN_TIME          \n" +
				"    , TO_DATE(MAX(T1.PROCHOUR),'YYYYMMDDHH24')    AS TRACKOUT_MAX_TIME         \n" +
				"FROM                                                                           \n" +
				"    (                                                                          \n" +
				"    SELECT /*+ ORDERED PARALLEL(2) */                                          \n" +
				"        F.IMPTSITEID                                                           \n" +
				"        , D.PRODGRPNAME                                                        \n" +
				"        , CASE                                                                 \n" +
				"            WHEN IMPTSTEPGRPID LIKE 'T%%' OR IMPTSTEPGRPID LIKE 'A%%' THEN 'T' \n" +
				"            WHEN IMPTSTEPGRPID LIKE 'F%%' OR IMPTSTEPGRPID LIKE 'B%%' THEN 'F' \n" +
				"            WHEN IMPTSTEPGRPID LIKE 'L%%' OR IMPTSTEPGRPID LIKE 'C%%' THEN 'L' \n" +
				"            END             AS AREA                                            \n" +
				"        , F.INSPHOUR       /* 검사시간 */                                      \n" +
				"        , F.PROCHOUR       /* 공정시간 */                                      \n" +
				"        , F.INSPSTEPTYPE                                                       \n" +
				"        , F.IMPTSTEPGRPID                                                      \n" +
				"        , F.IMPTEQPID                                                          \n" +
				"        , F.DECGRADECD                                                         \n" +
				"        , F.DECQTY                                                             \n" +
				"    FROM                                                                       \n" +
				"        yms_dm.F_INSPIMPTDEFECTEQP F   /* 공정이력테이블 */                    \n" +
				"        , yms_dm.D_PRODUCT         D                                           \n" +
				"    WHERE                                                                      \n" +
				"        F.INSPHOUR          >= %s                                              \n" +  // FROM_ETL_HOUR
				"        AND F.INSPHOUR      <= %s                                              \n" +  // TO_ETL_HOUR
				"        AND F.SITEID        =  %s                                              \n" +  // LINE
				"        AND F.INSPSTEPTYPE  IN ( %s )                                          \n" +  // SUB_AREA_CODE
				"        AND D.PRODGRPNAME   IN ( %s)                                           \n" +  // USER_GROUP_CODE
				"        AND F.PRODTYPE      IN ( %s )                                          \n" +  // PRODUCT_TYPE
				"        AND F.PRODID = D.PRODID                                                \n" +
				"        AND F.DECQTY > 0                                                       \n" +
				"    ) T1                                                                       \n" +
				"GROUP BY                                                                       \n" +
				"    T1.IMPTSITEID                                                              \n" +
				"    , T1.PRODGRPNAME                                                           \n" +
				"    , T1.AREA                                                                  \n" +
				""
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
	public List<WIP_MINMAX_DATE_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<WIP_MINMAX_DATE_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<WIP_MINMAX_DATE_ReadBean>();
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
							System.out.println(String.format("    LINE_CODE         = [%s]", rs.getString("LINE_CODE"        )));
							System.out.println(String.format("    USER_GROUP_CODE   = [%s]", rs.getString("USER_GROUP_CODE"  )));
							System.out.println(String.format("    AREA              = [%s]", rs.getString("AREA"             )));
							System.out.println(String.format("    TRACKIN_MIN_TIME  = [%s]", rs.getString("TRACKIN_MIN_TIME" )));
							System.out.println(String.format("    TRACKOUT_MAX_TIME = [%s]", rs.getString("TRACKOUT_MAX_TIME")));
						}
						
						if (Flag.TRUE) {
							WIP_MINMAX_DATE_ReadBean bean = new WIP_MINMAX_DATE_ReadBean();

							bean.setLineCode       (rs.getString("LINE_CODE"        ));
							bean.setUserGroupCode  (rs.getString("USER_GROUP_CODE"  ));
							bean.setArea           (rs.getString("AREA"             ));
							bean.setTrackinMinTime (rs.getString("TRACKIN_MIN_TIME" ));
							bean.setTrackoutMaxTime(rs.getString("TRACKOUT_MAX_TIME"));
							
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
				Logger.getInstance(WIP_MINMAX_DATE_Main.jobId, WIP_MINMAX_DATE_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(WIP_MINMAX_DATE_Main.jobId);
			WIP_MINMAX_DATE_OracleReader reader = new WIP_MINMAX_DATE_OracleReader();
			
			for (WIP_MINMAX_DATE_ReadBean bean : reader.getList(true)) {
				System.out.println(bean);
			}
			
			System.out.println("the read ok !!!");
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
