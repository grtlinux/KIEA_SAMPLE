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

package sdc.anal.mura.macro.A11.MURA_D.v01;

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
 * @file_name MURA_D_OracleReader.java
 *
 */
public class MURA_D_OracleReader extends AbstractOracleReader
{
	private List<MURA_D_ReadBean> list = new ArrayList<MURA_D_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public MURA_D_OracleReader()
	{
		if (Flag.TRUE) {
		}
	}

	/**
	 * 
	 * getLine56LC
	 *
	 * @return
	 */
	private String getLine56LC()
	{
		String query = null;
		
		query = String.format("" +
				"/* MURA_D : L5FAB, L6FAB - LC */                                            \n" +
				"SELECT /*+ PARALLEL(2) */                                                   \n" +
				"       t1.procid, t1.siteid, t1.glassid AS cellid,                          \n" +
				"       CASE                                                                 \n" +
				"          WHEN SUBSTR (t1.glassid, 8, 1) BETWEEN 'A' AND 'Z'                \n" +
				"             THEN    SUBSTR (t1.glassid, 1, 7)                              \n" +
				"                  || ''                                                     \n" +
				"                  || SUBSTR (t1.glassid, 8)                                 \n" +
				"          ELSE SUBSTR (t1.glassid, 1, 7) || '' || SUBSTR (t1.glassid, 8, 2) \n" +
				"       END glassid,                                                         \n" +
				"       d.prodgrpname,                                                       \n" +
				"       t1.prodid,                                                           \n" +
				"       orgstepid,                                                           \n" +
				"       measeqpunitid,                                                       \n" +
				"       dcoldate,                                                            \n" +
				"       itemid,                                                              \n" +
				"       subitemid,                                                           \n" +
				"       datavalue,                                                           \n" +
				"       code_x gateline,                                                     \n" +
				"       code_y dataline,                                                     \n" +
				"       code_x2 gateline2,                                                   \n" +
				"       code_y2 dataline2,                                                   \n" +
				"       t1.prodtype,                                                         \n" +
				"       CASE                                                                 \n" +
				"          WHEN SUBSTR (t1.glassid, 8, 1) BETWEEN 'A' AND 'Z'                \n" +
				"             THEN SUBSTR (t1.glassid, -2)                                   \n" +
				"          ELSE SUBSTR (t1.glassid, -1)                                      \n" +
				"       END cell_loc_id                                                      \n" +
				"  FROM yms_dm.h_meas_rundata_cell t1, yms_dm.d_product d                    \n" +
				" WHERE t1.dcoldate >= TO_DATE (%s, 'YYYYMMDDHH24MISS')                      \n" +   // FROM_ETL_DT
				"   AND t1.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')                       \n" +   // TO_ETL_DT
				"   AND t1.siteid = %s                                                       \n" +   // LINE
				"   AND t1.orgstepid LIKE 'L__080'                                           \n" +
				"   AND t1.procid IN ( %s )                                                  \n" +   // USER_GROUP_CODE
				"   AND t1.itemid IN ( %s )                                                  \n" +   // DEFECT_GROUP_CODE
				"   AND t1.prodid = d.prodid                                                 \n" +
				""
				//, "'20140701110000'", "'20140702105959'", "'LTL097QL01-A01'", "'L6FAB'", "'L6FABSTDALONE'", "'32PR'");
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
		);

		return query;
	}
	
