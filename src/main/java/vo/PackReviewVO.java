package vo;

import java.time.LocalDate;

public class PackReviewVO {
	Long pkReviewNum;

	Long packageNum; // 상품 번호
	Long pkOrderNum; // 주문 번호
	Long userNum; // 회원 번호
	
	String packageName; // 상품 이름
	String packageUnit; //1인 2인 3인 ...
	
	String storeName; // 스토어 이름
	String pkReviewImgUrl;
	
	LocalDate orderdAt; // 구매일자
	LocalDate deadline; // 작성 기한 (구매일자 + 30일)
	LocalDate createdAt;
	
	String userId;
	Integer pkRating;
	String pkReviewContent;
	
	
	Boolean pkCommentStatus;
	String pkComment;
	public Long getPkReviewNum() {
		return pkReviewNum;
	}
	public void setPkReviewNum(Long pkReviewNum) {
		this.pkReviewNum = pkReviewNum;
	}
	public Long getPackageNum() {
		return packageNum;
	}
	public void setPackageNum(Long packageNum) {
		this.packageNum = packageNum;
	}
	public Long getPkOrderNum() {
		return pkOrderNum;
	}
	public void setPkOrderNum(Long pkOrderNum) {
		this.pkOrderNum = pkOrderNum;
	}
	public Long getUserNum() {
		return userNum;
	}
	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPackageUnit() {
		return packageUnit;
	}
	public void setPackageUnit(String packageUnit) {
		this.packageUnit = packageUnit;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getPkReviewImgUrl() {
		return pkReviewImgUrl;
	}
	public void setPkReviewImgUrl(String pkReviewImgUrl) {
		this.pkReviewImgUrl = pkReviewImgUrl;
	}
	public LocalDate getOrderdAt() {
		return orderdAt;
	}
	public void setOrderdAt(LocalDate orderdAt) {
		this.orderdAt = orderdAt;
	}
	public LocalDate getDeadline() {
		return deadline;
	}
	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getPkRating() {
		return pkRating;
	}
	public void setPkRating(Integer pkRating) {
		this.pkRating = pkRating;
	}
	public String getPkReviewContent() {
		return pkReviewContent;
	}
	public void setPkReviewContent(String pkReviewContent) {
		this.pkReviewContent = pkReviewContent;
	}
	public Boolean getPkCommentStatus() {
		return pkCommentStatus;
	}
	public void setPkCommentStatus(Boolean pkCommentStatus) {
		this.pkCommentStatus = pkCommentStatus;
	}
	public String getPkComment() {
		return pkComment;
	}
	public void setPkComment(String pkComment) {
		this.pkComment = pkComment;
	}
	
}
