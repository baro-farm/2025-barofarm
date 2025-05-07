package vo;

import java.util.Date;
import java.util.List;

import dto.buyer.Address;

public class UserVO {
	Long userNum;
	String userId;
	String pwd;
	String userName;
	String phone;
	Date birthDate;
	String email;
	Boolean isSeller;
	Boolean isDeleted;
	List<Address> addresses;
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
	public Boolean getIsSeller() {
		return isSeller;
	}
	public void setIsSeller(Boolean isSeller) {
		this.isSeller = isSeller;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	@Override
	public String toString() {
		return "UserVO [userNum=" + userNum + ", userId=" + userId + ", pwd=" + pwd + ", userName=" + userName
				+ ", phone=" + phone + ", birthDate=" + birthDate + ", email=" + email + ", isSeller=" + isSeller
				+ ", isDeleted=" + isDeleted + ", addresses=" + addresses + "]";
	}
	
	
}
