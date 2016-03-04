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
import kiea.proj.sdc.anal.macro.sample.a11.aib.bean.BUBBLE_INDEX_ReadBean;
import kiea.proj.sdc.anal.util.StrUtil;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name BUBBLE_INDEX_OracleReader.java
 *
 */
public class BUBBLE_INDEX_OracleReader extends AbstractOracleReader
{
	private List<BUBBLE_INDEX_ReadBean> list = new ArrayList<BUBBLE_INDEX_ReadBean>();
	
	@SuppressWarnings("unused")
	private String strFromDateTime    = null;
	@SuppressWarnings("unused")
	private String strToDateTime      = null;
	@SuppressWarnings("unused")
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	@SuppressWarnings("unused")
	private String strDefectGroupCode = null;
	
	/**
	 * Sample02OracleReader
	 */
	public BUBBLE_INDEX_OracleReader(String strFromDateTime, String strToDateTime, String strLine, String strAreaCode, String strUserGroupCode, String strDefectGroupCode)
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
	private String getLineLC()
	{
		String query = null;
		
		query = String.format("" +
				"/* BUBBLE_INDEX :  LC */                    \n" +
				"SELECT                                      \n" +
				"    PROCID,                                 \n" +
				"    CELLLOCID,                              \n" +
				"    COLIDX,                                 \n" +
				"    ROWIDX                                  \n" +
				"FROM                                        \n" +
				"    YMS_DM.D_CELLCONVERT                    \n" +
				"WHERE                                       \n" +
				"    %s LIKE PROCID || '%%'                  \n" +   // USER_GROUP_CODE
				"/* PROCID = SUBSTR(&USER_GROUP_CODE.,2,7) */\n" +
				""
				//, "'20140701110000'", "'20140702105959'", "'LTL097QL01-A01'", "'L6FAB'", "'L6FABSTDALONE'", "'32PR'");
				, StrUtil.quote(strUserGroupCode)
		);

		return query;
	}
	
	/**
	 * 
	 * getLine56MOD
	 *
	 * @return
	 */
	private String getLineMOD()
	{
		String query = null;
		
		query = String.format("" +
				"/* BUBBLE_INDEX :  MOD */                                         \n" +
				"WITH TEMP1 AS (                                                   \n" +
				"    SELECT *                                                      \n" +
				"    FROM                                                          \n" +
				"        YMS_DM.D_PRODUCT                                          \n" +
				"    WHERE                                                         \n" +
				"        PRODID = %s                                               \n" +   // USER_GROUP_CODE
				")                                                                 \n" +
				"SELECT                                                            \n" +
				"    T2.PROCID,                                                    \n" +
				"    T2.CELLLOCID,                                                 \n" +
				"    T2.COLIDX,                                                    \n" +
				"    T2.ROWIDX /* 2013.05.27 SELECT 컬럼 수정*/                    \n" +
				"FROM                                                              \n" +
				"    TEMP1 T1,                                                     \n" +
				"    YMS_DM.D_CELLCONVERT T2                                       \n" +
				"WHERE                                                             \n" +
				"    T1.PROCID LIKE T2.PROCID||'%%'                                \n" +
				"/* 2013.05.27 WHERE 조건 변경 SUBSTR(T1.PROCID,1,7) = T2.PROCID */\n" +
				""
				//, "'20140701110000'", "'20140702105959'", "'LTL097QL01-A01'", "'L6FAB'", "'L6FABSTDALONE'", "'32PR'");
				, StrUtil.quote(strUserGroupCode)
		);

		return query;
	}
	
	/**
	 * getList
	 */
	public List<BUBBLE_INDEX_ReadBean> getList()
	{
		if (Flag.TRUE) {
			try {
				String query = null;
				
				if ("LC".equals(strAreaCode)) {
					query = getLineLC();
				} else if ("MOD".equals(strAreaCode)) {
					query = getLineMOD();
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
							System.out.println(String.format("    PROCID    = [%s]", rs.getString("PROCID"   )));
							System.out.println(String.format("    CELLLOCID = [%s]", rs.getString("CELLLOCID")));
							System.out.println(String.format("    COLIDX    = [%s]", rs.getString("COLIDX"   )));
							System.out.println(String.format("    ROWIDX    = [%s]", rs.getString("ROWIDX"   )));
						}
						
						if (Flag.TRUE) {
							BUBBLE_INDEX_ReadBean bean = new BUBBLE_INDEX_ReadBean();

							bean.setProcId   (rs.getString("PROCID"   ));
							bean.setCellLocId(rs.getString("CELLLOCID"));
							bean.setColIdx   (rs.getString("COLIDX"   ));
							bean.setRowIdx   (rs.getString("ROWIDX"   ));
							
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

			if (Flag.TRUE) {
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

			BUBBLE_INDEX_OracleReader reader = new BUBBLE_INDEX_OracleReader(strFromDateTime, strToDateTime, strLine, strAreaCode, strUserGroupCode, strDefectGroupCode);
			reader.getList();
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
