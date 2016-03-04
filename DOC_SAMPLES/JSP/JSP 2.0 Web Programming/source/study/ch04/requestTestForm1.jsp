<%@ page contentType="text/html;charset=euc-kr"%>
<html>
<body>
<h2>Request내장객체 예제1</h2>
<FORM METHOD=POST ACTION="requestTest1.jsp">
 이름 : <INPUT TYPE="text" NAME="name"><br>
 나이 : <INPUT TYPE="text" NAME="age"><br>
 성별 : 남자 <INPUT  TYPE="radio" NAME="gender"  VALUE="m" checked>
       여자 <INPUT TYPE="radio" NAME="gender" VALUE="f"><br>
 취미 : <SELECT NAME="hobby" >
			<OPTION SELECTED VALUE="잠자기">잠자기</OPTION>
			<OPTION VALUE="TV시청">TV시청</OPTION>
			<OPTION VALUE="만화보기">만화보기</OPTION>
			<OPTION VALUE="게임">게임</OPTION>
			<OPTION VALUE="등산">등산</OPTION>
		</SELECT><br>
<INPUT TYPE="submit" value="보내기">
</FORM>
</body>
</html>