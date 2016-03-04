<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"  %>

<fmt:requestEncoding value="euc-kr"/>

<html>
<head><title>JSTL fmt 예제 - requestEncoding</title></head>
<body>
 파라메터:<c:out value="${param.id}"/>
<form method="post" action="jstlEx6.jsp">
<input type="text" name="id">
<input type="submit" value="확인">
</form>
</body>
</html>