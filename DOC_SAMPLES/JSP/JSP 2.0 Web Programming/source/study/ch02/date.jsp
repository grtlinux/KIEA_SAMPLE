<!--date.jsp-->
<!--jsp예제-->
<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page import="java.util.*, java.text.*" %>
<html>
  <body>
   <% 
      Date date = new Date();
      SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
      String strdate = simpleDate.format(date);
   %>
   보통의 JSP 페이지의 형태입니다.<br>
   오늘 날짜는 <%= strdate%> 입니다.
  </body>
</html>

