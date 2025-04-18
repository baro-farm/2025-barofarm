package dto.buyer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProdReview {
	
	Long reviewNum;
	Long userNum;
	Long productNum;
	Integer pdRating;
	String pdReviewContent;
	String pdReviewImgUrl;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	boolean pdStatus;
	String pdComment;
	boolean pdCommentStatus;
	
	public ProdReview() {
		super();
	}

	public ProdReview(Long userNum, Long productNum, Integer pdRating, String pdReviewContent, String pdReviewImgUrl) {
		super();
		this.userNum = userNum;
		this.productNum = productNum;
		this.pdRating = pdRating;
		this.pdReviewContent = pdReviewContent;
		this.pdReviewImgUrl = pdReviewImgUrl;
	}

	public ProdReview(Long reviewNum, String pdComment) {
		super();
		this.reviewNum = reviewNum;
		this.pdComment = pdComment;
	}

	public Long getReviewNum() {
		return reviewNum;
	}

	public void setReviewNum(Long reviewNum) {
		this.reviewNum = reviewNum;
	}

	public Long getUserNum() {
		return userNum;
	}

	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}

	public Long getProductNum() {
		return productNum;
	}

	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}

	public Integer getPdRating() {
		return pdRating;
	}

	public void setPdRating(Integer pdRating) {
		this.pdRating = pdRating;
	}

	public String getPdReviewContent() {
		return pdReviewContent;
	}

	public void setPdReviewContent(String pdReviewContent) {
		this.pdReviewContent = pdReviewContent;
	}

	public String getPdReviewImgUrl() {
		return pdReviewImgUrl;
	}

	public void setPdReviewImgUrl(String pdReviewImgUrl) {
		this.pdReviewImgUrl = pdReviewImgUrl;
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

	public boolean isPdStatus() {
		return pdStatus;
	}

	public void setPdStatus(boolean pdStatus) {
		this.pdStatus = pdStatus;
	}

	public String getPdComment() {
		return pdComment;
	}

	public void setPdComment(String pdComment) {
		this.pdComment = pdComment;
	}

	public boolean isPdCommentStatus() {
		return pdCommentStatus;
	}

	public void setPdCommentStatus(boolean pdCommentStatus) {
		this.pdCommentStatus = pdCommentStatus;
	}
	
	
}
