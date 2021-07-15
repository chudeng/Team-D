<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<html>
<head>
    <title>결과 페이지</title>
</head>
<body>
    <br><br><br>
    <!-- 
        String msg = (String)session.getAttribute("msg");
    
        if(msg != null)
        {
            if(msg.equals("0"))
                out.println("<font size='6'>회원정보가 수정되었습니다.</font>");
            else if(msg.equals("1"))
                out.println("<font size='6'>회원가입을 축하드립니다.</font>");
            
            session.removeAttribute("msg");
        }
        else
        {
            out.println("<font size='6'>회원정보가 삭제되었습니다.</font>");
        }
        //이 부분을 아래와 같이 EL과 JSTL을 이용해 변경하였다.
     -->
    
    <c:set var="msg" value="${sessionScope.msg}" scope="session" />
    <c:choose>
        <c:when test="${msg!=null && msg=='0'}">
            <font size='6'>회원정보가 수정되었습니다.</font>
            <c:remove var="msg" scope="session"></c:remove>
        </c:when>
        <c:when test="${msg!=null && msg=='1'}">
            <font size='6'>회원가입을 축하드립니다.</font>
            <c:remove var="msg" scope="session"></c:remove>
        </c:when>
        <c:otherwise>
            <font size='6'>회원정보가 삭제되었습니다.</font>
        </c:otherwise>
    </c:choose>
 
    <br><br>
    <input type="button" value="메인으로" onclick="javascript:window.location='MainForm.do'"/>
    
</body>
</html>