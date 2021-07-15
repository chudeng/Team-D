<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
    <%--
        String contentPage=request.getParameter("contentPage");
        if(contentPage==null)
            contentPage="FirstView.jsp";
            
        �� �κ��� EL�� JSTL�� �����ϸ� �Ʒ��� ����.    
    --%>
    
<html>
<head>
    <title>���� ȭ��</title>
    
    <style>
        /*
        ���̾ƿ� ��ü ��� ���� �±�  
        - margin:0 auto 0 auto;(�ð�������� ��, ������, �Ʒ�, ����)
        ����, ������ �ٱ������� auto�� �ָ� �߾������� �ȴ�. 
        
        ��ü�� �����ִ� �±� #wrap 
        */
        #wrap {
            width: 950px;
            margin: 0 auto 0 auto;
        }
        
        #header {
            text-align: center;
            width: 950px;
            height: 150px;
            background-color: #92FFFF;
            padding: 60px 0px;
        }
        
        #main {
            float: left;
            width: 950px;
            height: 500px;
            background-color: #FFCA6C;
            text-align:center;
            vertical-align: middle;
        }
        #footer {
            clear: left;
            width: 950px;
            height: 100px;
            background-color: #7DFE74;
        }
    </style>
    
</head>
<body>
    <div id="wrap">
        <div id="header">
            <jsp:include page="Header.jsp" />
        </div>
        <div id="main">
        
            <!-- contentPage�� ���� ��� FirstView.jsp�� �����ش�. -->
            <c:set var="contentPage" value="${param.contentPage}"/>
            <c:if test="${contentPage==null}">    
                <jsp:include page="FirstView.jsp" />
            </c:if>
            <jsp:include page="${contentPage}" />
            
        </div>
    </div>
    
</body>
</html>