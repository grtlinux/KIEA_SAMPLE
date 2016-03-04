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

package sdc.anal.lya.macro.A11.PRODUCT.v01;

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
 * @file_name AIB_PRODUCT_OracleReader.java
 *
 */
public class PRODUCT_OracleReader extends AbstractOracleReader
{
	private List<PRODUCT_ReadBean> list = new ArrayList<PRODUCT_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public PRODUCT_OracleReader()
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
				"SELECT                \n" +
				"    PRODID,           \n" +
				"    PROCID,           \n" +
				"    PRODDESC,         \n" +
				"    PRODGRPNAME,      \n" +
				"    REPPRODID,        \n" +
				"    CELLQTY,          \n" +
				"    BINGRADE,         \n" +
				"    CUSTNAME,         \n" +
				"    SITEID,           \n" +
				"    INCH,             \n" +
				"    PRODTYPE,         \n" +
				"    USETYPE,          \n" +
				"    SUMYN,            \n" +
				"    LCCODE,           \n" +
				"    PRODMNGCD         \n" +
				"FROM                  \n" +
				"    YMS_DM.D_PRODUCT  \n" +
				""
				);

		return query;
	}

	/**
	 * getList
	 */
	@Override
	public List<PRODUCT_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<PRODUCT_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<PRODUCT_ReadBean>();
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
							System.out.println(String.format("    PRODID      = [%s]", rs.getString("PRODID"     )));
							System.out.println(String.format("    PROCID      = [%s]", rs.getString("PROCID"     )));
							System.out.println(String.format("    PRODDESC    = [%s]", rs.getString("PRODDESC"   )));
							System.out.println(String.format("    PRODGRPNAME = [%s]", rs.getString("PRODGRPNAME")));
							System.out.println(String.format("    REPPRODID   = [%s]", rs.getString("REPPRODID"  )));
							System.out.println(String.format("    CELLQTY     = [%s]", rs.getString("CELLQTY"    )));
							System.out.println(String.format("    BINGRADE    = [%s]", rs.getString("BINGRADE"   )));
							System.out.println(String.format("    CUSTNAME    = [%s]", rs.getString("CUSTNAME"   )));
							System.out.println(String.format("    SITEID      = [%s]", rs.getString("SITEID"     )));
							System.out.println(String.format("    INCH        = [%s]", rs.getString("INCH"       )));
							System.out.println(String.format("    PRODTYPE    = [%s]", rs.getString("PRODTYPE"   )));
							System.out.println(String.format("    USETYPE     = [%s]", rs.getString("USETYPE"    )));
							System.out.println(String.format("    SUMYN       = [%s]", rs.getString("SUMYN"      )));
							System.out.println(String.format("    LCCODE      = [%s]", rs.getString("LCCODE"     )));
							System.out.println(String.format("    PRODMNGCD   = [%s]", rs.getString("PRODMNGCD"  )));
						}
						
						if (Flag.TRUE) {
							PRODUCT_ReadBean bean = new PRODUCT_ReadBean();

							bean.setProdId     (rs.getString("PRODID"     ));
							bean.setProcId     (rs.getString("PROCID"     ));
							bean.setProdDesc   (rs.getString("PRODDESC"   ));
							bean.setProdGrpName(rs.getString("PRODGRPNAME"));
							bean.setRepProdId  (rs.getString("REPPRODID"  ));
							bean.setCellQty    (rs.getString("CELLQTY"    ));
							bean.setBinGrade   (rs.getString("BINGRADE"   ));
							bean.setCustName   (rs.getString("CUSTNAME"   ));
							bean.setSiteId     (rs.getString("SITEID"     ));
							bean.setInch       (rs.getString("INCH"       ));
							bean.setProdType   (rs.getString("PRODTYPE"   ));
							bean.setUseType    (rs.getString("USETYPE"    ));
							bean.setSumYn      (rs.getString("SUMYN"      ));
							bean.setLcCode     (rs.getString("LCCODE"     ));
							bean.setProdMngCd  (rs.getString("PRODMNGCD"  ));
							
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
				Logger.getInstance(PRODUCT_Main.jobId, PRODUCT_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(PRODUCT_Main.jobId);
			PRODUCT_OracleReader reader = new PRODUCT_OracleReader();
			
			for (PRODUCT_ReadBean bean : reader.getList(true)) {
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
