<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member Update</title>
<script type="text/javascript" src="script/member.js"></script>
</head>
<body>
	<h3>Update</h3>
	<form method="POST" action="${pageContext.request.contextPath }/memberUpdate.do" name="joinform">
		<table>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" readonly="readonly" value="${mVo.email }"></td>
			</tr>
			<tr>
				<td>암호</td>
				<td><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td>암호확인</td>
				<td><input type="password" name="pwdpwd"></td>
			</tr>
			<tr>
				<td>별명</td>
				<td><input type="text" name="nickname" value="${mVo.nickname }"></td>
			</tr>
			<tr>
				<td>성별</td>
				<td><c:choose>
					<c:when test="${mVo.sex == 1 }">
						<input type="radio" name="sex" value=1 checked="checked">남
						<input type="radio" name="sex" value=2>여
					</c:when>
					<c:otherwise>
						<input type="radio" name="sex" value=1>남
						<input type="radio" name="sex" value=2 checked="checked">여
					</c:otherwise>
				</c:choose></td>
			</tr>
			<tr>
				<td>인사말</td>
				<td><input type="text" name="greeting" value="${mVo.greeting }"></td>
			</tr>
			<tr>
				<td><input type="submit" value="OK" onclick="return joinCheck()"></td>
				<td><input type="reset" value="cancle"></td>
			</tr>
			<tr>
				<td colspan="2">${message }</td>
			</tr>
		</table>
	</form>
</body>
</html>