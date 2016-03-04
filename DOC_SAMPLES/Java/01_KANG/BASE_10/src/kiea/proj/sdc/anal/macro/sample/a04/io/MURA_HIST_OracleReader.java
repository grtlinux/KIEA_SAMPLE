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
import kiea.proj.sdc.anal.macro.sample.a04.bean.MURA_HIST_ReadBean;
import kiea.proj.sdc.anal.util.StrUtil;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MURA_HIST_OracleReader.java
 *
 */
public class MURA_HIST_OracleReader extends AbstractOracleReader
{
	private List<MURA_HIST_ReadBean> list = new ArrayList<MURA_HIST_ReadBean>();
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	/**
	 * Sample02OracleReader
	 */
	public MURA_HIST_OracleReader(String strFromDateTime, String strToDateTime, String strLine, String strAreaCode, String strUserGroupCode, String strDefectGroupCode)
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
				"/* MURA_HIST : L5FAB, L6FAB - LC */                                                              \n" +
				"WITH insp_temp1 AS (                                                                             \n" +
				"    SELECT /*+ PARALLEL(2) */                                                                    \n" +
				"        DISTINCT                                                                                 \n" +
				"        CASE                                                                                     \n" +
				"            WHEN SUBSTR (t1.glassid, 8, 1) BETWEEN 'A' AND 'Z'                                   \n" +
				"            THEN SUBSTR (t1.glassid, 1, 7)|| ''|| SUBSTR (t1.glassid, 8)                         \n" +
				"            ELSE SUBSTR (t1.glassid, 1, 7)|| ''|| SUBSTR (t1.glassid, 8,2)                       \n" +
				"        END GLASSID,                                                                             \n" +
				"        t1.siteid                                                                                \n" +
				"    FROM yms_dm.h_meas_rundata_cell t1, yms_dm.d_product d                                       \n" +
				"    WHERE t1.dcoldate >= TO_DATE (%s, 'YYYYMMDDHH24MISS')                                        \n" +   // FROM_ETL_DT
				"        AND t1.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')                                       \n" +   // TO_ETL_DT
				"        AND t1.siteid = %s                                                                       \n" +   // LINE
				"        AND t1.orgstepid like 'L__080'                                                           \n" +
				"        AND T1.PROCID IN ( %s )                                                                  \n" +   // USER_GROUP_CODE
				"        AND t1.ITEMID IN ( %s )                                                                  \n" +   // DEFECT_GROUP_CODE
				"        AND t1.prodid = d.prodid                                                                 \n" +
				")                                                                                                \n" +
				"SELECT /*+ ORDERED USE_NL(T2 T3) INDEX(T2 DW_GLASSCELL_EVENTHIST_PK) INDEX(T3 DW_MD_STEP_PK) */  \n" +
				"    T2.OWNINGSITEID                      AS LINE_CODE,                                           \n" +
				"    T2.GLASSCELLID                       AS GLASS_ID,                                            \n" +
				"    CASE WHEN T2.WORKERID = 'FDC' THEN 'FDC' ELSE 'SPC' END EVENT_OCCUR_CODE,                    \n" +
				"    CASE WHEN T2.WORKERID = 'FDC' THEN 'FDC' ELSE 'SPC' END EVENT_OCCUR_DETIAL_CODE,             \n" +
				"    T2.STEPID                            AS STEP_ID,                                             \n" +
				"    CASE                                                                                         \n" +
				"        WHEN T2.STEPID LIKE '%%BANK'  THEN T2.STEPID                                             \n" +
				"        WHEN T2.STEPID LIKE '%%STOCK' THEN T2.STEPID                                             \n" +
				"        WHEN T2.STEPID LIKE '%%PACK'  THEN T2.STEPID                                             \n" +
				"        WHEN T2.STEPID LIKE 'SM010'  THEN T2.STEPID                                              \n" +
				"        ELSE SUBSTR(T2.STEPID,1,1)||'__'||SUBSTR(T2.STEPID,4,3)                                  \n" +
				"    END                                  AS MAINSTEPID,                                          \n" +
				"    T2.EQPID                             AS EQP_ID,                                              \n" +
				"    T2.COMMENTDESC                       AS COMMENTS,                                            \n" +
				"    T2.GLASSSTATUS                       AS WORK_STATUS,                                         \n" +
				"    T2.EVENTVALUE                        AS EVENT_OCCUR_ID,                                      \n" +
				"    T2.EVENTDATE                         AS EVENT_OCCUR_DT                                       \n" +
				"FROM insp_temp1 T1, YMS_DW.DW_GLASSCELL_EVENTHIST  T2 , YMS_DW.DW_MD_STEP T3                     \n" +
				"WHERE 1=1                                                                                        \n" +
				"    AND T1.SITEID     = T2.SITEID                                                                \n" +
				"    AND T1.GLASSID    = T2.GLASSCELLID                                                           \n" +
				"    AND T2.ACTIVITY   = 'Hold'                                                                   \n" +
				"    AND T2.WORKERID   IN ('FDC','SPC')                                                           \n" +
				"    AND T2.SITEID     = T3.SOURCESITEID                                                          \n" +
				"    AND T2.PROCESSID  = T3.PROCESSID                                                             \n" +
				"    AND T2.STEPID     = T3.STEPID                                                                \n" +
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
				"/* MURA_HIST : L7AFAB, L7BFAB, L8AFAB, L8ZFAB - LC */                                           \n" +
				"WITH insp_temp1 AS (                                                                            \n" +
				"    SELECT  /*+ PARALLEL(2) */                                                                  \n" +
				"        DISTINCT                                                                                \n" +
				"        CASE                                                                                    \n" +
				"            WHEN SUBSTR (t1.glassid, 1, 1) IN ('7', 'G')                                        \n" +
				"                THEN SUBSTR (t1.glassid, 1, 9)                                                  \n" +
				"            WHEN SUBSTR (t1.glassid, 1, 1) IN ('8', 'H')                                        \n" +
				"                THEN SUBSTR (t1.glassid, 1, 8)                                                  \n" +
				"        END glassid,                                                                            \n" +
				"        /* glassid 구하는 방법 변경 2013.04.17 CHOI TO YEOM */                                  \n" +
				"        /* CASE                                             */                                  \n" +
				"        /*   WHEN TO_NUMBER (d.inch) <= 18                  */                                  \n" +
				"        /*      THEN SUBSTR (glassid, 1, 9)                 */                                  \n" +
				"        /*   ELSE SUBSTR (glassid, 1, 8)                    */                                  \n" +
				"        /* END glassid                                      */                                  \n" +
				"        t1.siteid                                                                               \n" +
				"    FROM yms_dm.h_meas_rundata_cell t1, yms_dm.d_product d                                      \n" +
				"    WHERE t1.dcoldate >=TO_DATE(%s, 'YYYYMMDDHH24MISS')                                         \n" +   // FROM_ETL_DT
				"        AND t1.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')                                      \n" +   // TO_ETL_DT
				"        AND t1.siteid = %s                                                                      \n" +   // LINE
				"        AND t1.measstepgrpid LIKE 'L%%'                                                         \n" +
				"        AND t1.procid IN ( %s )                                                                 \n" +   // USER_GROUP_CODE
				"        AND t1.itemid IN ( %s )                                                                 \n" +   // DEFECT_GROUP_CODE
				"        AND t1.prodid = d.prodid                                                                \n" +
				")                                                                                               \n" +
				"SELECT /*+ ORDERED USE_NL(T2 T3) INDEX(T2 DW_GLASSCELL_EVENTHIST_PK) INDEX(T3 DW_MD_STEP_PK) */ \n" +
				"    t2.owningsiteid AS line_code, t2.glasscellid AS glass_id,                                   \n" +
				"    CASE WHEN t2.workerid = 'FDC' THEN 'FDC'  ELSE 'SPC' END event_occur_code,                  \n" +
				"    CASE WHEN t2.workerid = 'FDC' THEN 'FDC'  ELSE 'SPC' END event_occur_detial_code,           \n" +
				"    t2.stepid AS step_id,                                                                       \n" +
				"    CASE                                                                                        \n" +
				"        WHEN t2.stepid LIKE '%%BANK' THEN t2.stepid                                             \n" +
				"        WHEN t2.stepid LIKE '%%STOCK' THEN t2.stepid                                            \n" +
				"        WHEN t2.stepid LIKE '%%PACK' THEN t2.stepid                                             \n" +
				"        WHEN t2.stepid LIKE 'SM010' THEN t2.stepid                                              \n" +
				"        ELSE    SUBSTR (t2.stepid, 1, 1)|| '__'|| SUBSTR (t2.stepid, 4, 3)                      \n" +
				"    END AS mainstepid,                                                                          \n" +
				"    t2.eqpid AS eqp_id,                                                                         \n" +
				"    t2.commentdesc AS comments,                                                                 \n" +
				"    t2.glassstatus AS work_status,                                                              \n" +
				"    t2.eventvalue AS event_occur_id,                                                            \n" +
				"    t2.eventdate AS event_occur_dt                                                              \n" +
				"FROM insp_temp1 t1, yms_dw.dw_glasscell_eventhist t2, yms_dw.dw_md_step t3                      \n" +
				"WHERE 1 = 1                                                                                     \n" +
				"    AND t1.siteid = t2.siteid                                                                   \n" +
				"    AND t1.glassid = t2.glasscellid                                                             \n" +
				"    AND t2.activity = 'Hold'                                                                    \n" +
				"    AND t2.workerid IN ('FDC', 'SPC')                                                           \n" +
				"    AND t2.siteid = t3.sourcesiteid                                                             \n" +
				"    AND t2.processid = t3.processid                                                             \n" +
				"    AND t2.stepid = t3.stepid                                                                   \n" +
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
				"/* MURA_HIST : L7AFAB, L7BFAB, L8AFAB, L8ZFAB - MOD */                                             \n" +
				"WITH insp_temp1 AS (                                                                               \n" +
				"    SELECT /*+ PARALLEL(2) */                                                                      \n" +
				"        DISTINCT                                                                                   \n" +
				"        CASE                                                                                       \n" +
				"            WHEN SUBSTR(t1.glassid,1,1) IN ('7','G') THEN SUBSTR(t1.glassid,1,9)                   \n" +
				"            WHEN SUBSTR(t1.glassid,1,1) IN ('8','H') THEN SUBSTR(t1.glassid,1,8)                   \n" +
				"        END glassid,                                                                               \n" +
				"        t1.siteid                                                                                  \n" +
				"    FROM yms_dm.h_meas_rundata_cell t1, yms_dm.d_product d                                         \n" +
				"    WHERE t1.dcoldate >= TO_DATE (%s, 'YYYYMMDDHH24MISS')                                          \n" +   // FROM_ETL_DT
				"        AND t1.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')                                         \n" +   // TO_ETL_DT
				"        AND t1.siteid = %s                                                                         \n" +   // LINE
				"        AND t1.measstepgrpid = '7M020'                                                             \n" +
				"        AND T1.PROCID IN ( %s )                                                                    \n" +   // USER_GROUP_CODE
				"        AND t1.ITEMID IN ( %s )                                                                    \n" +   // DEFECT_GROUP_CODE
				"        AND t1.prodid = d.prodid                                                                   \n" +
				")                                                                                                  \n" +
				"SELECT /*+ ORDERED USE_NL(T2 T3) INDEX(T2 DW_MF_GLASSCELL_EVENTHIST_PK) INDEX(T3 DW_MD_STEP_PK) */ \n" +
				"    T2.OWNINGSITEID                      AS LINE_CODE,                                             \n" +
				"    T2.GLASSCELLID                       AS GLASS_ID,                                              \n" +
				"    CASE WHEN T2.WORKERID = 'FDC' THEN 'FDC' ELSE 'SPC' END EVENT_OCCUR_CODE,                      \n" +
				"    CASE WHEN T2.WORKERID = 'FDC' THEN 'FDC' ELSE 'SPC' END EVENT_OCCUR_DETIAL_CODE,               \n" +
				"    T2.STEPID                            AS STEP_ID,                                               \n" +
				"    CASE                                                                                           \n" +
				"        WHEN T2.STEPID LIKE '%%BANK'  THEN T2.STEPID                                               \n" +
				"        WHEN T2.STEPID LIKE '%%STOCK' THEN T2.STEPID                                               \n" +
				"        WHEN T2.STEPID LIKE '%%PACK'  THEN T2.STEPID                                               \n" +
				"        WHEN T2.STEPID LIKE 'SM010'  THEN T2.STEPID                                                \n" +
				"        ELSE SUBSTR(T2.STEPID,1,1)||'__'||SUBSTR(T2.STEPID,4,3)                                    \n" +
				"    END                                  AS MAINSTEPID,                                            \n" +
				"    T2.EQPID                             AS EQP_ID,                                                \n" +
				"    T2.COMMENTDESC                       AS COMMENTS,                                              \n" +
				"    T2.GLASSSTATUS                       AS WORK_STATUS,                                           \n" +
				"    T2.EVENTVALUE                        AS EVENT_OCCUR_ID,                                        \n" +
				"    T2.EVENTDATE                         AS EVENT_OCCUR_DT                                         \n" +
				"FROM insp_temp1 T1, YMS_DW.DW_GLASSCELL_EVENTHIST  T2 , YMS_DW.DW_MD_STEP T3                       \n" +
				"WHERE 1=1                                                                                          \n" +
				"    AND T1.SITEID     = T2.SITEID                                                                  \n" +
				"    AND T1.GLASSID    = T2.GLASSCELLID                                                             \n" +
				"    AND T2.ACTIVITY   = 'Hold'                                                                     \n" +
				"    AND T2.WORKERID   IN ('FDC','SPC')                                                             \n" +
				"    AND T2.SITEID     = T3.SOURCESITEID                                                            \n" +
				"    AND T2.PROCESSID  = T3.PROCESSID                                                               \n" +
				"    AND T2.STEPID     = T3.STEPID                                                                  \n" +
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
	public List<MURA_HIST_ReadBean> getList()
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
							System.out.println(String.format("    LINE_CODE               = [%s]", rs.getString("LINE_CODE"              )));
							System.out.println(String.format("    GLASS_ID                = [%s]", rs.getString("GLASS_ID"               )));
							System.out.println(String.format("    EVENT_OCCUR_CODE        = [%s]", rs.getString("EVENT_OCCUR_CODE"       )));
							System.out.println(String.format("    EVENT_OCCUR_DETIAL_CODE = [%s]", rs.getString("EVENT_OCCUR_DETIAL_CODE")));
							System.out.println(String.format("    STEP_ID                 = [%s]", rs.getString("STEP_ID"                )));
							System.out.println(String.format("    MAINSTEPID              = [%s]", rs.getString("MAINSTEPID"             )));
							System.out.println(String.format("    EQP_ID                  = [%s]", rs.getString("EQP_ID"                 )));
							System.out.println(String.format("    COMMENTS                = [%s]", rs.getString("COMMENTS"               )));
							System.out.println(String.format("    WORK_STATUS             = [%s]", rs.getString("WORK_STATUS"            )));
							System.out.println(String.format("    EVENT_OCCUR_ID          = [%s]", rs.getString("EVENT_OCCUR_ID"         )));
							System.out.println(String.format("    EVENT_OCCUR_DT          = [%s]", rs.getString("EVENT_OCCUR_DT"         )));
						}
						
						if (Flag.TRUE) {
							MURA_HIST_ReadBean bean = new MURA_HIST_ReadBean();

							bean.setLineCode            (rs.getString("LINE_CODE"              ));
							bean.setGlassId             (rs.getString("GLASS_ID"               ));
							bean.setEventOccurCode      (rs.getString("EVENT_OCCUR_CODE"       ));
							bean.setEventOccurDetialCode(rs.getString("EVENT_OCCUR_DETIAL_CODE"));
							bean.setStepId              (rs.getString("STEP_ID"                ));
							bean.setMainStepId          (rs.getString("MAINSTEPID"             ));
							bean.setEqpId               (rs.getString("EQP_ID"                 ));
							bean.setComments            (rs.getString("COMMENTS"               ));
							bean.setWorkStatus          (rs.getString("WORK_STATUS"            ));
							bean.setEventOccurId        (rs.getString("EVENT_OCCUR_ID"         ));
							bean.setEventOccurDt        (rs.getString("EVENT_OCCUR_DT"         ));
							
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

			if (Flag.TRUE) {
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

			if (!Flag.TRUE) {
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

			MURA_HIST_OracleReader reader = new MURA_HIST_OracleReader(strFromDateTime, strToDateTime, strLine, strAreaCode, strUserGroupCode, strDefectGroupCode);
			reader.getList();
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
