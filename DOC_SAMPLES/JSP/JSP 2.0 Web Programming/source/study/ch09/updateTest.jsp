<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page import="java.sql.*"%>

<%
  request.setCharacterEncoding("euc-kr");

  String id= request.getParameter("id");
  String passwd= request.getParameter("passwd");
  String name= request.getParameter("name");

  Connection conn=null;
  PreparedStatement pstmt=null;
  ResultSet rs=null;

  try{
	String jdbcUrl="jdbc:mysql://localhost:3306/jsptest";
    String dbId="jspid";
    String dbPass="jsppass";
	 
	Class.forName("com.mysql.jdbc.Driver");
	conn=DriverManager.getConnection(jdbcUrl,dbId ,dbPass );
	
	String sql= "select id, passwd from member1 where id= ?";
	pstmt=conn.prepareStatement(sql);
    pstmt.setString(1,id);
	rs=pstmt.executeQuery();
    
	if(rs.next()){ 
		String rId=rs.getString("id");
		String rPasswd=rs.getString("passwd");
      if(id.equals(rId) && passwd.equals(rPasswd)){
	    sql= "update member1 set name= ?  where id= ? ";
	    pstmt=conn.prepareStatement(sql);
	    pstmt.setString(1,name);
	    pstmt.setString(2,id);
	    pstmt.executeUpdate();
	   
%>
<html>
<head><title>레코드 수정예제</title></head>
<body>
  member1 테이블의 레코드를 수정했습니다.
</body>
</html>
<%
     }else{
		out.println("패스워드가 틀렸습니다.");}
	}else{
		 out.println("아이디가 틀렸습니다.");
	}
	}catch(Exception e){ 
		e.printStackTrace();
	}finally{
		if(rs != null) try{rs.close();}catch(SQLException sqle){}
		if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
		if(conn != null) try{conn.close();}catch(SQLException sqle){}
	}
%>