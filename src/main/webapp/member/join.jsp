<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Join page</title>
<script type="text/javascript" src="script/member.js"></script>

</head>
<body>
	<h3>Join</h3>
	<form method="POST" action="${pageContext.request.contextPath }/join.do" name="joinform">
		<table>
			<tr>
				<td>이메일</td>
				<td><input type="email" name="email"><input type="hidden" name="reid"><input type="button" value="중복확인" onclick="return idCheck()"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" name="pwdpwd"></td>
			</tr>
			<tr>
				<td>별명</td>
				<td><input type="text" name="nickname"></td>
			</tr>
			<tr>
				<td>성별</td>
				<td>
					<input type="radio" name="sex" value=1 checked>남
					<input type="radio" name="sex" value=2 checked>여
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="join" onclick="return joinCheck()"></td>
				<td><input type="reset" value="cancle"></td>
			</tr>
			<tr>
				<td colspan="2">${message }</td>
			</tr>
		</table>
	</form>
</body>
</html>