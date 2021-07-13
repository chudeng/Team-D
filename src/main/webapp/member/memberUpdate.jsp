<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>내 정보 수정</title>
	<link rel="stylesheet"  href="style/myinfo_style.css">
</head>
<body>

	<!-- 배경 틀 -->
	<div id="background">
		<hr id="line">
		
		<!-- 위에 버튼, 현재 (웹사이트에서) 위치, 검색창 -->
		<div id="header">
			<div id="arrrow"> </div>
			<!-- 현재 위치 표시 -->
			<div id="location_box">    다락방 > 내 정보 수정 </div>
			
			<!-- 검색 -->
			<div id="tag_search">
				<!--검색 버튼-->
				<input type="submit" id="tag_search" name="search" value="검색">
				<!--검색 창-->
		        <input type="search" id="tag_search" name="tag_search" placeholder="키워드 검색...">
			</div> <!-- closing tag for tag_search -->
		</div><!-- closing tag for id="header" -->
		
		<div id="main_body">
			<!--COLUMN 1: 현재 프로필 정보 표시 바-->
			<div id="left_column">
				
				<div id="profile_area">
					<div id="profile_photo">
						<!-- image is currently placeholder -->
						<img id="current_propic" src="http://placehold.it/200x200" alt="current profile pic">
					</div> <!-- end tag for id="photo" -->
					
					<div id="profile_name">
						<div id="nickname">${mVo.nickname}</div>
						<input id="infbtn" type="submit" value="내 정보">
					</div><!-- end tag for id="profile_name" -->
					
					<div id="profile_info">
						<div id="info_greeting">${mVo.greeting }</div>
						<div id="info_other">
							<div id="b_day">YYYY.MM.DD</div>
							<div id="email">${mVo.email }</div>
						</div><!-- end tag for id="info_others" -->
					</div><!-- end tag for id="profile_info" -->
					
				</div> <!-- end tag for id="profile_area" -->
			</div><!--end tag for id="left_column"-->
      		<!--END COLUMN 1-->


      		<!--COLUMN 2: 개인정보 수정 필드-->
			<div id="middle_column">
				<input id="write_button" type="submit" value="글쓰기">
				
				<hr>
				<div id="middle_column_content">
				
					<form action="myinfoUpdate.do" method="post" name="frm" class="navigation_bar_preview">
						<table>
							<tr>
								<td><label for="email">이메일: </label></td>
								<td><input type="text" name="email" size="27"
									value="${mVo.email}" readonly></td>
							</tr>
							<tr>
								<td><label for="nickname">닉네임: </label></td>
								<td><input type="text" name="nickname" size="27"
									value="${mVo.nickname}"></td>
							</tr>
							<tr>
								<td><label for="pwd">비밀번호: </label></td>
								<td><input type="password" name="pwd" size="27"></td>
							</tr>
							<tr height="30">
								<td width="80">비밀번호 확인: </td>
								<td><input type="password" name="pwd_check" size="27"></td>
							</tr>
							<tr>
								<td><label for="birthday">생년월일: </label></td>
								<td><input type="date" name="birthday"></td>
							</tr>
							<tr>
								<td><label for="sex">성별:</label></td>
								<td>
									<c:choose>
										<c:when test="{mVo.sex==1}">
											<input type="radio" name="sex" value="1" checked="checked"> 남자
											<input type="radio" name="sex" value="2"> 여자
										</c:when>
										<c:otherwise>
											<input type="radio" name="sex" value="1"> 남자
											<input type="radio" name="sex" value="2" checked="checked"> 여자
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>
								<td><label for="greeting">상태 메세지:</label></td>
								<td>
									<input type="text" name="greeting" placeholder="자기 소개글을 써주세요 ㅇㅂㅇ!"
									value="${mVo.greeting}" size="27">
								</td>
								
							</tr>
							<tr align="center">
								<td><input type="submit" name="save" value="저장" onclick="return joinCheck()"></td>
								<td><input type="reset" name="reset" value="취소"></td>
							</tr>
							<tr>
								<td colspan="2">${message }</td>
							</tr>
						</table>
					</form>
					
				</div><!--end tag for middle_column_content-->
			</div><!--end tag for middle_column-->
      		<!--END COLUMN 2-->
			
			
			<!-- COLUMN 3: 태그 필드 -->
			<div id="right_column">
				<div><h1>TAGS</h1></div>
				<div>
					<a href="#a">#오늘날씨</a>
         			<a href="#b">#음악</a>
         			<a href="#c">#오늘의_메뉴</a>
				</div>
			</div><!--end tag for right_column-->
      		<!--END COLUMN 3-->



		</div> <!-- closing tag for id="main_body" -->
		<hr id="line2">
		
	</div> <!-- closing tag for id="background" -->
</body>
</html>
