<%@ page contentType="text/html;charset=euc-kr"%>
<%
	request.setCharacterEncoding("euc-kr");
%>
<h2>Request내장객체 예제1</h2>
<%
	String name = request.getParameter("name");
	String age = request.getParameter("age");
	String gender = request.getParameter("gender");
	String hobby = request.getParameter("hobby");

	if(gender.equals("m")){
	     gender = "남자";
	}else{
	     gender = "여자";
	}
%>

<%=name%> 님의 정보는 다음과 같습니다.<p>
나이 : <%=age%><p>
성별 : <%=gender%><p>
취미 : <%=hobby%>