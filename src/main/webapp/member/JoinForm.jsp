<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
	<title>회원가입 화면</title>

	<style type="text/css">
		table{
			margin-left:auto; 
			margin-right:auto;
			border:3px solid skyblue;
		}
		
		td{
			border:1px solid skyblue
		}
		
		#title{
			background-color:skyblue
		}
	</style>

	<script type="text/javascript">
	
		// 필수 입력정보인 아이디, 비밀번호가 입력되었는지 확인하는 함수
		function checkValue()
		{
			if(!document.userInfo.email.value){
				alert("이메일을 입력하세요.");
				return false;
			}
			
			if(!document.userInfo.pwd.value){
				alert("비밀번호를 입력하세요.");
				return false;
			}
			
			// 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
			if(document.userInfo.pwd.value != document.userInfo.pwdcheck.value ){
				alert("비밀번호를 동일하게 입력하세요.");
				return false;
			}
		}
		
		// 취소 버튼 클릭시 첫화면으로 이동
		function goFirstForm() {
			location.href="MainForm.do";
		}
	</script>
	
</head>
<body>
		<br><br>
		<b><font size="6" color="gray">회원가입</font></b>
		<br><br><br>
		
		
		<!-- 입력한 값을 전송하기 위해 form 태그를 사용한다 -->
		<!-- 값(파라미터) 전송은 POST 방식, 전송할 페이지는 JoinPro.jsp -->
		<form method="post" action="MemberJoinAction.do" 
				name="userInfo" onsubmit="return checkValue()">
			<table>
				<tr>
					<td id="title">이메일</td>
					<td>
						<input type="text" name="email" maxlength="50">
						<input type="button" value="중복확인" >	
					</td>
				</tr>
						
				<tr>
					<td id="title">비밀번호</td>
					<td>
						<input type="password" name="pwd" maxlength="50">
					</td>
				</tr>
				
				<tr>
					<td id="title">비밀번호 확인</td>
					<td>
						<input type="password" name="pwdcheck" maxlength="50">
					</td>
				</tr>
					
				<tr>
					<td id="title">별명</td>
					<td>
						<input type="text" name="nickname" maxlength="50">
					</td>
				</tr>
					
				<tr>
					<td id="title">성별</td>
					<td>
						<input type="radio" name="sex" value="남" checked>남
						<input type="radio" name="sex" value="여" >여
					</td>
				</tr>
					
				<tr>
					<td id="title">생일</td>
					<td>
						<input type="text" name="birthyy" maxlength="4" placeholder="년(4자)" size="6" >
						<select name="birthmm">
							<option value="">월</option>
							<option value="01" >1</option>
							<option value="02" >2</option>
							<option value="03" >3</option>
							<option value="04" >4</option>
							<option value="05" >5</option>
							<option value="06" >6</option>
							<option value="07" >7</option>
							<option value="08" >8</option>
							<option value="09" >9</option>
							<option value="10" >10</option>
							<option value="11" >11</option>
							<option value="12" >12</option>
						</select>
						<input type="text" name="birthdd" maxlength="2" placeholder="일" size="4" >
					</td>
				</tr>
				
			</table>
			<br>
			<input type="submit" value="가입"/>  
			<input type="button" value="취소" onclick="goFirstForm()">
		</form>
</body>
</html>