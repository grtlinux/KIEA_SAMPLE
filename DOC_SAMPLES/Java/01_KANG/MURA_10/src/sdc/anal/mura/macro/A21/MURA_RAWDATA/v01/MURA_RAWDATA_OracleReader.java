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

package sdc.anal.mura.macro.A21.MURA_RAWDATA.v01;

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
 * @file_name MURA_RAWDATA_OracleReader.java
 *
 */
public class MURA_RAWDATA_OracleReader extends AbstractOracleReader
{
	private List<MURA_RAWDATA_ReadBean> list = new ArrayList<MURA_RAWDATA_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public MURA_RAWDATA_OracleReader()
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

		return query;
	}
	
	/**
	 * getList
	 */
	@Override
	public List<MURA_RAWDATA_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<MURA_RAWDATA_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<MURA_RAWDATA_ReadBean>();
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
				
				OracleConnection conn = Connection04.getInstance().getConn();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i < 10000000 && rs.next(); i++) {
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
							System.out.println(String.format("    DCOLTIME      = [%s]", rs.getString("DCOLTIME"     )));
							System.out.println(String.format("    ITEMID        = [%s]", rs.getString("ITEMID"       )));
							System.out.println(String.format("    SUBITEMID     = [%s]", rs.getString("SUBITEMID"    )));
							System.out.println(String.format("    DEFECTNAME    = [%s]", rs.getString("DEFECTNAME"   )));
							System.out.println(String.format("    DATAVALUE     = [%s]", rs.getString("DATAVALUE"    )));
							System.out.println(String.format("    GATELINE      = [%s]", rs.getString("GATELINE"     )));
							System.out.println(String.format("    DATALINE      = [%s]", rs.getString("DATALINE"     )));
							System.out.println(String.format("    GATELINE2     = [%s]", rs.getString("GATELINE2"    )));
							System.out.println(String.format("    DATALINE2     = [%s]", rs.getString("DATALINE2"    )));
							System.out.println(String.format("    PRODTYPE      = [%s]", rs.getString("PRODTYPE"     )));
							System.out.println(String.format("    CELL_LOC_ID   = [%s]", rs.getString("CELL_LOC_ID"  )));
							System.out.println(String.format("    WIDTH         = [%s]", rs.getString("WIDTH"        )));
							System.out.println(String.format("    HEIGHT        = [%s]", rs.getString("HEIGHT"       )));
							System.out.println(String.format("    X_VALUE       = [%s]", rs.getString("X_VALUE"      )));
							System.out.println(String.format("    Y_VALUE       = [%s]", rs.getString("Y_VALUE"      )));
							System.out.println(String.format("    X2_VALUE      = [%s]", rs.getString("X2_VALUE"     )));
							System.out.println(String.format("    Y2_VALUE      = [%s]", rs.getString("Y2_VALUE"     )));
						}
						
						if (Flag.TRUE) {
							MURA_RAWDATA_ReadBean bean = new MURA_RAWDATA_ReadBean();

							bean.setProcId       (rs.getString("PROCID"       ));
							bean.setSiteId       (rs.getString("SITEID"       ));
							bean.setCellId       (rs.getString("CELLID"       ));
							bean.setGlassId      (rs.getString("GLASSID"      ));
							bean.setProdGrpName  (rs.getString("PRODGRPNAME"  ));
							bean.setProdId       (rs.getString("PRODID"       ));
							bean.setOrgStepId    (rs.getString("ORGSTEPID"    ));
							bean.setMeasEqpUnitId(rs.getString("MEASEQPUNITID"));
							bean.setDcolTime     (rs.getString("DCOLTIME"     ));
							bean.setItemId       (rs.getString("ITEMID"       ));
							bean.setSubItemId    (rs.getString("SUBITEMID"    ));
							bean.setDefectName   (rs.getString("DEFECTNAME"   ));
							bean.setDataValue    (rs.getString("DATAVALUE"    ));
							bean.setGateLine     (rs.getString("GATELINE"     ));
							bean.setDataLine     (rs.getString("DATALINE"     ));
							bean.setGateLine2    (rs.getString("GATELINE2"    ));
							bean.setDataLine2    (rs.getString("DATALINE2"    ));
							bean.setProdType     (rs.getString("PRODTYPE"     ));
							bean.setCellLocId    (rs.getString("CELL_LOC_ID"  ));
							bean.setWidth        (rs.getString("WIDTH"        ));
							bean.setHeight       (rs.getString("HEIGHT"       ));
							bean.setXValue       (rs.getString("X_VALUE"      ));
							bean.setYValue       (rs.getString("Y_VALUE"      ));
							bean.setX2Value      (rs.getString("X2_VALUE"     ));
							bean.setY2Value      (rs.getString("Y2_VALUE"     ));
							
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
				Logger.getInstance(MURA_RAWDATA_Main.jobId, MURA_RAWDATA_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(MURA_RAWDATA_Main.jobId);
			
			MURA_RAWDATA_OracleReader reader = new MURA_RAWDATA_OracleReader();
			
			for (MURA_RAWDATA_ReadBean bean : reader.getList(true)) {
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
