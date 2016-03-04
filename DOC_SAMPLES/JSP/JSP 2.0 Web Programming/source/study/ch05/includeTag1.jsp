<%@ page contentType="text/html;charset=euc-kr"%>
<h1>포함하는 페이지 includeTag1.jsp 입니다.</h1>
<%
   request.setCharacterEncoding("euc-kr");
%>
<hr>
<jsp:include page="includeTagTop1.jsp" flush="false"/>
includeTag1.jsp의 나머지 내용입니다.