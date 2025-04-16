package dto.admin;

import java.text.NumberFormat;
import java.time.LocalDateTime;

public class Store {
	Long sellerNum;
	String userId;
	String userName;
	String phone;
	String birthDate;
	String email;
	LocalDateTime createdAt;
	Integer isDeleted;
	String storeName;
	String businessNum;
	Integer isAlarm;
	Long totalSalesAmount;

	public Long getSellerNum() {
		return sellerNum;
	}

	public void setSellerNum(Long sellerNum) {
		this.sellerNum = sellerNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getBusinessNum() {
		return businessNum;
	}

	public void setBusinessNum(String businessNum) {
		this.businessNum = businessNum;
	}

	public Integer getIsAlarm() {
		return isAlarm;
	}

	public void setIsAlarm(Integer isAlarm) {
		this.isAlarm = isAlarm;
	}

	public Long getTotalSalesAmount() {
		return totalSalesAmount;
	}

	public void setTotalSalesAmount(Long totalSalesAmount) {
		this.totalSalesAmount = totalSalesAmount;
	}

	public Store() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getAmount() {
		NumberFormat formatter = NumberFormat.getInstance();
		String formatted = formatter.format(totalSalesAmount); // 1000000 → "1,000,000"
		return formatted;
	}


}
