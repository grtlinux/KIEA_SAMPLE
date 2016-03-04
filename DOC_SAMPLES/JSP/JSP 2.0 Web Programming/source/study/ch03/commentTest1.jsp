<%@ page contentType="text/html;charset=euc-kr"%>

<h2>주석 예제- HTML주석, JSP주석, 자바주석</h2>
<%
   String name = "JSP";
%>
<!-- HTML 주석 부분입니다. <%=name%> & Servlet -->
<%-- JS P주석 부분으로 JSP 페이지에서만 사용할 수 있고 '소스보기'를 해도 보이지 않습니다.
     물론 실행되지도 않습니다. 
--%>
<%=name/* 자바주석을 표현식에서 사용하고 있습니다.*/%> & Servlet

