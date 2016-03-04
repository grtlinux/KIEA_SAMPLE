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

package kiea.proj.sdc.anal.common;

import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.util.Properties;

import kiea.kr.co.tain.base.Flag;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDriver;

/**
 * @author KangSeok
 * @date 2014. 8. 6.
 * @file_name Connection.java
 *
 */
public class Connection
{
	private static final int CONNECTION_LOOP = 2;
	
	private static OracleConnection oracleConnection = null;
	
	/**
	 * 
	 * getOracle
	 *
	 * @return
	 * @throws Exception
	 */
	public static synchronized OracleConnection getOracleConnection(String user, String password) throws SQLException
	{
		if (Flag.TRUE) {
			/*
			 * 단위 매크로가 접속하고 종료하기 때문에
			 * TODO : 2014.08.11 : 나중에 어떤방식으로 할지 고민이 필요함.
			 */
			String strUser = user;
			String strPassword = password;

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
			
			if (Flag.TRUE) {
				oracleConnection =  (OracleConnection) oracleDriver.connect(strUrl, prop);
			}
			
			if (!Flag.TRUE) {
				for (int i=0; i < CONNECTION_LOOP; i++) {
					
					try {
						oracleConnection =  (OracleConnection) oracleDriver.connect(strUrl, prop);
					} catch (SQLRecoverableException e) {
						if (Flag.TRUE) System.out.println("Exception : " + e.getMessage());
						
						try { Thread.sleep(1000); } catch (InterruptedException ex) {}
						continue;
					}
					
					break;
				}
			}
		}
		
		if (!Flag.TRUE) {
			/*
			 * close처리하지 않는다면 한개의 oracle 세션으로 모든 처리를 할 수 있다.
			 */
			if (oracleConnection == null) {
				String strUser = user;
				String strPassword = password;

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
				
				oracleConnection =  (OracleConnection) oracleDriver.connect(strUrl, prop);
			}
		}

		return oracleConnection;
	}
	
	public static OracleConnection getOracleConnection() throws SQLException
	{
		if (!Flag.TRUE) return getOracleConnection("DEVTRC", "wkehdghk1");

		if (Flag.TRUE) return getOracleConnection("YMS_TEST", "test11gyms#");
		
		return null;
	}

	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			try {
				OracleConnection conn;

				conn = Connection.getOracleConnection();
				conn.close();
				
				conn = Connection.getOracleConnection();
				conn.close();
				if (Flag.TRUE) System.out.println("##### Connection.getOracleConnection() is OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
