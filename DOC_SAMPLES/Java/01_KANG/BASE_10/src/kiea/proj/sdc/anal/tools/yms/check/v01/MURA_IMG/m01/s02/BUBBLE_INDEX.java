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

package kiea.proj.sdc.anal.tools.yms.check.v01.MURA_IMG.m01.s02;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.util.StrUtil;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 11.
 * @file_name BUBBLE_INDEX.java
 *
 */
public class BUBBLE_INDEX
{
	/*
	 * 
	 *     BUBBLE_INDEX
	 *
	 */
	
	///////////////////////////////////////////////////////////////////////////

	private static void lineLC()
	{
		if (Flag.TRUE) {
			try {
				String query = String.format("" +
						"/* BUBBLE_INDEX : LC  */                  \n" +
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

				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();
				
				if (Flag.TRUE) {
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData md = rs.getMetaData();

					for (int i=0; i < 1000 && rs.next(); i++) {
						
						StringBuffer sb = new StringBuffer();
						sb.append(String.format("(%4d)   ", i));
						
						for (int col=1; col <= md.getColumnCount(); col++) {
							sb.append("\t").append(rs.getString(col));
						}
						
						System.out.println(sb.toString());
					}
					
					System.out.println("------------- META DATA -----------------------");
					
					for (int i=1; i <= md.getColumnCount(); i++) {
						System.out.println(String.format("%2d) [%-30s] [%-50s]", i, md.getColumnName(i), md.getColumnClassName(i)));
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
						"/* BUBBLE_INDEX : MOD  */                                \n" +
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
						System.out.println(String.format("%2d) [%-30s] [%-50s]", i, md.getColumnName(i), md.getColumnClassName(i)));
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
		if (!Flag.TRUE) {
			if (Flag.TRUE) lineLC();    // LC
			if (!Flag.TRUE) lineMOD();   // MOD
		}

		if (Flag.TRUE) {
			String strAreaCode = Parameter.getInstance().getStrAreaCode();
			
			if ("LC".equals(strAreaCode)) {
				lineLC();
			} else if ("MOD".equals(strAreaCode)) {
				lineMOD();
			}
		}
	}
}
