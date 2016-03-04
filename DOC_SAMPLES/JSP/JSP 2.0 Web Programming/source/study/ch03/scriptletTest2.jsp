<%@ page contentType="text/html;charset=euc-kr"%>

<h2>스크립트릿 예제2</h2>
<%
   double var1 = 5.5;
   
   if(var1 > 5){
%>
      변수 var1의 값은 5보다 크다.
<%}else{%>
      변수 var1의 값은 5보다 작거나 같다.
<%}%>