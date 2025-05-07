package dto.seller;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Advertisement {
	Long adsNum;
	String title;
	String content;
	String productName;
	String imgUrl;
	String productUrl;
	LocalDate startDate;
	LocalDate endDate;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	Long userNum;
	String status;
	
	String storeName;
	public Advertisement() {
	}
	public Advertisement(Long adsNum, String title, String content, String productName, String imgUrl,
			String productUrl, LocalDate startDate, LocalDate endDate, LocalDateTime createdAt, LocalDateTime updatedAt,
			Long userNum, String status) {
		this.adsNum = adsNum;
		this.title = title;
		this.content = content;
		this.productName = productName;
		this.imgUrl = imgUrl;
		this.productUrl = productUrl;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.userNum = userNum;
		this.status = status;
	}
	public Advertisement(String title, String content, String productName, String imgUrl, String productUrl,
			Long userNum) {
		this.title = title;
		this.content = content;
		this.productName = productName;
		this.imgUrl = imgUrl;
		this.productUrl = productUrl;
		this.userNum = userNum;
	}
	public Long getAdsNum() {
		return adsNum;
	}
	public void setAdsNum(Long adsNum) {
		this.adsNum = adsNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getProductUrl() {
		return productUrl;
	}
	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Long getUserNum() {
		return userNum;
	}
	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
}
