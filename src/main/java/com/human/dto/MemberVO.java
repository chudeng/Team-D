// 회원정보를 저장하는 클래스
// VO(value object): 회원 테이블의 데이터를 가져와 프로그램에서 사용하기 전에 보관하는 클래스
// 일반적으로 테이블의 구조와 동일.


package com.human.dto;

public class MemberVO {
	private String email;
	private String pwd;
	private String nickname;
	private String greeting;
	private int sex;
	private int admin;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	@Override
	public String toString() {
		return "MemberVO [email=" + email + ", pwd=" + pwd + ", nickname=" + nickname + ", greeting=" + greeting
				+ ", sex=" + sex + ", admin=" + admin + "]";
	}


}