<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>    
<%@ page import="com.member.model.MemberVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
 
<html>
<head>
    <title>회원 리스트 - 관리자 화면</title>
    
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
    <b><font size="6" color="gray">전체 회원정보</font></b>
    <br><br>
    
    <table>    
        <tr align="center">
            <td id=title>이메일</td>
            <td id=title>비밀번호</td>
            <td id=title>별명</td>
            <td id=title>성별</td>
            <td id=title>생년월일</td>
            <td id=title>가입일</td>
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