<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page import="java.sql.*"%>

<%
  Connection con=null;

  try{
	String jdbcUrl="jdbc:mysql://localhost:3306/jsptest";
    String dbId="jspid";
    String dbPass="jsppass";
	
	Class.forName("com.mysql.jdbc.Driver");
	con=DriverManager.getConnection(jdbcUrl,dbId ,dbPass );
	//아래와 같이 작성해도 됨
	//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jsptest?user=jspid&password=jsppass");
	out.println("제대로 연결되었습니다.");
	}catch(Exception e){ 
		e.printStackTrace();
	}
%>
