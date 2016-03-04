<%@ page contentType = "text/html; charset=euc-kr" %>
<%
   String control = request.getParameter("CONTROL");
   String pageTitle = request.getParameter("PAGETITLE");
   String content = request.getParameter("CONTENTPAGE");
   String left = control+"Left.jsp";
%>
<html>
<head><title><%=pageTitle%></title></head>
<body>

<table width="500" border="1" cellpadding="2" cellspacing="0">
<tr>
    <td colspan="2">
        <jsp:include page="top.jsp" flush="false" />
    </td>
</tr>
<tr>
    <td width="150" valign="top">
        <jsp:include page="<%=left%>" flush="false" />
    </td>
    <td width="350" valign="top">
        <jsp:include page="<%= content %>" flush="false" />
    </td>
</tr>
<tr>
    <td colspan="2">
        <jsp:include page="bottom.jsp" flush="false" />
    </td>
</tr>
</body>
</html>