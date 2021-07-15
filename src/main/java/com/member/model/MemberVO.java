package com.member.model;
 
import java.sql.Timestamp;
 
 
// 데이터의 전달을 담당하는 클래스 - DTO
public class MemberVO 
{
    private String email;		
    private String pwd;    
    private String nickname;	//별명      
    private String sex;
    private String greeting;	//자기소개글
    private String birthyy;     //생일 - 년
    private String birthmm;     //생일 - 월
    private String birthdd;     //생일 - 일
//  private String mail1; 		//이메일 - @ 앞부분
//  private String mail2;      	//이메일 - @ 뒷부분
    private int admin;			//관리자 여부
    private Timestamp reg;		//가입일
    
    
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getGreeting() {
		return greeting;
	}
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	public String getBirthyy() {
		return birthyy;
	}
	public void setBirthyy(String birthyy) {
		this.birthyy = birthyy;
	}
	public String getBirthmm() {
		return birthmm;
	}
	public void setBirthmm(String birthmm) {
		this.birthmm = birthmm;
	}
	public String getBirthdd() {
		return birthdd;
	}
	public void setBirthdd(String birthdd) {
		this.birthdd = birthdd;
	}
//	public String getMail1() {
//		return mail1;
//	}
//	public void setMail1(String mail1) {
//		this.mail1 = mail1;
//	}
//	public String getMail2() {
//		return mail2;
//	}
//	public void setMail2(String mail2) {
//		this.mail2 = mail2;
//	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public Timestamp getReg() {
		return reg;
	}
	public void setReg(Timestamp reg) {
		this.reg = reg;
	}
    
}