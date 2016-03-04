<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head><title>JSTL fmt ¿¹Á¦ - formatNumber, formatDate</title></head>
<body>

number  : <fmt:formatNumber value="12345.678" type="number"/><br>
currency: <fmt:formatNumber value="12345.678" type="currency" currencySymbol="£Ü"/><br>
percent : <fmt:formatNumber value="12345.678" type="percent"/><br>
pattern=".0" :<fmt:formatNumber value="12345.678" pattern=".0"/> <p>

<c:set var="now" value="<%= new java.util.Date() %>" />
<c:out value="${now}"/><br>
date : <fmt:formatDate value="${now}" type="date" /> <br>
time : <fmt:formatDate value="${now}" type="time" /> <br>
both : <fmt:formatDate value="${now}" type="both" />

</body>
</html>