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

package sdc.anal.mura.macro.A25.RP_MUR_ERD_FDC_SUM01.v04;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
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
 * @file_name MOD_BIN_OracleReader.java
 *
 */
public class RP_MUR_ERD_FDC_SUM01_OracleReader extends AbstractOracleReader
{
	private List<RP_MUR_ERD_FDC_SUM01_Bean> list = new ArrayList<RP_MUR_ERD_FDC_SUM01_Bean>();
	
	private Set<String> setEQP = new HashSet<String>();
	private Set<String> setGLASS = new HashSet<String>();
	
	/**
	 * Sample02OracleReader
	 */
	public RP_MUR_ERD_FDC_SUM01_OracleReader()
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
	private String getQuery()
	{
		int i;
		StringBuffer sbEqp = new StringBuffer();
		StringBuffer sbGlass = new StringBuffer();
		
		i = 0;
		for (String eqpId : setEQP) {
			if (i > 0)
				sbEqp.append(",");
			
			sbEqp.append(StrUtil.quote(eqpId));
			
			i++;
		}
		
		if (i == 0) {
			sbEqp.append("''");
		}
		
		i = 0;
		for (String glassId : setGLASS) {
			if (i > 0)
				sbGlass.append(",");
			
			sbGlass.append(StrUtil.quote(glassId));
			
			i++;
		}
		
		if (i == 0) {
			sbGlass.append("''");
		}

		String query = null;
		
		query = String.format(""
				+ "SELECT /*+PARALLEL(2) ORDERED */                                         \n"
				+ "    T1.LINE                                                              \n"
				+ "    , T1.PART                                                            \n"
				+ "    , T1.EQPMODEL                                                        \n"
				+ "    , T1.EQPID                                                           \n"
				+ "    , T1.MODULE_NAME                                                     \n"
				+ "    , T1.PROCID                                                          \n"
				+ "    , T1.PRODID                                                          \n"
				+ "    , T1.PPID                                                            \n"
				+ "    , T1.GLASSID                                                         \n"
				+ "    , T1.PROCESSSTEP                                                     \n"
				+ "    , T1.BEGINSTEP                                                       \n"
				+ "    , T1.SENSOR_NAME                                                     \n"
				+ "    , T1.PARAM                                                           \n"
				+ "    , T1.PARAM_VALUE                                                     \n"
				+ "    , T1.USL                                                             \n"
				+ "    , T1.LSL                                                             \n"
				+ "    , T1.BEGINTIME                                                       \n"
				+ "FROM                                                                     \n"
				+ "    YMS_DW.DW_EQP_FDC_SUM T1                                             \n"
				+ "WHERE 1=1                                                                \n"
				//+ "    AND ROWNUM <= 10                                                     \n"
				+ "    AND T1.LINE        =  %s                                             \n"  // LINE
				+ "    AND T1.BEGINTIME >= TO_DATE(%s,'YYYYMMDDHH24MISS') - 7               \n"  // TODATETIME
				+ "    AND T1.BEGINTIME <= TO_DATE(%s,'YYYYMMDDHH24MISS')                   \n"  // TODATETIME
				+ "    AND T1.EQPID       IN ( %s )                                         \n"
				+ "    AND T1.GLASSID     IN ( %s )                                         \n"
				//+ "    AND T1.BEGINTIME >= SYSDATE - 1                                      \n"
				+ "    AND T1.PARAM       IN ('MEAN')                                       \n"
				+ ""
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, sbEqp.toString()
				, sbGlass.toString()
				);

		return query;
	}

	/**
	 * getList
	 */
	@Override
	public List<RP_MUR_ERD_FDC_SUM01_Bean> getList()
	{
		return getList(false);
	}
	
