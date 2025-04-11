package dto.buyer;

import java.util.Date;

public class User {
	Long userNum;
	String userId;
	String pwd;
	String userName;
	String phone;
	Date birthDate;
	String email;
	boolean isSeller;
	boolean deleted;
	Date createdAt;
	Date updatedAt;
	String fcmToken;
	
	
	public User() {
		super();
	}
	
	public User(Long userNum, String userId, String pwd, String userName, String phone, Date birthDate, String email) {
		super();
		this.userNum = userNum;
		this.userId = userId;
		this.pwd = pwd;
		this.userName = userName;
		this.phone = phone;
		this.birthDate = birthDate;
		this.email = email;
	}

	public User(String userId, String pwd, String userName, String phone, Date birthDate, String email) {
		super();
		this.userId = userId;
		this.pwd = pwd;
		this.userName = userName;
		this.phone = phone;
		this.birthDate = birthDate;
		this.email = email;
	}

	public User(Long userNum, String userId, String fcmToken) {
		super();
		this.userNum = userNum;
		this.userId = userId;
		this.fcmToken = fcmToken;
	}

	public User(Long userNum, String userId, String pwd, String userName, String phone, Date birthDate, String email,
			boolean isSeller, boolean deleted, Date createdAt, Date updatedAt, String fcmToken) {
		super();
		this.userNum = userNum;
		this.userId = userId;
		this.pwd = pwd;
		this.userName = userName;
		this.phone = phone;
		this.birthDate = birthDate;
		this.email = email;
		this.isSeller = isSeller;
		this.deleted = deleted;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.fcmToken = fcmToken;
	}

	public Long getUserNum() {
		return userNum;
	}

	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getIsSeller() {
		return isSeller;
	}

	public void setIsSeller(boolean isSeller) {
		this.isSeller = isSeller;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getFcmToken() {
		return fcmToken;
	}

	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}

	@Override
	public String toString() {
		return "User [userNum=" + userNum + ", userId=" + userId + ", pwd=" + pwd + ", userName=" + userName
				+ ", phone=" + phone + ", birthDate=" + birthDate + ", email=" + email + ", isSeller=" + isSeller
				+ ", deleted=" + deleted + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", fcmToken="
				+ fcmToken + "]";
	}

}
