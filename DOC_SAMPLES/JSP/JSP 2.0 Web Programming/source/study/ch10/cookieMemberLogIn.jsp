<%@ page contentType="text/html; charset=euc-kr" %>
<%@include file="color.jsp" %>
<html>
<head>
<title>쿠키를 사용한 간단한 회원인증</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="<%=bodyback_c%>" >
  <form method="post" action="cookieMemberLogInOK.jsp">
    <table width="300" border="1" align="center" cellspacing="0" cellpadding="0">
      <tr> 
         <td bgcolor="<%=title_c%>" colspan="2"><div align="center">로그인</div></td>
       </tr>
       <tr> 
          <td width="100" bgcolor="<%=title_c%>">아이디</td>
          <td width="200" bgcolor="<%=value_c%>"><input type="text" name="id"></td>
       </tr>
       <tr> 
          <td width="100" bgcolor="<%=title_c%>">비밀번호</td>
          <td width="200" bgcolor="<%=value_c%>"><input type="password" name="passwd"></td>
       </tr>
       <tr> 
          <td colspan="2" align="center">
		    <input type="submit" value="로그인">
                &nbsp;&nbsp;&nbsp;
            <input type="reset" value="다시작성">
          </td>
      </tr>
    </table>
  </form>
</body>
</html>