	public List<RP_MUR_ERD_FDC_SUM01_Bean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<RP_MUR_ERD_FDC_SUM01_Bean>();
				return list;
			}
			
			try {
				String query = null;
				
				query = getQuery();
				
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				if (Flag.TRUE) Logger.info("[" + query + "]");
				
				OracleConnection conn = Connection04.getInstance().getConn();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i >= 0 && rs.next(); i++) {                     // TODO : 2014.08.26 : 최대건수 처리
						if (!Flag.TRUE) {
							System.out.println(String.format("%3d) --------------------------------------", i));
							System.out.println(String.format("    LINE        = [%s]", rs.getString("LINE"       )));
							System.out.println(String.format("    PART        = [%s]", rs.getString("PART"       )));
							System.out.println(String.format("    EQPMODEL    = [%s]", rs.getString("EQPMODEL"   )));
							System.out.println(String.format("    EQPID       = [%s]", rs.getString("EQPID"      )));
							System.out.println(String.format("    MODULE_NAME = [%s]", rs.getString("MODULE_NAME")));
							System.out.println(String.format("    PROCID      = [%s]", rs.getString("PROCID"     )));
							System.out.println(String.format("    PRODID      = [%s]", rs.getString("PRODID"     )));
							System.out.println(String.format("    PPID        = [%s]", rs.getString("PPID"       )));
							System.out.println(String.format("    GLASSID     = [%s]", rs.getString("GLASSID"    )));
							System.out.println(String.format("    PROCESSSTEP = [%s]", rs.getString("PROCESSSTEP")));
							System.out.println(String.format("    BEGINSTEP   = [%s]", rs.getString("BEGINSTEP"  )));
							System.out.println(String.format("    SENSOR_NAME = [%s]", rs.getString("SENSOR_NAME")));
							System.out.println(String.format("    PARAM       = [%s]", rs.getString("PARAM"      )));
							System.out.println(String.format("    PARAM_VALUE = [%s]", rs.getString("PARAM_VALUE")));
							System.out.println(String.format("    USL         = [%s]", rs.getString("USL"        )));
							System.out.println(String.format("    LSL         = [%s]", rs.getString("LSL"        )));
							System.out.println(String.format("    BEGINTIME   = [%s]", rs.getString("BEGINTIME"  )));
						}
						
						if (Flag.TRUE) {
							RP_MUR_ERD_FDC_SUM01_Bean bean = new RP_MUR_ERD_FDC_SUM01_Bean();

							bean.setLine       (rs.getString("LINE"       ));
							bean.setPart       (rs.getString("PART"       ));
							bean.setEqpModel   (rs.getString("EQPMODEL"   ));
							bean.setEqpId      (rs.getString("EQPID"      ));
							bean.setModuleName (rs.getString("MODULE_NAME"));
							bean.setProcId     (rs.getString("PROCID"     ));
							bean.setProdId     (rs.getString("PRODID"     ));
							bean.setPpid       (rs.getString("PPID"       ));
							bean.setGlassId    (rs.getString("GLASSID"    ));
							bean.setProcessStep(rs.getString("PROCESSSTEP"));
							bean.setBeginStep  (rs.getString("BEGINSTEP"  ));
							bean.setSensorName (rs.getString("SENSOR_NAME"));
							bean.setParam      (rs.getString("PARAM"      ));
							bean.setParamValue (rs.getString("PARAM_VALUE"));
							bean.setUsl        (rs.getString("USL"        ));
							bean.setLsl        (rs.getString("LSL"        ));
							bean.setBeginTime  (rs.getString("BEGINTIME"  ));
							
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
	
	public void setEqpGlassList(List<SO_SYS_GLASS_LIST2_Bean> inList1, List<RP_MUR_WIP_SUMM1_Bean> inList2)
	{
		if (Flag.TRUE) {
			for (SO_SYS_GLASS_LIST2_Bean bean : inList1) {
				
				setGLASS.add(bean.getGlassId());
				
				if (!Flag.TRUE) Print.println("SO_SYS_CELLID_LIST2_Bean > %s", bean);
			}
			
			if (!Flag.TRUE) {
				/*
				 * 확인출력
				 */
				for (String glassId : setGLASS) {
					Print.println("GLASSID : %s", glassId);
				}
			}
		}
		
		if (Flag.TRUE) {
			for (RP_MUR_WIP_SUMM1_Bean bean : inList2) {
				
				setEQP.add(bean.getSuspiciousEqp());
				
				if (!Flag.TRUE) Print.println("RP_SYS_WIP_SUMM_Bean > %s", bean);
			}
			
			if (!Flag.TRUE) {
				/*
				 * 확인출력
				 */
				for (String eqpId : setEQP) {
					Print.println("EQPID : %s", eqpId);
				}
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			SystemProperties.setTesting("010");
			BasePath.getInstance();
			try {
				Logger.getInstance(RP_MUR_ERD_FDC_SUM01_Main.jobId, RP_MUR_ERD_FDC_SUM01_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(RP_MUR_ERD_FDC_SUM01_Main.jobId);
			
			RP_MUR_ERD_FDC_SUM01_OracleReader reader = new RP_MUR_ERD_FDC_SUM01_OracleReader();
			
			for (RP_MUR_ERD_FDC_SUM01_Bean bean : reader.getList(true)) {
				System.out.println(bean);
			}
			
			System.out.println("the read ok !!! size=" + reader.getList(true).size());
		}
		
		Logger.exit();
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
