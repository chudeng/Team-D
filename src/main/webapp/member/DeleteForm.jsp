<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
	<title>Ż�� ȭ��</title>
	
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
		// ��й�ȣ ���Է½� ���â
		function checkValue(){
			if(!document.deleteform.pwd.value){
				alert("��й�ȣ�� �Է����� �ʾҽ��ϴ�.");
				return false;
			}
		}
	</script>
	
</head>
<body>
		<br><br>
		<b><font size="6" color="gray">�� ����</font></b>
		<br><br><br>
		
		<form name="deleteform" method="post" action="MemberDeleteAction.do"
			onsubmit="return checkValue()">
		
			<table>
				<tr>
					<td bgcolor="skyblue">��й�ȣ</td>
					<td><input type="password" name="pwd" maxlength="50"></td>
				</tr>
			</table>
				
			<br> 
			<input type="button" value="���" onclick="javascript:window.location='MainForm.do'">
			<input type="submit" value="Ż��" /> 
		</form>
</body>
</html>