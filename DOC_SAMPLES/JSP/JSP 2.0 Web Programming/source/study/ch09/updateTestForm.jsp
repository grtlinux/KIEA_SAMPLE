<%@ page contentType="text/html; charset=euc-kr" %>
<html>
<head><title>레코드 수정예제</title></head>
<body>
  <h2>member1 테이블의 레코드 수정예제</h2>
  <FORM METHOD="post" ACTION="updateTest.jsp">
    아이디 : <INPUT TYPE="text" NAME="id"><p>
    패스워드 : <INPUT TYPE="password" NAME="passwd"><p>
    변경할 이름:<INPUT TYPE="text" NAME="name"><p>
    <INPUT TYPE="submit" VALUE="보내기">
  </FORM>
</body>
</html>