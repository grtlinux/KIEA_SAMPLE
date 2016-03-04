<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page import="ch10.logon.LogonDBBean"%>
<% request.setCharacterEncoding("euc-kr");%>
<%
    String id = request.getParameter("id");
	String passwd  = request.getParameter("passwd");
	
	LogonDBBean manager = LogonDBBean.getInstance();
    int check= manager.userCheck(id,passwd);

	if(check==1){
		Cookie cookie = new Cookie("memId", id);
		cookie.setMaxAge(20*60);
		response.addCookie(cookie);
		response.sendRedirect("cookieLogInConfirm.jsp");
	}else if(check==0){%>
	<script> 
	  alert("비밀번호가 맞지 않습니다.");
      history.go(-1);
	</script>
<%	}else{ %>
	<script>
	  alert("아이디가 맞지 않습니다..");
	  history.go(-1);
	</script>
<%}	%>