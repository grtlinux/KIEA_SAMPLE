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

package sdc.anal.mura.macro.A22.MURA_IMAGE.v01;

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
 * @file_name MURA_IMAGE_OracleReader.java
 *
 */
public class MURA_IMAGE_OracleReader extends AbstractOracleReader
{
	private List<MURA_IMAGE_ReadBean> list = new ArrayList<MURA_IMAGE_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public MURA_IMAGE_OracleReader()
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
	private String getLine56()
	{
		String query = null;
		
		query = String.format("" +
				"/* MURA_IMAGE : L5FAB, L6FAB  */                                             \n" +
				"SELECT                                                                       \n" +
				"    siteid, processid, productid, stepid, moduletype, eqpid, batchid,        \n" +
				"    cellid AS glasscellid, etime, imagepath                                  \n" +
				"FROM (                                                                       \n" +
				"    SELECT                                                                   \n" +
				"        siteid, processid, productid, stepid, moduletype, eqpid,             \n" +
				"        batchid, cellid, coordx, coordy, gateline, dataline, size_s,         \n" +
				"        size_w, size_h, size_l, code, /*DEFECTNAME,*/ grade,                 \n" +
				"        LOWER('http://12.91.60.70:8888/filesrv_5new/nas56/'                  \n" +
				"        || SUBSTR (imagefilepath,INSTR (imagefilepath, '/', 1, 3) + 1,       \n" +
				"        INSTR (imagefilepath, '/', 1, 8)- INSTR (imagefilepath, '/', 1, 3))  \n" +
				"        || cellid)|| '/GRAY_PCF.jpg' imagepath,                              \n" +
				"        imagefilepath, stackflag, etime,                                     \n" +
				"        ROW_NUMBER () OVER (PARTITION BY cellid ORDER BY etime) rn           \n" +
				"    FROM (                                                                   \n" +
				"        SELECT /*+ PARALLEL(2) ORDERED USE_HASH(S2 S3) FULL(S3) */           \n" +
				"            s2.siteid, /*S2.SYSID,*/ s2.moduleid, s2.moduletype,             \n" +
				"            s2.eqpid, s2.processid, s2.productid, s2.stepid,                 \n" +
				"            s2.prodtype, s2.batchid, s2.operatorid, s2.ppid,                 \n" +
				"            s2.in_cstid, s2.out_cstid, s2.eventtime, s2.teststate,           \n" +
				"            s2.coordinates, s2.starttime, s2.endtime,                        \n" +
				"            /*S2.ETLMODIFYDATE,*/                                            \n" +
				"            s3.siteid site, /*S3.SYSID,*/ s3.NO, s3.ID cellid,               \n" +
				"            s3.coordx, s3.coordy, s3.gateline, s3.dataline,                  \n" +
				"            s3.grade, s3.code, s3.size_s, s3.size_w, s3.size_h,              \n" +
				"            s3.size_l, s3.stackflag, s3.stackcount, s3.stackstep,            \n" +
				"            s3.imagefilepath, s3.data01, s3.data02, s3.data03,               \n" +
				"            s3.data04, s3.data05, s3.data06, s3.data07, s3.data08,           \n" +
				"            s3.data09, s3.data10, s3.data11, s3.data12, s3.data13,           \n" +
				"            s3.data14, s3.data15, s3.data16, s3.data17, s3.data18,           \n" +
				"            s3.data19, s3.data20, s3.data21, s3.data22, s3.data23,           \n" +
				"            s3.data24, s3.data25, s3.data26, s3.data27, s3.data28,           \n" +
				"            s3.data29, s3.data30, s3.eventtime etime /*, S3.ETLMODIFYDATE,*/ \n" +
				"        FROM yms_dw.dw_rdp_insp_history s2,                                  \n" +
				"            yms_dw.dw_rdp_defect_info s3                                     \n" +
				"        WHERE 1 = 1                                                          \n" +
				"            AND s2.sysid = s3.sysid                                          \n" +
				"        ) a                                                                  \n" +
				"    WHERE 'L' || '__' || SUBSTR (stepid, 4) IN ('L__080', 'L__082')          \n" +
				"        AND eqpid LIKE '%%AT%%'                                              \n" +
				"        AND siteid = %s                                                      \n" +  // LINE
				"        AND site   = %s                                                      \n" +  // LINE
				"        AND eventtime >= TO_DATE (%s, 'yyyymmddhh24miss')                    \n" +  // FROM_ETL_DT
				"        AND eventtime < TO_DATE (%s, 'yyyymmddhh24miss')                     \n" +  // TO_ETL_DT
				"        AND etime >= TO_DATE (%s, 'yyyymmddhh24miss')                        \n" +  // FROM_ETL_DT
				"        AND etime < TO_DATE (%s, 'yyyymmddhh24miss')                         \n" +  // TO_ETL_DT
				"    )                                                                        \n" +
				"WHERE  rn = 1                                                                \n" +
				""
				, StrUtil.quote(Parameter.getInstance().getStrLine           ())
				, StrUtil.quote(Parameter.getInstance().getStrLine           ())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime   ())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime     ())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime   ())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime     ())
				);

		return query;
	}
	
	/**
	 * 
	 * getLine78
	 *
	 * @return
	 */
	private String getLine78()
	{
		String query = null;
		
		query = String.format("" +
				"/* MURA_IMAGE : L7AFAB, L7BFAB, L8AFAB, L8ZFAB */                                                                                            \n" +
				"SELECT                                                                                                                                       \n" +
				"    SITEID, PROCESSID, PRODUCTID, GLASSCELLID, CELLID, STEPID, INSPECTDATE, eqpid, eqpunitid, IMAGEPATH                                      \n" +
				"    ,'' as moduletype, '' as batchid, '' as etime                                                                                            \n" +  // <- 임시로 추가함.
				"FROM                                                                                                                                         \n" +
				"    (                                                                                                                                        \n" +
				"    SELECT                                                                                                                                   \n" +
				"        SITEID, PROCESSID, PRODUCTID, GLASSCELLID, CELLID, STEPID, INSPECTDATE, eqpid, eqpunitid,                                            \n" +
				"        case /*L8ZFAB*/                                                                                                                      \n" +
				"            when siteid = 'L8ZFAB' and substr(imagepath,6,2)='8z' then 'http://12.96.10.200:8888/filesrv_8z'||imagepath||'/GRAY_PCF.jpg'     \n" +
				"            when siteid = 'L8ZFAB' and substr(imagepath,6,2)='8y' then 'http://12.96.10.200:8888/filesrv_8y'||imagepath||'/GRAY_PCF.jpg'     \n" +
				"            when siteid = 'L8ZFAB' and substr(imagepath,6,2)='8a' then 'http://12.96.10.200:8888/filesrv_8a_new'||imagepath||'/GRAY_PCF.jpg' \n" +
				"            when siteid = 'L8ZFAB' and substr(imagepath,6,2)='8b' then 'http://12.96.10.200:8888/filesrv_8b'||imagepath||'/GRAY_PCF.jpg'     \n" +
				"            /*L8AFAB*/                                                                                                                       \n" +
				"            when siteid = 'L8AFAB' and substr(imagepath,6,2)='8z' then 'http://12.96.10.200:8888/filesrv_8z'||imagepath||'/GRAY_PCF.jpg'     \n" +
				"            when siteid = 'L8AFAB' and substr(imagepath,6,2)='8y' then 'http://12.96.10.200:8888/filesrv_8y'||imagepath||'/GRAY_PCF.jpg'     \n" +
				"            when siteid = 'L8AFAB' and substr(imagepath,6,2)='8a' then 'http://12.96.10.200:8888/filesrv_8a_new'||imagepath||'/GRAY_PCF.jpg' \n" +
				"            when siteid = 'L8AFAB' and substr(imagepath,6,2)='8b' then 'http://12.96.10.200:8888/filesrv_8b'||imagepath||'/GRAY_PCF.jpg'     \n" +
				"            /*L7BFAB*/                                                                                                                       \n" +
				"            when SITEID = 'L7BFAB' then 'http://12.96.10.200:8888/filesrv_7b'||imagepath||'/GRAY_PCF.jpg'                                    \n" +
				"            when SITEID = 'L7AFAB' then 'http://12.96.10.200:8888/filesrv_7b'||imagepath||'/GRAY_PCF.jpg'                                    \n" +
				"            else '' end imagepath,                                                                                                           \n" +
				"        case when eqpunitid in ('7BLAV01_AV01','7BLAV02_AV01','7BLAV03_AV01','7BLAV04_AV01','7BLAV05_AV01','7BLAV06_AV01','7BLAV07_AV01')    \n" +
				"            then 'vi' else gubun end as gubun, /* 7B 라인 CT 설비군 추가 */                                                                  \n" +
				"            ROW_NUMBER() OVER (PARTITION BY GLASSCELLID ORDER BY INSPECTDATE) RN                                                             \n" +
				"    FROM                                                                                                                                     \n" +
				"        (                                                                                                                                    \n" +
				"        select                                                                                                                               \n" +
				"            SITEID, PROCESSID, PRODUCTID, GLASSCELLID, CELLID, STEPID, INSPECTDATE, eqpid, eqpunitid, gubun,                                 \n" +
				"            max(replace(image,'raw','img')) IMAGEPATH                                                                                        \n" +
				"        from                                                                                                                                 \n" +
				"            (                                                                                                                                \n" +
				"            SELECT /*+ parallel(2) full(eqp) full(dih)*/                                                                                     \n" +
				"                def.defectcd, def.siteid, def.defectname,                                                                                    \n" +
				"                def.defectgrpname, def.areaid, /*dih.siteid, eqp.siteid,*/                                                                   \n" +
				"                eqp.processid, eqp.productid, eqp.stepid, dih.inspectsysid, eqp.glasscellid, dih.cellid,                                     \n" +
				"                dih.seq, dih.inspectdate, eqp.inspectdate eqpdate, dih.decisiongrade, dih.defectcd,                                          \n" +
				"                dih.xcoord, dih.ycoord, dih.gateline, dih.dataline,                                                                          \n" +
				"                dih.imagepath,                                                                                                               \n" +
				"                case when dih.siteid in ('L8ZFAB','L8AFAB')                                                                                  \n" +
				"                     then substr(dih.imagepath,INSTR(DIH.IMAGEPATH,'/',1,2),instr(dih.imagepath,'/',1,10)-INSTR(DIH.IMAGEPATH,'/',1,2))      \n" +
				"                     /* substr(dih.imagepath,INSTR(DIH.IMAGEPATH,'/',1,2),instr(dih.imagepath,'/',1,6)-INSTR(DIH.IMAGEPATH,'/',1,2)) */      \n" +
				"                     /* ||'/'||lower(productid)||substr(dih.imagepath,INSTR(DIH.IMAGEPATH,'/',1,7),instr(dih.imagepath,'/',1,10)-    */      \n" +
				"                     /* INSTR(DIH.IMAGEPATH,'/',1,7))                                                                                */      \n" +
				"                     when dih.siteid in ('L7BFAB','L7AFAB')                                                                                  \n" +
				"                     then substr(dih.imagepath,INSTR(DIH.IMAGEPATH,'/',1,2),instr(dih.imagepath,'/',1,5)-INSTR(DIH.IMAGEPATH,'/',1,2))       \n" +
				"                            ||'/'||lower(productid)||substr(dih.imagepath,INSTR(DIH.IMAGEPATH,'/',1,6),instr(dih.imagepath,'/',1,9)-         \n" +
				"                            INSTR(DIH.IMAGEPATH,'/',1,6))                                                                                    \n" +
				"                     else '' end image,                                                                                                      \n" +
				"                case when dih.siteid IN ('L8AFAB','L8ZFAB') then substr(dih.imagepath,INSTR(DIH.IMAGEPATH,'/',1,5)+3,2)                      \n" +
				"                     when dih.siteid IN ('L7AFAB','L7BFAB') then substr(dih.imagepath,INSTR(DIH.IMAGEPATH,'/',1,4)+3,2)                      \n" +
				"                     else '' end gubun,                                                                                                      \n" +
				"                dih.defectsizesquare, dih.defectsizewidth,                                                                                   \n" +
				"                dih.defectsizeheight, dih.defectsizelen, dih.stackflag,                                                                      \n" +
				"                dih.stackqty, dih.stacksteplist, dih.sysid, /*dih.ETLMODIFYDATE,*/ dih.sourcesiteid,                                         \n" +
				"                eqp.eqpid, eqp.eqpunitid, eqp.producttype                                                                                    \n" +
				"            FROM yms_dm.d_defect def,                                                                                                        \n" +
				"                YMS_DW.DW_GLASSCELL_INSPEQPHIST eqp,                                                                                         \n" +
				"                YMS_DW.DW_DEFECT_INSPHIST dih                                                                                                \n" +
				"            WHERE def.defectcd(+) = dih.defectcd                                                                                             \n" +
				"                AND def.siteid(+) = dih.siteid                                                                                               \n" +
				"                AND eqp.inspectsysid = dih.inspectsysid                                                                                      \n" +
				"                AND dih.siteid = eqp.siteid                                                                                                  \n" +
				"            )                                                                                                                                \n" +
				"        where siteid = %s  /*-- 입력조건*/                                                                                                   \n" +  // LINE
				"            AND 'L'||'__'||substr(stepid,4) IN ('L__050','L__050VI','L__060','L__060R01','L__060R02','L__060R03')                            \n" +
				"            AND GUBUN IN ('vi','at','ct')                                                                                                    \n" +
				"            AND INSPECTDATE >= TO_DATE(%s,'YYYYMMDDHH24MISS')  /*-- 입력조건*/                                                               \n" +  // FROM_ETL_DT
				"            AND INSPECTDATE < TO_DATE(%s,'YYYYMMDDHH24MISS')   /*-- 입력조건*/                                                               \n" +  // TO_ETL_DT
				"        GROUP BY SITEID, PROCESSID, PRODUCTID, GLASSCELLID, CELLID, STEPID, INSPECTDATE, eqpid, eqpunitid, gubun                             \n" +
				"        )                                                                                                                                    \n" +
				"    )                                                                                                                                        \n" +
				"WHERE RN = 1                                                                                                                                 \n" +
				"    AND GUBUN IN ('vi','at')                                                                                                                 \n" +
				"Order by SITEID, PROCESSID, PRODUCTID, GLASSCELLID, CELLID, STEPID, INSPECTDATE, eqpid, eqpunitid                                            \n" +
				""
				, StrUtil.quote(Parameter.getInstance().getStrLine           ())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime   ())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime     ())
				);

		return query;
	}

	/**
	 * getList
	 */
	@Override
	public List<MURA_IMAGE_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<MURA_IMAGE_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<MURA_IMAGE_ReadBean>();
				return list;
			}
			
			try {
				String query = null;

				if ("L5FAB".equals(Parameter.getInstance().getStrLine()) || "L6FAB".equals(Parameter.getInstance().getStrLine())) {
					query = getLine56();
				} else if ("L7AFAB".equals(Parameter.getInstance().getStrLine()) || "L7BFAB".equals(Parameter.getInstance().getStrLine()) || "L8AFAB".equals(Parameter.getInstance().getStrLine()) || "L8ZFAB".equals(Parameter.getInstance().getStrLine())) {
					query = getLine78();
				} else {
					return list;
				}
				
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				
				OracleConnection conn = Connection04.getInstance().getConn();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i < 1000000 && rs.next(); i++) {
						if (!Flag.TRUE) {
							System.out.println(String.format("%3d) --------------------------------------", i));
							System.out.println(String.format("    SITEID       = [%s]", rs.getString("SITEID"      )));
							System.out.println(String.format("    PROCESSID    = [%s]", rs.getString("PROCESSID"   )));
							System.out.println(String.format("    PRODUCTID    = [%s]", rs.getString("PRODUCTID"   )));
							System.out.println(String.format("    STEPID       = [%s]", rs.getString("STEPID"      )));
							System.out.println(String.format("    MODULETYPE   = [%s]", rs.getString("MODULETYPE"  )));
							System.out.println(String.format("    EQPID        = [%s]", rs.getString("EQPID"       )));
							System.out.println(String.format("    BATCHID      = [%s]", rs.getString("BATCHID"     )));
							System.out.println(String.format("    GLASSCELLID  = [%s]", rs.getString("GLASSCELLID" )));
							System.out.println(String.format("    ETIME        = [%s]", rs.getString("ETIME"       )));
							System.out.println(String.format("    IMAGEPATH    = [%s]", rs.getString("IMAGEPATH"   )));
						}
						
						if (Flag.TRUE) {
							MURA_IMAGE_ReadBean bean = new MURA_IMAGE_ReadBean();

							bean.setSiteId     (rs.getString("SITEID"      ));
							bean.setProcessId  (rs.getString("PROCESSID"   ));
							bean.setProductId  (rs.getString("PRODUCTID"   ));
							bean.setStepId     (rs.getString("STEPID"      ));
							bean.setModuleType (rs.getString("MODULETYPE"  ));
							bean.setEqpId      (rs.getString("EQPID"       ));
							bean.setBatchId    (rs.getString("BATCHID"     ));
							bean.setGlassCellId(rs.getString("GLASSCELLID" ));
							bean.setETime      (rs.getString("ETIME"       ));
							bean.setImagePath  (rs.getString("IMAGEPATH"   ));
							
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
				Logger.getInstance(MURA_IMAGE_Main.jobId, MURA_IMAGE_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(MURA_IMAGE_Main.jobId);
			
			MURA_IMAGE_OracleReader reader = new MURA_IMAGE_OracleReader();
			
			for (MURA_IMAGE_ReadBean bean : reader.getList(true)) {
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
