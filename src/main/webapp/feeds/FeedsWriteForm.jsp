<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
	<title>�Խ��� - �۾���</title>
	
	<style type="text/css">
		#title{
			height : 16;
			font-family :'����';
			font-size : 12;
			text-align :center;
		}
	</style>
	
</head>
<body>

	<br>
	<b><font size="6" color="gray">�۾���</font></b>
	<br>
	
	<form method="post" action="FeedsWriteAction.bo" name="feedsForm" enctype="multipart/form-data">
	<input type="text" readonly="readonly" name="email" value="${sessionScope.sessionEmail}">
	<table width="700" border="3" bordercolor="lightgray" align="center">
		<tr>
			<td id="title">�ۼ���</td>
			<td>${sessionScope.sessionEmail}</td>
		</tr>
			<tr>
			<td id="title">
				�� ��
			</td>
			<td>
				<input name="title" type="text" size="70" maxlength="100" placeholder="������ �Է��ϼ���."/>
			</td>		
		</tr>
		<tr>
			<td id="title">
				�� ��
			</td>
			<td>
				<textarea name="contents" cols="72" rows="20"></textarea>			
			</td>		
		</tr>
		<tr>
			<td id="title">
				����÷��
			</td>
			<td>
				<input type="file" name="" />
			</td>	
		</tr>

		<tr align="center" valign="middle">
			<td colspan="5">
				<input type="reset" value="�ۼ����" >
				<input type="submit" value="���" >
				<input type="button" value="���" >			
			</td>
		</tr>
	</table>	
	</form>
	
</body>
</html>