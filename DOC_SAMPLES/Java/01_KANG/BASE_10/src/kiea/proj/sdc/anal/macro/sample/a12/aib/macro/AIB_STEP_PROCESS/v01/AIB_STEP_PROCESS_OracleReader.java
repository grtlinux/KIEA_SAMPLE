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

package kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_STEP_PROCESS.v01;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.io.AbstractOracleReader;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_STEP_PROCESS_OracleReader.java
 *
 */
public class AIB_STEP_PROCESS_OracleReader extends AbstractOracleReader
{
	private List<AIB_STEP_PROCESS_ReadBean> list = new ArrayList<AIB_STEP_PROCESS_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public AIB_STEP_PROCESS_OracleReader()
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
				"SELECT                      \n" +
				"    DIV_CODE,               \n" +
				"    LINE_CODE,              \n" +
				"    STEP_ID,                \n" +
				"    SUBSTEP_ID,             \n" +
				"    EQPUNITSLOT_ID,         \n" +
				"    USE_YN,                 \n" +
				"    REGISTER_DATE           \n" +
				"FROM                        \n" +
				"    YMS_LYA.D_STEP_PROCESS  \n" +
				""
				);

		return query;
	}

	/**
	 * getList
	 */
	@Override
	public List<AIB_STEP_PROCESS_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<AIB_STEP_PROCESS_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<AIB_STEP_PROCESS_ReadBean>();
				return list;
			}
			
			try {
				String query = null;
				
				query = getQuery();
				
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				
				OracleConnection conn = Connection.getOracleConnection();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i < 1000000 && rs.next(); i++) {
						if (!Flag.TRUE) {
							System.out.println(String.format("%3d) --------------------------------------", i));
							System.out.println(String.format("    DIV_CODE       = [%s]", rs.getString("DIV_CODE"      )));
							System.out.println(String.format("    LINE_CODE      = [%s]", rs.getString("LINE_CODE"     )));
							System.out.println(String.format("    STEP_ID        = [%s]", rs.getString("STEP_ID"       )));
							System.out.println(String.format("    SUBSTEP_ID     = [%s]", rs.getString("SUBSTEP_ID"    )));
							System.out.println(String.format("    EQPUNITSLOT_ID = [%s]", rs.getString("EQPUNITSLOT_ID")));
							System.out.println(String.format("    USE_YN         = [%s]", rs.getString("USE_YN"        )));
							System.out.println(String.format("    REGISTER_DATE  = [%s]", rs.getString("REGISTER_DATE" )));
						}
						
						if (Flag.TRUE) {
							AIB_STEP_PROCESS_ReadBean bean = new AIB_STEP_PROCESS_ReadBean();

							bean.setDivCode      (rs.getString("DIV_CODE"      ));
							bean.setLineCode     (rs.getString("LINE_CODE"     ));
							bean.setStepId       (rs.getString("STEP_ID"       ));
							bean.setSubStepId    (rs.getString("SUBSTEP_ID"    ));
							bean.setEqpUnitSlotId(rs.getString("EQPUNITSLOT_ID"));
							bean.setUseYn        (rs.getString("USE_YN"        ));
							bean.setRegisterDate (rs.getString("REGISTER_DATE" ));
							
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
			Parameter.getInstance(2);
			AIB_STEP_PROCESS_OracleReader reader = new AIB_STEP_PROCESS_OracleReader();
			
			for (AIB_STEP_PROCESS_ReadBean bean : reader.getList(true)) {
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
