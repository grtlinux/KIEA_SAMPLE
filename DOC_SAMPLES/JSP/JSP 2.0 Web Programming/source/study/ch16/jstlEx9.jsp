<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head><title>JSTL fmt ¿¹Á¦ - timeZone</title></head>
<body>

<c:set var="now" value="<%= new java.util.Date() %>" />

Korea   KST  : <fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/><br>
UK   GMT  : <fmt:timeZone value="GMT"> 
                  <fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/>
               </fmt:timeZone>
</body>
</html>