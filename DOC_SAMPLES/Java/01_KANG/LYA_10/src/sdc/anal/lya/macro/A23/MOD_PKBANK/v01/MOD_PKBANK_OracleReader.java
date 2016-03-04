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

package sdc.anal.lya.macro.A23.MOD_PKBANK.v01;

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
 * @file_name MOD_PKBANK_OracleReader.java
 *
 */
public class MOD_PKBANK_OracleReader extends AbstractOracleReader
{
	private List<MOD_PKBANK_ReadBean> list = new ArrayList<MOD_PKBANK_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public MOD_PKBANK_OracleReader()
	{
		if (Flag.TRUE) {
		}
	}

	/**
	 * 
	 * getLineMOD
	 *
	 * @return
	 */
	private String getLineMOD()
	{
		String query = null;
		
		query = String.format("" +
				"/******************/                                                                                  \n" +
				"/*** MOD PKBANK ***/                                                                                  \n" +
				"/******************/                                                                                  \n" +
				"SELECT /*+ ORDERED PARALLEL ( 2 )  INDEX ( T1 H_INSPDEFECT_IDX01 )  INDEX ( F H_IMPTDEFECTEQP_PK ) */ \n" +
				"    F.SITEID                                                                                          \n" +
				"    ,D.PRODGRPNAME                                                                                    \n" +
				"    ,F.PRODID                                                                                         \n" +
				"    ,F.GLASSCELLID                                                                                    \n" +
				"    ,F.IMPTSTEPGRPID                                                                                  \n" +
				"    ,F.IMPTEQPID                                                                                      \n" +
				"    ,F.PROCHOUR                                                                                       \n" +
				"    ,T1.*                                                                                             \n" +
				"FROM                                                                                                  \n" +
				"    yms_dm.D_PRODUCT D                                                                                \n" +
				"    ,yms_dm.H_INSPDEFECT T1                                                                           \n" +
				"    ,yms_dm.H_IMPTDEFECTEQP F                                                                         \n" +
				"WHERE  1=1                                                                                            \n" +
				//"    AND ROWNUM <= 100                                                                                 \n" +
				"    AND T1.INSPHOUR >= %s                                                                             \n" +  // FROM_ETL_HOUR
				"    AND T1.INSPHOUR <= %s                                                                             \n" +  // TO_ETL_HOUR
				"    AND F.SITEID    =  %s                                                                             \n" +  // LINE
				"    AND F.IMPTSTEPGRPID IN ('PK_BANK')                                                                \n" +
				"    AND D.PRODGRPNAME IN ( %s )                                                                       \n" +  // USER_GROUP_CODE
				"    AND F.PRODTYPE IN ( %s )                                                                          \n" +  // PRODUCT_TYPE
				"    AND T1.PRODID = D.PRODID                                                                          \n" +
				"    AND F.GLASSCELLID = T1.CELLID                                                                     \n" +
				"    AND T1.SITEID = %s                                                                                \n" +  // LINE
				"    AND T1.INSPSTEPTYPE IN ( %s )                                                                     \n" +  // SUB_AREA_CODE
				""
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrProductType())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
				);

