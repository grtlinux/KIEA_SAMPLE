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

package kiea.proj.sdc.anal.macro.sample.a11.aib.io;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.macro.impl.io.AbstractOracleReader;
import kiea.proj.sdc.anal.macro.sample.a11.aib.bean.CELL_MAP_ReadBean;
import kiea.proj.sdc.anal.util.StrUtil;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name CELL_MAP_OracleReader.java
 *
 */
public class CELL_MAP_OracleReader extends AbstractOracleReader
{
	private List<CELL_MAP_ReadBean> list = new ArrayList<CELL_MAP_ReadBean>();
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	/**
	 * Sample02OracleReader
	 */
	public CELL_MAP_OracleReader(String strFromDateTime, String strToDateTime, String strLine, String strAreaCode, String strUserGroupCode, String strDefectGroupCode)
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
	 * getLine56LC
	 *
	 * @return
	 */
	private String getLine56LC()
	{
		String query = null;
		
		query = String.format("" +
				" /* CELL_MAP : L5FAB, L6FAB - LC  */                                  \n" +
				"WITH temp1 as (                                                       \n" +
				"    SELECT /*+ PARALLEL(2) */                                         \n" +
				"        DISTINCT t1.procid,  t1.prodid                                \n" +
				"    FROM yms_dm.h_meas_rundata_cell t1 /*,  yms_dm.d_product d */     \n" +
				"    WHERE t1.dcoldate >= TO_DATE (%s, 'YYYYMMDDHH24MISS')             \n" +   // FROM_ETL_DT
				"        AND t1.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')            \n" +   // TO_ETL_DT
				"        AND t1.siteid = %s                                            \n" +   // LINE
				"        AND t1.orgstepid like 'L__080'                                \n" +
				"        AND T1.PROCID IN ( %s )                                       \n" +   // USER_GROUP_CODE
				"        AND t1.ITEMID IN ( %s )                                       \n" +   // DEFECT_GROUP_CODE
				")                                                                     \n" +
				"SELECT T2.*                                                           \n" +
				"FROM temp1 T1, YMS_DM.D_CELLCONVERT T2                                \n" +
				"WHERE t1.procid LIKE t2.procid || '%%'                                \n" +
				"/* 아래 쿼리가 성능상 더 좋음(동일 결과 예상) */                      \n" +
				"/* select * from yms_dm.d_cellconvert         */                      \n" +
				"/* where &USER_GROUP_CODE. like procid||'%%'  */                      \n" +
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
	 * getLine56MOD
	 *
	 * @return
	 */
	private String getLine56MOD()
	{
		String query = null;
		
		query = String.format("" +
				" /* CELL_MAP : L5FAB, L6FAB - MOD  */                                \n" +
				"WITH temp1 as                                                        \n" +
				"(                                                                    \n" +
				"    SELECT /*+PARALLEL(2) */                                         \n" +
				"        DISTINCT d.procid,  t1.prodid                                \n" +
				"    FROM yms_dm.h_meas_rundata_cell t1  , yms_dm.d_product d         \n" +
				"    WHERE t1.dcoldate >= TO_DATE (%s, 'YYYYMMDDHH24MISS')            \n" +   // FROM_ETL_DT
				"        AND t1.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')           \n" +   // TO_ETL_DT
				"        AND t1.siteid = %s                                           \n" +   // LINE
				"        AND T1.measstepgrpid = '7M020'                               \n" +
				"        AND T1.PRODID IN (%s)                                        \n" +   // USER_GROUP_CODE
				"        AND t1.ITEMID IN (%s)                                        \n" +   // DEFECT_GROUP_CODE
				"        AND T1.PRODID = D.PRODID                                     \n" +
				")                                                                    \n" +
				"SELECT T2.*                                                          \n" +
				"FROM temp1 T1, YMS_DM.D_CELLCONVERT T2                               \n" +
				"WHERE t1.procid LIKE t2.procid||'%%'                                 \n" +
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
	private String getLine78LC()
	{
		String query = null;
		
		query = String.format("" +
				"/* CELL_MAP : L7AFAB, L7BFAB, L8AFAB, L8ZFAB - LC */                  \n" +
				"WITH temp1 as (                                                       \n" +
				"    SELECT /*+PARALLEL(2) */                                          \n" +
				"        DISTINCT t1.procid,  t1.prodid                                \n" +
				"    FROM yms_dm.h_meas_rundata_cell t1 /*,  yms_dm.d_product d */     \n" +
				"    WHERE t1.dcoldate >= TO_DATE (%s, 'YYYYMMDDHH24MISS')             \n" +   // FROM_ETL_DT
				"        AND t1.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')            \n" +   // TO_ETL_DT
				"        AND t1.siteid = %s                                            \n" +   // LINE
				"        AND t1.measstepgrpid like 'L%%'                               \n" +
				"        AND T1.PROCID IN ( %s )                                       \n" +   // USER_GROUP_CODE
				"        AND t1.ITEMID IN ( %s )                                       \n" +   // DEFECT_GROUP_CODE
				")                                                                     \n" +
				"SELECT T2.*                                                           \n" +
				"FROM temp1 T1, YMS_DM.D_CELLCONVERT T2                                \n" +
				"WHERE t1.procid LIKE t2.procid||'%%'                                  \n" +
				"/* 아래 쿼리가 성능상 더 좋음(동일 결과 예상) */                      \n" +
				"/* select * from yms_dm.d_cellconvert         */                      \n" +
				"/* where &USER_GROUP_CODE. like procid||'%%'  */                      \n" +
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
	 * 
	 * getLine78MOD
	 *
	 * @return
	 */
	private String getLine78MOD()
	{
		String query = null;
		
		query = String.format("" +
				"/* CELL_MAP : L7AFAB, L7BFAB, L8AFAB, L8ZFAB - MOD */                 \n" +
				"WITH temp1 as (                                                       \n" +
				"    SELECT /*+PARALLEL(2) */                                          \n" +
				"        DISTINCT d.procid,  t1.prodid                                 \n" +
				"    FROM yms_dm.h_meas_rundata_cell t1 , yms_dm.d_product d           \n" +
				"    WHERE t1.dcoldate >= TO_DATE (%s, 'YYYYMMDDHH24MISS')             \n" +   // FROM_ETL_DT
				"        AND t1.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')            \n" +   // TO_ETL_DT
				"        AND t1.siteid = %s                                            \n" +   // LINE
				"        AND T1.measstepgrpid = '7M020'                                \n" +
				"        AND T1.PRODID IN ( %s )                                       \n" +   // USER_GROUP_CODE
				"        AND t1.ITEMID IN ( %s )                                       \n" +   // DEFECT_GROUP_CODE
				"        AND T1.PRODID = D.PRODID                                      \n" +
				")                                                                     \n" +
				"SELECT T2.*                                                           \n" +
				"FROM temp1 T1, YMS_DM.D_CELLCONVERT T2                                \n" +
				"WHERE t1.procid LIKE t2.procid||'%%'                                  \n" +
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
	public List<CELL_MAP_ReadBean> getList()
	{
		if (Flag.TRUE) {
			try {
				String query = null;
				
				if ("L5FAB".equals(strLine) || "L6FAB".equals(strLine)) {
					if ("LC".equals(strAreaCode)) {
						query = getLine56LC();
					} else if ("MOD".equals(strAreaCode)) {
						query = getLine56MOD();
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
							System.out.println(String.format("    PROCID         = [%s]", rs.getString("PROCID"        )));
							System.out.println(String.format("    CELLLOCID      = [%s]", rs.getString("CELLLOCID"     )));
							System.out.println(String.format("    POINT_X        = [%s]", rs.getString("POINT_X"       )));
							System.out.println(String.format("    POINT_Y        = [%s]", rs.getString("POINT_Y"       )));
							System.out.println(String.format("    WIDTH          = [%s]", rs.getString("WIDTH"         )));
							System.out.println(String.format("    HEIGHT         = [%s]", rs.getString("HEIGHT"        )));
							System.out.println(String.format("    GATERESOLUTION = [%s]", rs.getString("GATERESOLUTION")));
							System.out.println(String.format("    DATARESOLUTION = [%s]", rs.getString("DATARESOLUTION")));
							System.out.println(String.format("    COLIDX         = [%s]", rs.getString("COLIDX"        )));
							System.out.println(String.format("    ROWIDX         = [%s]", rs.getString("ROWIDX"        )));
							System.out.println(String.format("    PIXCEL_X       = [%s]", rs.getString("PIXCEL_X"      )));
							System.out.println(String.format("    PIXCEL_Y       = [%s]", rs.getString("PIXCEL_Y"      )));
							System.out.println(String.format("    X_LOGIC1       = [%s]", rs.getString("X_LOGIC1"      )));
							System.out.println(String.format("    Y_LOGIC1       = [%s]", rs.getString("Y_LOGIC1"      )));
							System.out.println(String.format("    X_LOGIC2       = [%s]", rs.getString("X_LOGIC2"      )));
							System.out.println(String.format("    Y_LOGIC2       = [%s]", rs.getString("Y_LOGIC2"      )));
							System.out.println(String.format("    SITEID         = [%s]", rs.getString("SITEID"        )));
							System.out.println(String.format("    GLASS_HEIGHT   = [%s]", rs.getString("GLASS_HEIGHT"  )));
							System.out.println(String.format("    MAPCELL        = [%s]", rs.getString("MAPCELL"       )));
						}
						
						if (Flag.TRUE) {
							CELL_MAP_ReadBean bean = new CELL_MAP_ReadBean();

							bean.setProcId        (rs.getString("PROCID"        ));
							bean.setCellLocId     (rs.getString("CELLLOCID"     ));
							bean.setPointX        (rs.getString("POINT_X"       ));
							bean.setPointY        (rs.getString("POINT_Y"       ));
							bean.setWidth         (rs.getString("WIDTH"         ));
							bean.setHeight        (rs.getString("HEIGHT"        ));
							bean.setGateResolution(rs.getString("GATERESOLUTION"));
							bean.setDataResolution(rs.getString("DATARESOLUTION"));
							bean.setColIdx        (rs.getString("COLIDX"        ));
							bean.setRowIdx        (rs.getString("ROWIDX"        ));
							bean.setPixcelX       (rs.getString("PIXCEL_X"      ));
							bean.setPixcelY       (rs.getString("PIXCEL_Y"      ));
							bean.setxLogic1       (rs.getString("X_LOGIC1"      ));
							bean.setyLogic1       (rs.getString("Y_LOGIC1"      ));
							bean.setxLogic2       (rs.getString("X_LOGIC2"      ));
							bean.setyLogic2       (rs.getString("Y_LOGIC2"      ));
							bean.setSiteId        (rs.getString("SITEID"        ));
							bean.setGlassHeight   (rs.getString("GLASS_HEIGHT"  ));
							bean.setMapCell       (rs.getString("MAPCELL"       ));
							
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

			String strFromDateTime    = null;
			String strToDateTime      = null;
			String strLine            = null;
			String strAreaCode        = null;
			String strUserGroupCode   = null;
			String strDefectGroupCode = null;

			if (!Flag.TRUE) {
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

			if (!Flag.TRUE) {
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

			CELL_MAP_OracleReader reader = new CELL_MAP_OracleReader(strFromDateTime, strToDateTime, strLine, strAreaCode, strUserGroupCode, strDefectGroupCode);
			reader.getList();
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
