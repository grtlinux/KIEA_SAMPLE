<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>JSTL core 예제 - set, out, remove</title>
</head>
<body>

browser변수값 설정
<c:set var="browser" value="${header['User-Agent']}"/><br>
<c:out value="${browser}"/><p>
browser변수값 제거 후
<c:remove var="browser"/>
<c:out value="${browser}"/>
</body>
</html>
