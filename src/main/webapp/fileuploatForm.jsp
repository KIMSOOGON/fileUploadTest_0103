<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>��ǰ���</h1>
	<form action="./fileuploadAction.jsp" method="post" enctype="multipart/form-data">
		<div>
			��ǰ�̸� : <input type="text" name="itemName">
		</div>
		<div>
			��ǰ�̹��� : <input type="file" name="itemImg">
		</div>
		<div>
			<button type="submit">��ǰ���</button>
		</div>	
	</form>
</body>
</html>