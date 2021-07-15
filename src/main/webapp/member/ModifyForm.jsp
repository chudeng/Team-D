<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.member.model.MemberDAO" %>    
<%@ page import="com.member.model.MemberVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
 
    <title>ȸ������ ����ȭ��</title>
    
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
            var selectMail = document.getElementById('mail2'); // select ���̵� �����´�.
            for (i = 0, j = selectMail.length; i < j; i++)  // select �ϴ� option ����ŭ �ݺ��� ������.
            {
                if (selectMail.options[i].value == val)  // �ԷµȰ��� option�� value�� ������ ��
                {
                    selectMail.options[i].selected = true; // ��������� üũ�ǵ��� �Ѵ�.
                    break;
                }
            }
        } */
        
        // ��й�ȣ �Է¿��� üũ
        function checkValue() {
        	if(!document.userInfo.pwd.value){
				alert("��й�ȣ�� �Է��ϼ���.");
				return false;
			}
			
			// ��й�ȣ�� ��й�ȣ Ȯ�ο� �Էµ� ���� �������� Ȯ��
			if(document.userInfo.pwd.value != document.userInfo.pwdcheck.value ){
				alert("��й�ȣ�� �����ϰ� �Է��ϼ���.");
				return false;
			}
        }
        
    </script>
    
</head>
<body onload="init()">
 
        <br><br>
        <b><font size="6" color="gray">ȸ������ ����</font></b>
        <br><br><br>
        <!-- ȸ�������� ������ member ������ ��´�. -->
        <c:set var="member" value="${requestScope.memberInfo}"/>
        
        <!-- �Է��� ���� �����ϱ� ���� form �±׸� ����Ѵ� -->
        <!-- ��(�Ķ����) ������ POST ��� -->
        <form method="post" action="MemberModifyAction.do" 
                name="userInfo" onsubmit="return checkValue()">
                
            <table>
                <tr>
                    <td id="title">�̸���</td>
                    <td id="title">${member.email}</td>
                </tr>
                <tr>
                    <td id="title">��й�ȣ</td>
                    <td>
                        <input type="password" name="pwd" maxlength="16" value="${member.pwd}">
                    </td>
                </tr>
                <tr>
                    <td id="title">��й�ȣ Ȯ��</td>
                    <td>
                        <input type="password" name="pwdcheck" maxlength="16" value="${member.pwd}">
                    </td>
                </tr>
            </table>    
            <br><br>    
            <table>
 
                <tr>
                    <td id="title">����</td>
                    <td>
                    	<input type="text" name="nickname" maxlength="12" value="${member.nickname}">
                    </td>
                </tr>
                    
                <tr>
                    <td id="title">���� </td>
                    <td>
                    <c:set var="sex" value="${member.sex}"/>
                    <c:choose>
					<c:when test="${sex eq '��'}">
						<input type="radio" name="sex" value="��" checked="checked">��
						<input type="radio" name="sex" value="��">��
					</c:when>
					<c:otherwise>
						<input type="radio" name="sex" value="��">��
						<input type="radio" name="sex" value="��" checked="checked">��
					</c:otherwise>
					</c:choose>
					</td>
                </tr>
                    
                <tr>
                    <td id="title">����</td>
                    <td>
                   		<input type="text" name="birthyy" maxlength="4" value="${member.birthyy}" size="6" >��
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
							</select>��
						<input type="text" name="birthdd" maxlength="2" value="${member.birthdd}" size="4" >��
                        
                    </td>
                </tr>
                <tr>
                    <td id="title">�λ縻</td>
                    <td>
                    	<textarea rows="10" cols="20" name="greeting">${member.greeting}</textarea>
                    </td>
                </tr>
                    
            <%--     <tr>
                    <td id="title">�̸���</td>
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
                    <td id="title">�޴���ȭ</td>
                    <td>
                        <input type="text" name="phone" value="${member.phone}"/>
                    </td>
                </tr>
                <tr>
                    <td id="title">�ּ�</td>
                    <td>
                        <input type="text" size="50" name="address"
                            value="${member.address}"/>
                    </td>
                </tr> --%>
            </table>
            <br><br>
            <input type="button" value="���" onclick="javascript:window.location='MainForm.do'">
            <input type="submit" value="����"/>  
        </form>
        
</body>
</html>