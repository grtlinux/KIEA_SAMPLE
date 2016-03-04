<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page import="java.sql.*"%>

<%
  request.setCharacterEncoding("euc-kr");

  String id= request.getParameter("id");
  String passwd= request.getParameter("passwd");
  String name= request.getParameter("name");
  Timestamp register=new Timestamp(System.currentTimeMillis());

  Connection conn=null;
  PreparedStatement pstmt=null;
  
  try{
	String jdbcUrl="jdbc:mysql://localhost:3306/jsptest";
    String dbId="jspid";
    String dbPass="jsppass";
	 
	Class.forName("com.mysql.jdbc.Driver");
	conn=DriverManager.getConnection(jdbcUrl,dbId ,dbPass );
	
	String sql= "insert into member1 values (?,?,?,?)";
	pstmt=conn.prepareStatement(sql);
	pstmt.setString(1,id);
    pstmt.setString(2,passwd);
	pstmt.setString(3,name);
	pstmt.setTimestamp(4,register);
	pstmt.executeUpdate();

	}catch(Exception e){ 
		e.printStackTrace();
	}finally{
		if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
		if(conn != null) try{conn.close();}catch(SQLException sqle){}
	}
%>
<html>
<head><title>레코드 삽입(추가)예제</title></head>
<body>
  member1 테이블에 새로운 레코드를 삽입(추가)했습니다.
</body>
</html>