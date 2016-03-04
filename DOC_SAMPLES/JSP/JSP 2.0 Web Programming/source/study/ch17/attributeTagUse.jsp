<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ taglib prefix="tagFile" tagdir="/WEB-INF/tags" %>

<html>
<head><title>JSP2.0에서 태그 파일을 사용한 커스텀 태그 작성</title></head>
<body>

<h3>JSP2.0에서 태그 파일을 사용한 커스텀 태그 작성 3</h3>
    <TABLE border="0">
      <TR valign="top">
        <TD>
          <tagFile:display color="#ff0000" bgcolor="#ffc0c0"  title="Travel">
	        눈꽃축제<br/>
            온천여행<br/>
            묻지마여행<br/>
	      </tagFile:display>
        </TD>
        <TD>
          <tagFile:display color="#00fc00" bgcolor="#c0ffc0" title="Technology">
	        JAVA<BR>
	        JSP<BR>
	        Oracle<BR>
	      </tagFile:display>
        </TD>
        <TD>
          <tagFile:display color="#ffcc11" bgcolor="#ffffcc"title="Sports">
	        농구<BR/>
            축구<BR/>
            배구<BR/>
	  </tagFile:display>
        </TD>
      </TR>
    </TABLE>

</body>
</html>