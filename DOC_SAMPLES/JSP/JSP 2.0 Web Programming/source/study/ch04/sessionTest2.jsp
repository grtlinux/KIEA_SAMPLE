<%@ page contentType="text/html;charset=euc-kr"%>
<%
	request.setCharacterEncoding("euc-kr");
%>
<h2>Session내장객체 예제</h2>
<%
	String sports = request.getParameter("sports");
	String game = request.getParameter("game");
	String id = (String)session.getAttribute("id"); 
	String sessionId = session.getId();
	
	if(id != null){
%>
<b><%=id%></b>님 접속해주셔서 감사합니다. <br>
<%=id%> 님 좋아하는 스포츠는 <%=sports%>이고, <br>
좋아하는 게임은 <%=game%> 입니다.<p>
현재 웹브라우저의 세션 ID : <%=sessionId%><p>

<%
	 session.invalidate();
	}else{
         out.println("로그인을 하시기 바랍니다.");
    }
%>