<%@ page contentType = "text/html; charset=euc-kr" %>
<%
   String control = request.getParameter("CONTROL");
   String pageNum = request.getParameter("PAGENUM");
   String content = "/view/" + control +"_" + pageNum + ".jsp";
   String left = "/module/" + control+"Left.jsp";
%>
<html>
<head><title>템플릿 페이지를 사용한 웹페이지</title></head>
<body>

<table width="500" border="1" cellpadding="2" cellspacing="0">
<tr>
    <td colspan="2">
        <jsp:include page="/module/top.jsp" flush="false" />
    </td>
</tr>
<tr>
    <td width="150" valign="top">
        <jsp:include page="<%=left%>" flush="false" />
    </td>
    <td width="350" valign="top">
        <jsp:include page="<%=content%>" flush="false" />
    </td>
</tr>
<tr>
    <td colspan="2">
        <jsp:include page="/module/bottom.jsp" flush="false" />
    </td>
</tr>
</body>
</html>