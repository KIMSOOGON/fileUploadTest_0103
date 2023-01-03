<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>formEnctype</title>
</head>
<body>
	<!-- 디폴트값 : 주소창문자열 -->
	<!-- get/post 상관없음 -->
	<!-- action으로 문자열을 넘긴다 -->
	<form action="./action.jsp" enctype="application/x-www-form-urlencoded" method="post">
		<input type="text" name="urlId">
		<input type="file" name="urlFile">
		<button type="submit">application/x-www-form-urlencoded</button>
	</form>
	
	<!-- 무조건 method가 post방식 -->
	<!-- action으로 스트링(기계어, 이진수)을 넘김 -->
	<!-- binary로 보낸다 (무조건 2진수로 바뀌어서 송신) -->
	<form action="./action.jsp" enctype="multipart/form-data" method="post">
		<input type="text" name="multiId">
		<input type="file" name="multiFile">
		<button type="submit">multipart/form-data</button>
	</form>
</body>
</html>