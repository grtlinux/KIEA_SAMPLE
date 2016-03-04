<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>간단한 컨트롤러(Controller)의 사용 예제</title>
</head>
<body>
결과:
<c:set var="result" value="${requestScope.result}" />
<c:out value="${result}"/>
</body>
</html>
