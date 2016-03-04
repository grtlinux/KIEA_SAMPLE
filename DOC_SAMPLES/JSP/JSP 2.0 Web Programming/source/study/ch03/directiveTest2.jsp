<%@ page contentType="text/html;charset=euc-kr"
         import="java.util.Date"
		 session="true"
		 buffer="8kb"
		 autoFlush="true"
		 isThreadSafe="true"
%>
<h2>Directive 예제2 - import, session, buffer, autoFlush, isThreadSafe 속성</h2>
<%
    Date date = new Date();
	//Date클래스는 현재 날짜와 시간에 대한 정보를 
	//얻어낼 필요가 있을때 사용된다.
%>

현재의 날짜와 시간은 <%=date.toLocaleString()%> 입니다. 