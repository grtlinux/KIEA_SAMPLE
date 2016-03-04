<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>템플릿 페이지를 사용한 웹어플리케이션</title></head>
<body>

<table width="850" border="1" cellpadding="2" cellspacing="0">
<tr>
    <td colspan="2">
        <jsp:include page="/module/top.jsp" flush="false" />
    </td>
</tr>
<tr>
    <td width="150" valign="top">
        <jsp:include page="/module/left.jsp" flush="false" />
    </td>
    <td width="700" valign="top">
        <jsp:include page="${CONTENT}" flush="false" />
    </td>
</tr>
<tr>
    <td width="150" valign="top" align="center">
	    <img src="/study/module/logo.jpg" border="0">
	</td>
	<td width="700" valign="top">
        <jsp:include page="/module/bottom.jsp" flush="false" />
    </td>
</tr>
</body>
</html>