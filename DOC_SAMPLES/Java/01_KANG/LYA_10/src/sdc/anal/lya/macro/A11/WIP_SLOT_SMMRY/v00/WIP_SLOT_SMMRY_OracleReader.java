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

package sdc.anal.lya.macro.A11.WIP_SLOT_SMMRY.v00;

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
import kiea.proj.sdc.anal.util.log.v02.Logger;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_WIP_SLOT_SMMRY_OracleReader.java
 *
 */
public class WIP_SLOT_SMMRY_OracleReader extends AbstractOracleReader
{
	private List<WIP_SLOT_SMMRY_ReadBean> list = new ArrayList<WIP_SLOT_SMMRY_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public WIP_SLOT_SMMRY_OracleReader()
	{
		if (Flag.TRUE) {
		}
	}

	/**
	 * 
	 * getLine56TFT
	 *
	 * @return
	 */
	private String getLine56TFT()
	{
		String query = null;
		
		query = String.format("" +
				"/* AIB_WIP_SLOT_SMMRY : 5,6 : WIP-TFT-EQP */   \n" +
				"/* 해당 쿼리는 DB 부하 이슈로 빈셋 생성함 */   \n" +
				""
				);

		return query;
	}

	/**
	 * 
	 * getLine56LCMOD
	 *
	 * @return
	 */
	private String getLine56LCMOD()
	{
		String query = null;
		
		query = String.format("" +
				"/* AIB_WIP_SLOT_SMMRY : 5,6 : WIP-LC/MOD-EQP */\n" +
				"/* 해당 쿼리는 DB 부하 이슈로 빈셋 생성함 */   \n" +
				""
				);

		return query;
	}

	/**
	 * 
	 * getLine78TFT
	 *
	 * @return
	 */
	private String getLine78TFT()
	{
		String query = null;
		
		query = String.format("" +
				"/* AIB_WIP_SLOT_SMMRY : 7,8 : WIP-TFT-EQP */   \n" +
				"/* 해당 쿼리는 DB 부하 이슈로 빈셋 생성함 */   \n" +
				""
				);

		return query;
	}

	/**
	 * 
	 * getLine78LCMOD
	 *
	 * @return
	 */
	private String getLine78LCMOD()
	{
		String query = null;
		
		query = String.format("" +
				"/* AIB_WIP_SLOT_SMMRY : 7,8 : WIP-LC/MOD-EQP */\n" +
				"/* 해당 쿼리는 DB 부하 이슈로 빈셋 생성함 */   \n" +
				""
				);

		return query;
	}

