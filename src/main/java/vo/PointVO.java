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
	
	private String month;         // yyyy-MM
    private Integer chargePoint;      // 충전포인트 합계
    private Integer usePoint;         // 사용포인트 합계
    
    private Integer currentMonth;
    private Integer lastMonth;
    private Integer increaseLastMonth;
    private Integer increasePercentage;

	
	public Integer getCurrentMonth() {
		return currentMonth;
	}
	public void setCurrentMonth(Integer currentMonth) {
		this.currentMonth = currentMonth;
	}
	public Integer getLastMonth() {
		return lastMonth;
	}
	public void setLastMonth(Integer lastMonth) {
		this.lastMonth = lastMonth;
	}
	public Integer getIncreaseLastMonth() {
		return increaseLastMonth;
	}
	public void setIncreaseLastMonth(Integer increaseLastMonth) {
		this.increaseLastMonth = increaseLastMonth;
	}
	public Integer getIncreasePercentage() {
		return increasePercentage;
	}
	public void setIncreasePercentage(Integer increasePercentage) {
		this.increasePercentage = increasePercentage;
	}
	public void setChargePoint(Integer chargePoint) {
		this.chargePoint = chargePoint;
	}
	public void setUsePoint(Integer usePoint) {
		this.usePoint = usePoint;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getChargePoint() {
		return chargePoint;
	}
	public void setChargePoint(int chargePoint) {
		this.chargePoint = chargePoint;
	}
	public int getUsePoint() {
		return usePoint;
	}
	public void setUsePoint(int usePoint) {
		this.usePoint = usePoint;
	}
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
