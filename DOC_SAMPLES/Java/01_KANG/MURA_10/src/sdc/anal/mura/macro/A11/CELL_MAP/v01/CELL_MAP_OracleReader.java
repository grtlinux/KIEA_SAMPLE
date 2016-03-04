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

package sdc.anal.mura.macro.A11.CELL_MAP.v01;

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
 * @file_name CELL_MAP_OracleReader.java
 *
 */
public class CELL_MAP_OracleReader extends AbstractOracleReader
{
	private List<CELL_MAP_ReadBean> list = new ArrayList<CELL_MAP_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public CELL_MAP_OracleReader()
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
	public List<CELL_MAP_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<CELL_MAP_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<CELL_MAP_ReadBean>();
				return list;
			}
			
			try {
				String query = null;
				
				if ("L5FAB".equals(Parameter.getInstance().getStrLine()) || "L6FAB".equals(Parameter.getInstance().getStrLine())) {
					if ("LC".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine56LC();
					} else if ("MOD".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine56MOD();
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
							bean.setXLogic1       (rs.getString("X_LOGIC1"      ));
							bean.setYLogic1       (rs.getString("Y_LOGIC1"      ));
							bean.setXLogic2       (rs.getString("X_LOGIC2"      ));
							bean.setYLogic2       (rs.getString("Y_LOGIC2"      ));
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
				Logger.getInstance(CELL_MAP_Main.jobId, CELL_MAP_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(CELL_MAP_Main.jobId);
			
			CELL_MAP_OracleReader reader = new CELL_MAP_OracleReader();
			
			for (CELL_MAP_ReadBean bean : reader.getList(true)) {
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
