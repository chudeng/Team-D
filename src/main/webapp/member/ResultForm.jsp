<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
<html>
<head>
    <title>��� ������</title>
</head>
<body>
    <br><br><br>
    <!-- 
        String msg = (String)session.getAttribute("msg");
    
        if(msg != null)
        {
            if(msg.equals("0"))
                out.println("<font size='6'>ȸ�������� �����Ǿ����ϴ�.</font>");
            else if(msg.equals("1"))
                out.println("<font size='6'>ȸ�������� ���ϵ帳�ϴ�.</font>");
            
            session.removeAttribute("msg");
        }
        else
        {
            out.println("<font size='6'>ȸ�������� �����Ǿ����ϴ�.</font>");
        }
        //�� �κ��� �Ʒ��� ���� EL�� JSTL�� �̿��� �����Ͽ���.
     -->
    
    <c:set var="msg" value="${sessionScope.msg}" scope="session" />
    <c:choose>
        <c:when test="${msg!=null && msg=='0'}">
            <font size='6'>ȸ�������� �����Ǿ����ϴ�.</font>
            <c:remove var="msg" scope="session"></c:remove>
        </c:when>
        <c:when test="${msg!=null && msg=='1'}">
            <font size='6'>ȸ�������� ���ϵ帳�ϴ�.</font>
            <c:remove var="msg" scope="session"></c:remove>
        </c:when>
        <c:otherwise>
            <font size='6'>ȸ�������� �����Ǿ����ϴ�.</font>
        </c:otherwise>
    </c:choose>
 
    <br><br>
    <input type="button" value="��������" onclick="javascript:window.location='MainForm.do'"/>
    
</body>
</html>