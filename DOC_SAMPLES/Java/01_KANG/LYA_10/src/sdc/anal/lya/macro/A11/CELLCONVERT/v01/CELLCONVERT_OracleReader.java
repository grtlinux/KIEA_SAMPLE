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

package sdc.anal.lya.macro.A11.CELLCONVERT.v01;

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
 * @file_name CELLCONVERT_OracleReader.java
 *
 */
public class CELLCONVERT_OracleReader extends AbstractOracleReader
{
	private List<CELLCONVERT_ReadBean> list = new ArrayList<CELLCONVERT_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public CELLCONVERT_OracleReader()
	{
		if (Flag.TRUE) {
		}
	}

	/**
	 * 
	 * getQuery
	 *
	 * @return
	 */
	private String getQuery()
	{
		String query = null;
		
		query = String.format("" +
				"SELECT                    \n" +
				"    PROCID,               \n" +
				"    CELLLOCID,            \n" +
				"    POINT_X,              \n" +
				"    POINT_Y,              \n" +
				"    WIDTH,                \n" +
				"    HEIGHT,               \n" +
				"    GATERESOLUTION,       \n" +
				"    DATARESOLUTION,       \n" +
				"    COLIDX,               \n" +
				"    ROWIDX,               \n" +
				"    PIXCEL_X,             \n" +
				"    PIXCEL_Y,             \n" +
				"    X_LOGIC1,             \n" +
				"    Y_LOGIC1,             \n" +
				"    X_LOGIC2,             \n" +
				"    Y_LOGIC2,             \n" +
				"    SITEID,               \n" +
				"    GLASS_HEIGHT          \n" +
				"FROM                      \n" +
				"    YMS_DM.D_CELLCONVERT  \n" +
				""
				);

		return query;
	}

	/**
	 * getList
	 */
	@Override
	public List<CELLCONVERT_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<CELLCONVERT_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<CELLCONVERT_ReadBean>();
				return list;
			}
			
			try {
				String query = null;
				
				query = getQuery();
				
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				
				OracleConnection conn = Connection04.getInstance().getConn();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i < 1000000 && rs.next(); i++) {
						if (!Flag.TRUE) {
							System.out.println(String.format("%3d) --------------------------------------", i));
							System.out.println(String.format("    PROCID         = [%s]", rs.getString("PROCID"        )));
							System.out.println(String.format("    CELLLOCID      = [%s]", rs.getString("CELLLOCID"     )));
							System.out.println(String.format("    POINT_X        = [%s]", rs.getString("POINT_X"       )));
							System.out.println(String.format("    POINT_Y        = [%s]", rs.getString("POINT_Y"       )));
							System.out.println(String.format("    WIDTH          = [%s]", rs.getString("WIDTH"         )));
							System.out.println(String.format("    HEIGHT         = [%s]", rs.getString("HEIGHT"        )));
							System.out.println(String.format("    GATERESOLUTION = [%s]", rs.getString("GATERESOLUTION")));
							System.out.println(String.format("    DATARESOLUTION = [%s]", rs.getString("DATARESOLUTION")));
							System.out.println(String.format("    COLIDX         = [%s]", rs.getString("COLIDX"        )));
							System.out.println(String.format("    ROWIDX         = [%s]", rs.getString("ROWIDX"        )));
							System.out.println(String.format("    PIXCEL_X       = [%s]", rs.getString("PIXCEL_X"      )));
							System.out.println(String.format("    PIXCEL_Y       = [%s]", rs.getString("PIXCEL_Y"      )));
							System.out.println(String.format("    X_LOGIC1       = [%s]", rs.getString("X_LOGIC1"      )));
							System.out.println(String.format("    Y_LOGIC1       = [%s]", rs.getString("Y_LOGIC1"      )));
							System.out.println(String.format("    X_LOGIC2       = [%s]", rs.getString("X_LOGIC2"      )));
							System.out.println(String.format("    Y_LOGIC2       = [%s]", rs.getString("Y_LOGIC2"      )));
							System.out.println(String.format("    SITEID         = [%s]", rs.getString("SITEID"        )));
							System.out.println(String.format("    GLASS_HEIGHT   = [%s]", rs.getString("GLASS_HEIGHT"  )));
						}
						
						if (Flag.TRUE) {
							CELLCONVERT_ReadBean bean = new CELLCONVERT_ReadBean();

							bean.setProcId        (rs.getString("PROCID"        ));
							bean.setCellLocId     (rs.getString("CELLLOCID"     ));
							bean.setPointX        (rs.getString("POINT_X"       ));
							bean.setPointY        (rs.getString("POINT_Y"       ));
							bean.setWidth         (rs.getString("WIDTH"         ));
							bean.setHeight        (rs.getString("HEIGHT"        ));
							bean.setGateResolution(rs.getString("GATERESOLUTION"));
							bean.setDataResolution(rs.getString("DATARESOLUTION"));
							bean.setColIdx        (rs.getString("COLIDX"        ));
							bean.setRowIdx        (rs.getString("ROWIDX"        ));
							bean.setPixcelX       (rs.getString("PIXCEL_X"      ));
							bean.setPixcelY       (rs.getString("PIXCEL_Y"      ));
							bean.setXLogic1       (rs.getString("X_LOGIC1"      ));
							bean.setYLogic1       (rs.getString("Y_LOGIC1"      ));
							bean.setXLogic2       (rs.getString("X_LOGIC2"      ));
							bean.setYLogic2       (rs.getString("Y_LOGIC2"      ));
							bean.setSiteId        (rs.getString("SITEID"        ));
							bean.setGlassHeight   (rs.getString("GLASS_HEIGHT"  ));
							
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
				Logger.getInstance(CELLCONVERT_Main.jobId, CELLCONVERT_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(CELLCONVERT_Main.jobId);

			CELLCONVERT_OracleReader reader = new CELLCONVERT_OracleReader();
			
			for (CELLCONVERT_ReadBean bean : reader.getList(true)) {
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
