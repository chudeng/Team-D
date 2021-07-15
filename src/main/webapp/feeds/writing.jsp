<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Writing page</title>

<!-- 입력정보 유효성 검사 javascript -->
<script type="text/javascript" src="../script/feeds.js"></script>
</head>
<body>
	<h3>Writing</h3>
	<form action="${pageContext.request.contextPath }/login.do">
	Title:<input type="text" name="title"><br>
	<input type="hidden" name="email" value="${loginUser.email }"><br>
	Date:<input type="text" name="created"><br>
	Contents:<textarea cols="20" rows="10" name="contents"></textarea>
	<input type="submit"><br>
	</form>
</body>
</html>