	/**
	 * 
	 * getLine78LC
	 *
	 * @return
	 */
	private String getLine78LC()
	{
		String query = null;
		
		query = String.format("" +
				"/* MURA_D : L7AFAB, L7BFAB, L8AFAB, L8ZFAB - LC */                 \n" +
				"SELECT /*+ PARALLEL(2) */                                          \n" +
				"       t1.procid,                                                  \n" +
				"       t1.siteid,                                                  \n" +
				"       t1.glassid AS cellid,                                       \n" +
				"       CASE                                                        \n" +
				"          WHEN SUBSTR (t1.glassid, 1, 1) IN ('7', 'G')             \n" +
				"             THEN SUBSTR (t1.glassid, 1, 9)                        \n" +
				"          WHEN SUBSTR (t1.glassid, 1, 1) IN ('8', 'H')             \n" +
				"             THEN SUBSTR (t1.glassid, 1, 8)                        \n" +
				"       END glassid,                                                \n" +
				"       d.prodgrpname,                                              \n" +
				"       t1.prodid,                                                  \n" +
				"       orgstepid,                                                  \n" +
				"       measeqpunitid,                                              \n" +
				"       dcoldate,                                                   \n" +
				"       itemid,                                                     \n" +
				"       subitemid,                                                  \n" +
				"       datavalue,                                                  \n" +
				"       code_x gateline,                                            \n" +
				"       code_y dataline,                                            \n" +
				"       code_x2 gateline2,                                          \n" +
				"       code_y2 dataline2,                                          \n" +
				"       t1.prodtype,                                                \n" +
				"       CASE                                                        \n" +
				"          WHEN SUBSTR (t1.glassid, 1, 1) IN ('7', 'G')             \n" +
				"             THEN SUBSTR (t1.glassid, 10, 1)                       \n" +
				"          WHEN SUBSTR (t1.glassid, 1, 1) IN ('8', 'H')             \n" +
				"             THEN SUBSTR (t1.glassid, 9, 2)                        \n" +
				"       END cell_loc_id                                             \n" +
				"FROM   yms_dm.h_meas_rundata_cell t1, yms_dm.d_product d           \n" +
				" WHERE t1.dcoldate >= TO_DATE (%s, 'YYYYMMDDHH24MISS')             \n" +   // FROM_ETL_DT
				"   AND t1.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')              \n" +   // TO_ETL_DT
				"   AND t1.siteid = %s                                              \n" +   // LINE
				"   AND t1.measstepgrpid LIKE 'L%%'                                 \n" +
				"   AND t1.procid IN ( %s )                                         \n" +   // USER_GROUP_CODE
				"   AND t1.itemid IN ( %s )                                         \n" +   // DEFECT_GROUP_CODE
				"   AND t1.prodid = d.prodid                                        \n" +
				""
				//, "'20140701110000'", "'20140702105959'", "'LTL097QL01-A01'", "'L6FAB'", "'L6FABSTDALONE'", "'32PR'");
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
		);

		return query;
	}

	/**
	 * 
	 * getLine78MOD
	 *
	 * @return
	 */
	private String getLine78MOD()
	{
		String query = null;
		
		query = String.format("" +
				"/* MURA_D : L7AFAB, L7BFAB, L8AFAB, L8ZFAB - MOD */               \n" +
				"SELECT /*+ PARALLEL(2) */                                         \n" +
				"       t1.procid,                                                 \n" +
				"       t1.siteid,                                                 \n" +
				"       t1.glassid AS cellid,                                      \n" +
				"       CASE                                                       \n" +
				"          WHEN SUBSTR (t1.glassid, 1, 1) IN ('7', 'G')            \n" +
				"             THEN SUBSTR (t1.glassid, 1, 9)                       \n" +
				"          WHEN SUBSTR (t1.glassid, 1, 1) IN ('8', 'H')            \n" +
				"             THEN SUBSTR (t1.glassid, 1, 8)                       \n" +
				"       END glassid,                                               \n" +
				"       d.prodgrpname,                                             \n" +
				"       t1.prodid,                                                 \n" +
				"       orgstepid,                                                 \n" +
				"       measeqpunitid,                                             \n" +
				"       dcoldate,                                                  \n" +
				"       itemid,                                                    \n" +
				"       subitemid,                                                 \n" +
				"       datavalue,                                                 \n" +
				"       code_x gateline,                                           \n" +
				"       code_y dataline,                                           \n" +
				"       code_x2 gateline2,                                         \n" +
				"       code_y2 dataline2,                                         \n" +
				"       t1.prodtype,                                               \n" +
				"       CASE                                                       \n" +
				"          WHEN SUBSTR (t1.glassid, 1, 1) IN ('7', 'G')            \n" +
				"             THEN SUBSTR (t1.glassid, 10, 1)                      \n" +
				"          WHEN SUBSTR (t1.glassid, 1, 1) IN ('8', 'H')            \n" +
				"             THEN SUBSTR (t1.glassid, 9, 2)                       \n" +
				"       END cell_loc_id                                            \n" +
				"  FROM yms_dm.h_meas_rundata_cell t1, yms_dm.d_product d          \n" +
				" WHERE t1.dcoldate >= TO_DATE (%s, 'YYYYMMDDHH24MISS')            \n" +   // FROM_ETL_DT
				"   AND t1.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')             \n" +   // TO_ETL_DT
				"   AND t1.siteid = %s                                             \n" +   // LINE
				"   AND t1.measstepgrpid = '7M020'                                 \n" +
				"   AND t1.prodid IN ( %s )                                        \n" +   // USER_GROUP_CODE
				"   AND t1.itemid IN ( %s )                                        \n" +   // DEFECT_GROUP_CODE
				"   AND t1.prodid = d.prodid                                       \n" +
				""
				//, "'20140702070000'", "'20140703065959'", "'LSC480HN02-G01'", "'L8AFAB'", "'8AFAB'", "'L8AFAB'", "'L8AFAB'", "'L8AFAB'");
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
		);

		return query;
	}
	
