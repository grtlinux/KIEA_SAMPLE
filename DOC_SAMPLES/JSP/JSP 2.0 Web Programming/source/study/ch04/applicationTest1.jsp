<%@ page contentType="text/html;charset=euc-kr"%>
<%
	String info = application.getServerInfo();
	int major = application.getMajorVersion();
	int minor = application.getMinorVersion();
    String path = application.getRealPath("/");
	application.log("로그 기록");
%>

<h2>Application내장객체 예제</h2>
웹 컨테이너의 이름과 버전 : <%=info%><p>
서블릿의 버전 : <%=major%>.<%=minor%><p>
웹 어플리케이션 폴더의 로컬 시스템 경로 : <%=path%>