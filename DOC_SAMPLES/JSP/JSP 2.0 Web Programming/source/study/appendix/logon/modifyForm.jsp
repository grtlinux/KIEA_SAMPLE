<%@ page contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/view/color.jspf"%>

<html>
<head>
<title>회원정보수정</title>
<link href="style.css" rel="stylesheet" type="text/css">


<script language="JavaScript">
   <!-- 
    function checkIt() {
        var userinput = eval("document.userinput");
               
        if(!userinput.passwd.value ) {
            alert("비밀번호를 입력하세요");
            return false;
        }
        if(userinput.passwd.value != userinput.passwd2.value)
        {
            alert("비밀번호를 동일하게 입력하세요");
            return false;
        }
       
        if(!userinput.username.value) {
            alert("사용자 이름을 입력하세요");
            return false;
        }
        if(!userinput.jumin1.value  || !userinput.jumin2.value )
        {
            alert("주민등록번호를 입력하세요");
            return false;
        }
    }
-->
</script>

<body bgcolor="${bodyback_c}">
<form method="post" action="/study/appendix/logon/modifyPro.do" name="userinput" onsubmit="return checkIt()">

  <table width="600" border="1" cellspacing="0" cellpadding="3"  align="center">
    <tr > 
      <td  colspan="2" height="39" bgcolor="${title_c}" align="center">
	     <font size="+1" ><b>회원 정보수정</b></font></td>
    </tr>
    <tr>
      <td colspan="2" class="normal" align="center">회원의 정보를 수정합니다.</td>
    </tr>
     <tr> 
      <td width="200" bgcolor="${value_c}"><b>아이디 입력</b></td>
      <td width="400" bgcolor="${value_c}">&nbsp;</td>
    <tr>  

    <tr> 
      <td  width="200"> 사용자 ID</td>
      <td  width="400">${member.id}</td>
	  <input type="hidden" name="id" value="${sessionScope.memId}">
    </tr>
    
     <tr> 
      <td width="200"> 비밀번호</td>
      <td width="400"> 
        <input type="password" name="passwd" size="10" maxlength="10" value="${member.passwd}">
      </td>
    <tr>  
    <tr> 
      <td  width="200" bgcolor="${value_c}"><b>개인정보 입력</b></td>
      <td width="400" bgcolor="${value_c}">&nbsp;</td>
    <tr>  
    <tr> 
      <td   width="200">사용자 이름</td>
      <td  width="400"> 
        <input type="text" name="name" size="15" maxlength="20" value="${member.name}">
      </td>
    </tr>
    <tr> 
      <td width="200">주민등록번호</td>
      <td width="400"> 
        ${member.jumin1}-${member.jumin2}
      </td>
    </tr>
   <tr> 
      <td width="200">E-Mail</td>
      <td width="400">
	    <c:if test="${empty member.email }">
		  <input type="text" name="email" size="40" maxlength="30" >
		</c:if>
		<c:if test="${!empty member.email }">
          <input type="text" name="email" size="40" maxlength="30" value="${member.email}">	
		</c:if>
      </td>
    </tr>
    <tr> 
      <td width="200">Blog</td>
      <td width="400"> 
	    <c:if test="${empty member.blog}">
		  <input type="text" name="blog" size="60" maxlength="50" >
		</c:if>
		<c:if test="${!empty member.blog}">
          <input type="text" name="blog" size="60" maxlength="50" value="${member.blog}">
		</c:if>
      </td>
    </tr>      
    <tr> 
      <td colspan="2" align="center" bgcolor="${value_c}"> 
       <input type="submit" name="modify" value="수   정" >
       <input type="button" value="취  소" onclick="document.location.href='/study/appendix/logon/main.do'">      
      </td>
    </tr>
  </table>
</form>
</body>
</html>