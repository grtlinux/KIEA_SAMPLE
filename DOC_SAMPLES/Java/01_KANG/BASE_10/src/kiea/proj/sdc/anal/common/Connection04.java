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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.sql.Statement;
import java.util.Properties;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.util.AnalProperties;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDriver;

/**
 * @author KangSeok
 * @date 2014. 8. 6.
 * @file_name Connection.java
 *
 */
public class Connection04
{
	private static final int CONNECTION_TRY_LOOP = 3;
	private static final int WAIT_TIME = 5000;

	private String strUser = null;
	private String strPassword = null;
	private String strUrl = null;
	
	private OracleConnection conn = null;

	/**
	 * private constructor
	 * 
	 * @param user
	 * @param pass
	 */
	private Connection04(String user, String pass)
	{
		if (Flag.TRUE) {
			/*
			 * 접속정보를 세팅한다.
			 */
			this.strUser = user;
			this.strPassword = pass;

			this.strUrl = ""
					+ "jdbc:oracle:thin:@"
					+ "(DESCRIPTION="
					+ "    (ADDRESS_LIST=(LOAD_BALANCE=ON)"
					+ "        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.221)(PORT=1521))"
					+ "        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.222)(PORT=1521))"
					+ "        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.223)(PORT=1521))"
					+ "        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.241)(PORT=1521))"
					+ "        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.242)(PORT=1521))"
					+ "        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.243)(PORT=1521))"
					+ "    )"
					+ "    (CONNECT_DATA =(SERVER=DEDICATED)"
					+ "        (SERVICE_NAME=ymsdb)"
					+ "    )"
					+ ")";
		}
		
		if (Flag.TRUE) {
			/*
			 * 접속을 시도한다. 연속접속시도
			 */
			this.conn = null;
			
			for (int i=0; this.conn == null && i < CONNECTION_TRY_LOOP; i++) {
				
				OracleDriver oracleDriver = new OracleDriver();
				
				Properties prop = new Properties();
				prop.setProperty("user", strUser);
				prop.setProperty("password", strPassword);
				
				if (Flag.TRUE) {
					try {
						this.conn =  (OracleConnection) oracleDriver.connect(strUrl, prop);
						if (Flag.TRUE) Print.println("STATUS : Oracle 접속정보 OK!! (TRY IDX=%d) conn != null", i);
					} catch (SQLRecoverableException e) {
						// 메시지 -> IO 오류: The Network Adapter could not establish the connection
						e.printStackTrace();
						this.conn = null;
						try { Thread.sleep(WAIT_TIME); } catch (InterruptedException ex) {}
					} catch (SQLException e) {
						e.printStackTrace();
						this.conn = null;
						try { Thread.sleep(WAIT_TIME); } catch (InterruptedException ex) {}
					} finally {
					}
				}
			}
			
			if (this.conn == null) {
				/*
				 * CONNECTION 실패
				 */
				System.out.println("ERROR : 접속시도 에러 (" + CONNECTION_TRY_LOOP + " 번)");
				System.exit(-1);
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public OracleConnection getConn()
	{
		OracleConnection connection = null;
		
		if (Flag.TRUE) {
			connection = this.conn;
		}
		
		return connection;
	}
	
	/**
	 * 
	 * @param flag
	 */
	public void close(boolean flag)
	{
		if (Flag.TRUE) {
			if (flag) {
				/*
				 * flag = TRUE 이면 close가 정상적용되어 conn.close 한다.
				 */
				try {
					this.conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					this.conn = null;
					Connection04.instance = null;
				}
			}
		}
	}
	
	/**
	 * 
	 */
	public void close()
	{
		if (Flag.TRUE) close(false);
	}
	
	/**
	 * get schema
	 * 
	 * @param testSchema
	 * @param realSchema
	 * @return
	 */
	@SuppressWarnings("unused")
	private String _getSchema(String testSchema, String realSchema)
	{
		String ret = null;
		
		if (Flag.TRUE) {
			if ("R".equals(AnalProperties.getInstance().getSystemType())) {
				ret = realSchema;
			} else {
				ret = testSchema;
			}
		}
		
		return ret;
	}
	
	/**
	 * get schema
	 * 
	 * @return
	 */
	public String getSchema()
	{
		return this.strUser;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	/*
	 * Singleton
	 */
	private static Connection04 instance = null;
	
	public static Connection04 getInstance()
	{
		if (Flag.TRUE) {
			/*
			 * TODO : 2014.11.26
			 * ANAL 분석시스템의 TYPE이 무엇인가에 따른 접속계정
			 *     D (Development 개발) : getInstance("YMS_TEST", "test11gyms#");
			 *     T (Test 테스트)      : getInstance("YMS_TEST", "test11gyms#");
			 *     R (Real 운영)        : getInstance("DEVTRC", "wkehdghk1");
			 *     
			 *    if ("R".equals(AnalProperties.getInstance().getSystemType())) {
			 *    	System.out.println(">YMS_LYA");
			 *    } else {
			 *    	System.out.println(">YMS_TEST");
			 *    }
			 *     
			 * 차후에는 환경파일에서 암호화적용된 문자열로 처리함.
			 */
			
			String strSystemType = AnalProperties.getInstance().getSystemType();
			
			if ("R".equals(strSystemType)) {
				/*
				 * 운영 계정
				 */
				// return getInstance("YMS_LYA", "#####");
				return getInstance("DEVTRC", "wkehdghk1");
			} else if ("T".equals(strSystemType)) {
				/*
				 * 테스트 계정
				 */
				return getInstance("YMS_TEST", "test11gyms#");
			} else {
				/*
				 * 개발 계정
				 */
				return getInstance("YMS_TEST", "test11gyms#");
			}
		}
		
		return getInstance("YMS_TEST", "test11gyms#");   // Default Oracle 계정
	}

	/**
	 * DATE : 2014.11.24
	 * 현재 private은 오라클 계정접속을 막기 위한 방법이고
	 * 나중에 안정화 되면 private을 public으로 한다. (예상)
	 * 
	 * @param user
	 * @param pass
	 * @return
	 */
	private static synchronized Connection04 getInstance(String user, String pass)
	{
		if (instance == null) {
			instance = new Connection04(user, pass);
		}
		
		return instance;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			try {
				
				for (int i=0; i < 1000; i++) {
					OracleConnection conn = Connection04.getInstance().getConn();
					
					String query = String.format("SELECT * FROM anal_jobid WHERE ROWNUM <= 1 ");

					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					if (rs != null) {
						System.out.println("Query OK!!! - " + i);
					} else {
						System.out.println("Query Fail!!! - " + i);
					}

					try { Thread.sleep(500); } catch (InterruptedException ex) {}
					
					Connection04.getInstance().close(true);
				}
				
				if (Flag.TRUE) System.out.println("##### Connection.getOracleConnection() is OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test02()
	{
		if (Flag.TRUE) {
			try {
				
				for (int i=0; i < 1000; i++) {
					OracleConnection conn = Connection04.getInstance().getConn();
					
					String query = String.format("SELECT * FROM anal_jobid WHERE ROWNUM <= 1 ");

					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					if (rs != null) {
						System.out.println("Query OK!!! - " + i);
					} else {
						System.out.println("Query Fail!!! - " + i);
					}

					try { Thread.sleep(500); } catch (InterruptedException ex) {}
				}
				
				Connection04.getInstance().close(true);

				if (Flag.TRUE) System.out.println("##### Connection.getOracleConnection() is OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test03()
	{
		if (Flag.TRUE) {
			SystemProperties.setTesting("010");
		}

		if (Flag.TRUE) {
			
			String strSystemType = AnalProperties.getInstance().getSystemType();
			
			if ("R".equals(strSystemType)) {
				/*
				 * YMS_LYA
				 */
				Print.println("%s> %s", strSystemType, "운영 (Running, Real)");
			} else if ("T".equals(strSystemType)) {
				/*
				 * YMS_TEST
				 */
				Print.println("%s> %s", strSystemType, "테스트 (Testing)");
			} else {
				/*
				 * YMS_TEST
				 */
				Print.println("%s> %s", strSystemType, "개발 (Development)");
			}
		}
		
		if (Flag.TRUE) {
			System.out.println("getSchema >" + Connection04.getInstance().getSchema());
		}
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (!Flag.TRUE) test02();
		if (Flag.TRUE) test03();
	}
}
