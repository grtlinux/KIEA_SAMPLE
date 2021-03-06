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

package sdc.anal.mura.macro.A11.MURA_DESC.v04;

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
 * @file_name MURA_DESC_OracleReader.java
 *
 */
public class MURA_DESC_OracleReader extends AbstractOracleReader
{
	private List<MURA_DESC_ReadBean> list = new ArrayList<MURA_DESC_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public MURA_DESC_OracleReader()
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
				"/* MURA_DESC : L5FAB, L6FAB - LC */  \n" +
				"SELECT                               \n" +
				"    DISTINCT ITEMID, ITEMNAME        \n" +
				"FROM                                 \n" +
				"    yms_dm.d_trendmeasitem           \n" +
				"WHERE 1=1                            \n" +
				"    AND SITEID IN ('L6FAB','L5FAB')  \n" +
				"    AND ITEMNAME LIKE '%%얼룩%%'     \n" +
				""
				//, "'20140701110000'", "'20140702105959'", "'LTL097QL01-A01'", "'L6FAB'", "'L6FABSTDALONE'", "'32PR'");
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
		);

		return query;
	}
	
	/**
	 * 
	 * getLine7LC
	 *
	 * @return
	 */
	private String getLine7LC()
	{
		String query = null;
		
		query = String.format("" +
				"/* MURA_DESC : L7AFAB, L7BFAB - LC */           \n" +
				"SELECT                                          \n" +
				"    DISTINCT ITEMID, ITEMNAME                   \n" +
				"FROM                                            \n" +
				"    yms_dm.d_trendmeasitem                      \n" +
				"WHERE 1=1                                       \n" +
				"    AND MEASSTEPGRPID in ('L__050VI','L__050')  \n" +
				"    AND ITEMNAME LIKE '%%얼룩%%'                \n" +
				""
				//, "'20140701110000'", "'20140702105959'", "'LTL097QL01-A01'", "'L6FAB'", "'L6FABSTDALONE'", "'32PR'");
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
		);

		return query;
	}
	
	/**
	 * 
	 * getLine8LC
	 *
	 * @return
	 */
	private String getLine8LC()
	{
		String query = null;
		
		query = String.format("" +
				"/* MURA_DESC : L8AFAB, L8ZFAB - LC */           \n" +
				"SELECT                                          \n" +
				"    DISTINCT ITEMID, ITEMNAME                   \n" +
				"FROM                                            \n" +
				"    YMS_DM.D_TRENDMEASITEM                      \n" +
				"    /* TEST 를 위해 YMS_DM.D_TRENDMEASITEM */   \n" +
				"    /*  -> MDW_TEST.D_TRENDMEASITEM 로 수정 */  \n" +
				"WHERE                                           \n" +
				"    MEASSTEPGRPID = 'L__060S02'                 \n" +
				""
				//, "'20140701110000'", "'20140702105959'", "'LTL097QL01-A01'", "'L6FAB'", "'L6FABSTDALONE'", "'32PR'");
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
		);

		return query;
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
				"/* MURA_DESC :  MOD */                          \n" +
				"SELECT                                          \n" +
				"    DISTINCT ITEMID, ITEMNAME                   \n" +
				"FROM                                            \n" +
				"    YMS_DM.D_TRENDMEASITEM                      \n" +
				"    /* TEST 를 위해 YMS_DM.D_TRENDMEASITEM */   \n" +
				"    /*  -> MDW_TEST.D_TRENDMEASITEM 로 수정 */  \n" +
				"WHERE                                           \n" +
				"    MEASSTEPGRPID = '7M020'                     \n" +
				""
				//, "'20140701110000'", "'20140702105959'", "'LTL097QL01-A01'", "'L6FAB'", "'L6FABSTDALONE'", "'32PR'");
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
		);

		return query;
	}
	
	/**
	 * getList
	 */
	@Override
	public List<MURA_DESC_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<MURA_DESC_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<MURA_DESC_ReadBean>();
				return list;
			}
			
			try {
				String query = null;
				
				if ("LC".equals(Parameter.getInstance().getStrAreaCode())) {
					if ("L5FAB".equals(Parameter.getInstance().getStrLine()) || "L6FAB".equals(Parameter.getInstance().getStrLine())) {
						query = getLine56LC();
					} else if ("L7AFAB".equals(Parameter.getInstance().getStrLine()) || "L7BFAB".equals(Parameter.getInstance().getStrLine())) {
						query = getLine7LC();
					} else if ("L8AFAB".equals(Parameter.getInstance().getStrLine()) || "L8ZFAB".equals(Parameter.getInstance().getStrLine())) {
						query = getLine8LC();
					} else {
						return list;
					}
				} else if ("MOD".equals(Parameter.getInstance().getStrAreaCode())) {
					query = getLineMOD();
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
							System.out.println(String.format("    ITEMID   = [%s]", rs.getString("ITEMID"  )));
							System.out.println(String.format("    ITEMNAME = [%s]", rs.getString("ITEMNAME")));
						}
						
						if (Flag.TRUE) {
							MURA_DESC_ReadBean bean = new MURA_DESC_ReadBean();

							bean.setItemId  (rs.getString("ITEMID"  ));
							bean.setItemName(rs.getString("ITEMNAME"));
							
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
			SystemProperties.setTesting("020");
			BasePath.getInstance();
			try {
				Logger.getInstance(MURA_DESC_Main.jobId, MURA_DESC_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(MURA_DESC_Main.jobId);
			
			MURA_DESC_OracleReader reader = new MURA_DESC_OracleReader();
			
			for (MURA_DESC_ReadBean bean : reader.getList(true)) {
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
