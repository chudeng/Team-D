<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log In page</title>

<!-- 입력정보 유효성 검사 javascript -->
<script type="text/javascript" src="../script/member.js"></script>
</head>
<body>
	<h3>Log In</h3>
	
	<!-- "POST"형식의 form으로 login.jsp에 입력된 내용을 login.do servlet으로 보냄. -->
	<form method="POST" action="${pageContext.request.contextPath }/login.do" name="loginform">
		<table>
			<tr>
				<td>이메일</td>
				<td colspan="2"><input type="email" name="email"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td colspan="2"><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<!-- "Log In" 버튼 클릭시, ID와 비밀번호가 입력되었는지 member.js의 loginCheck()를 통해 확인. --> 
				<td><input type="submit" value="Log In" onclick="return loginCheck()"></td>
				<td style="text-align: center"><input type="reset" value="cancel"></td>
				<td style="text-align: right"><input type="button" value="Join" onclick="location.href='${pageContext.request.contextPath }/join.do'">
				<td>
			</tr>
			<tr>
				<td colspan="3" style="text-align: center"><span style="color: red;">${message}</span></td>
			</tr>
		</table>
	</form>
</body>
</html>