<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<html>
    <head>
        <title>첫화면</title>
    </head>
    <body>        
            <!-- 로그인 안되었을 경우 -->
    <c:if test="${sessionScope.sessionEmail==null}">
        <br><br><br>
        <br><br><br>
        <img src="img/welcome.jpg">
        <br><br>    
    </c:if>    
            <!-- 로그인 -->
    <c:if test="${sessionScope.sessionEmail!=null}">    
        <br><br><br>
        <font size=6 color="skyblue">${sessionScope.sessionEmail}</font>
        <font size=6>님 환영합니다.</font>
    </c:if>    
    
    </body>
</html>