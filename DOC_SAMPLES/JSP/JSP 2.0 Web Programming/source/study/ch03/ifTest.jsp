<%@ page contentType="text/html;charset=euc-kr"%>
<% request.setCharacterEncoding("euc-kr");%>
<h2>If-else문 예제 - 좋아하는 색 선택</h2>

<%
   String name = request.getParameter("name");
   String color = request.getParameter("color");
   String selectColor = "";
  
   if (color.equals("blue")) {
 	   selectColor = "파랑색";
   } else if (color.equals("green")) {
	   selectColor = "초록색";
    }else if (color.equals("red")){
       selectColor = "빨강색";
   }else{
       selectColor = "기타색";
    }
%>

<%=name%>님이 선택한 색은 <%=selectColor%>입니다.<p>
선택한 색:<br>
<img src="<%=color + ".jpg"%>" border="0">