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

package kiea.z01.ztest.t01.mdb.t01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

import kiea.kr.co.tain.base.Flag;

/**
 * MdbTestMain 클래스
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: ICA Information and Communication </p>
 *
 * @name    MdbTestMain
 * @author  강석
 * @version 1.0, 2013/06/21
 * @since   jdk1.6.0_45
 *
 *
 * -
 * Class.forName("org.gjt.mm.mysql.Driver");
 * c = DriverManager.getConnection("jdbc:mysql://localhost/tspc?useUnicode=true&characterEncoding=euckr", "root", "321");
 *
 * import java.sql.Connection;
 * import java.sql.DriverManager;
 * import java.sql.PreparedStatement;
 * import java.sql.ResultSet;
 * import java.sql.SQLException;
 * import java.sql.Statement;
 * import java.util.Properties;
 *
 * public class DBConnection {
 *     private String DB_URL = "Jdbc:Odbc:Testdata";
 *     private String DB_USER = "test";
 *     private String DB_PASSWORD= "test";
 *     Connection conn = null;
 *
 *     public Connection getConnection() {
 *
 *         try {
 *             Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
 *         } catch (ClassNotFoundException e1) {
 *             e1.printStackTrace();
 *         }
 *
 *         try {
 *             Properties props = new Properties();
 *             props.put("charSet", "8859_1" );
 *             props.put("user", DB_USER);
 *             props.put("password", DB_PASSWORD);
 *             conn = DriverManager.getConnection(DB_URL, props);
 *         } catch (SQLException e) {
 *             e.printStackTrace();
 *         }
 *
 *         return conn;
 *     }
 *
 *
 * 그리고 여기서는 Properties를 사용하였는데 이건 mdb의 한글문제때문이다.
 * mdb는 한글을 8859_1로 사용하기 때문에 한글을 안깨지고 받아오기 위해서는
 * Connection을 연결할 때 charSet을 8859_1로 설정해 주어야 한다
 *
 *
 *         DBConnection dbConn = new DBConnection();
 *         Connection conn = null;
 *         PreparedStatement psmt = null;
 *         ResultSet rs = null;
 *
 *         try {
 *             String sql = " SELECT username FROM member ";
 *             conn = dbConn.getConnection();
 *             psmt = conn.prepareStatement(sql);
 *             rs = psmt.executeQuery();
 *
 *             while (rs.next()) {
 *                 String username = new String(rs.getString("username").getBytes("8859_1"), "euc-kr");
 *             }
 *
 *         } catch(Exception e) {
 *             e.printStackTrace();
 *         } finally {
 *             dbConn.disConnection(rs,psmt, conn);
 *         }
 *
 * 이제 Connection을 사용해서 Select 쿼리를 날리는 부분이다.
 * import등의 필요없는 부분은 빼버렸다. 결과적으로는 14번째 라인만 보면 된다.
 * 영문이나 숫자는 괜찮지만 한글의 경우 디비커넥션에서 사용할 때 Access에서
 * 한글을 8859_1(ISO-8859-1)을 사용했기 때문에 여기서도 받아온 스트링을 14번
 * 라인처럼 엔코딩을 변경해 주어야 받아온 한글이 깨지지 않고 표시된다.
 * (특이하게도 문서의 encoding이 euc-kr이든 utf-8이든 동일하게 위처럼
 * euc-kr로 인코딩을 해주어야 한글이 깨지지 않는다.)
 *
 * INSERT나 UPDATE등 자바쪽에서 mdb로 한글을 넣을때도 동일하게 인코딩 타입을
 * 변경해서 넣어주어야 한다. 당연히 인코딩은 반대로 해서...
 *
 *         String str = new String("한글".getBytes("euc-kr"), "ISO-8859-1");
 *
 * 위처럼 하면 된다. ( ISO-8859-1와 8859_1는 동인한 인코딩이다.)
 *
 * 디비를 mdb를 쓰면 한글을 넣고 빼는 일은 빈번할테니까 위 2가지를 메서드로
 * 따로 만들어두면 편할것이다.
 *
 * --------------------------------------------------------------------
 * [ ODBC 세팅 ]
 * 
 *     64비트 버전을 Microsoft Windows 운영체제의 다음 버전을 Microsoft 개발형 데이터베이스 연결(ODBC) 데이터 원본 관리자
 *     도구(Odbcad32.exe)에 포함 되어 있습니다.
 * 
 *        - 32비트 버전의 Odbcad32.exe 파일은 %systemdrive%\Windows\SysWoW64 폴더에 있습니다.
 *        - 64비트 버전의 Odbcad32.exe 파일은 %systemdrive%\Windows\System32 폴더에 있습니다.
 *
 *     Odbcad32.exe 파일에서 다음과 같은 유형의 데이터 원본이름(DSN)을 표시합니다.
 * 
 *        - 시스템DSN
 *        - 사용자DSN
 *
 * [ 64비트 프로그램으로 변경하여 사용하는 경우 ]
 *     
 *     64비트 ODBC 드라이버 프로그램을 실행시켜 Access Driver를 추가시켜야 한다.
 *     
 *        - AccessDatabaseEngine.exe     : 32비트 엔진
 *        - AccessDatabaseEngine_X64.exe : 64비트 엔진
 *     
 *     Microsoft Access Database Engine 2010 재배포 가능 페키지
 *     
 *     방법)
 *     1. Microsoft Office 2007 설치를 제거한다.
 *     2. AccessDatabaseEngine_X64.exe 실행하여 설치한다.
 *     3. Microsoft Office 2007 설치한다.
 *
 */

