<%@ page contentType="text/html;charset=euc-kr"%>
<h2>If-else문 예제 - 좋아하는 색 선택</h2>
<FORM METHOD="post" ACTION="ifTest.jsp">
이름입력 : <INPUT TYPE="text" NAME="name"><p>
색  선택 : 
<SELECT NAME="color">
        <option value="blue" selected>파랑색</option>
        <option value="green">초록색</option>
        <option value="red">빨강색</option>
        <option value="yellow">기타</option>
</SELECT><p>
<INPUT TYPE="submit" VALUE="확인">
</FORM>