		return query;
	}

	/**
	 * getList
	 */
	@Override
	public List<MOD_PKBANK_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<MOD_PKBANK_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<MOD_PKBANK_ReadBean>();
				return list;
			}
			
			try {
				String query = null;
				
				if ("MOD".equals(Parameter.getInstance().getStrAreaCode())) {
					query = getLineMOD();
				} else {
					return list;
				}
				
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				
				OracleConnection conn = Connection04.getInstance().getConn();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i < 100000 && rs.next(); i++) {                     // TODO : 2014.08.26 : 최대건수 처리
						if (!Flag.TRUE) {
							System.out.println(String.format("%3d) --------------------------------------", i));
							System.out.println(String.format("    SITEID             = [%s]", rs.getString("SITEID"            )));
							System.out.println(String.format("    PRODGRPNAME        = [%s]", rs.getString("PRODGRPNAME"       )));
							System.out.println(String.format("    PRODID             = [%s]", rs.getString("PRODID"            )));
							System.out.println(String.format("    GLASSCELLID        = [%s]", rs.getString("GLASSCELLID"       )));
							System.out.println(String.format("    IMPTSTEPGRPID      = [%s]", rs.getString("IMPTSTEPGRPID"     )));
							System.out.println(String.format("    IMPTEQPID          = [%s]", rs.getString("IMPTEQPID"         )));
							System.out.println(String.format("    PROCHOUR           = [%s]", rs.getString("PROCHOUR"          )));
							System.out.println(String.format("    CELLID             = [%s]", rs.getString("CELLID"            )));
							System.out.println(String.format("    GLASSID            = [%s]", rs.getString("GLASSID"           )));
							System.out.println(String.format("    INSPSTEPTYPE       = [%s]", rs.getString("INSPSTEPTYPE"      )));
							System.out.println(String.format("    DECGRADECD         = [%s]", rs.getString("DECGRADECD"        )));
							System.out.println(String.format("    INSPDATE           = [%s]", rs.getString("INSPDATE"          )));
							System.out.println(String.format("    INSPHOUR           = [%s]", rs.getString("INSPHOUR"          )));
							System.out.println(String.format("    INSPEQPID          = [%s]", rs.getString("INSPEQPID"         )));
							System.out.println(String.format("    BATCHID            = [%s]", rs.getString("BATCHID"           )));
							System.out.println(String.format("    PRODTYPE           = [%s]", rs.getString("PRODTYPE"          )));
							System.out.println(String.format("    PROCID             = [%s]", rs.getString("PROCID"            )));
							System.out.println(String.format("    INSPSTEPID         = [%s]", rs.getString("INSPSTEPID"        )));
							System.out.println(String.format("    BINGRADECD         = [%s]", rs.getString("BINGRADECD"        )));
							System.out.println(String.format("    MATCHLOTTYPE       = [%s]", rs.getString("MATCHLOTTYPE"      )));
							System.out.println(String.format("    DEFECTCD           = [%s]", rs.getString("DEFECTCD"          )));
							System.out.println(String.format("    WORKERID           = [%s]", rs.getString("WORKERID"          )));
							System.out.println(String.format("    CELLLOCID          = [%s]", rs.getString("CELLLOCID"         )));
							System.out.println(String.format("    DEFECTSYSID        = [%s]", rs.getString("DEFECTSYSID"       )));
							System.out.println(String.format("    OUTSITEID          = [%s]", rs.getString("OUTSITEID"         )));
							System.out.println(String.format("    USERDEF01          = [%s]", rs.getString("USERDEF01"         )));
							System.out.println(String.format("    DEFECTQTY          = [%s]", rs.getString("DEFECTQTY"         )));
							System.out.println(String.format("    ETLMODIFYDATE      = [%s]", rs.getString("ETLMODIFYDATE"     )));
							System.out.println(String.format("    ALTERETLMODIFYDATE = [%s]", rs.getString("ALTERETLMODIFYDATE")));
						}
						
						if (Flag.TRUE) {
							MOD_PKBANK_ReadBean bean = new MOD_PKBANK_ReadBean();

							bean.setSiteId            (rs.getString("SITEID"            ));
							bean.setProdGrpName       (rs.getString("PRODGRPNAME"       ));
							bean.setProdId            (rs.getString("PRODID"            ));
							bean.setGlassCellId       (rs.getString("GLASSCELLID"       ));
							bean.setImptStepGrpId     (rs.getString("IMPTSTEPGRPID"     ));
							bean.setImptEqpId         (rs.getString("IMPTEQPID"         ));
							bean.setProcHour          (rs.getString("PROCHOUR"          ));
							bean.setCellId            (rs.getString("CELLID"            ));
							bean.setGlassId           (rs.getString("GLASSID"           ));
							bean.setInspStepType      (rs.getString("INSPSTEPTYPE"      ));
							bean.setDecGradeCd        (rs.getString("DECGRADECD"        ));
							bean.setInspDate          (rs.getString("INSPDATE"          ));
							bean.setInspHour          (rs.getString("INSPHOUR"          ));
							bean.setInspEqpId         (rs.getString("INSPEQPID"         ));
							bean.setBatchId           (rs.getString("BATCHID"           ));
							bean.setProdType          (rs.getString("PRODTYPE"          ));
							bean.setProcId            (rs.getString("PROCID"            ));
							bean.setInspStepId        (rs.getString("INSPSTEPID"        ));
							bean.setBinGradeCd        (rs.getString("BINGRADECD"        ));
							bean.setMatchLotType      (rs.getString("MATCHLOTTYPE"      ));
							bean.setDefectCd          (rs.getString("DEFECTCD"          ));
							bean.setWorkerId          (rs.getString("WORKERID"          ));
							bean.setCellLocId         (rs.getString("CELLLOCID"         ));
							bean.setDefectSysId       (rs.getString("DEFECTSYSID"       ));
							bean.setOutSiteId         (rs.getString("OUTSITEID"         ));
							bean.setUserDef01         (rs.getString("USERDEF01"         ));
							bean.setDefectQty         (rs.getString("DEFECTQTY"         ));
							bean.setEtlModifyDate     (rs.getString("ETLMODIFYDATE"     ));
							bean.setAlterEtlModifyDate(rs.getString("ALTERETLMODIFYDATE"));
							
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
				Logger.getInstance(MOD_PKBANK_Main.jobId, MOD_PKBANK_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(MOD_PKBANK_Main.jobId);
			
			MOD_PKBANK_OracleReader reader = new MOD_PKBANK_OracleReader();
			
			for (MOD_PKBANK_ReadBean bean : reader.getList(true)) {
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
