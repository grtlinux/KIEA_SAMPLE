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

package kiea.proj.sdc.anal.macro.sample.a04.io;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.macro.impl.io.AbstractOracleReader;
import kiea.proj.sdc.anal.macro.sample.a04.bean.MURA_D_ReadBean;
import kiea.proj.sdc.anal.util.StrUtil;
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
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	/**
	 * Sample02OracleReader
	 */
	public MURA_D_OracleReader(String strFromDateTime, String strToDateTime, String strLine, String strAreaCode, String strUserGroupCode, String strDefectGroupCode)
	{
		if (Flag.TRUE) {
			this.strFromDateTime    = strFromDateTime;
			this.strToDateTime      = strToDateTime;
			this.strLine            = strLine;
			this.strAreaCode        = strAreaCode;
			this.strUserGroupCode   = strUserGroupCode;
			this.strDefectGroupCode = strDefectGroupCode;
		}
	}

	/**
	 * 
	 * getLine56MOD
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
				, StrUtil.quote(strFromDateTime)
				, StrUtil.quote(strToDateTime)
				, StrUtil.quote(strLine)
				, StrUtil.quote(strUserGroupCode)
				, StrUtil.quote(strDefectGroupCode)
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
				, StrUtil.quote(strFromDateTime)
				, StrUtil.quote(strToDateTime)
				, StrUtil.quote(strLine)
				, StrUtil.quote(strUserGroupCode)
				, StrUtil.quote(strDefectGroupCode)
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
				, StrUtil.quote(strFromDateTime)
				, StrUtil.quote(strToDateTime)
				, StrUtil.quote(strLine)
				, StrUtil.quote(strUserGroupCode)
				, StrUtil.quote(strDefectGroupCode)
		);

		return query;
	}
	
	/**
	 * getList
	 */
	public List<MURA_D_ReadBean> getList()
	{
		if (Flag.TRUE) {
			try {
				String query = null;
				
				if ("L5FAB".equals(strLine) || "L6FAB".equals(strLine)) {
					if ("LC".equals(strAreaCode)) {
						query = getLine56LC();
					} else if ("MOD".equals(strAreaCode)) {
						return list;
					}
				} else if ("L7AFAB".equals(strLine) || "L7BFAB".equals(strLine) || "L8AFAB".equals(strLine) || "L8ZFAB".equals(strLine)) {
					if ("LC".equals(strAreaCode)) {
						query = getLine78LC();
					} else if ("MOD".equals(strAreaCode)) {
						query = getLine78MOD();
					}
				} else {
					return list;
				}
				
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				
				OracleConnection conn = Connection.getOracleConnection();

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
				
				conn.close();
				if (!Flag.TRUE) System.out.println("OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	@SuppressWarnings("unused")
	private static void test01()
	{
		if (Flag.TRUE) {

			String strFromDateTime    = null;
			String strToDateTime      = null;
			String strLine            = null;
			String strAreaCode        = null;
			String strUserGroupCode   = null;
			String strDefectGroupCode = null;

			if (!Flag.TRUE && Flag.FALSE) {
				/*
				 * L5FAB, L6FAB - LC
				 */
				strFromDateTime    = "20140805090000";
				strToDateTime      = "20140806085959";
				strLine            = "L6FAB";
				strAreaCode        = "LC";
				strUserGroupCode   = "L6156H11";
				strDefectGroupCode = "POR";
			}

			if (!Flag.TRUE) {
				/*
				 * L5FAB, L6FAB - MOD
				 */
				strFromDateTime    = "20140701110000";
				strToDateTime      = "20140702105959";
				strLine            = "L6FAB";
				strAreaCode        = "MOD";
				strUserGroupCode   = "LTL097QL01-A01";
				strDefectGroupCode = "32PR";
			}

			if (!Flag.TRUE && Flag.FALSE) {
				/*
				 * L7AFAB, L7BFAB, L8AFAB, L8ZFAB - LC
				 */
				strFromDateTime    = "20140702110000";
				strToDateTime      = "20140703105959";
				strLine            = "L8AFAB";
				strAreaCode        = "LC";
				strUserGroupCode   = "L8480F71";
				strDefectGroupCode = "32PR";
			}

			if (Flag.TRUE) {
				/*
				 * L7AFAB, L7BFAB, L8AFAB, L8ZFAB - MOD
				 */
				strFromDateTime    = "20140702070000";
				strToDateTime      = "20140703065959";
				strLine            = "L8AFAB";
				strAreaCode        = "MOD";
				strUserGroupCode   = "LSC480HN02-G01";
				strDefectGroupCode = "32BOR";
			}

			MURA_D_OracleReader reader = new MURA_D_OracleReader(strFromDateTime, strToDateTime, strLine, strAreaCode, strUserGroupCode, strDefectGroupCode);
			reader.getList();
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
