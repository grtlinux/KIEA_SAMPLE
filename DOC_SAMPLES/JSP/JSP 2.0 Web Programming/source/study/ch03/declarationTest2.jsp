<%@ page contentType="text/html;charset=euc-kr"%>

<h2>선언문 예제2 - 멤버변수와 메소드</h2>
<%!
  double var1;
  int var2 = 1;

  public double sum(){
    return var1 + var2;
  }

  String str="하하하..";
  int var3 = 10;
%>

변수 var1 과 변수 var2의 합: <%=sum()%><p>
변수 str의 내용: <%=str%><p>
변수 var3의 값: <%=var3%>