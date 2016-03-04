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

package kiea.z01.ztest.t01.oracle.thin;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import kiea.kr.co.tain.base.Flag;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDriver;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name YmsTableListTestMain.java
 *
 */
public class YmsTableListTestMain
{

	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * test01
	 *
	 */
	private static void test01()
	{
		if (Flag.TRUE) {
			try {
				String strUser = "YMS_TEST";
				String strPassword = "test11gyms#";
				String strUrl = "jdbc:oracle:thin:@" +
						"(DESCRIPTION=" +
						"    (ADDRESS_LIST=(LOAD_BALANCE=ON)" +
						"        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.221)(PORT=1521))" +
						"        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.222)(PORT=1521))" +
						"        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.223)(PORT=1521))" +
						"        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.241)(PORT=1521))" +
						"        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.242)(PORT=1521))" +
						"        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.243)(PORT=1521))" +
						"    )" +
						"    (CONNECT_DATA =(SERVER=DEDICATED)" +
						"        (SERVICE_NAME=ymsdb)" +
						"    )" +
						")";
						
				
				OracleDriver oracleDriver = new OracleDriver();
				
				Properties prop = new Properties();
				prop.setProperty("user", strUser);
				prop.setProperty("password", strPassword);
				
				OracleConnection conn = (OracleConnection) oracleDriver.connect(strUrl, prop);

				if (Flag.TRUE) {
					List<String> list = new ArrayList<String>();
					
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select table_name from tabs");
					for (int i=0; i < 1000 && rs.next(); i++) {
						if (!Flag.TRUE) System.out.println(String.format("%3d) = [%s]", i, rs.getString(1)));
						list.add(rs.getString(1));
					}
					
					if (Flag.TRUE) {
						Collections.sort(list, new Comparator<String>() {
							@Override
							public int compare(String obj1, String obj2) {
								return obj1.compareTo(obj2);
							}
						});
					} else {
						Collections.sort(list);
					}

					int i = 0;
					for (String line : list) {
						if (Flag.TRUE) System.out.println(String.format("%3d) = [%s]", i++, line));
					}
				}
				
				if (!Flag.TRUE) {
					List<String> list = new ArrayList<String>();
					
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select table_name from tabs");
					
					for (int i=0; i < 1000 && rs.next(); i++) {
						if (!Flag.TRUE) System.out.println(String.format("%3d) = [%s]", i, rs.getString(1)));
						list.add(rs.getString(1));
					}
					
					Collections.sort(list);
					
					for (String line : list) {
						System.out.println("[" + line + "]");
					}
				}
				
				conn.close();
				System.out.println("OK!");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * main
	 *
	 * @param args
	 */
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
