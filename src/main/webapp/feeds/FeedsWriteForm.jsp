<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
	<title>게시판 - 글쓰기</title>
	
	<style type="text/css">
		#title{
			height : 16;
			font-family :'돋움';
			font-size : 12;
			text-align :center;
		}
	</style>
	
</head>
<body>

	<br>
	<b><font size="6" color="gray">글쓰기</font></b>
	<br>
	
	<form method="post" action="FeedsWriteAction.bo" name="feedsForm" enctype="multipart/form-data">
	<input type="text" readonly="readonly" name="email" value="${sessionScope.sessionEmail}">
	<table width="700" border="3" bordercolor="lightgray" align="center">
		<tr>
			<td id="title">작성자</td>
			<td>${sessionScope.sessionEmail}</td>
		</tr>
			<tr>
			<td id="title">
				제 목
			</td>
			<td>
				<input name="title" type="text" size="70" maxlength="100" placeholder="제목을 입력하세요."/>
			</td>		
		</tr>
		<tr>
			<td id="title">
				내 용
			</td>
			<td>
				<textarea name="contents" cols="72" rows="20"></textarea>			
			</td>		
		</tr>
		<tr>
			<td id="title">
				파일첨부
			</td>
			<td>
				<input type="file" name="" />
			</td>	
		</tr>

		<tr align="center" valign="middle">
			<td colspan="5">
				<input type="reset" value="작성취소" >
				<input type="submit" value="등록" >
				<input type="button" value="목록" >			
			</td>
		</tr>
	</table>	
	</form>
	
</body>
</html>