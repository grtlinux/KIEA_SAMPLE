<%@ page contentType="text/html; charset=euc-kr" %>
<%@include file="color.jsp" %>

<% 
	try{
	  if(session.getAttribute("memId")==null){
         response.sendRedirect("sessionMemberLogIn.jsp");}	
	}catch(Exception e){}%>
<html>
<head>
<title>세션을 사용한 간단한 회원인증</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="<%=bodyback_c%>" >
  <form method="post" action="sessionLogOut.jsp">
    <table width="300" border="1" align="center" cellspacing="0" cellpadding="0">
      <tr> 
         <td align="center" bgcolor="<%=value_c%>"> 
           <b><%= session.getAttribute("memId") %></b>님이 로그인 하셨습니다. 
         </td>
	  </tr>
	  <tr>
		 <td align="center" bgcolor="<%=value_c%>">
		   <input type="submit" value="로그아웃">
		 </td>
      </tr>
    </table>
  </form>
</body>
</html>