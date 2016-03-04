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

package sdc.anal.mura.macro.A22.CONTACT_MAP.v04;

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
 * @file_name CONTACT_MAP_OracleReader.java
 *
 */
public class CONTACT_MAP_OracleReader extends AbstractOracleReader
{
	private List<CONTACT_MAP_ReadBean> list = new ArrayList<CONTACT_MAP_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public CONTACT_MAP_OracleReader()
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
				"/* CONTACT_MAP : L5FAB, L6FAB  */                                           \n" +
				"SELECT                                                                      \n" +
				"    LINE||'_'||PART||'_'||MAKER||'_'||UNITNAME||'_'||CONTACTMAP AS EQP_NAME \n" +
				"    , T1.*                                                                  \n" +
				"FROM                                                                        \n" +
				"    YMS_DM.D_CONTACTMAP_INFO T1                                             \n" +
				"    /* 테스트용 TABLE 로 적용시 바꿔야함*/                                  \n" +
				"WHERE                                                                       \n" +
				"    LINE = %s                                                               \n" +  // LINE
				""
				, StrUtil.quote(Parameter.getInstance().getStrLine           ())
				);

		return query;
	}

	/**
	 * 
	 * getLine78MOD
	 *
	 * @return
	 */
	private String getLine78()
	{
		String query = null;
		
		query = String.format("" +
				"/* CONTACT_MAP : L7AFAB, L7BFAB, L8AFAB, L8ZFAB */                          \n" +
				"SELECT                                                                      \n" +
				"    LINE||'_'||PART||'_'||MAKER||'_'||UNITNAME||'_'||CONTACTMAP AS EQP_NAME \n" +
				"    , T1.*                                                                  \n" +
				"FROM                                                                        \n" +
				"    YMS_DM.D_CONTACTMAP_INFO T1                                             \n" +
				"    /* 테스트용 TABLE 로 적용시 바꿔야함*/                                  \n" +
				"WHERE                                                                       \n" +
				"    LINE = %s                                                               \n" +  // LINE
				""
				, StrUtil.quote(Parameter.getInstance().getStrLine           ())
				);

		return query;
	}

	/**
	 * getList
	 */
	@Override
	public List<CONTACT_MAP_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<CONTACT_MAP_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<CONTACT_MAP_ReadBean>();
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
							System.out.println(String.format("    EQP_NAME         = [%s]", rs.getString("EQP_NAME"        )));
							System.out.println(String.format("    LINE             = [%s]", rs.getString("LINE"            )));
							System.out.println(String.format("    AREAID           = [%s]", rs.getString("AREAID"          )));
							System.out.println(String.format("    PART             = [%s]", rs.getString("PART"            )));
							System.out.println(String.format("    MAKER            = [%s]", rs.getString("MAKER"           )));
							System.out.println(String.format("    UNITNAME         = [%s]", rs.getString("UNITNAME"        )));
							System.out.println(String.format("    CONTACTMAP       = [%s]", rs.getString("CONTACTMAP"      )));
							System.out.println(String.format("    COORD_X1         = [%s]", rs.getString("COORD_X1"        )));
							System.out.println(String.format("    COORD_Y1         = [%s]", rs.getString("COORD_Y1"        )));
							System.out.println(String.format("    COORD_X2         = [%s]", rs.getString("COORD_X2"        )));
							System.out.println(String.format("    COORD_Y2         = [%s]", rs.getString("COORD_Y2"        )));
							System.out.println(String.format("    MATERIAL         = [%s]", rs.getString("MATERIAL"        )));
							System.out.println(String.format("    TYPE             = [%s]", rs.getString("TYPE"            )));
							System.out.println(String.format("    VERSION          = [%s]", rs.getString("VERSION"         )));
							System.out.println(String.format("    CONTACTMAP_ATTR1 = [%s]", rs.getString("CONTACTMAP_ATTR1")));
							System.out.println(String.format("    CONTACTMAP_ATTR2 = [%s]", rs.getString("CONTACTMAP_ATTR2")));
							System.out.println(String.format("    USERID           = [%s]", rs.getString("USERID"          )));
							System.out.println(String.format("    UPDATETIME       = [%s]", rs.getString("UPDATETIME"      )));
						}
						
						if (Flag.TRUE) {
							CONTACT_MAP_ReadBean bean = new CONTACT_MAP_ReadBean();

							bean.setEqpName        (rs.getString("EQP_NAME"        ));
							bean.setLine           (rs.getString("LINE"            ));
							bean.setAreaId         (rs.getString("AREAID"          ));
							bean.setPart           (rs.getString("PART"            ));
							bean.setMaker          (rs.getString("MAKER"           ));
							bean.setUnitName       (rs.getString("UNITNAME"        ));
							bean.setContactMap     (rs.getString("CONTACTMAP"      ));
							bean.setCoordX1        (rs.getString("COORD_X1"        ));
							bean.setCoordY1        (rs.getString("COORD_Y1"        ));
							bean.setCoordX2        (rs.getString("COORD_X2"        ));
							bean.setCoordY2        (rs.getString("COORD_Y2"        ));
							bean.setMaterial       (rs.getString("MATERIAL"        ));
							bean.setType           (rs.getString("TYPE"            ));
							bean.setVersion        (rs.getString("VERSION"         ));
							bean.setContactMapAttr1(rs.getString("CONTACTMAP_ATTR1"));
							bean.setContactMapAttr2(rs.getString("CONTACTMAP_ATTR2"));
							bean.setUserId         (rs.getString("USERID"          ));
							bean.setUpdateTime     (rs.getString("UPDATETIME"      ));
							
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
				Logger.getInstance(CONTACT_MAP_Main.jobId, CONTACT_MAP_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(CONTACT_MAP_Main.jobId);
			
			CONTACT_MAP_OracleReader reader = new CONTACT_MAP_OracleReader();
			
			for (CONTACT_MAP_ReadBean bean : reader.getList(true)) {
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
