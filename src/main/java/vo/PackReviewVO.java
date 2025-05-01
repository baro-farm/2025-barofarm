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
	
	
}
