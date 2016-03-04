<%@ page contentType="text/html;charset=euc-kr"%>

 <h2>선언문 예제1 - 멤버변수와 지역변수</h2>
<%
	String var1 = var2 + " web programming"; //지역변수var1의 선언및 초기값할당
%>
<%!
	String var2 = "JSP";//멤버변수var2의 선언및 초기값할당
%>
출력 결과 :  <%=var1%>