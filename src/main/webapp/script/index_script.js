// 'SignIn' 'SignUp' 버튼 클릭 화면 이동
function move() {
	const signUpButton = document.getElementById('signUp');
	const signInButton = document.getElementById('signIn');
	const container = document.getElementById('container');

	signUpButton.addEventListener('click', () => {
		container.classList.add("right_panel_active");
	});

	signInButton.addEventListener('click', () => {
		container.classList.remove("right_panel_active");
	});
}

const email = 'aaa@aa.aaa';
const password = 'aaaaaa';

// 로그인
function login() {
    const form = document.login_form;
    const checkEmail = checkValidEmail(form);

		// <수정사항> DB 연동 후 이메일에 맞는 비밀번호 확인으로 바꾸기
    const checkPwd = checkValidPassword(form);

		// 경고 메시지 제거
    if (checkEmail)
        document.getElementById('alert_email_login').innerText = "";

    if (checkPwd)
        document.getElementById('alert_password_login').innerText = "";

    if (checkEmail && checkPwd) {
        // 로그인 성공, 피드창으로 이동
				if (form.email.value == email){
					form.location.replace("./feeds/feeds.html")
				}
    }
}

// 회원가입
function register() {
    const form = document.register_form;
    const checkUsername = checkValidUsername(form);
    const checkEmail = checkValidEmail(form);
    const checkPwd = checkValidPassword(form);

		// 경고 메시지 제거
    if (checkUsername)
        document.getElementById('alert_name').innerText = "";

    if (checkEmail)
        document.getElementById('alert_email').innerText = "";

    if (checkPwd)
        document.getElementById('alert_password').innerText = "";

    if (checkUsername && checkEmail && checkPwd) {
        // 회원가입 성공, 피드창으로 이동
    }
}

// 이름 확인
function checkValidUsername(form) {
    if (form.name.value == "") {
        document.getElementById('alert_name').innerText = "Please enter username.";
        return false;
    }

    return true;
}

// 이메일 확인
function checkValidEmail(form) {
		var divName = '';

		if (form == document.register_form) {
				divName = 'alert_email'
		}
		else if (form == document.login_form) {
				divName = 'alert_email_login'
		}

    if (form.email.value == "") {
        document.getElementById(divName).innerText = "Please enter email.";
        return false;
    }

    const exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

    // "a@a.a" 이메일 형식 검사.
    if (exptext.test(form.email.value) === false) {
        document.getElementById('divName').innerText = "Email is not valid.";
        return false;
    }

    return true;
}

// 비밀번호 확인
function checkValidPassword(form) {
		var divName = '';

		if (form == document.register_form) {
				divName = 'alert_password'
		}
		else if (form == document.login_form) {
				divName = 'alert_password_login'
		}

    if (form.password.value == "") {
        document.getElementById(divName).innerText = "Please enter password.";
        return false;
    }

    const pw = form.password.value;
    // String.prototype.search() :: 검색된 문자열 중에 첫 번째로 매치되는 것의 인덱스를 반환한다. 찾지 못하면 -1 을 반환한다.
    // 숫자
    const num = pw.search(/[0-9]/g);
    // 알파벳
    const eng = pw.search(/[a-z]/ig);
    // 특수문자
    const spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

    if (pw.length < 6) {
        // 최소 6문자.
        document.getElementById(divName).innerText = "Password must be at least 6 characters.";
        return false;
    } else if (pw.search(/\s/) != -1) {
        // 공백 제거.
        document.getElementById(divName).innerText = "Please enter your password without spaces.";
        return false;
    }

    return true;
}

// 비밀번호 찾기
function sendEmail(email) {
	const form = document.login_form;

	if(form.getElementById(email)){

	}
}
