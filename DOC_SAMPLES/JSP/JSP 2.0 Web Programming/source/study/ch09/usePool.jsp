<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import = "java.sql.*,javax.sql.*, javax.naming.*" %>

<html>
<head><title>DBCP를 사용한 테이블의 레코드를 화면에 표시하는 예제</title></head>
<body>
  <h3>DBCP를 사용한member1 테이블의 레코드를 화면에 표시하는 예제</h3>
  <TABLE width="550" border="1">
  <TR>
  	<TD width="100">아이디</TD>
  	<TD width="100">패스워드</TD>
  	<TD width="100">이름</TD>
  	<TD width="250">가입일자</TD> 
  </TR>
<%
  Connection conn=null;
  PreparedStatement pstmt=null;
  ResultSet rs=null;

  try{
	Context initCtx = new InitialContext();
    Context envCtx = (Context) initCtx.lookup("java:comp/env");
    DataSource ds = (DataSource)envCtx.lookup("jdbc/jsptest");
    conn = ds.getConnection();

	String sql= "select * from member1";
	pstmt=conn.prepareStatement(sql);
	rs=pstmt.executeQuery();

	while(rs.next()){
	  String id= rs.getString("id");
      String passwd= rs.getString("passwd");
      String name= rs.getString("name");
      Timestamp register=rs.getTimestamp("reg_date");

%>
     <TR>
  	   <TD width="100"><%=id%></TD>
  	   <TD width="100"><%=passwd%></TD>
  	   <TD width="100"><%=name%></TD>
  	   <TD width="250"><%=register.toString()%></TD>
    </TR>
<%  } 
  }catch(Exception e){ 
		e.printStackTrace();
  }finally{
	    if(rs != null) try{rs.close();}catch(SQLException sqle){}
		if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
		if(conn != null) try{conn.close();}catch(SQLException sqle){}
  }
%>
    
</table>

</body>
</html>