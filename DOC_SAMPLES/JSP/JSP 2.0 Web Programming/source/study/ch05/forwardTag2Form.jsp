<%@ page contentType="text/html;charset=euc-kr"%>
<h2>포워딩될 페이지에 파라미터값 전달하기 예제</h2>
<FORM METHOD="post" ACTION="forwardTagFrom2.jsp">
이름 입력 : <INPUT TYPE="text" NAME="name"><br>
색 선택: <br>
<INPUT TYPE="radio" NAME="color" VALUE="yellow">노랑색<br>
<INPUT TYPE="radio" NAME="color" VALUE="green">초록색<br>
<INPUT TYPE="radio" NAME="color" VALUE="blue">파랑색<br>
<INPUT TYPE="radio" NAME="color" VALUE="red">빨강색<p>
<INPUT TYPE="submit" VALUE="확인">
</FORM>