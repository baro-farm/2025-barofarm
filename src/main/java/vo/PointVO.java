package vo;

import java.time.LocalDate;

public class PointVO {
	String userId;
	String storeName;
	String type;
	String userName;
	String pType;
	Integer usedPoint;
	Integer currPoint;
	LocalDate createdAt;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getpType() {
		return pType;
	}
	public void setpType(String pType) {
		this.pType = pType;
	}
	public Integer getUsedPoint() {
		return usedPoint;
	}
	public void setUsedPoint(Integer usedPoint) {
		this.usedPoint = usedPoint;
	}
	public Integer getCurrPoint() {
		return currPoint;
	}
	public void setCurrPoint(Integer currPoint) {
		this.currPoint = currPoint;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	
}
