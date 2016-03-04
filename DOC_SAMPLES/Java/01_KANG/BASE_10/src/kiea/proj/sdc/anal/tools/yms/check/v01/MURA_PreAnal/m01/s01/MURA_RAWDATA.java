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

package kiea.proj.sdc.anal.tools.yms.check.v01.MURA_PreAnal.m01.s01;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.util.StrUtil;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 11.
 * @file_name MURA_RAWDATA.java
 *
 */
public class MURA_RAWDATA
{
	/*
	 * 
	 *     MURA_RAWDATA
	 *
	 */
	
	///////////////////////////////////////////////////////////////////////////

	private static void line56LC()
	{
		if (Flag.TRUE) {
			try {
				String query = String.format("" +
						"/* MURA_RAWDATA : L5FAB, L6FAB - LC */                                         \n" +
						"WITH temp1 as (                                                                \n" +
						"    SELECT /*+ PARALLEL(2) */                                                  \n" +
						"        t1.procid, t1.siteid, t1.glassid AS CELLID,                            \n" +
						"        CASE                                                                   \n" +
						"            WHEN SUBSTR (t1.glassid, 8, 1) BETWEEN 'A' AND 'Z'                 \n" +
						"            THEN    SUBSTR (t1.glassid, 1, 7)|| ''|| SUBSTR (t1.glassid, 8,1)  \n" +
						"            ELSE    SUBSTR (t1.glassid, 1, 7)|| ''|| SUBSTR (t1.glassid, 8,2)  \n" +
						"            END GLASSID,                                                       \n" +
						"        d.prodgrpname, t1.prodid, orgstepid, measeqpunitid, dcoldate,          \n" +
						"        itemid, subitemid, datavalue,                                          \n" +
						"        code_x gateline, code_y dataline,                                      \n" +
						"        code_x2 gateline2, code_y2 dataline2,                                  \n" +
						"        t1.prodtype,                                                           \n" +
						"        CASE                                                                   \n" +
						"            WHEN SUBSTR (t1.glassid,8, 1) BETWEEN 'A' AND 'Z'                  \n" +
						"            THEN    SUBSTR (t1.glassid, -2)                                    \n" +
						"            ELSE    SUBSTR (t1.glassid, -1)                                    \n" +
						"            END  cell_loc_id  /* L7156A1 제외 */                               \n" +
						"    FROM YMS_DM.H_MEAS_RUNDATA_CELL t1 , yms_dm.d_product d                    \n" +
						"    WHERE t1.dcoldate >= TO_DATE (%s, 'YYYYMMDDHH24MISS')                      \n" +  // FROM_ETL_DT
						"        AND t1.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')                     \n" +  // TO_ETL_DT
						"        AND t1.siteid = %s                                                     \n" +  // LINE
						"        AND t1.orgstepid like 'L__080'                                         \n" +
						"        AND T1.PROCID IN ( %s )                                                \n" +  // USER_GROUP_CODE
						"        AND t1.ITEMID IN ( %s )                                                \n" +  // DEFECT_GROUP_CODE
						"        and t1.prodid = d.prodid                                               \n" +
						")                                                                              \n" +
						"SELECT /*+ PARALLEL(2) */                                                      \n" +
						"    t2.PROCID                                                                  \n" +
						"    , T2.SITEID                                                                \n" +
						"    , T2.CELLID                                                                \n" +
						"    , T2.GLASSID                                                               \n" +
						"    , T2.PRODGRPNAME                                                           \n" +
						"    , T2.PRODID                                                                \n" +
						"    , T2.ORGSTEPID                                                             \n" +
						"    , T2.MEASEQPUNITID                                                         \n" +
						"    , T2.DCOLDATE as DCOLTIME                                                  \n" +
						"    , T2.ITEMID                                                                \n" +
						"    , T2.SUBITEMID                                                             \n" +
						"    , T4.DEFECTNAME                                                            \n" +
						"    , T2.DATAVALUE                                                             \n" +
						"    , T2.GATELINE                                                              \n" +
						"    , T2.DATALINE                                                              \n" +
						"    , T2.GATELINE2                                                             \n" +
						"    , T2.DATALINE2                                                             \n" +
						"    , T2.PRODTYPE                                                              \n" +
						"    , T2.CELL_LOC_ID                                                           \n" +
						"    , t3.width                                                                 \n" +
						"    , t3.height                                                                \n" +
						"    , CASE                                                                     \n" +
						"      WHEN t3.width - t3.height < 0                                            \n" +
						"         THEN CASE                                                             \n" +
						"                WHEN t3.x_logic1 = 'POINT_X + GATE * PIXCEL_X'                 \n" +
						"                   THEN t3.point_x + t2.gateline * t3.pixcel_x                 \n" +
						"                WHEN t3.x_logic1 = 'POINT_X + DATA * PIXCEL_X'                 \n" +
						"                   THEN t3.point_x + t2.dataline * t3.pixcel_x                 \n" +
						"             END                                                               \n" +
						"      ELSE CASE                                                                \n" +
						"      WHEN t3.x_logic2 = 'POINT_X + WIDTH - DATA * PIXCEL_X'                   \n" +
						"         THEN t3.point_x + t3.width - t2.dataline * t3.pixcel_x                \n" +
						"      WHEN t3.x_logic2 = 'POINT_X + DATA * PIXCEL_X'                           \n" +
						"         THEN t3.point_x + t2.dataline * t3.pixcel_x                           \n" +
						"    END                                                                        \n" +
						"    END x_value,                                                               \n" +
						"    CASE                                                                       \n" +
						"      WHEN t3.width - t3.height < 0                                            \n" +
						"         THEN CASE                                                             \n" +
						"                WHEN t3.y_logic1 =                                             \n" +
						"                             'POINT_Y + HEIGHT - DATA * PIXCEL_Y'              \n" +
						"                   THEN   t3.point_y                                           \n" +
						"                        + t3.height                                            \n" +
						"                        - t2.dataline * t3.pixcel_y                            \n" +
						"                WHEN t3.y_logic1 = 'POINT_Y + GATE * PIXCEL_Y'                 \n" +
						"                   THEN t3.point_y + t2.gateline * t3.pixcel_y                 \n" +
						"             END                                                               \n" +
						"      ELSE CASE                                                                \n" +
						"      WHEN t3.y_logic2 = 'POINT_Y + HEIGHT - GATE * PIXCEL_Y'                  \n" +
						"         THEN t3.point_y + t3.height - t2.gateline * t3.pixcel_y               \n" +
						"      WHEN t3.y_logic2 = 'POINT_Y + GATE * PIXCEL_Y'                           \n" +
						"         THEN t3.point_y + t2.gateline * t3.pixcel_y                           \n" +
						"    END                                                                        \n" +
						"    END y_value,                                                               \n" +
						"          CASE                                                                 \n" +
						"      WHEN t3.width - t3.height < 0                                            \n" +
						"         THEN CASE                                                             \n" +
						"                WHEN t3.x_logic1 = 'POINT_X + GATE * PIXCEL_X'                 \n" +
						"                   THEN t3.point_x + t2.gateline2 * t3.pixcel_x                \n" +
						"                WHEN t3.x_logic1 = 'POINT_X + DATA * PIXCEL_X'                 \n" +
						"                   THEN t3.point_x + t2.dataline2 * t3.pixcel_x                \n" +
						"             END                                                               \n" +
						"      ELSE CASE                                                                \n" +
						"      WHEN t3.x_logic2 = 'POINT_X + WIDTH - DATA * PIXCEL_X'                   \n" +
						"         THEN t3.point_x + t3.width - t2.dataline2 * t3.pixcel_x               \n" +
						"      WHEN t3.x_logic2 = 'POINT_X + DATA * PIXCEL_X'                           \n" +
						"         THEN t3.point_x + t2.dataline2 * t3.pixcel_x                          \n" +
						"    END                                                                        \n" +
						"    END x2_value,                                                              \n" +
						"    CASE                                                                       \n" +
						"      WHEN t3.width - t3.height < 0                                            \n" +
						"         THEN CASE                                                             \n" +
						"                WHEN t3.y_logic1 =                                             \n" +
						"                             'POINT_Y + HEIGHT - DATA * PIXCEL_Y'              \n" +
						"                   THEN   t3.point_y                                           \n" +
						"                        + t3.height                                            \n" +
						"                        - t2.dataline2 * t3.pixcel_y                           \n" +
						"                WHEN t3.y_logic1 = 'POINT_Y + GATE * PIXCEL_Y'                 \n" +
						"                   THEN t3.point_y + t2.gateline2 * t3.pixcel_y                \n" +
						"             END                                                               \n" +
						"      ELSE CASE                                                                \n" +
						"      WHEN t3.y_logic2 = 'POINT_Y + HEIGHT - GATE * PIXCEL_Y'                  \n" +
						"         THEN t3.point_y + t3.height - t2.gateline2 * t3.pixcel_y              \n" +
						"      WHEN t3.y_logic2 = 'POINT_Y + GATE * PIXCEL_Y'                           \n" +
						"         THEN t3.point_y + t2.gateline2 * t3.pixcel_y                          \n" +
						"    END                                                                        \n" +
						"    END y2_value                                                               \n" +
						"FROM temp1 t2, yms_dm.d_cellconvert t3, yms_dm.d_defect t4                     \n" +
						"WHERE t2.procid like t3.procid||'%%'                                           \n" +
						"    /** 2013.05.13 YEOM SUBSTR(t2.procid,1,7) = t3.procid  **/                 \n" +
						"    AND t2.cell_loc_id = t3.celllocid                                          \n" +
						"    AND t2.subitemid   = t4.DEFECTCD(+)                                        \n" +
						"    AND t2.siteid      = t4.siteid(+)                                          \n" +
						""
						, StrUtil.quote(Parameter.getInstance().getStrFromDateTime   ())
						, StrUtil.quote(Parameter.getInstance().getStrToDateTime     ())
						, StrUtil.quote(Parameter.getInstance().getStrLine           ())
						, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode  ())
						, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
						);

				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();
				
				if (Flag.TRUE) {
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData md = rs.getMetaData();

					for (int i=0; i < 1000 && rs.next(); i++) {
						
						StringBuffer sb = new StringBuffer();
						sb.append(String.format("(%4d)   ", i));
						
						for (int col=1; col <= md.getColumnCount(); col++) {
							sb.append("\t").append(rs.getString(col));
						}
						
						System.out.println(sb.toString());
					}
					
					System.out.println("------------- META DATA -----------------------");
					
					for (int i=1; i <= md.getColumnCount(); i++) {
						System.out.println(String.format("%2d) [%-30s] [%-50s]", i, md.getColumnName(i), md.getColumnClassName(i)));
					}
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
				if (!Flag.TRUE) System.out.println("[" + query + "]");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void line56MOD()
	{
		if (Flag.TRUE) {
			try {
				String query = String.format("" +
						"/* MURA_RAWDATA : L5FAB, L6FAB - MOD  */                                       \n" +
						"WITH temp1 as (                                                                \n" +
						"    SELECT /*+ PARALLEL(2) */                                                  \n" +
						"        d.procid, t1.siteid, t1.glassid AS CELLID,                             \n" +
						"        CASE                                                                   \n" +
						"            WHEN SUBSTR (t1.glassid, 8, 1) BETWEEN 'A' AND 'Z'                 \n" +
						"            THEN    SUBSTR (t1.glassid, 1, 7)|| ''|| SUBSTR (t1.glassid, 8,1)  \n" +
						"            ELSE    SUBSTR (t1.glassid, 1, 7)|| ''|| SUBSTR (t1.glassid, 8,2)  \n" +
						"            END GLASSID,                                                       \n" +
						"        d.prodgrpname, t1.prodid, orgstepid, measeqpunitid, dcoldate,          \n" +
						"        itemid, subitemid, datavalue,                                          \n" +
						"        code_x gateline, code_y dataline,                                      \n" +
						"        code_x2 gateline2, code_y2 dataline2,                                  \n" +
						"        t1.prodtype,                                                           \n" +
						"        CASE                                                                   \n" +
						"            WHEN SUBSTR (t1.glassid,8, 1) BETWEEN 'A' AND 'Z'                  \n" +
						"            THEN    SUBSTR (t1.glassid, -2)                                    \n" +
						"            ELSE    SUBSTR (t1.glassid, -1)                                    \n" +
						"            END  cell_loc_id  /* L7156A1 제외 */                               \n" +
						"    FROM yms_dm.h_meas_rundata_cell t1, yms_dm.d_product d                     \n" +
						"    WHERE t1.dcoldate > TO_DATE (%s, 'YYYYMMDDHH24MISS')                       \n" +  // FROM_ETL_DT
						"        AND t1.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')                     \n" +  // TO_ETL_DT
						"        AND t1.siteid = %s                                                     \n" +  // LINE
						"        AND t1.measstepgrpid = '7M020' /* 액정 like 'L%%' */                   \n" +
						"        AND t1.prodid = d.prodid                                               \n" +
						"        AND T1.PRODID IN ( %s )                                                \n" +  // USER_GROUP_CODE
						"        AND T1.ITEMID IN  ( %s )                                               \n" +  // DEFECT_GROUP_CODE
						")                                                                              \n" +
						"SELECT /*+ PARALLEL(2) */                                                      \n" +
						"    t2.PROCID                                                                  \n" +
						"    , T2.SITEID                                                                \n" +
						"    , T2.CELLID                                                                \n" +
						"    , T2.GLASSID                                                               \n" +
						"    , T2.PRODGRPNAME                                                           \n" +
						"    , T2.PRODID                                                                \n" +
						"    , T2.ORGSTEPID                                                             \n" +
						"    , T2.MEASEQPUNITID                                                         \n" +
						"    , T2.DCOLDATE as DCOLTIME                                                  \n" +
						"    , T2.ITEMID                                                                \n" +
						"    , T2.SUBITEMID                                                             \n" +
						"    , T4.DEFECTNAME                                                            \n" +
						"    , T2.DATAVALUE                                                             \n" +
						"    , T2.GATELINE                                                              \n" +
						"    , T2.DATALINE                                                              \n" +
						"    , T2.GATELINE2                                                             \n" +
						"    , T2.DATALINE2                                                             \n" +
						"    , T2.PRODTYPE                                                              \n" +
						"    , T2.CELL_LOC_ID                                                           \n" +
						"    , t3.width                                                                 \n" +
						"    , t3.height                                                                \n" +
						"    , CASE                                                                     \n" +
						"      WHEN t3.width - t3.height < 0                                            \n" +
						"         THEN CASE                                                             \n" +
						"                WHEN t3.x_logic1 = 'POINT_X + GATE * PIXCEL_X'                 \n" +
						"                   THEN t3.point_x + t2.gateline * t3.pixcel_x                 \n" +
						"                WHEN t3.x_logic1 = 'POINT_X + DATA * PIXCEL_X'                 \n" +
						"                   THEN t3.point_x + t2.dataline * t3.pixcel_x                 \n" +
						"             END                                                               \n" +
						"      ELSE CASE                                                                \n" +
						"      WHEN t3.x_logic2 = 'POINT_X + WIDTH - DATA * PIXCEL_X'                   \n" +
						"         THEN t3.point_x + t3.width - t2.dataline * t3.pixcel_x                \n" +
						"      WHEN t3.x_logic2 = 'POINT_X + DATA * PIXCEL_X'                           \n" +
						"         THEN t3.point_x + t2.dataline * t3.pixcel_x                           \n" +
						"    END                                                                        \n" +
						"    END x_value,                                                               \n" +
						"    CASE                                                                       \n" +
						"      WHEN t3.width - t3.height < 0                                            \n" +
						"         THEN CASE                                                             \n" +
						"                WHEN t3.y_logic1 =                                             \n" +
						"                             'POINT_Y + HEIGHT - DATA * PIXCEL_Y'              \n" +
						"                   THEN   t3.point_y                                           \n" +
						"                        + t3.height                                            \n" +
						"                        - t2.dataline * t3.pixcel_y                            \n" +
						"                WHEN t3.y_logic1 = 'POINT_Y + GATE * PIXCEL_Y'                 \n" +
						"                   THEN t3.point_y + t2.gateline * t3.pixcel_y                 \n" +
						"             END                                                               \n" +
						"      ELSE CASE                                                                \n" +
						"      WHEN t3.y_logic2 = 'POINT_Y + HEIGHT - GATE * PIXCEL_Y'                  \n" +
						"         THEN t3.point_y + t3.height - t2.gateline * t3.pixcel_y               \n" +
						"      WHEN t3.y_logic2 = 'POINT_Y + GATE * PIXCEL_Y'                           \n" +
						"         THEN t3.point_y + t2.gateline * t3.pixcel_y                           \n" +
						"    END                                                                        \n" +
						"    END y_value,                                                               \n" +
						"          CASE                                                                 \n" +
						"      WHEN t3.width - t3.height < 0                                            \n" +
						"         THEN CASE                                                             \n" +
						"                WHEN t3.x_logic1 = 'POINT_X + GATE * PIXCEL_X'                 \n" +
						"                   THEN t3.point_x + t2.gateline2 * t3.pixcel_x                \n" +
						"                WHEN t3.x_logic1 = 'POINT_X + DATA * PIXCEL_X'                 \n" +
						"                   THEN t3.point_x + t2.dataline2 * t3.pixcel_x                \n" +
						"             END                                                               \n" +
						"      ELSE CASE                                                                \n" +
						"      WHEN t3.x_logic2 = 'POINT_X + WIDTH - DATA * PIXCEL_X'                   \n" +
						"         THEN t3.point_x + t3.width - t2.dataline2 * t3.pixcel_x               \n" +
						"      WHEN t3.x_logic2 = 'POINT_X + DATA * PIXCEL_X'                           \n" +
						"         THEN t3.point_x + t2.dataline2 * t3.pixcel_x                          \n" +
						"    END                                                                        \n" +
						"    END x2_value,                                                              \n" +
						"    CASE                                                                       \n" +
						"      WHEN t3.width - t3.height < 0                                            \n" +
						"         THEN CASE                                                             \n" +
						"                WHEN t3.y_logic1 =                                             \n" +
						"                             'POINT_Y + HEIGHT - DATA * PIXCEL_Y'              \n" +
						"                   THEN   t3.point_y                                           \n" +
						"                        + t3.height                                            \n" +
						"                        - t2.dataline2 * t3.pixcel_y                           \n" +
						"                WHEN t3.y_logic1 = 'POINT_Y + GATE * PIXCEL_Y'                 \n" +
						"                   THEN t3.point_y + t2.gateline2 * t3.pixcel_y                \n" +
						"             END                                                               \n" +
						"      ELSE CASE                                                                \n" +
						"      WHEN t3.y_logic2 = 'POINT_Y + HEIGHT - GATE * PIXCEL_Y'                  \n" +
						"         THEN t3.point_y + t3.height - t2.gateline2 * t3.pixcel_y              \n" +
						"      WHEN t3.y_logic2 = 'POINT_Y + GATE * PIXCEL_Y'                           \n" +
						"         THEN t3.point_y + t2.gateline2 * t3.pixcel_y                          \n" +
						"    END                                                                        \n" +
						"    END y2_value                                                               \n" +
						"FROM temp1 t2, yms_dm.d_cellconvert t3, yms_dm.d_defect t4                     \n" +
						"WHERE t2.procid like t3.procid||'%%'                                           \n" +
						"    /** 2013.05.13 YEOM SUBSTR(t2.procid,1,7) = t3.procid  **/                 \n" +
						"    AND t2.cell_loc_id = t3.celllocid                                          \n" +
						"    AND t2.subitemid = t4.DEFECTCD(+)                                          \n" +
						"    AND t2.siteid = t4.siteid(+)                                               \n" +
						""
						, StrUtil.quote(Parameter.getInstance().getStrFromDateTime   ())
						, StrUtil.quote(Parameter.getInstance().getStrToDateTime     ())
						, StrUtil.quote(Parameter.getInstance().getStrLine           ())
						, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode  ())
						, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
						);

				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();
				
				if (Flag.TRUE) {
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData md = rs.getMetaData();

					for (int i=0; i < 200000 && rs.next(); i++) {
						
						StringBuffer sb = new StringBuffer();
						sb.append(String.format("(%4d)   ", i));
						
						for (int col=1; col <= md.getColumnCount(); col++) {
							sb.append("\t").append(rs.getString(col));
						}
						
						System.out.println(sb.toString());
					}
					
					System.out.println("------------- META DATA -----------------------");
					
					for (int i=1; i <= md.getColumnCount(); i++) {
						System.out.println(String.format("%2d) [%-30s] [%-50s]", i, md.getColumnName(i), md.getColumnClassName(i)));
					}
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
				if (!Flag.TRUE) System.out.println("[" + query + "]");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void line78LC()
	{
		if (Flag.TRUE) {
			try {
				String query = String.format("" +
						"/* MURA_RAWDATA : L7AFAB, L7BFAB, L8AFAB, L8ZFAB - LC */                              \n" +
						"WITH temp1 as (                                                                       \n" +
						"    SELECT /*+ PARALLEL(2) */                                                         \n" +
						"        t1.procid, t1.siteid, t1.glassid AS CELLID,                                   \n" +
						"        CASE                                                                          \n" +
						"            WHEN SUBSTR(t1.glassid,1,1) IN ('7','G') THEN SUBSTR(t1.glassid,1,9)      \n" +
						"            WHEN SUBSTR(t1.glassid,1,1) IN ('8','H') THEN SUBSTR(t1.glassid,1,8)      \n" +
						"            END glassid,                                                              \n" +
						"        d.prodgrpname, t1.prodid, orgstepid, measeqpunitid, dcoldate,                 \n" +
						"        itemid, subitemid, datavalue,                                                 \n" +
						"        code_x gateline, code_y dataline,                                             \n" +
						"        code_x2 gateline2, code_y2 dataline2,                                         \n" +
						"        t1.prodtype,                                                                  \n" +
						"        CASE                                                                          \n" +
						"            WHEN SUBSTR(t1.glassid,1,1) IN ('7','G') THEN SUBSTR (t1.glassid, 10, 1)  \n" +
						"            WHEN SUBSTR(t1.glassid,1,1) IN ('8','H') THEN SUBSTR (t1.glassid, 9, 2)   \n" +
						"            END cell_loc_id                                                           \n" +
						"    FROM yms_dm.h_meas_rundata_cell t1 , yms_dm.d_product d                           \n" +
						"    WHERE t1.dcoldate >= TO_DATE (%s, 'YYYYMMDDHH24MISS')                             \n" +  // FROM_ETL_DT
						"        AND t1.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')                            \n" +  // TO_ETL_DT
						"        AND t1.siteid = %s                                                            \n" +  // LINE
						"        AND t1.measstepgrpid like 'L%%'                                               \n" +
						"        AND T1.PROCID IN ( %s )                                                       \n" +  // USER_GROUP_CODE
						"        AND t1.ITEMID IN ( %s )                                                       \n" +  // DEFECT_GROUP_CODE
						"        and t1.prodid = d.prodid                                                      \n" +
						")                                                                                     \n" +
						"SELECT /*+ PARALLEL(2) */                                                             \n" +
						"    t2.PROCID                                                                         \n" +
						"    , T2.SITEID                                                                       \n" +
						"    , T2.CELLID                                                                       \n" +
						"    , T2.GLASSID                                                                      \n" +
						"    , T2.PRODGRPNAME                                                                  \n" +
						"    , T2.PRODID                                                                       \n" +
						"    , T2.ORGSTEPID                                                                    \n" +
						"    , T2.MEASEQPUNITID                                                                \n" +
						"    , T2.DCOLDATE as DCOLTIME                                                         \n" +
						"    , T2.ITEMID                                                                       \n" +
						"    , T2.SUBITEMID                                                                    \n" +
						"    , T4.DEFECTNAME                                                                   \n" +
						"    , T2.DATAVALUE                                                                    \n" +
						"    , T2.GATELINE                                                                     \n" +
						"    , T2.DATALINE                                                                     \n" +
						"    , T2.GATELINE2                                                                    \n" +
						"    , T2.DATALINE2                                                                    \n" +
						"    , T2.PRODTYPE                                                                     \n" +
						"    , T2.CELL_LOC_ID                                                                  \n" +
						"    , t3.width                                                                        \n" +
						"    , t3.height                                                                       \n" +
						"    , CASE                                                                            \n" +
						"        WHEN t3.width - t3.height < 0                                                 \n" +
						"          THEN CASE                                                                   \n" +
						"                 WHEN t3.x_logic1 = 'POINT_X + GATE * PIXCEL_X'                       \n" +
						"                    THEN t3.point_x + t2.gateline * t3.pixcel_x                       \n" +
						"                 WHEN t3.x_logic1 = 'POINT_X + DATA * PIXCEL_X'                       \n" +
						"                    THEN t3.point_x + t2.dataline * t3.pixcel_x                       \n" +
						"              END                                                                     \n" +
						"        ELSE CASE                                                                     \n" +
						"            WHEN t3.x_logic2 = 'POINT_X + WIDTH - DATA * PIXCEL_X'                    \n" +
						"              THEN t3.point_x + t3.width - t2.dataline * t3.pixcel_x                  \n" +
						"            WHEN t3.x_logic2 = 'POINT_X + DATA * PIXCEL_X'                            \n" +
						"              THEN t3.point_x + t2.dataline * t3.pixcel_x                             \n" +
						"            END                                                                       \n" +
						"        END x_value                                                                   \n" +
						"    , CASE                                                                            \n" +
						"        WHEN t3.width - t3.height < 0                                                 \n" +
						"        THEN CASE                                                                     \n" +
						"             WHEN t3.y_logic1 =                                                       \n" +
						"                          'POINT_Y + HEIGHT - DATA * PIXCEL_Y'                        \n" +
						"                THEN   t3.point_y                                                     \n" +
						"                     + t3.height                                                      \n" +
						"                     - t2.dataline * t3.pixcel_y                                      \n" +
						"             WHEN t3.y_logic1 = 'POINT_Y + GATE * PIXCEL_Y'                           \n" +
						"                THEN t3.point_y + t2.gateline * t3.pixcel_y                           \n" +
						"          END                                                                         \n" +
						"        ELSE CASE                                                                     \n" +
						"            WHEN t3.y_logic2 = 'POINT_Y + HEIGHT - GATE * PIXCEL_Y'                   \n" +
						"                THEN t3.point_y + t3.height - t2.gateline * t3.pixcel_y               \n" +
						"            WHEN t3.y_logic2 = 'POINT_Y + GATE * PIXCEL_Y'                            \n" +
						"                THEN t3.point_y + t2.gateline * t3.pixcel_y                           \n" +
						"            END                                                                       \n" +
						"        END y_value                                                                   \n" +
						"    , CASE                                                                            \n" +
						"        WHEN t3.width - t3.height < 0                                                 \n" +
						"          THEN CASE                                                                   \n" +
						"                 WHEN t3.x_logic1 = 'POINT_X + GATE * PIXCEL_X'                       \n" +
						"                    THEN t3.point_x + t2.gateline2 * t3.pixcel_x                      \n" +
						"                 WHEN t3.x_logic1 = 'POINT_X + DATA * PIXCEL_X'                       \n" +
						"                    THEN t3.point_x + t2.dataline2 * t3.pixcel_x                      \n" +
						"              END                                                                     \n" +
						"        ELSE CASE                                                                     \n" +
						"            WHEN t3.x_logic2 = 'POINT_X + WIDTH - DATA * PIXCEL_X'                    \n" +
						"              THEN t3.point_x + t3.width - t2.dataline2 * t3.pixcel_x                 \n" +
						"            WHEN t3.x_logic2 = 'POINT_X + DATA * PIXCEL_X'                            \n" +
						"              THEN t3.point_x + t2.dataline2 * t3.pixcel_x                            \n" +
						"            END                                                                       \n" +
						"        END x2_value                                                                  \n" +
						"    , CASE                                                                            \n" +
						"        WHEN t3.width - t3.height < 0                                                 \n" +
						"        THEN CASE                                                                     \n" +
						"          WHEN t3.y_logic1 =                                                          \n" +
						"                      'POINT_Y + HEIGHT - DATA * PIXCEL_Y'                            \n" +
						"            THEN   t3.point_y                                                         \n" +
						"                 + t3.height                                                          \n" +
						"                 - t2.dataline2 * t3.pixcel_y                                         \n" +
						"          WHEN t3.y_logic1 = 'POINT_Y + GATE * PIXCEL_Y'                              \n" +
						"                        THEN t3.point_y + t2.gateline2 * t3.pixcel_y                  \n" +
						"          END                                                                         \n" +
						"        ELSE CASE                                                                     \n" +
						"           WHEN t3.y_logic2 = 'POINT_Y + HEIGHT - GATE * PIXCEL_Y'                    \n" +
						"              THEN t3.point_y + t3.height - t2.gateline2 * t3.pixcel_y                \n" +
						"           WHEN t3.y_logic2 = 'POINT_Y + GATE * PIXCEL_Y'                             \n" +
						"              THEN t3.point_y + t2.gateline2 * t3.pixcel_y                            \n" +
						"           END                                                                        \n" +
						"        END y2_value                                                                  \n" +
						"FROM temp1 t2, yms_dm.d_cellconvert t3, yms_dm.d_defect t4                            \n" +
						"WHERE t2.procid like t3.procid || '%%'                                                \n" +
						"    /* 변경요청들어옴(PROCID문제) 2013.04.20 CHOI TO YEOM */                          \n" +
						"    /* SUBSTR(t2.procid,1,7) = t3.procid  */                                          \n" +
						"    /* T3.PROCID 에서 변경 D_CELLCONVER 테이블 8자리 PROCID 존재 2013.04.12 YEOM */   \n" +
						"    AND t2.cell_loc_id = t3.celllocid                                                 \n" +
						"    AND t2.subitemid   = t4.DEFECTCD(+)                                               \n" +
						"    AND t2.siteid      = t4.siteid(+) /* 탕정 site, 천안은 변경 필요 */               \n" +
						""
						, StrUtil.quote(Parameter.getInstance().getStrFromDateTime   ())
						, StrUtil.quote(Parameter.getInstance().getStrToDateTime     ())
						, StrUtil.quote(Parameter.getInstance().getStrLine           ())
						, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode  ())
						, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
						);

				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();
				
				if (Flag.TRUE) {
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData md = rs.getMetaData();

					for (int i=0; i < 10000 && rs.next(); i++) {
						
						StringBuffer sb = new StringBuffer();
						sb.append(String.format("(%4d)   ", i));
						
						for (int col=1; col <= md.getColumnCount(); col++) {
							sb.append("\t").append(rs.getString(col));
						}
						
						System.out.println(sb.toString());
					}
					
					System.out.println("------------- META DATA -----------------------");
					
					for (int i=1; i <= md.getColumnCount(); i++) {
						System.out.println(String.format("%2d) [%-30s] [%-50s]", i, md.getColumnName(i), md.getColumnClassName(i)));
					}
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
				if (!Flag.TRUE) System.out.println("[" + query + "]");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void line78MOD()
	{
		if (Flag.TRUE) {
			try {
				String query = String.format("" +
						"/* MURA_RAWDATA : L7AFAB, L7BFAB, L8AFAB, L8ZFAB - MOD */                             \n" +
						"WITH temp1 AS (                                                                       \n" +
						"    SELECT  /*+ PARALLEL(2) */                                                        \n" +
						"        d.procid, t1.siteid, glassid as cellid,                                       \n" +
						"        CASE                                                                          \n" +
						"            WHEN SUBSTR(t1.glassid,1,1) IN ('7','G') THEN SUBSTR(t1.glassid,1,9)      \n" +
						"            WHEN SUBSTR(t1.glassid,1,1) IN ('8','H') THEN SUBSTR(t1.glassid,1,8)      \n" +
						"            END glassid,                                                              \n" +
						"        d.prodgrpname, t1.prodid, orgstepid, measeqpunitid, dcoldate,                 \n" +
						"        itemid, subitemid, datavalue,                                                 \n" +
						"        code_x gateline, code_y dataline,                                             \n" +
						"        code_x2 gateline2, code_y2 dataline2,                                         \n" +
						"        t1.prodtype,                                                                  \n" +
						"        CASE                                                                          \n" +
						"            WHEN SUBSTR(t1.glassid,1,1) IN ('7','G') THEN SUBSTR (t1.glassid, 10, 1)  \n" +
						"            WHEN SUBSTR(t1.glassid,1,1) IN ('8','H') THEN SUBSTR (t1.glassid, 9, 2)   \n" +
						"            END cell_loc_id                                                           \n" +
						"    FROM yms_dm.h_meas_rundata_cell t1, yms_dm.d_product d                            \n" +
						"    WHERE t1.dcoldate > TO_DATE (%s, 'YYYYMMDDHH24MISS')                              \n" +  // FROM_ETL_DT
						"        AND t1.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')                            \n" +  // TO_ETL_DT
						"        AND t1.siteid = %s                                                            \n" +  // LINE
						"        AND t1.measstepgrpid = '7M020' /* 액정 like 'L%%' */                          \n" +
						"        AND t1.prodid = d.prodid                                                      \n" +
						"        AND T1.PRODID IN ( %s )                                                       \n" +  // USER_GROUP_CODE
						"        AND T1.ITEMID IN  ( %s )                                                      \n" +  // DEFECT_GROUP_CODE
						")                                                                                     \n" +
						"SELECT /*+ PARALLEL(2) */                                                             \n" +
						"    t2.PROCID                                                                         \n" +
						"    , T2.SITEID                                                                       \n" +
						"    , T2.CELLID                                                                       \n" +
						"    , T2.GLASSID                                                                      \n" +
						"    , T2.PRODGRPNAME                                                                  \n" +
						"    , T2.PRODID                                                                       \n" +
						"    , T2.ORGSTEPID                                                                    \n" +
						"    , T2.MEASEQPUNITID                                                                \n" +
						"    , T2.DCOLDATE as DCOLTIME                                                         \n" +
						"    , T2.ITEMID                                                                       \n" +
						"    , T2.SUBITEMID                                                                    \n" +
						"    , T4.DEFECTNAME                                                                   \n" +
						"    , T2.DATAVALUE                                                                    \n" +
						"    , T2.GATELINE                                                                     \n" +
						"    , T2.DATALINE                                                                     \n" +
						"    , T2.GATELINE2                                                                    \n" +
						"    , T2.DATALINE2                                                                    \n" +
						"    , T2.PRODTYPE                                                                     \n" +
						"    , T2.CELL_LOC_ID                                                                  \n" +
						"    , t3.width                                                                        \n" +
						"    , t3.height                                                                       \n" +
						"    , CASE                                                                            \n" +
						"        WHEN t3.width - t3.height < 0                                                 \n" +
						"            THEN CASE                                                                 \n" +
						"                WHEN t3.x_logic1 = 'POINT_X + GATE * PIXCEL_X'                        \n" +
						"                    THEN t3.point_x + t2.gateline * t3.pixcel_x                       \n" +
						"                WHEN t3.x_logic1 = 'POINT_X + DATA * PIXCEL_X'                        \n" +
						"                    THEN t3.point_x + t2.dataline * t3.pixcel_x                       \n" +
						"                END                                                                   \n" +
						"        ELSE CASE                                                                     \n" +
						"            WHEN t3.x_logic2 = 'POINT_X + WIDTH - DATA * PIXCEL_X'                    \n" +
						"                THEN t3.point_x + t3.width - t2.dataline * t3.pixcel_x                \n" +
						"            WHEN t3.x_logic2 = 'POINT_X + DATA * PIXCEL_X'                            \n" +
						"                THEN t3.point_x + t2.dataline * t3.pixcel_x                           \n" +
						"            END                                                                       \n" +
						"        END x_value                                                                   \n" +
						"    , CASE                                                                            \n" +
						"        WHEN t3.width - t3.height < 0                                                 \n" +
						"        THEN CASE                                                                     \n" +
						"            WHEN t3.y_logic1 =                                                        \n" +
						"            'POINT_Y + HEIGHT - DATA * PIXCEL_Y'                                      \n" +
						"                THEN   t3.point_y                                                     \n" +
						"                + t3.height                                                           \n" +
						"                - t2.dataline * t3.pixcel_y                                           \n" +
						"            WHEN t3.y_logic1 = 'POINT_Y + GATE * PIXCEL_Y'                            \n" +
						"                THEN t3.point_y + t2.gateline * t3.pixcel_y                           \n" +
						"            END                                                                       \n" +
						"        ELSE CASE                                                                     \n" +
						"            WHEN t3.y_logic2 = 'POINT_Y + HEIGHT - GATE * PIXCEL_Y'                   \n" +
						"                THEN t3.point_y + t3.height - t2.gateline * t3.pixcel_y               \n" +
						"            WHEN t3.y_logic2 = 'POINT_Y + GATE * PIXCEL_Y'                            \n" +
						"                THEN t3.point_y + t2.gateline * t3.pixcel_y                           \n" +
						"            END                                                                       \n" +
						"        END y_value                                                                   \n" +
						"    , CASE                                                                            \n" +
						"        WHEN t3.width - t3.height < 0                                                 \n" +
						"        THEN CASE                                                                     \n" +
						"            WHEN t3.x_logic1 = 'POINT_X + GATE * PIXCEL_X'                            \n" +
						"                THEN t3.point_x + t2.gateline2 * t3.pixcel_x                          \n" +
						"            WHEN t3.x_logic1 = 'POINT_X + DATA * PIXCEL_X'                            \n" +
						"                THEN t3.point_x + t2.dataline2 * t3.pixcel_x                          \n" +
						"            END                                                                       \n" +
						"        ELSE CASE                                                                     \n" +
						"            WHEN t3.x_logic2 = 'POINT_X + WIDTH - DATA * PIXCEL_X'                    \n" +
						"                THEN t3.point_x + t3.width - t2.dataline2 * t3.pixcel_x               \n" +
						"            WHEN t3.x_logic2 = 'POINT_X + DATA * PIXCEL_X'                            \n" +
						"                THEN t3.point_x + t2.dataline2 * t3.pixcel_x                          \n" +
						"            END                                                                       \n" +
						"        END x2_value                                                                  \n" +
						"    , CASE                                                                            \n" +
						"        WHEN t3.width - t3.height < 0                                                 \n" +
						"        THEN CASE                                                                     \n" +
						"            WHEN t3.y_logic1 =                                                        \n" +
						"            'POINT_Y + HEIGHT - DATA * PIXCEL_Y'                                      \n" +
						"                THEN   t3.point_y                                                     \n" +
						"                + t3.height                                                           \n" +
						"                - t2.dataline2 * t3.pixcel_y                                          \n" +
						"            WHEN t3.y_logic1 = 'POINT_Y + GATE * PIXCEL_Y'                            \n" +
						"                THEN t3.point_y + t2.gateline2 * t3.pixcel_y                          \n" +
						"            END                                                                       \n" +
						"        ELSE CASE                                                                     \n" +
						"            WHEN t3.y_logic2 = 'POINT_Y + HEIGHT - GATE * PIXCEL_Y'                   \n" +
						"                THEN t3.point_y + t3.height - t2.gateline2 * t3.pixcel_y              \n" +
						"            WHEN t3.y_logic2 = 'POINT_Y + GATE * PIXCEL_Y'                            \n" +
						"                THEN t3.point_y + t2.gateline2 * t3.pixcel_y                          \n" +
						"            END                                                                       \n" +
						"        END y2_value                                                                  \n" +
						"FROM temp1 t2, yms_dm.d_cellconvert t3, yms_dm.d_defect t4                            \n" +
						"WHERE t2.procid like t3.procid || '%%'                                                \n" +
						"    /* 변경요청들어옴(PROCID문제) 2013.04.20 CHOI TO YEOM */                          \n" +
						"    /* SUBSTR(t2.procid,1,7) = t3.procid */                                           \n" +
						"    /* T3.PROCID 에서 변경 D_CELLCONVER 테이블 8자리 PROCID 존재 2013.04.12 YEOM */   \n" +
						"    AND t2.cell_loc_id = t3.celllocid                                                 \n" +
						"    AND t2.subitemid = t4.DEFECTCD(+)                                                 \n" +
						"    AND t2.siteid = t4.siteid(+) /* 탕정 site, 천안은 변경 필요 */                    \n" +
						""
						, StrUtil.quote(Parameter.getInstance().getStrFromDateTime   ())
						, StrUtil.quote(Parameter.getInstance().getStrToDateTime     ())
						, StrUtil.quote(Parameter.getInstance().getStrLine           ())
						, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode  ())
						, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
						);

				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();
				
				if (Flag.TRUE) {
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData md = rs.getMetaData();

					for (int i=0; i < 10000 && rs.next(); i++) {
						
						StringBuffer sb = new StringBuffer();
						sb.append(String.format("(%4d)   ", i));
						
						for (int col=1; col <= md.getColumnCount(); col++) {
							sb.append("\t").append(rs.getString(col));
						}
						
						System.out.println(sb.toString());
					}
					
					System.out.println("------------- META DATA -----------------------");
					
					for (int i=1; i <= md.getColumnCount(); i++) {
						System.out.println(String.format("%2d) [%-30s] [%-50s]", i, md.getColumnName(i), md.getColumnClassName(i)));
					}
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
				if (!Flag.TRUE) System.out.println("[" + query + "]");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args)
	{
		if (!Flag.TRUE) {
			if (Flag.TRUE) line56LC();    // L5FAB, L6FAB - LC
			if (!Flag.TRUE) line56MOD();   // L5FAB, L6FAB - MOD
			if (!Flag.TRUE) line78LC();    // L7AFAB, L7BFAB, L8AFAB, L8ZFAB - LC
			if (!Flag.TRUE) line78MOD();   // L7AFAB, L7BFAB, L8AFAB, L8ZFAB - MOD
		}

		if (Flag.TRUE) {
			String strLine = Parameter.getInstance().getStrLine();
			String strAreaCode = Parameter.getInstance().getStrAreaCode();
			
			if ("L5FAB".equals(strLine) || "L6FAB".equals(strLine)) {
				if ("LC".equals(strAreaCode)) {
					line56LC();
				} else if ("MOD".equals(strAreaCode)) {
					line56MOD();
				}
			} else if ("L7AFAB".equals(strLine) || "L7BFAB".equals(strLine) || "L8AFAB".equals(strLine) || "L8ZFAB".equals(strLine)) {
				if ("LC".equals(strAreaCode)) {
					line78LC();
				} else if ("MOD".equals(strAreaCode)) {
					line78MOD();
				}
			}
		}
	}
}
