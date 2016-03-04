<%@ page contentType="text/html;charset=euc-kr"%>

<h2>표현식 예제1 - 배열의 내용 출력</h2>
<%!
	String str[] = {"JSP가","정말","재미","있다"};//배열의 초기화 블럭을 사용하면 배열의 선언, 메모리할당,초기값설정을 한번에 할 수 있다.
%>
<table border="1" width="250">
    <tr><td>배열의 첨자</td><td>배열의 내용</td></tr>
<% for (int i=0;i<str.length;i++){ //배열명.length는 배열의 요소의 수를 리턴한다%>
    <tr><td><%=i%></td><td><%=str[i]%></td>
    </tr>
<%}%>
</table>