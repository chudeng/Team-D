<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  

<html>
<head>
	<title>�� �󼼺���</title>
	
	<style type="text/css">
		#wrap {
			width: 800px;
			margin: 0 auto 0 auto;
		}
	
		#detailBoard{
			text-align :center;
		}
		
		#title{
			height : 16;
			font-family :'����';
			font-size : 12;
			text-align :center;
		}
	</style>
	
	<script type="text/javascript">
		function changeView(value)
		{
			location.href="FeedsListAction.bo?page=${pageNum}";		
		}
		
		function doAction(value)
		{
			if(value == 0) // ����
				location.href="BoardUpdateFormAction.bo?num=${board.board_num}&page=${pageNum}";
			else if(value == 1) // ����
				location.href="BoardDeleteAction.bo?num=${board.board_num}";
		}
	</script>
</head>
<body>

<div id="wrap">
	<br><br>
	<div id="board">
		<table id="detailBoard" width="800" border="3" bordercolor="lightgray">
		
			<tr>
				<td id="title">�ۼ���</td>
				<td>${feed.created}</td>
			</tr>
			<tr>
				<td id="title">�ۼ���</td>
				<td>${feed.email}</td>
			</tr>
			<tr>
				<td id="title">
					�� ��
				</td>
				<td>
					${feed.title}
				</td>		
			</tr>
			<tr>
				<td id="title">
					�� ��
				</td>
				<td>
					${feed.contents}
				</td>		
			</tr>
	
			<tr align="center" valign="middle">
				<td colspan="5">
				<c:if test="${sessionScope.sessionEmail !=null}">
					<c:if test="${sessionScope.sessionEmail == feed.email}">
						<input type="button" value="����" onclick="doAction(0)">
						<input type="button" value="����" onclick="doAction(1)">
					</c:if>
				</c:if>		
					<input type="button" value="���" onclick="changeView(0)">			
				</td> <!-- javascript:location.href='BoardListAction.bo?page=${pageNum}' -->
			</tr>
		</table>
	</div>
</div>	

</body>
</html>