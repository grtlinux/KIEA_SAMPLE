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

package sdc.anal.lya.macro.A11.INSPSTEP_DEFECTTRACE.v04;

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
 * @file_name AIB_INSPSTEP_DEFECTTRACE_OracleReader.java
 *
 */
public class INSPSTEP_DEFECTTRACE_OracleReader extends AbstractOracleReader
{
	private List<INSPSTEP_DEFECTTRACE_ReadBean> list = new ArrayList<INSPSTEP_DEFECTTRACE_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public INSPSTEP_DEFECTTRACE_OracleReader()
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
				"SELECT                             \n" +
				"    SITEID,                        \n" +
				"    EQPUNITID,                     \n" +
				"    INSPSTEPGRPID,                 \n" +
				"    INSPSTEPGRPNAME,               \n" +
				"    AREAID,                        \n" +
				"    INSPSTEPGRPTYPE,               \n" +
				"    ORD,                           \n" +
				"    COORDCONVERTYN                 \n" +
				"FROM                               \n" +
				"    YMS_DM.D_INSPSTEP_DEFECTTRACE  \n" +
				""
				);

		return query;
	}

	/**
	 * getList
	 */
	@Override
	public List<INSPSTEP_DEFECTTRACE_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<INSPSTEP_DEFECTTRACE_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<INSPSTEP_DEFECTTRACE_ReadBean>();
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
							System.out.println(String.format("    SITEID          = [%s]", rs.getString("SITEID"         )));
							System.out.println(String.format("    EQPUNITID       = [%s]", rs.getString("EQPUNITID"      )));
							System.out.println(String.format("    INSPSTEPGRPID   = [%s]", rs.getString("INSPSTEPGRPID"  )));
							System.out.println(String.format("    INSPSTEPGRPNAME = [%s]", rs.getString("INSPSTEPGRPNAME")));
							System.out.println(String.format("    AREAID          = [%s]", rs.getString("AREAID"         )));
							System.out.println(String.format("    INSPSTEPGRPTYPE = [%s]", rs.getString("INSPSTEPGRPTYPE")));
							System.out.println(String.format("    ORD             = [%s]", rs.getString("ORD"            )));
							System.out.println(String.format("    COORDCONVERTYN  = [%s]", rs.getString("COORDCONVERTYN" )));
						}
						
						if (Flag.TRUE) {
							INSPSTEP_DEFECTTRACE_ReadBean bean = new INSPSTEP_DEFECTTRACE_ReadBean();

							bean.setSiteId         (rs.getString("SITEID"         ));
							bean.setEqpUnitId      (rs.getString("EQPUNITID"      ));
							bean.setInspStepGrpId  (rs.getString("INSPSTEPGRPID"  ));
							bean.setInspStepGrpName(rs.getString("INSPSTEPGRPNAME"));
							bean.setAreaId         (rs.getString("AREAID"         ));
							bean.setInspStepGrpType(rs.getString("INSPSTEPGRPTYPE"));
							bean.setOrd            (rs.getString("ORD"            ));
							bean.setCoordConvertYn (rs.getString("COORDCONVERTYN" ));
							
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
				Logger.getInstance(INSPSTEP_DEFECTTRACE_Main.jobId, INSPSTEP_DEFECTTRACE_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(INSPSTEP_DEFECTTRACE_Main.jobId);
			INSPSTEP_DEFECTTRACE_OracleReader reader = new INSPSTEP_DEFECTTRACE_OracleReader();
			
			for (INSPSTEP_DEFECTTRACE_ReadBean bean : reader.getList(true)) {
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
