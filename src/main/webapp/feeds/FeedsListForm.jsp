<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>        
<html>
<head>
	<title>��ü �Խñ�</title>
	
	<style type="text/css">
		#wrap {
			width: 800px;
			margin: 0 auto 0 auto;
		}

		#topForm{
			text-align :right;
		}

		#board, #pageForm, #searchForm{
			text-align :center;
		}
		
		#bList{
			text-align :center;
		}
	</style>
	
	<script type="text/javascript">
		function writeForm(){
			location.href="FeedsWriteForm.bo";
		}
	</script>
	
</head>
<body>	

<div id="wrap">

	<!-- �۸�� �� �κ�-->
	<br>
	<div id="topForm">
		<!-- �α����� �Ǿ����� ������ �۾��� ��ư�� ����-->
		<c:if test="${sessionScope.sessionEmail!=null}">
			<input type="button" value="�۾���" onclick="writeForm()">
		</c:if>	
	</div>
	
	<!-- �Խñ� ��� �κ� -->
	<br>
	<div id="board">
		<table id="bList" width="800" border="3" bordercolor="lightgray">
			<tr heigh="30">
				<td>�۹�ȣ</td>
				<td>����</td>
				<td>�ۼ���</td>
				<td>�ۼ���</td>
			</tr>
			<c:forEach var="feed" items="${requestScope.list}">
			<tr>
				<td>${feed.idx }</td>
				<td>
					<a href="FeedDetailAction.bo?num=${feed.idx }&pageNum=${sPage})">${feed.title }</a>
				</td>
				<td>
					<a href="#">${feed.email }</a>
				</td>
				<td>${feed.created }</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	
	<!-- ������ �ѹ� �κ� -->
	<br>
	<div id="pageForm">
		<c:if test="${startPage != 1}">
            <a href='BoardListAction.bo?page=${startPage-1}'>[ ���� ]</a>
        </c:if>
        
        <c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
            <c:if test="${pageNum == sPage}">
                ${pageNum}&nbsp;
            </c:if>
            <c:if test="${pageNum != sPage}">
                <a href='BoardListAction.bo?page=${pageNum}'>${pageNum}&nbsp;</a>
            </c:if>
        </c:forEach>
        
        <c:if test="${endPage != maxPage }">
            <a href='BoardListAction.bo?page=${endPage+1 }'>[����]</a>
        </c:if>
	</div>
	
	<!--  �˻� �κ� -->
	<br>
	<div id="searchForm">
		<form>
			<select name="opt">
				<option value="0">����</option>
				<option value="1">����</option>
				<option value="2">����+����</option>
				<option value="3">�۾���</option>
			</select>
			<input type="text" size="20" name="condition"/>&nbsp;
			<input type="submit" value="�˻�"/>
		</form>	
	</div>
</div>	

</body>
</html>