<%@ page contentType="text/html;charset=euc-kr"%>

<html>
<head>
<title>썸네일(Thumbnail)이미지 예제</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
</head>

<body>
<form name="fileForm" method="post" enctype="multipart/form-data" action="thumbnailPro.jsp">
	원본이미지: 
	<input type="file" name="image"><br>
	<input type="submit" value="썸네일이미지 만들기"><br>
</form>
</body>
</html>