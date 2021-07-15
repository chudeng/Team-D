<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>    
<%@ page import="com.member.model.MemberVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
 
<html>
<head>
    <title>ȸ�� ����Ʈ - ������ ȭ��</title>
    
    <style type="text/css">
        table{
            margin-left:auto; 
            margin-right:auto;
            border:3px solid skyblue;
        }
        
        td{
            border:1px solid skyblue
        }
        
        #title{
            background-color:skyblue
        }
    </style>
    
</head>
<body>
    <br><br>
    <b><font size="6" color="gray">��ü ȸ������</font></b>
    <br><br>
    
    <table>    
        <tr align="center">
            <td id=title>�̸���</td>
            <td id=title>��й�ȣ</td>
            <td id=title>����</td>
            <td id=title>����</td>
            <td id=title>�������</td>
            <td id=title>������</td>
        </tr>
        
        <c:set var="memberList" value="${requestScope.memberList}"/>
        <c:forEach var="member" items="${memberList}">
            <tr>
                <td>${member.email}</td>
                <td>${member.pwd}</td>
                <td>${member.nickname}</td>
                <td>${member.sex}</td>
                <td>${member.birthyy}</td>
                <td>${member.reg}</td>
            </tr>
        </c:forEach>    
    </table>
 
</body>
</html>