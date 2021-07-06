<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Page for member</h1>
	<table>
			<tr>
				<td>안녕하세요</td>
				<td>${loginUser.nickname}(${loginUser.email })님</td>
			</tr>
		</table>
	<form method="get" action="logout.do">
		<table>
			<tr>
				<td><input type="submit" value="logout"></td>
				<!-- 로그인된 email로 memberUpdate.do 수행 -->
				<td><input type="button" value="modify" onclick="location.href='memberUpdate.do?email=${loginUser.email}'"></td>
			</tr>
		</table>
	</form>
</body>
</html>