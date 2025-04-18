package dto.seller;

import java.time.LocalDateTime;

public class UsePoint {
	Long upNum;
	Long userNum;
	Integer usedPoint;
	String type;
	Integer currPoint;
	String payInfo;
	LocalDateTime createdAt;
	public UsePoint() {
	}
	public UsePoint(Long upNum, Long userNum, Integer usedPoint, String type, Integer currPoint, String payInfo,
			LocalDateTime createdAt) {
		this.upNum = upNum;
		this.userNum = userNum;
		this.usedPoint = usedPoint;
		this.type = type;
		this.currPoint = currPoint;
		this.payInfo = payInfo;
		this.createdAt = createdAt;
	}
	public UsePoint(Long userNum, Integer usedPoint, String type, Integer currPoint) {
		this.userNum = userNum;
		this.usedPoint = usedPoint;
		this.type = type;
		this.currPoint = currPoint;
	}
	public UsePoint(Long userNum, Integer usedPoint, String type, Integer currPoint, String payInfo) {
		this.userNum = userNum;
		this.usedPoint = usedPoint;
		this.type = type;
		this.currPoint = currPoint;
		this.payInfo = payInfo;
	}
	public Long getUpNum() {
		return upNum;
	}
	public void setUpNum(Long upNum) {
		this.upNum = upNum;
	}
	public Long getUserNum() {
		return userNum;
	}
	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}
	public Integer getUsedPoint() {
		return usedPoint;
	}
	public void setUsedPoint(Integer usedPoint) {
		this.usedPoint = usedPoint;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getCurrPoint() {
		return currPoint;
	}
	public void setCurrPoint(Integer currPoint) {
		this.currPoint = currPoint;
	}
	public String getPayInfo() {
		return payInfo;
	}
	public void setPayInfo(String payInfo) {
		this.payInfo = payInfo;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
