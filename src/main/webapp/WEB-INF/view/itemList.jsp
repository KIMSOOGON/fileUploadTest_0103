<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<c:forEach var="m" items="${list}" varStatus="s">
				<c:if test="${s.index % 5 == 0 && s.index != 0}">
					</tr><tr>
				</c:if>
				
				<td>
					<div><img src="${pageContext.request.contextPath}/upload/${m.fileName}" width="200" height="200"></div>
					<div>${m.itemName}</div>
					<div><a href="">수정</a> <a href="">삭제</a></div>
				</td>
			</c:forEach>
		</tr>
	</table>
</body>
</html>