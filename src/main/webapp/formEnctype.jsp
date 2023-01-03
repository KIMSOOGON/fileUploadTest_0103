<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>formEnctype</title>
</head>
<body>
	<!-- ����Ʈ�� : �ּ�â���ڿ� -->
	<!-- get/post ������� -->
	<!-- action���� ���ڿ��� �ѱ�� -->
	<form action="./action.jsp" enctype="application/x-www-form-urlencoded" method="post">
		<input type="text" name="urlId">
		<input type="file" name="urlFile">
		<button type="submit">application/x-www-form-urlencoded</button>
	</form>
	
	<!-- ������ method�� post��� -->
	<!-- action���� ��Ʈ��(����, ������)�� �ѱ� -->
	<!-- binary�� ������ (������ 2������ �ٲ� �۽�) -->
	<form action="./action.jsp" enctype="multipart/form-data" method="post">
		<input type="text" name="multiId">
		<input type="file" name="multiFile">
		<button type="submit">multipart/form-data</button>
	</form>
</body>
</html>