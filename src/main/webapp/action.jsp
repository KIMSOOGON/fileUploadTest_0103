<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>urlencoded</h1>
	<div>urlId : <%=request.getParameter("urlId")%></div>
	<div>urlFile : <%=request.getParameter("urlFile")%></div>
	
	<h1>multipart</h1>
	<div>request inputstream : <%=request.getInputStream()%></div>
	<!-- 2진수로 받아온다 -->
	
	<%
		InputStream is = request.getInputStream();
		
		int i = 0;
		while((i = is.read()) != -1) {
			System.out.print((char)i);
		}
	%>
</body>
</html>