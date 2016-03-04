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

package kiea.proj.sdc.anal.macro.sample.a11.mod.io;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.io.AbstractOracleReader;
import kiea.proj.sdc.anal.macro.sample.a11.mod.bean.MOD_RJ_ReadBean;
import kiea.proj.sdc.anal.util.StrUtil;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MOD_RJ_OracleReader.java
 *
 */
public class MOD_RJ_OracleReader extends AbstractOracleReader
{
	private List<MOD_RJ_ReadBean> list = new ArrayList<MOD_RJ_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public MOD_RJ_OracleReader()
	{
		if (Flag.TRUE) {
		}
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
	            "/****************************/                                   \n" +
	            "/***    RJ 시간정보      ****/                                   \n" +
	            "/****************************/                                   \n" +
	            "SELECT  /*+ PARALLEL(2) */                                       \n" +
	            "    M.CELLID           AS CELL_ID                                \n" +
	            "    , M.INSPSTEPTYPE   AS INSPSTEPTYPE                           \n" +
	            "    , D.DEFECTNAME     AS DEFECTNAME                             \n" +
	            "    , M.DEFECTCD       AS DEFECTCODE                             \n" +
	            "    , M.INSPSTEPID     AS INSPSTEPID                             \n" +
	            "    , M.INSPDATE       AS INSPTIME                               \n" +
	            "    , W.WORKERNAME     AS WORKERNAME                             \n" +
	            "    , W.WORKERID       AS WORKERID                               \n" +
	            "    , M.INSPEQPID      AS INSPEQPID                              \n" +
	            "FROM                                                             \n" +
	            "    yms_dm.H_INSPDEFECT                    M                     \n" +
	            "    , yms_dw.DW_MD_WORKER                  W                     \n" +
	            "    , yms_dm.D_DEFECT                      D                     \n" +
	            "WHERE 1=1                                                        \n" +
	            //"    AND ROWNUM < 10                                              \n" +
	            "    AND M.SITEID   =  %s                                         \n" +  // LINE
	            "    AND M.INSPHOUR >= %s                                         \n" +  // FROM_ETL_HOUR
	            "    AND M.INSPHOUR <= %s                                         \n" +  // TO_ETL_HOUR
	            "    AND M.DEFECTCD =  %s                                         \n" +  // DEFECT_GROUP_CODE
	            "    AND M.INSPSTEPTYPE IN ( %s )                                 \n" +  // SUB_AREA_CODE
	            "    AND ( M.INSPSTEPTYPE IN ( 'MMT','MFT','SDSZ_FT','SDSZ_MT' )  \n" +
	            "        OR  M.INSPSTEPTYPE LIKE '%%FT' )                         \n" +
	            "    AND D.DEFECTCD =  M.DEFECTCD                                 \n" +
	            "    AND W.WORKERID =  M.WORKERID                                 \n" +
				""
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
				);

		return query;
	}

	/**
	 * getList
	 */
	public List<MOD_RJ_ReadBean> getList()
	{
		if (Flag.TRUE) {
			try {
				String query = null;
				
				if ("MOD".equals(Parameter.getInstance().getStrAreaCode())) {
					query = getLineMOD();
				} else {
					return list;
				}
				
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				
				OracleConnection conn = Connection.getOracleConnection();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i < 100000 && rs.next(); i++) {                     // TODO : 2014.08.26 : 최대건수 처리
						if (!Flag.TRUE) {
							System.out.println(String.format("%3d) --------------------------------------", i));
							System.out.println(String.format("    CELL_ID      = [%s]", rs.getString("CELL_ID"     )));
							System.out.println(String.format("    INSPSTEPTYPE = [%s]", rs.getString("INSPSTEPTYPE")));
							System.out.println(String.format("    DEFECTNAME   = [%s]", rs.getString("DEFECTNAME"  )));
							System.out.println(String.format("    DEFECTCODE   = [%s]", rs.getString("DEFECTCODE"  )));
							System.out.println(String.format("    INSPSTEPID   = [%s]", rs.getString("INSPSTEPID"  )));
							System.out.println(String.format("    INSPTIME     = [%s]", rs.getString("INSPTIME"    )));
							System.out.println(String.format("    WORKERNAME   = [%s]", rs.getString("WORKERNAME"  )));
							System.out.println(String.format("    WORKERID     = [%s]", rs.getString("WORKERID"    )));
							System.out.println(String.format("    INSPEQPID    = [%s]", rs.getString("INSPEQPID"   )));
						}
						
						if (Flag.TRUE) {
							MOD_RJ_ReadBean bean = new MOD_RJ_ReadBean();

							bean.setCellId      (rs.getString("CELL_ID"     ));
							bean.setInspStepType(rs.getString("INSPSTEPTYPE"));
							bean.setDefectName  (rs.getString("DEFECTNAME"  ));
							bean.setDefectCode  (rs.getString("DEFECTCODE"  ));
							bean.setInspStepId  (rs.getString("INSPSTEPID"  ));
							bean.setInspTime    (rs.getString("INSPTIME"    ));
							bean.setWorkerName  (rs.getString("WORKERNAME"  ));
							bean.setWorkerId    (rs.getString("WORKERID"    ));
							bean.setInspEqpId   (rs.getString("INSPEQPID"   ));
							
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
			Parameter.getInstance(7);
			MOD_RJ_OracleReader reader = new MOD_RJ_OracleReader();
			reader.getList();
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
