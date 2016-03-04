<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"  %>

<fmt:bundle basename="bundle.testBundle">
<html>
<head><title>JSTL fmt ¿¹Á¦ - bundle , message</title></head>
<body>

<fmt:message key="name"/>
<p>

<fmt:message key="message" var="msg"/>
<c:out value="${msg}"/>

</body>
</html>

</fmt:bundle>