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

package sdc.anal.mura.macro.A22.BUBBLE_IDX.v04;

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
 * @file_name BUBBLE_IDX_OracleReader.java
 *
 */
public class BUBBLE_IDX_OracleReader extends AbstractOracleReader
{
	private List<BUBBLE_IDX_ReadBean> list = new ArrayList<BUBBLE_IDX_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public BUBBLE_IDX_OracleReader()
	{
		if (Flag.TRUE) {
		}
	}

	/**
	 * 
	 * getLineLC
	 *
	 * @return
	 */
	private String getLineLC()
	{
		String query = null;
		
		query = String.format("" +
				"/* BUBBLE_IDX : LC  */                  \n" +
				"SELECT                                    \n" +
				"    PROCID, CELLLOCID, COLIDX, ROWIDX     \n" +
				"    , POINT_X, POINT_Y                    \n" +
				"FROM                                      \n" +
				"    YMS_DM.D_CELLCONVERT                  \n" +
				"WHERE                                     \n" +
				"    %s LIKE PROCID || '%%'                \n" +  // USER_GROUP_CODE
				""
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode  ())
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
				"/* BUBBLE_IDX : MOD  */                                \n" +
				"WITH TEMP1 AS (                                          \n" +
				"    SELECT                                               \n" +
				"        *                                                \n" +
				"    FROM                                                 \n" +
				"        YMS_DM.D_PRODUCT                                 \n" +
				"    WHERE                                                \n" +
				"        PRODID = %s                                      \n" +  // USER_GROUP_CODE
				")                                                        \n" +
				"SELECT                                                   \n" +
				"    T2.PROCID, T2.CELLLOCID, T2.COLIDX, T2.ROWIDX        \n" +
				"    , POINT_X, POINT_Y /* 2013.05.27 SELECT 컬럼 수정*/  \n" +
				"FROM                                                     \n" +
				"    TEMP1 T1                                             \n" +
				"    , YMS_DM.D_CELLCONVERT T2                            \n" +
				"WHERE                                                    \n" +
				"    T1.PROCID LIKE T2.PROCID||'%%'                       \n" +
				""
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode  ())
				);

		return query;
	}

	/**
	 * getList
	 */
	@Override
	public List<BUBBLE_IDX_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<BUBBLE_IDX_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<BUBBLE_IDX_ReadBean>();
				return list;
			}
			
			try {
				String query = null;
				
				if ("LC".equals(Parameter.getInstance().getStrAreaCode())) {
					query = getLineLC();
				} else if ("MOD".equals(Parameter.getInstance().getStrAreaCode())) {
					query = getLineMOD();
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
							System.out.println(String.format("    PROCID    = [%s]", rs.getString("PROCID"   )));
							System.out.println(String.format("    CELLLOCID = [%s]", rs.getString("CELLLOCID")));
							System.out.println(String.format("    COLIDX    = [%s]", rs.getString("COLIDX"   )));
							System.out.println(String.format("    ROWIDX    = [%s]", rs.getString("ROWIDX"   )));
							System.out.println(String.format("    POINT_X   = [%s]", rs.getString("POINT_X"  )));
							System.out.println(String.format("    POINT_Y   = [%s]", rs.getString("POINT_Y"  )));
						}
						
						if (Flag.TRUE) {
							BUBBLE_IDX_ReadBean bean = new BUBBLE_IDX_ReadBean();

							bean.setProcId   (rs.getString("PROCID"   ));
							bean.setCellLocId(rs.getString("CELLLOCID"));
							bean.setColIdx   (rs.getString("COLIDX"   ));
							bean.setRowIdx   (rs.getString("ROWIDX"   ));
							bean.setPointX   (rs.getString("POINT_X"  ));
							bean.setPointY   (rs.getString("POINT_Y"  ));
							
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
			SystemProperties.setTesting("020");
			BasePath.getInstance();
			try {
				Logger.getInstance(BUBBLE_IDX_Main.jobId, BUBBLE_IDX_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(BUBBLE_IDX_Main.jobId);
			
			BUBBLE_IDX_OracleReader reader = new BUBBLE_IDX_OracleReader();
			
			for (BUBBLE_IDX_ReadBean bean : reader.getList(true)) {
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
