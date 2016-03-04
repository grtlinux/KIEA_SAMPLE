<%@ page contentType="text/html;charset=euc-kr"%>
<html>
  <head><title>JSP스크립트 예제</title></head>
  <body>
    <h2>JSP스크립트 예제</h2>
    <%!
 	  String declaration = "선언문 연습입니다";
    %>
    <%!
 	  public String declarationMethod(){
 	    return declaration;
 	  }
    %>
    <%
 	  String scriptlet = "스크립트릿 연습";
      String comment = "주석문 연습";
 	  out.println("내장객체를 이용한 내용 출력 : " + declaration + "<p>");
    %>

    선언문 출력하기(변수) : <%=declaration%><p>
    선언문 출력하기(메소드) : <%=declarationMethod()%><p>
    스크립트릿 출력하기 : <%=scriptlet%><p>
   <!--JSP에서 사용하는 HTML주석부분-->
   <!-- HTML주석 :  <%=comment%> --><p>
   <%-- JSP 주석 : <%=comment%> --%>
   <%  /* 자바주석 
    (여러줄 주석)
     */  
   %> 
   <%// 자바주석(한줄 주석)%>
  </body>
</html>



