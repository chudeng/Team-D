<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<html>
<head>
    <title>��� ����</title>
    
    <link rel="stylesheet" href="css/bootstrap.min.css">
    
    <style type="text/css">
        #wrap{
            text-align: center;
            width: 800px;
            height: 150px;
        }
    </style>
    
    <script type="text/javascript">
        
        function changeView(value){
            
            if(value == "0") // HOME ��ư Ŭ���� ùȭ������ �̵�
            {
                location.href="MainForm.do";
            }
            else if(value == "1") // �α��� ��ư Ŭ���� �α��� ȭ������ �̵�
            {
                location.href="LoginForm.do";
            }
            else if(value == "2") // ȸ������ ��ư Ŭ���� ȸ������ ȭ������ �̵�
            {
                location.href="JoinForm.do";
            }
            else if(value == "3") // �α׾ƿ� ��ư Ŭ���� �α׾ƿ� ó��
            {
                location.href="MemberLogoutAction.do";
            }
            else if(value == "4") // ������ ��ư Ŭ���� ȸ������ �����ִ� ȭ������ �̵�
            {
                location.href="MemberInfoAction.do";
            }
            else if(value == "5")
            {
                location.href="MemberListAction.do";
            }
            else if(value == "6")
            {
                location.href="FeedsListAction.bo";
            }
        }
    </script>
    
</head>
<body>
    <div id = "wrap">
        <p>
            <button class="btn btn-success" onclick="changeView(0)">HOME</button>
 
        <!-- // �α��� �ȵǾ��� ��� - �α���, ȸ������ ��ư�� �����ش�. -->
        <c:if test="${sessionScope.sessionEmail==null}">
            <button id="loginBtn" class="btn btn-primary" onclick="changeView(1)">�α���</button>
            <button id="joinBtn" class="btn btn-primary" onclick="changeView(2)">ȸ������</button>
        </c:if>
    
        <!-- // �α��� �Ǿ��� ��� - �α׾ƿ�, ������ ��ư�� �����ش�. -->
        <c:if test="${sessionScope.sessionEmail!=null}">
            <button id="logoutBtn" class="btn btn-primary" onclick="changeView(3)">�α׾ƿ�</button>
            <button id="updateBtn" class="btn btn-primary" onclick="changeView(4)">������</button>
 
        </c:if>
        
            <button id="joinBtn" class="btn btn-info" onclick="changeView(6)">�Խ���</button>
            
        <!--  ������ �α��� -->
        <c:if test="${sessionScope.sessionEmail !=null && sessionScope.sessionAdmin == 1}">
            <button id="memberViewBtn" class="btn btn-warning" onclick="changeView(5)">ȸ������</button>
        </c:if>
                
        </p>
    </div>
</body>
</html>