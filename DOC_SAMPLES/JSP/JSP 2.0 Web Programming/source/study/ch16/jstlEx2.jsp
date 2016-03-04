<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>JSTL core 예제 - if, choose, when, otherwise</title>
</head>
<body>
<c:set var="country" value="${'Korea'}"/>
<c:if test="${country != null}">
  국가명: <c:out value="${country}"/>
</c:if>
<p>

<c:choose>
  <c:when test="${country == 'Korea'}">
     <c:out value="${country}"/>의 겨울은 춥다.
  </c:when>
  <c:when test="${country == 'Canada'}">
     <c:out value="${country}"/>의 겨울은 너무 춥다.
  </c:when>
  <c:otherwise>
     그외의 나라들의 겨울은 알 수 없다.
  </c:otherwise>
</c:choose>
</body>
</html>
