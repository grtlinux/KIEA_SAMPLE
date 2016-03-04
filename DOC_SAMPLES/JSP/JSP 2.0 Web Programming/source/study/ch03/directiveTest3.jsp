<%@ page contentType="text/html;charset=euc-kr" 
      errorPage="error.jsp" %>
<h2>Directive 예제3 - errorPage, isErrorPage 속성</h2>
<%
  int a =1;
  int b =0;
%>
a과 b의 사칙연산<p>
a + b = <%=a+b%><p>
a - b = <%=a-b%><p>
a * b = <%=a*b%><p>
a / b = <%=a/b%><p>