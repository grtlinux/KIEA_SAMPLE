<%@ page contentType="text/html;charset=euc-kr"%>
<%@ page import="java.util.Date"%>

<h2>표현식 예제2 - 기본데이터타입 및 레퍼런스타입출력</h2>

<%
    Date date = new Date();
    int var1 = 10;
	int var2 = var1 > 8 ? 20 : 10; //조건 연산자를 사용해서 var2변수의 값을 지정 	
%>

현재 날짜와 시간: <%=date.toString()%><p>
변수 var1의 값: <%=var1%><p>
변수 var2의 값: <%=var2%><p>
변수 var1 + 변수var2의 값: <%=var1+var2%><p>

<%--Wrapper클래스: 기본데이터타입을 객체로 변환해야할 경우 사용--%>
변수 var1의 값(Wrapper클래스 사용): <%=(new Integer(var1)).toString()%><p>