public class MdbTestMain
{
	private static void test01_create()
	{
		if (Flag.TRUE) {
			try {

				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

				Properties props = new Properties();
				props.put("charSet", "EUC-KR");
				props.put("user", "kang");
				props.put("password", "seok");

				Connection db = DriverManager.getConnection("jdbc:odbc:mdbKANG01", props);

				StringBuffer sb = new StringBuffer();
				sb.append(" CREATE TABLE imsi                    ");
				sb.append(" (                                    ");
				sb.append("     ID        int primary key        ");
				sb.append("     , NAME    varchar(255)           ");
				sb.append("     , CONTENT varchar(255)           ");
				sb.append(" )                                    ");
				
				Statement statement = db.createStatement();
				int ret = statement.executeUpdate(sb.toString());
				
				System.out.println("ret = " + ret);

				db.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void test01_insert()
	{
		if (Flag.TRUE) {
			try {

				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

				Properties props = new Properties();
				props.put("charSet", "EUC-KR");
				props.put("user", "kang");
				props.put("password", "seok");

				Connection db = DriverManager.getConnection("jdbc:odbc:kangKang01", props);

				StringBuffer sb = new StringBuffer();
				sb.append(" INSERT INTO kang                     ");
				sb.append(" (                                    ");
				sb.append("     ID                               ");
				sb.append("     , NAME                           ");
				sb.append("     , CONTENT                        ");
				sb.append(" )                                    ");
				sb.append(" VALUES                               ");
				sb.append(" (                                    ");
				sb.append("     9                                ");
				sb.append("     , 'TABLE'                        ");
				sb.append("     , '이것은 INSERT로 생성.'");
				sb.append(" )                                    ");
				
				Statement statement = db.createStatement();
				int ret = statement.executeUpdate(sb.toString());
				
				System.out.println("ret = " + ret);

				db.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void test01_update()
	{
		if (Flag.TRUE) {
			try {

				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

				Properties props = new Properties();
				props.put("charSet", "EUC-KR");
				props.put("user", "kang");
				props.put("password", "seok");

				Connection db = DriverManager.getConnection("jdbc:odbc:kangKang01", props);

				StringBuffer sb = new StringBuffer();
				sb.append(" UPDATE            ");
				sb.append("     kang          ");
				sb.append(" SET               ");
				sb.append("     NAME = 'KANG'");
				sb.append(" WHERE             ");
				sb.append("     ID = 3        ");
				
				Statement statement = db.createStatement();
				int ret = statement.executeUpdate(sb.toString());
				
				System.out.println("ret = " + ret);

				db.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void test01_delete()
	{
		if (Flag.TRUE) {
			try {

				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

				Properties props = new Properties();
				props.put("charSet", "EUC-KR");
				props.put("user", "kang");
				props.put("password", "seok");

				Connection db = DriverManager.getConnection("jdbc:odbc:kangKang01", props);

				StringBuffer sb = new StringBuffer();
				sb.append(" DELETE FROM       ");
				sb.append("     kang          ");
				sb.append(" WHERE             ");
				sb.append("     ID = 9        ");
				
				Statement statement = db.createStatement();
				int ret = statement.executeUpdate(sb.toString());
				
				System.out.println("ret = " + ret);

				db.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void test01_select()
	{
		if (!Flag.TRUE) {
			try {

				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

				Properties props = new Properties();
				props.put("charSet", "8859_1");
				props.put("user", "kang");
				props.put("password", "seok");

				Connection db = DriverManager.getConnection("jdbc:odbc:kangKang01", props);

				Statement statement = db.createStatement();
				ResultSet rs = statement.executeQuery("select * from kang"); // query
				
				ResultSetMetaData rsMeta = rs.getMetaData();
				int columnCount = rsMeta.getColumnCount();
				
				int iRowNum = 0;
				while (rs.next()) {
					iRowNum ++;
					System.out.println("Row Number [" + iRowNum + "]");

					for (int i=1; i <= columnCount; i++) {
						String strColumnName = rsMeta.getColumnName(i);
						String strText = rs.getString(i);

						strText = new String(strText.getBytes("ISO-8859-1"), "EUC-KR");
						System.out.println("\t[" + i + "] [" + strColumnName + "=" + strText + "]");
					}
				}

				db.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!Flag.TRUE) {
			try {

				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

				Properties props = new Properties();
				//props.put("charSet", "8859_1");
				props.put("charSet", "EUC-KR");
				props.put("user", "kang");
				props.put("password", "seok");

				Connection db = DriverManager.getConnection("jdbc:odbc:kangKang01", props);

				Statement statement = db.createStatement();
				ResultSet rs = statement.executeQuery("select * from kang"); // query
				
				ResultSetMetaData rsMeta = rs.getMetaData();
				int columnCount = rsMeta.getColumnCount();
				
				int iRowNum = 0;
				while (rs.next()) {
					iRowNum ++;
					System.out.println("Row Number [" + iRowNum + "]");

					for (int i=1; i <= columnCount; i++) {
						String strColumnName = rsMeta.getColumnName(i);
						String strText = rs.getString(i);

						//strText = new String(strText.getBytes("ISO-8859-1"), "EUC-KR");
						System.out.println("\t[" + i + "] [" + strColumnName + "=" + strText + "]");
					}
				}

				db.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!Flag.TRUE) {
			try {

				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

				Properties props = new Properties();
				props.put("charSet", "8859_1");
				props.put("user", "kang");
				props.put("password", "seok");

				Connection db = DriverManager.getConnection("jdbc:odbc:kangBookCatalog", props);

				Statement statement = db.createStatement();
				ResultSet rs = statement.executeQuery("select * from BookList");  // query
				
				ResultSetMetaData rsMeta = rs.getMetaData();
				int columnCount = rsMeta.getColumnCount();

				int iRowNum = 0;
				while (rs.next()) {
					iRowNum ++;
					System.out.println("Row Number [" + iRowNum + "]");

					for (int i=1; i <= columnCount; i++) {
						String strColumnName = rsMeta.getColumnName(i);
						String strText = rs.getString(i);

						System.out.println("\t[" + i + "] [" + strColumnName + "=" + strText + "]");
					}
				}

				db.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Flag.TRUE) {
			try {

				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

				Properties props = new Properties();
				props.put("charSet", "EUC-KR");
				props.put("user", "kang");
				props.put("password", "seok");

				/*
				 * TODO : 2014.07.25 : 직접 DNS 선언 없이 mdb 파일을 직접 연결처리한다. 
				 */
				Connection db = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)}; DBQ=D:/KANG/WORK/workspace7/KIEA_02/src/kiea/z01/ztest/t01/mdb/db/KANG01.mdb", props);

				Statement statement = db.createStatement();
				ResultSet rs = statement.executeQuery("select * from kang"); // query
				
				ResultSetMetaData rsMeta = rs.getMetaData();
				int columnCount = rsMeta.getColumnCount();
				
				int iRowNum = 0;
				while (rs.next()) {
					iRowNum ++;
					System.out.println("Row Number [" + iRowNum + "]");

					for (int i=1; i <= columnCount; i++) {
						String strColumnName = rsMeta.getColumnName(i);
						String strText = rs.getString(i);

						System.out.println("\t[" + i + "] [" + strColumnName + "=" + strText + "]");
					}
				}

				db.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void test01_drop()
	{
		if (Flag.TRUE) {
			try {

				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

				Properties props = new Properties();
				props.put("charSet", "EUC-KR");
				props.put("user", "kang");
				props.put("password", "seok");

				Connection db = DriverManager.getConnection("jdbc:odbc:kangKang01", props);

				StringBuffer sb = new StringBuffer();
				sb.append(" DROP TABLE imsi      ");
				
				Statement statement = db.createStatement();
				int ret = statement.executeUpdate(sb.toString());
				
				System.out.println("ret = " + ret);

				db.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01_create();
		if (!Flag.TRUE) test01_insert();
		if (!Flag.TRUE) test01_update();
		if (!Flag.TRUE) test01_delete();
		if (!Flag.TRUE) test01_select();
		if (!Flag.TRUE) test01_drop();
	}
}
