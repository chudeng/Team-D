<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.member.model.MemberDAO" %>    
<%@ page import="com.member.model.MemberVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
 
    <title>회원정보 수정화면</title>
    
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
    
    <script type="text/javascript">
    
     /*    function init(){
            setComboValue("${member.mail2}");
        }
 
        function setComboValue(val) 
        {
            var selectMail = document.getElementById('mail2'); // select 아이디를 가져온다.
            for (i = 0, j = selectMail.length; i < j; i++)  // select 하단 option 수만큼 반복문 돌린다.
            {
                if (selectMail.options[i].value == val)  // 입력된값과 option의 value가 같은지 비교
                {
                    selectMail.options[i].selected = true; // 같은경우라면 체크되도록 한다.
                    break;
                }
            }
        } */
        
        // 비밀번호 입력여부 체크
        function checkValue() {
        	if(!document.userInfo.pwd.value){
				alert("비밀번호를 입력하세요.");
				return false;
			}
			
			// 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
			if(document.userInfo.pwd.value != document.userInfo.pwdcheck.value ){
				alert("비밀번호를 동일하게 입력하세요.");
				return false;
			}
        }
        
    </script>
    
</head>
<body onload="init()">
 
        <br><br>
        <b><font size="6" color="gray">회원정보 수정</font></b>
        <br><br><br>
        <!-- 회원정보를 가져와 member 변수에 담는다. -->
        <c:set var="member" value="${requestScope.memberInfo}"/>
        
        <!-- 입력한 값을 전송하기 위해 form 태그를 사용한다 -->
        <!-- 값(파라미터) 전송은 POST 방식 -->
        <form method="post" action="MemberModifyAction.do" 
                name="userInfo" onsubmit="return checkValue()">
                
            <table>
                <tr>
                    <td id="title">이메일</td>
                    <td id="title">${member.email}</td>
                </tr>
                <tr>
                    <td id="title">비밀번호</td>
                    <td>
                        <input type="password" name="pwd" maxlength="16" value="${member.pwd}">
                    </td>
                </tr>
                <tr>
                    <td id="title">비밀번호 확인</td>
                    <td>
                        <input type="password" name="pwdcheck" maxlength="16" value="${member.pwd}">
                    </td>
                </tr>
            </table>    
            <br><br>    
            <table>
 
                <tr>
                    <td id="title">별명</td>
                    <td>
                    	<input type="text" name="nickname" maxlength="12" value="${member.nickname}">
                    </td>
                </tr>
                    
                <tr>
                    <td id="title">성별 </td>
                    <td>
                    <c:set var="sex" value="${member.sex}"/>
                    <c:choose>
					<c:when test="${sex eq '남'}">
						<input type="radio" name="sex" value="남" checked="checked">남
						<input type="radio" name="sex" value="여">여
					</c:when>
					<c:otherwise>
						<input type="radio" name="sex" value="남">남
						<input type="radio" name="sex" value="여" checked="checked">여
					</c:otherwise>
					</c:choose>
					</td>
                </tr>
                    
                <tr>
                    <td id="title">생일</td>
                    <td>
                   		<input type="text" name="birthyy" maxlength="4" value="${member.birthyy}" size="6" >년
							<select name="birthmm">
								<option selected>${member.birthmm}</option>
								<option value="01" >1</option>
								<option value="02" >2</option>
								<option value="03" >3</option>
								<option value="04" >4</option>
								<option value="05" >5</option>
								<option value="06" >6</option>
								<option value="07" >7</option>
								<option value="08" >8</option>
								<option value="09" >9</option>
								<option value="10" >10</option>
								<option value="11" >11</option>
								<option value="12" >12</option>
							</select>월
						<input type="text" name="birthdd" maxlength="2" value="${member.birthdd}" size="4" >일
                        
                    </td>
                </tr>
                <tr>
                    <td id="title">인사말</td>
                    <td>
                    	<textarea rows="10" cols="20" name="greeting">${member.greeting}</textarea>
                    </td>
                </tr>
                    
            <%--     <tr>
                    <td id="title">이메일</td>
                    <td>
                        <input type="text" name="mail1" maxlength="50" 
                            value="${member.mail1}">
                        @
                        <select name="mail2" id="mail2">
                            <option value="naver.com">naver.com</option>
                            <option value="gmail.com">gmail.com</option>
                            <option value="daum.net" >daum.net</option>
                            <option value="nate.com">nate.com</option>                        
                        </select>
                    </td>
                </tr> --%>
                    
              <%--   <tr>
                    <td id="title">휴대전화</td>
                    <td>
                        <input type="text" name="phone" value="${member.phone}"/>
                    </td>
                </tr>
                <tr>
                    <td id="title">주소</td>
                    <td>
                        <input type="text" size="50" name="address"
                            value="${member.address}"/>
                    </td>
                </tr> --%>
            </table>
            <br><br>
            <input type="button" value="취소" onclick="javascript:window.location='MainForm.do'">
            <input type="submit" value="수정"/>  
        </form>
        
</body>
</html>