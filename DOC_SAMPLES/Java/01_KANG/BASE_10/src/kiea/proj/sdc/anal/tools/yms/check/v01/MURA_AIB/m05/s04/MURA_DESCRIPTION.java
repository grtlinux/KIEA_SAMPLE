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

package kiea.proj.sdc.anal.tools.yms.check.v01.MURA_AIB.m05.s04;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Connection;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 7.
 * @file_name MURA_DESCRIPTION.java
 *
 */
public class MURA_DESCRIPTION
{
	/*
	 * 
	 *     MURA_DESC
	 *
	 */
	
	///////////////////////////////////////////////////////////////////////////

	private static void line56LC()
	{
		if (Flag.TRUE) {
			try {
				String query = String.format("" +
						"/* MURA_DESC : L5FAB, L6FAB - LC */  \n" +
						"SELECT                               \n" +
						"    DISTINCT ITEMID, ITEMNAME        \n" +
						"FROM                                 \n" +
						"    yms_dm.d_trendmeasitem           \n" +
						"WHERE 1=1                            \n" +
						"    AND SITEID IN ('L6FAB','L5FAB')  \n" +
						"    AND ITEMNAME LIKE '%%얼룩%%'     \n" +
						""
						, "");

				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();
				
				if (Flag.TRUE) {
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData md = rs.getMetaData();

					for (int i=0; i < 30000 && rs.next(); i++) {
						
						StringBuffer sb = new StringBuffer();
						sb.append(String.format("(%4d)   ", i));
						
						for (int col=1; col <= md.getColumnCount(); col++) {
							sb.append("\t").append(rs.getString(col));
						}
						
						System.out.println(sb.toString());
					}
					
					System.out.println("------------- META DATA -----------------------");
					
					for (int i=1; i <= md.getColumnCount(); i++) {
						System.out.println(String.format("%d) [%s] [%s] [%s]", i, md.getColumnName(i), md.getColumnLabel(i), md.getColumnClassName(i)));
					}
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
				if (!Flag.TRUE) System.out.println("[" + query + "]");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void line7LC()
	{
		if (Flag.TRUE) {
			try {
				String query = String.format("" +
						"/* MURA_DESC : L7AFAB, L7BFAB - LC */           \n" +
						"SELECT                                          \n" +
						"    DISTINCT ITEMID, ITEMNAME                   \n" +
						"FROM                                            \n" +
						"    yms_dm.d_trendmeasitem                      \n" +
						"WHERE 1=1                                       \n" +
						"    AND MEASSTEPGRPID in ('L__050VI','L__050')  \n" +
						"    AND ITEMNAME LIKE '%%얼룩%%'                \n" +
						""
						, "");

				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();
				
				if (Flag.TRUE) {
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData md = rs.getMetaData();

					for (int i=0; i < 200000 && rs.next(); i++) {
						
						StringBuffer sb = new StringBuffer();
						sb.append(String.format("(%4d)   ", i));
						
						for (int col=1; col <= md.getColumnCount(); col++) {
							sb.append("\t").append(rs.getString(col));
						}
						
						System.out.println(sb.toString());
					}
					
					System.out.println("------------- META DATA -----------------------");
					
					for (int i=1; i <= md.getColumnCount(); i++) {
						System.out.println(String.format("%d) [%s] [%s] [%s]", i, md.getColumnName(i), md.getColumnLabel(i), md.getColumnClassName(i)));
					}
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
				if (!Flag.TRUE) System.out.println("[" + query + "]");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void line8LC()
	{
		if (Flag.TRUE) {
			try {
				String query = String.format("" +
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
						, "");

				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();
				
				if (Flag.TRUE) {
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData md = rs.getMetaData();

					for (int i=0; i < 10000 && rs.next(); i++) {
						
						StringBuffer sb = new StringBuffer();
						sb.append(String.format("(%4d)   ", i));
						
						for (int col=1; col <= md.getColumnCount(); col++) {
							sb.append("\t").append(rs.getString(col));
						}
						
						System.out.println(sb.toString());
					}
					
					System.out.println("------------- META DATA -----------------------");
					
					for (int i=1; i <= md.getColumnCount(); i++) {
						System.out.println(String.format("%d) [%s] [%s] [%s]", i, md.getColumnName(i), md.getColumnLabel(i), md.getColumnClassName(i)));
					}
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
				if (!Flag.TRUE) System.out.println("[" + query + "]");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void lineMOD()
	{
		if (Flag.TRUE) {
			try {
				String query = String.format("" +
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
						, "");

				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();
				
				if (Flag.TRUE) {
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData md = rs.getMetaData();

					for (int i=0; i < 10000 && rs.next(); i++) {
						
						StringBuffer sb = new StringBuffer();
						sb.append(String.format("(%4d)   ", i));
						
						for (int col=1; col <= md.getColumnCount(); col++) {
							sb.append("\t").append(rs.getString(col));
						}
						
						System.out.println(sb.toString());
					}
					
					System.out.println("------------- META DATA -----------------------");
					
					for (int i=1; i <= md.getColumnCount(); i++) {
						System.out.println(String.format("%d) [%s] [%s] [%s]", i, md.getColumnName(i), md.getColumnLabel(i), md.getColumnClassName(i)));
					}
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
				if (!Flag.TRUE) System.out.println("[" + query + "]");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args)
	{
		if (!Flag.TRUE) line56LC();    // L5FAB, L6FAB - LC
		if (!Flag.TRUE) line7LC();     // L5FAB, L6FAB - MOD
		if (!Flag.TRUE) line8LC();     // L7AFAB, L7BFAB, L8AFAB, L8ZFAB - LC
		if (Flag.TRUE) lineMOD();   // L7AFAB, L7BFAB, L8AFAB, L8ZFAB - MOD
	}
}
