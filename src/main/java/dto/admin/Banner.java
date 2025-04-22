package dto.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Banner {
	Long bannerNum;
	Long adsNum;
	String title;
	String imgUrl;
	String targetUrl;
	LocalDateTime createdAt;
	LocalDateTime endDate;
	boolean isPosted;
	Long userNum;
	public Banner() {
	}
	public Banner(Long bannerNum, Long adsNum, String title, String imgUrl, String targetUrl, LocalDateTime createdAt,
			LocalDateTime endDate, boolean isPosted, Long userNum) {
		this.bannerNum = bannerNum;
		this.adsNum = adsNum;
		this.title = title;
		this.imgUrl = imgUrl;
		this.targetUrl = targetUrl;
		this.createdAt = createdAt;
		this.endDate = endDate;
		this.isPosted = isPosted;
		this.userNum = userNum;
	}
	public Long getBannerNum() {
		return bannerNum;
	}
	public void setBannerNum(Long bannerNum) {
		this.bannerNum = bannerNum;
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
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getTargetUrl() {
		return targetUrl;
	}
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public boolean isPosted() {
		return isPosted;
	}
	public void setPosted(boolean isPosted) {
		this.isPosted = isPosted;
	}
	public Long getUserNum() {
		return userNum;
	}
	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}
}