	/**
	 * getList
	 */
	@Override
	public List<MURA_D_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<MURA_D_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<MURA_D_ReadBean>();
				return list;
			}
			
			try {
				String query = null;
				
				if ("L5FAB".equals(Parameter.getInstance().getStrLine()) || "L6FAB".equals(Parameter.getInstance().getStrLine())) {
					if ("LC".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine56LC();
					} else if ("MOD".equals(Parameter.getInstance().getStrAreaCode())) {
						return list;
					}
				} else if ("L7AFAB".equals(Parameter.getInstance().getStrLine()) || "L7BFAB".equals(Parameter.getInstance().getStrLine()) || "L8AFAB".equals(Parameter.getInstance().getStrLine()) || "L8ZFAB".equals(Parameter.getInstance().getStrLine())) {
					if ("LC".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine78LC();
					} else if ("MOD".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine78MOD();
					}
				} else {
					return list;
				}
				
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				if (Flag.TRUE) Logger.info("[" + query + "]");
				
				OracleConnection conn = Connection04.getInstance().getConn();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i < 1000000 && rs.next(); i++) {
						if (!Flag.TRUE) {
							System.out.println(String.format("%3d) --------------------------------------", i));
							System.out.println(String.format("    PROCID        = [%s]", rs.getString("PROCID"       )));
							System.out.println(String.format("    SITEID        = [%s]", rs.getString("SITEID"       )));
							System.out.println(String.format("    CELLID        = [%s]", rs.getString("CELLID"       )));
							System.out.println(String.format("    GLASSID       = [%s]", rs.getString("GLASSID"      )));
							System.out.println(String.format("    PRODGRPNAME   = [%s]", rs.getString("PRODGRPNAME"  )));
							System.out.println(String.format("    PRODID        = [%s]", rs.getString("PRODID"       )));
							System.out.println(String.format("    ORGSTEPID     = [%s]", rs.getString("ORGSTEPID"    )));
							System.out.println(String.format("    MEASEQPUNITID = [%s]", rs.getString("MEASEQPUNITID")));
							System.out.println(String.format("    DCOLDATE      = [%s]", rs.getString("DCOLDATE"     )));
							System.out.println(String.format("    ITEMID        = [%s]", rs.getString("ITEMID"       )));
							System.out.println(String.format("    SUBITEMID     = [%s]", rs.getString("SUBITEMID"    )));
							System.out.println(String.format("    DATAVALUE     = [%s]", rs.getString("DATAVALUE"    )));
							System.out.println(String.format("    GATELINE      = [%s]", rs.getString("GATELINE"     )));
							System.out.println(String.format("    DATALINE      = [%s]", rs.getString("DATALINE"     )));
							System.out.println(String.format("    GATELINE2     = [%s]", rs.getString("GATELINE2"    )));
							System.out.println(String.format("    DATALINE2     = [%s]", rs.getString("DATALINE2"    )));
							System.out.println(String.format("    PRODTYPE      = [%s]", rs.getString("PRODTYPE"     )));
							System.out.println(String.format("    CELL_LOC_ID   = [%s]", rs.getString("CELL_LOC_ID"  )));
						}
						
						if (Flag.TRUE) {
							MURA_D_ReadBean bean = new MURA_D_ReadBean();

							bean.setProcId       (rs.getString("PROCID"       ));
							bean.setSiteId       (rs.getString("SITEID"       ));
							bean.setCellId       (rs.getString("CELLID"       ));
							bean.setGlassId      (rs.getString("GLASSID"      ));
							bean.setProdRrpName  (rs.getString("PRODGRPNAME"  ));
							bean.setProdId       (rs.getString("PRODID"       ));
							bean.setOrgStepId    (rs.getString("ORGSTEPID"    ));
							bean.setMeasEqpUnitId(rs.getString("MEASEQPUNITID"));
							bean.setDcolDate     (rs.getString("DCOLDATE"     ));
							bean.setItemId       (rs.getString("ITEMID"       ));
							bean.setSubItemId    (rs.getString("SUBITEMID"    ));
							bean.setDataValue    (rs.getString("DATAVALUE"    ));
							bean.setGateLine     (rs.getString("GATELINE"     ));
							bean.setDataLine     (rs.getString("DATALINE"     ));
							bean.setGateLine2    (rs.getString("GATELINE2"    ));
							bean.setDataLine2    (rs.getString("DATALINE2"    ));
							bean.setProdType     (rs.getString("PRODTYPE"     ));
							bean.setCellLocId    (rs.getString("CELL_LOC_ID"  ));
							
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
				
				try { Thread.sleep(1000); } catch (InterruptedException ex) {};
				
				Connection04.getInstance().close();;
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
			SystemProperties.setTesting("020");
			BasePath.getInstance();
			try {
				Logger.getInstance(MURA_D_Main.jobId, MURA_D_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(MURA_D_Main.jobId);
			
			MURA_D_OracleReader reader = new MURA_D_OracleReader();
			
			for (MURA_D_ReadBean bean : reader.getList(true)) {
				System.out.println(bean);
			}
			
			System.out.println("the read ok !!!");
		}

		Logger.exit();
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
