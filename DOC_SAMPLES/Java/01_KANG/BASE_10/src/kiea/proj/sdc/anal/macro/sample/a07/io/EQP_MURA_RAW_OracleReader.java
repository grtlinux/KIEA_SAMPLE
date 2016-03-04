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

package kiea.proj.sdc.anal.macro.sample.a07.io;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.io.AbstractOracleReader;
import kiea.proj.sdc.anal.macro.sample.a07.bean.EQP_MURA_RAW_ReadBean;
import kiea.proj.sdc.anal.util.StrUtil;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name EQP_MURA_RAW_OracleReader.java
 *
 */
public class EQP_MURA_RAW_OracleReader extends AbstractOracleReader
{
	private List<EQP_MURA_RAW_ReadBean> list = new ArrayList<EQP_MURA_RAW_ReadBean>();
	
	@SuppressWarnings("unused")
	private String strFromDateTime    = null;
	@SuppressWarnings("unused")
	private String strToDateTime      = null;
	private String strLine            = null;
	@SuppressWarnings("unused")
	private String strAreaCode        = null;
	@SuppressWarnings("unused")
	private String strUserGroupCode   = null;
	@SuppressWarnings("unused")
	private String strDefectGroupCode = null;
	
	/**
	 * Sample02OracleReader
	 */
	public EQP_MURA_RAW_OracleReader(String strFromDateTime, String strToDateTime, String strLine, String strAreaCode, String strUserGroupCode, String strDefectGroupCode)
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
	 * getLine56
	 *
	 * @return
	 */
	private String getLine56()
	{
		String query = null;
		
		query = String.format("" +
				"/* EQP_MURA_RAW : L5FAB, L6FAB  */                              \n" +
				"SELECT                                                          \n" +
				"    *                                                           \n" +
				"FROM                                                            \n" +
				"    yms_dm.h_meas_rundata_cell                                  \n" +
				"WHERE                                                           \n" +
				"    siteid = %s                                                 \n" +  // LINE
				"    AND TO_DATE (%s, 'yyyymmddhh24miss') <= dcoldate            \n" +  // FROM_ETL_DT
				"    AND TO_DATE (%s, 'yyyymmddhh24miss') > dcoldate             \n" +  // TO_ETL_DT
				"    AND orgstepid like 'L__080'                                 \n" +
				"    AND itemid = %s                                             \n" +  // DEFECT_GROUP_CODE
				"    AND PROCID = %s                                             \n" +  // USER_GROUP_CODE
				""
				, StrUtil.quote(Parameter.getInstance().getStrLine           ())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime   ())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime     ())
				, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode  ())
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
				"/* EQP_MURA_RAW : L7AFAB, L7BFAB, L8AFAB, L8ZFAB  */            \n" +
				"SELECT                                                          \n" +
				"    *                                                           \n" +
				"FROM                                                            \n" +
				"    yms_dm.h_meas_rundata_cell                                  \n" +
				"WHERE                                                           \n" +
				"    siteid = %s                                                 \n" +  // LINE
				"    AND TO_DATE (%s, 'yyyymmddhh24miss') <= dcoldate            \n" +  // FROM_ETL_DT
				"    AND TO_DATE (%s, 'yyyymmddhh24miss') > dcoldate             \n" +  // TO_ETL_DT
				"    AND measstepgrpid LIKE 'L%%'                                \n" +
				"    AND itemid = %s                                             \n" +  // DEFECT_GROUP_CODE
				"    AND PROCID = %s                                             \n" +  // USER_GROUP_CODE
				""
				, StrUtil.quote(Parameter.getInstance().getStrLine           ())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime   ())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime     ())
				, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode  ())
				);

		return query;
	}

	/**
	 * getList
	 */
	public List<EQP_MURA_RAW_ReadBean> getList()
	{
		if (Flag.TRUE) {
			try {
				String query = null;
				
				if ("L5FAB".equals(strLine) || "L6FAB".equals(strLine)) {
					query = getLine56();
				} else if ("L7AFAB".equals(strLine) || "L7BFAB".equals(strLine) || "L8AFAB".equals(strLine) || "L8ZFAB".equals(strLine)) {
					query = getLine78();
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
							System.out.println(String.format("    SITEID         = [%s]", rs.getString("SITEID"        )));
							System.out.println(String.format("    SITEID_EQPTYPE = [%s]", rs.getString("SITEID_EQPTYPE")));
							System.out.println(String.format("    COMPMASTERKEY  = [%s]", rs.getString("COMPMASTERKEY" )));
							System.out.println(String.format("    GLASSID        = [%s]", rs.getString("GLASSID"       )));
							System.out.println(String.format("    PRODID         = [%s]", rs.getString("PRODID"        )));
							System.out.println(String.format("    PROCID         = [%s]", rs.getString("PROCID"        )));
							System.out.println(String.format("    ORGSTEPID      = [%s]", rs.getString("ORGSTEPID"     )));
							System.out.println(String.format("    MEASSTEPGRPID  = [%s]", rs.getString("MEASSTEPGRPID" )));
							System.out.println(String.format("    MEASEQPUNITID  = [%s]", rs.getString("MEASEQPUNITID" )));
							System.out.println(String.format("    DCOLDATE       = [%s]", rs.getString("DCOLDATE"      )));
							System.out.println(String.format("    ITEMID         = [%s]", rs.getString("ITEMID"        )));
							System.out.println(String.format("    SUBITEMID      = [%s]", rs.getString("SUBITEMID"     )));
							System.out.println(String.format("    DATAVALUE      = [%s]", rs.getString("DATAVALUE"     )));
							System.out.println(String.format("    CELLLOCID      = [%s]", rs.getString("CELLLOCID"     )));
							System.out.println(String.format("    USL            = [%s]", rs.getString("USL"           )));
							System.out.println(String.format("    LSL            = [%s]", rs.getString("LSL"           )));
							System.out.println(String.format("    TARGET         = [%s]", rs.getString("TARGET"        )));
							System.out.println(String.format("    CODE_X         = [%s]", rs.getString("CODE_X"        )));
							System.out.println(String.format("    CODE_Y         = [%s]", rs.getString("CODE_Y"        )));
							System.out.println(String.format("    PRODTYPE       = [%s]", rs.getString("PRODTYPE"      )));
							System.out.println(String.format("    CODE_X2        = [%s]", rs.getString("CODE_X2"       )));
							System.out.println(String.format("    CODE_Y2        = [%s]", rs.getString("CODE_Y2"       )));
							System.out.println(String.format("    ETLMODIFYDATE  = [%s]", rs.getString("ETLMODIFYDATE" )));
							System.out.println(String.format("    SHOTNO         = [%s]", rs.getString("SHOTNO"        )));
							System.out.println(String.format("    IMGFILE        = [%s]", rs.getString("IMGFILE"       )));
						}
						
						if (Flag.TRUE) {
							EQP_MURA_RAW_ReadBean bean = new EQP_MURA_RAW_ReadBean();

							bean.setSiteId       (rs.getString("SITEID"        ));
							bean.setSiteIdEqpType(rs.getString("SITEID_EQPTYPE"));
							bean.setCompMasterKey(rs.getString("COMPMASTERKEY" ));
							bean.setGlassId      (rs.getString("GLASSID"       ));
							bean.setProdId       (rs.getString("PRODID"        ));
							bean.setProcId       (rs.getString("PROCID"        ));
							bean.setOrgStepId    (rs.getString("ORGSTEPID"     ));
							bean.setMeasStepGrpId(rs.getString("MEASSTEPGRPID" ));
							bean.setMeasEqpUnitId(rs.getString("MEASEQPUNITID" ));
							bean.setDcolDate     (rs.getString("DCOLDATE"      ));
							bean.setItemId       (rs.getString("ITEMID"        ));
							bean.setSubItemId    (rs.getString("SUBITEMID"     ));
							bean.setDataValue    (rs.getString("DATAVALUE"     ));
							bean.setCellLocId    (rs.getString("CELLLOCID"     ));
							bean.setUsl          (rs.getString("USL"           ));
							bean.setLsl          (rs.getString("LSL"           ));
							bean.setTarget       (rs.getString("TARGET"        ));
							bean.setCodeX        (rs.getString("CODE_X"        ));
							bean.setCodeY        (rs.getString("CODE_Y"        ));
							bean.setProdType     (rs.getString("PRODTYPE"      ));
							bean.setCodeX2       (rs.getString("CODE_X2"       ));
							bean.setCodeY2       (rs.getString("CODE_Y2"       ));
							bean.setEtlModifyDate(rs.getString("ETLMODIFYDATE" ));
							bean.setShotNo       (rs.getString("SHOTNO"        ));
							bean.setImgFile      (rs.getString("IMGFILE"       ));
							
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

			EQP_MURA_RAW_OracleReader reader = new EQP_MURA_RAW_OracleReader(strFromDateTime, strToDateTime, strLine, strAreaCode, strUserGroupCode, strDefectGroupCode);
			reader.getList();
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
