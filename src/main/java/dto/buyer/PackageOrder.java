package dto.buyer;

import java.time.LocalDateTime;

public class PackageOrder {
	Long pkOrderNum;
	Long packageNum;
	Long userNum;
	Long subNum;
	Integer pkTotalPrice;
	String deleveryStatus;
	LocalDateTime orderedAt;
	Integer trackingNum;
	
	
	
	public PackageOrder() {
		super();
	}



	public PackageOrder(Long packageNum, Long userNum, Long subNum, Integer pkTotalPrice) {
		super();
		this.packageNum = packageNum;
		this.userNum = userNum;
		this.subNum = subNum;
		this.pkTotalPrice = pkTotalPrice;
	}



	public PackageOrder(Long packageNum, Long userNum, Long subNum, Integer pkTotalPrice, String deleveryStatus) {
		super();
		this.packageNum = packageNum;
		this.userNum = userNum;
		this.subNum = subNum;
		this.pkTotalPrice = pkTotalPrice;
		this.deleveryStatus = deleveryStatus;
	}



	public PackageOrder(Long pkOrderNum, Long packageNum, Long userNum, Long subNum, Integer pkTotalPrice,
			String deleveryStatus, LocalDateTime orderedAt, Integer trackingNum) {
		super();
		this.pkOrderNum = pkOrderNum;
		this.packageNum = packageNum;
		this.userNum = userNum;
		this.subNum = subNum;
		this.pkTotalPrice = pkTotalPrice;
		this.deleveryStatus = deleveryStatus;
		this.orderedAt = orderedAt;
		this.trackingNum = trackingNum;
	}



	public Long getPkOrderNum() {
		return pkOrderNum;
	}



	public void setPkOrderNum(Long pkOrderNum) {
		this.pkOrderNum = pkOrderNum;
	}



	public Long getPackageNum() {
		return packageNum;
	}



	public void setPackageNum(Long packageNum) {
		this.packageNum = packageNum;
	}



	public Long getUserNum() {
		return userNum;
	}



	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}



	public Long getSubNum() {
		return subNum;
	}



	public void setSubNum(Long subNum) {
		this.subNum = subNum;
	}



	public Integer getPkTotalPrice() {
		return pkTotalPrice;
	}



	public void setPkTotalPrice(Integer pkTotalPrice) {
		this.pkTotalPrice = pkTotalPrice;
	}



	public String getDeleveryStatus() {
		return deleveryStatus;
	}



	public void setDeleveryStatus(String deleveryStatus) {
		this.deleveryStatus = deleveryStatus;
	}



	public LocalDateTime getOrderedAt() {
		return orderedAt;
	}



	public void setOrderedAt(LocalDateTime orderedAt) {
		this.orderedAt = orderedAt;
	}



	public Integer getTrackingNum() {
		return trackingNum;
	}



	public void setTrackingNum(Integer trackingNum) {
		this.trackingNum = trackingNum;
	}



	@Override
	public String toString() {
		return "PackageOrder [pkOrderNum=" + pkOrderNum + ", packageNum=" + packageNum + ", userNum=" + userNum
				+ ", subNum=" + subNum + ", pkTotalPrice=" + pkTotalPrice + ", deleveryStatus=" + deleveryStatus
				+ ", orderedAt=" + orderedAt + ", trackingNum=" + trackingNum + "]";
	}
	
	
}
