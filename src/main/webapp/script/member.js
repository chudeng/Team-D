/**
회원관리 관련된 폼에 입력된 정보를 확인하는 JS
*/

/**
 * 로그인 처리 현황
1. 아이디와 암호 모두 입력되었을 때만 로그인 인증 처리 수행
2. 입력이 안되면 알림창으로 에러 메시지 출력
 */

function loginCheck(){
	if (document.loginform.email.value == ""){
		alert("아이디를 입력해 주세요.");
		document.loginform.email.focus();
		return false;
	} else if (document.loginform.pwd.value == ""){
		alert("비밀번호를 입력해주세요.");
		document.loginform.pwd.focus();
		return false;
	} else {
		return true;
	}
}


/**
회원정보 입력 필수 입력 사항 확인
이름, 아이디, 암호, 암호확인
아이디 4글자 이상
암호와 암호확인이 일치 확인
re id의 값이 존재하는지 확인하여 중복체크여부를 검사
 */

function joinCheck() {
	if (document.joinform.email.value == "") {
		alert("이메일을 입력해주세요.");
		document.joinform.email.focus();
		return false;
	}

	if (document.joinform.pwd.value == "") {
		alert("비밀번호를 입력해주세요.");
		document.joinform.pwd.focus();
		return false;
	}

	if (document.joinform.pwdpwd.value == "") {
		alert("비밀번호를 확인해주세요.");
		document.joinform.pwdpwd.focus();
		return false;
	}

	if (document.joinform.nickname.value == "") {
		alert("별명을 입력해주세요.");
		document.joinform.nickname.focus();
		return false;
	}

	if (document.joinform.nickname.value.length < 4) {
		alert("아이디는 영문 4글자, 한글 2글자 이상이어야 합니다.");
		document.joinform.nickname.focus();
		return false;
	}


	if (document.joinform.reid.value == "") {
		alert("아이디 중복체크를 하지 않았습니다.");
		return false;
	}

	if (document.joinform.pwd.value != document.joinform.pwdpwd.value) {
		alert("비밀번호가 일치하지 않습니다.");
		document.joinform.pwdpwd.focus();
		return false;
	}

	return true;
}

function idCheck() {
	// 사용자 아이디가 입력되었는지 확인 루틴 구현
	if (document.joinform.email.value == "") {
		alert("사용자 아이디를 입력해주세요.");
		document.joinform.email.focus();
		return false;
	}
	// 아이디 중복 체크를 위한 페이지는 새로운 창에 출력한다.(idcheck.jsp)
	var url = "idCheck.do?email=" + document.joinform.email.value;
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
}

function idOk(){
	opener.joinform.email.value = document.frm.email.value;
	opener.joinform.reid.value = document.frm.email.value;
	
	self.close();
}