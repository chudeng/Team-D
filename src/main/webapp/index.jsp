<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>다락방</title>
  <link rel="stylesheet" href="style/index_style.css">
  <script type="text/javascript" src="script/index_script.js"></script>
</head>
<body>
	<div class="container" id="container">
	
	  <!-- 회원가입 -->
		<div class="form_container sign_up_container">
			<form action="#" name="register_form">
				<h1>Welcome!</h1><br>
	
			  <input id="name" type="text" placeholder="Name" />
	      <div class="form_text_alert">
	          <div id="alert_name" class="form_text_alert"></div>
	      </div>
	
	      <input id="email" type="text" placeholder="Email" />
	      <div class="form_text_alert">
	          <div id="alert_email" class="form_text_alert"></div>
	      </div>
	
	      <input id="password" type="password" placeholder="Password" />
	      <div class="form_text_alert">
	          <div id="alert_password" class="form_text_alert"></div>
	      </div>
	
				<button onclick="register()">Sign Up</button>
			</form>
		</div>
	
	  <!-- 로그인 -->
	  <div class="form_container sign_in_container">
	    <form action="#" name="login_form">
	      <h1>Welcome Back!</h1><br>
	
	      <input id="email" type="text" placeholder="Email" />
	      <div class="form_text_alert">
	          <div id="alert_email_login" class="form_text_alert"></div>
	      </div>
	
	      <input id="password" type="password" placeholder="Password" />
	      <div class="form_text_alert">
	          <div id="alert_password_login" class="form_text_alert"></div>
	      </div>
	
	      <button onclick="login()">Sign In</button><br>
	
	      <!-- 비밀번호 찾기 -->
	      <input id="forgot-password-toggle" type="checkbox">
	      <label for="forgot-password-toggle">forget password?</label>
	
	      <div class="forgot-password-content">
	        <br>
	        <input id="email" type="text" placeholder="Enter Your Email" required>
	        <button onclick="sendEmail()">Send</button>
	      </div>
	
	    </form>
	  </div>
	
	  <!--  -->
		<div class="overlay_container">
			<div class="overlay">
				<div class="overlay_panel overlay_left">
					<button class="ghost" id="signIn" onclick="move()">Sign In</button>
				</div>
				<div class="overlay_panel overlay_right">
					<button class="ghost" id="signUp" onclick="move()">Sign Up</button>
				</div>
			</div>
		</div>
	
	</div>
</body>
</html>