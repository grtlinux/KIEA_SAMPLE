<%@ page contentType="text/html;charset=euc-kr"%>
<%@ taglib prefix="test" uri="/WEB-INF/tlds/el-functions.tld" %>
<% request.setCharacterEncoding("euc-kr");%>

<HEAD>
<TITLE>표현언어에서 함수사용하기</TITLE>
</HEAD>

<BODY>

<H3>표현언어에서 함수사용하기 -두 숫자의 덧셈</H3>
<P>
<FORM action="eLEx3.jsp" method="post">
   X : <input type="text" name="x" value="${param['x']}">
   <BR>
   Y : <input type="text" name="y" value="${param['y']}">
   <input type="submit" value="덧셈">
</FORM>

<P>
합은 : ${test:add(param["x"],param["y"])} 입니다.

</BODY>
</HTML>
