package dto;

import java.time.LocalDate;

public class User {
	Long userNum;
	String userid;
	String pwd;
	String userName;
	String phone;
	LocalDate birthDate;
	String email;
	Boolean isSeller;
	Boolean deleted;
	String fcmToken;
	
	public User() {
		super();
	}
	public User(Integer userNum, String userid, String pwd, String userName, String phone, LocalDate birthDate, String email,
			Boolean isSeller) {
		super();
		this.userNum = userNum;
		this.userid = userid;
		this.pwd = pwd;
		this.userName = userName;
		this.phone = phone;
		this.birthDate = birthDate;
		this.email = email;
		this.isSeller = isSeller;
	}
	
	public Integer getUserNum() {
		return userNum;
	}
	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getIsSeller() {
		return isSeller;
	}
	public void setIsSeller(Boolean isSeller) {
		this.isSeller = isSeller;
	}
	
	
}
