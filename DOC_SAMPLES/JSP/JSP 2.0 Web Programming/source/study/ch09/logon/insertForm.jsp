<%@ page contentType="text/html; charset=euc-kr" %>
<html>
<head><title>회원가입</title></head>
<body>
  <h2>회원가입</h2>
  <FORM METHOD="post" ACTION="insertPro.jsp">
    아이디 : <INPUT TYPE="text" NAME="id" maxlength="12"><p>
    패스워드 : <INPUT TYPE="password" NAME="passwd" maxlength="12"><p>
    이름:<INPUT TYPE="text" NAME="name" maxlength="10"><p>
	주민번호:<INPUT TYPE="text" NAME="jumin1" maxlength="6">
	          -<INPUT TYPE="text" NAME="jumin2" maxlength="7"><p>
    E-Mail:<INPUT TYPE="text" NAME="email" maxlength="30"><p>
	Blog:<INPUT TYPE="text" NAME="blog" maxlength="50"><p>
    <INPUT TYPE="submit" VALUE="보내기">
  </FORM>
</body>
</html>