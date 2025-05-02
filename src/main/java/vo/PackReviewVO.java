package vo;

import java.time.LocalDate;

public class PackReviewVO {

	private Long pkReviewNum;
	private Long userNum;
	private String userId;
	private Long packageNum;
	private Integer pkRating;
	private String pkReviewContent;
	private String pkReviewImgUrl;
	private LocalDate createdAt;
	private String pkComment;
	private Boolean pkCommentStatus;
	
	private Long pkOrderNum; // 주문 번호
	
	private String packageName; // 상품 이름
	private String packageUnit; //1인 2인 3인 ...
	
	private String storeName; // 스토어 이름
	
	private LocalDate orderdAt; // 구매일자
	private LocalDate deadline; // 작성 기한 (구매일자 + 30일)
	public Long getPkReviewNum() {
		return pkReviewNum;
	}
	public void setPkReviewNum(Long pkReviewNum) {
		this.pkReviewNum = pkReviewNum;
	}
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
	public Long getPackageNum() {
		return packageNum;
	}
	public void setPackageNum(Long packageNum) {
		this.packageNum = packageNum;
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
	public String getPkReviewImgUrl() {
		return pkReviewImgUrl;
	}
	public void setPkReviewImgUrl(String pkReviewImgUrl) {
		this.pkReviewImgUrl = pkReviewImgUrl;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	public String getPkComment() {
		return pkComment;
	}
	public void setPkComment(String pkComment) {
		this.pkComment = pkComment;
	}
	public Boolean getPkCommentStatus() {
		return pkCommentStatus;
	}
	public void setPkCommentStatus(Boolean pkCommentStatus) {
		this.pkCommentStatus = pkCommentStatus;
	}
	public Long getPkOrderNum() {
		return pkOrderNum;
	}
	public void setPkOrderNum(Long pkOrderNum) {
		this.pkOrderNum = pkOrderNum;
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
	
	

	
}
