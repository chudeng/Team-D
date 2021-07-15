<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>�� ����</title>
    
    <style type="text/css">
        #title{
            height : 16;
            font-family :'����';
            font-size : 12;
            text-align :center;
        }
    </style>
    
    <script type="text/javascript">
        function changeView()
        {
            location.href='BoardListAction.bo?page=${pageNum}';
        }
    </script>
    
</head>
<body>
 
    <br>
    <b><font size="6" color="gray">�� ����</font></b>
    <br>
    
    <form method="post" action="BoardUpdateAction.bo?page=${pageNum}" name="boardForm" 
            enctype="multipart/form-data">
 
    <input type="hidden" name="board_num" value="${board.board_num}"/>
    <input type="hidden" name="existing_file" value="${board.board_file}"/>
 
    <table width="700" border="3" bordercolor="lightgray" align="center">
        <tr>
            <td id="title">�ۼ���</td>
            <td>${board.board_id}</td>
        </tr>
            <tr>
            <td id="title">
                �� ��
            </td>
            <td>
                <input name="board_subject" type="text" size="70" maxlength="100" 
                    value="${board.board_subject}"/>
            </td>        
        </tr>
        <tr>
            <td id="title">
                �� ��
            </td>
            <td>
                <textarea name="board_content" cols="72" rows="20">
                    ${board.board_content}
                </textarea>            
            </td>        
        </tr>
                <!-- ����� �ƴ� ��쿡�� ���� ÷�� �����ϵ��� ó�� -->
        <c:if test="${board.board_parent==0}">    
            <tr>
                <td id="title">
                    ���� ����
                </td>
                <td>
                    &nbsp;&nbsp; ${board.board_file}
                </td>    
            </tr>
            <tr>
                <td id="title">
                    ÷������
                </td>
                <td>
                    <input type="file" name="board_file"/>
                </td>    
            </tr>
        </c:if>
        
        <tr align="center" valign="middle">
            <td colspan="5">
                <input type="reset" value="�ۼ����" >
                <input type="submit" value="����" >
                <input type="button" value="���" onclick="changeView()" >            
            </td>
        </tr>
    </table>    
    </form>
    
</body>
</html>