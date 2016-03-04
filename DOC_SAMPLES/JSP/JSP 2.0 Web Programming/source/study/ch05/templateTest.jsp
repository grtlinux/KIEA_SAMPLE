<%@ page contentType = "text/html; charset=euc-kr" %>
<%
   String contentPage = request.getParameter("CONTENTPAGE");
%>
<html>
<head><title>Template 페이지 연습</title></head>
<body>

<table width="500" border="1" cellpadding="2" cellspacing="0">
<tr>
    <td colspan="2">
        <jsp:include page="top.jsp" flush="false" />
    </td>
</tr>
<tr>
    <td width="150" valign="top">
        <jsp:include page="left.jsp" flush="false" />
    </td>
    <td width="350" valign="top">
        <jsp:include page="<%= contentPage %>" flush="false" />
    </td>
</tr>
<tr>
    <td colspan="2">
        <jsp:include page="bottom.jsp" flush="false" />
    </td>
</tr>
</body>
</html>