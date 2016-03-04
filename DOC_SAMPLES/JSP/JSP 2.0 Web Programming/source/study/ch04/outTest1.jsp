<%@ page contentType="text/html;charset=euc-kr"%>
<%
	int bufferSize = out.getBufferSize();
	int remainSize = out.getRemaining();
	int usedSize = bufferSize - remainSize;
%>
<h2>Out내장객체 예제</h2>
<b>현재 페이지에서의 버퍼의 사용현황</b><p>
출력 버퍼의 전체 크기 : <%=bufferSize%>byte<p>
현재 사용한 버퍼의 크기 : <%=usedSize%>byte<p>
남은 버퍼의 크기 : <% out.println(remainSize);%>byte<p>