	/**
	 * getList
	 */
	@Override
	public List<WIP_SLOT_SMMRY_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<WIP_SLOT_SMMRY_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<WIP_SLOT_SMMRY_ReadBean>();
				return list;
			}
			
			try {
				String query = null;
				
				if ("L5FAB".equals(Parameter.getInstance().getStrLine()) || "L6FAB".equals(Parameter.getInstance().getStrLine())) {
					if ("TFT".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine56TFT();
					} else if ("LC".equals(Parameter.getInstance().getStrAreaCode()) || "MOD".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine56LCMOD();
					} else {
						return list;
					}
				} else if ("L7AFAB".equals(Parameter.getInstance().getStrLine()) || "L7BFAB".equals(Parameter.getInstance().getStrLine()) || "L8AFAB".equals(Parameter.getInstance().getStrLine()) || "L8ZFAB".equals(Parameter.getInstance().getStrLine())) {
					if ("TFT".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine78TFT();
					} else if ("LC".equals(Parameter.getInstance().getStrAreaCode()) || "MOD".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine78LCMOD();
					} else {
						return list;
					}
				} else {
					return list;
				}
				
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				if (Flag.TRUE) return list;
				
				OracleConnection conn = Connection04.getInstance().getConn();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i < 1000000 && rs.next(); i++) {
						if (!Flag.TRUE) {
							System.out.println(String.format("%3d) --------------------------------------", i));
							System.out.println(String.format("    LINE_CODE               = [%s]", rs.getString("LINE_CODE"              )));
							System.out.println(String.format("    USER_GROUP_CODE         = [%s]", rs.getString("USER_GROUP_CODE"        )));
							System.out.println(String.format("    PROCESS_ID              = [%s]", rs.getString("PROCESS_ID"             )));
							System.out.println(String.format("    PRODUCT_ID              = [%s]", rs.getString("PRODUCT_ID"             )));
							System.out.println(String.format("    PRODUCT_TYPE            = [%s]", rs.getString("PRODUCT_TYPE"           )));
							System.out.println(String.format("    AREA_CODE               = [%s]", rs.getString("AREA_CODE"              )));
							System.out.println(String.format("    SUB_AREA_CODE           = [%s]", rs.getString("SUB_AREA_CODE"          )));
							System.out.println(String.format("    STEP_ID                 = [%s]", rs.getString("STEP_ID"                )));
							System.out.println(String.format("    EQP_ID                  = [%s]", rs.getString("EQP_ID"                 )));
							System.out.println(String.format("    GLASS_ID                = [%s]", rs.getString("GLASS_ID"               )));
							System.out.println(String.format("    CELL_ID                 = [%s]", rs.getString("CELL_ID"                )));
							System.out.println(String.format("    GLASS_DEFECT_CODE_RATIO = [%s]", rs.getString("GLASS_DEFECT_CODE_RATIO")));
							System.out.println(String.format("    BASE_CELL_NUM           = [%s]", rs.getString("BASE_CELL_NUM"          )));
							System.out.println(String.format("    DEFECT_CELL_NUM         = [%s]", rs.getString("DEFECT_CELL_NUM"        )));
							System.out.println(String.format("    TRCK_IN_TIME            = [%s]", rs.getString("TRCK_IN_TIME"           )));
							System.out.println(String.format("    TRCK_OUT_TIME           = [%s]", rs.getString("TRCK_OUT_TIME"          )));
							System.out.println(String.format("    MATCH_LOT_TYPE          = [%s]", rs.getString("MATCH_LOT_TYPE"         )));
							System.out.println(String.format("    BIN_GRADE_CODE          = [%s]", rs.getString("BIN_GRADE_CODE"         )));
							System.out.println(String.format("    DEFECT_GROUP_CODE       = [%s]", rs.getString("DEFECT_GROUP_CODE"      )));
							System.out.println(String.format("    DECISION_CODE           = [%s]", rs.getString("DECISION_CODE"          )));
						}
						
						if (Flag.TRUE) {
							WIP_SLOT_SMMRY_ReadBean bean = new WIP_SLOT_SMMRY_ReadBean();

							bean.setLineCode            (rs.getString("LINE_CODE"              ));
							bean.setUserGroupCode       (rs.getString("USER_GROUP_CODE"        ));
							bean.setProcessId           (rs.getString("PROCESS_ID"             ));
							bean.setProductId           (rs.getString("PRODUCT_ID"             ));
							bean.setProductType         (rs.getString("PRODUCT_TYPE"           ));
							bean.setAreaCode            (rs.getString("AREA_CODE"              ));
							bean.setSubAreaCode         (rs.getString("SUB_AREA_CODE"          ));
							bean.setStepId              (rs.getString("STEP_ID"                ));
							bean.setEqpId               (rs.getString("EQP_ID"                 ));
							bean.setGlassId             (rs.getString("GLASS_ID"               ));
							bean.setCellId              (rs.getString("CELL_ID"                ));
							bean.setGlassDefectCodeRatio(rs.getString("GLASS_DEFECT_CODE_RATIO"));
							bean.setBaseCellNum         (rs.getString("BASE_CELL_NUM"          ));
							bean.setDefectCellNum       (rs.getString("DEFECT_CELL_NUM"        ));
							bean.setTrckInTime          (rs.getString("TRCK_IN_TIME"           ));
							bean.setTrckOutTime         (rs.getString("TRCK_OUT_TIME"          ));
							bean.setMatchLotType        (rs.getString("MATCH_LOT_TYPE"         ));
							bean.setBinGradeCode        (rs.getString("BIN_GRADE_CODE"         ));
							bean.setDefectGroupCode     (rs.getString("DEFECT_GROUP_CODE"      ));
							bean.setDecisionCode        (rs.getString("DECISION_CODE"          ));
							
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
				Logger.getInstance(WIP_SLOT_SMMRY_Main.jobId, WIP_SLOT_SMMRY_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(WIP_SLOT_SMMRY_Main.jobId);
			WIP_SLOT_SMMRY_OracleReader reader = new WIP_SLOT_SMMRY_OracleReader();
			
			for (WIP_SLOT_SMMRY_ReadBean bean : reader.getList(true)) {
				System.out.println(bean);
			}
			
			System.out.println("the read ok !!!");
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
