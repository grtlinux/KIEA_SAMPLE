<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/view/color.jspf"%>

<html>
<head><title>정보수정</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="${bodyback_c}">
 <table width=500 cellpadding="0" cellspacing="0"  align="center" >
   <tr>
     <td>
      <form name="myform" action="/study/appendix/logon/modifyForm.do" method="post">
        <INPUT type="hidden" name="id" value="${sessionScope.memId}">
	    <INPUT type="submit" value="회원정보 수정">
      </form>
	 </td>
	 <td>
      <form name="myform" action="/study/appendix/logon/deleteForm.do" method="post">
        <INPUT type="hidden" name="id" value="${sessionScope.memId}">
        <INPUT type="submit" value="회원 탈퇴">
      </form>
	 </td>
	 <td>
      <form name="myform" action="/study/appendix/logon/main.do" method="post">
        <INPUT type="submit" value="메인으로">
      </form>
	 </td>
	</tr>
   </table>
</body>
</html>