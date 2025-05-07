package dto.seller;

import java.time.LocalDateTime;
import java.util.Date;

public class UsePoint {
	private Long upNum;
	private Long userNum;
	private Integer usedPoint;
	private String type;
	private Integer currPoint;
	private String payInfo;
	private LocalDateTime createdAt;
	private String merchantUid;
	private String impUid;
	
	Date createdAtDate;
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
	public UsePoint(Long upNum, Long userNum, Integer usedPoint, String type, Integer currPoint, String payInfo,
			LocalDateTime createdAt, String merchantUid, String impUid, Date createdAtDate) {
		this.upNum = upNum;
		this.userNum = userNum;
		this.usedPoint = usedPoint;
		this.type = type;
		this.currPoint = currPoint;
		this.payInfo = payInfo;
		this.createdAt = createdAt;
		this.merchantUid = merchantUid;
		this.impUid = impUid;
		this.createdAtDate = createdAtDate;
	}
	public UsePoint(Long userNum, Integer usedPoint, String type, Integer currPoint, String payInfo, String merchantUid,
			String impUid) {
		this.userNum = userNum;
		this.usedPoint = usedPoint;
		this.type = type;
		this.currPoint = currPoint;
		this.payInfo = payInfo;
		this.merchantUid = merchantUid;
		this.impUid = impUid;
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
	public String getMerchantUid() {
		return merchantUid;
	}
	public void setMerchantUid(String merchantUid) {
		this.merchantUid = merchantUid;
	}
	public String getImpUid() {
		return impUid;
	}
	public void setImpUid(String impUid) {
		this.impUid = impUid;
	}
	public Date getCreatedAtDate() {
		return createdAtDate;
	}
	public void setCreatedAtDate(Date createdAtDate) {
		this.createdAtDate = createdAtDate;
	}
}
