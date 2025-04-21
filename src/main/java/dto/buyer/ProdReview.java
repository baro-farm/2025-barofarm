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
	LocalDate createdAt;
	LocalDate updatedAt;
	boolean pdStatus;
	String pdComment;
	boolean pdCommentStatus;
	
	String storeName; // 스토어 이름
	String productName; // 상품 이름

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

	public ProdReview(Long userNum, Long productNum, Integer pdRating, String pdReviewContent, String pdReviewImgUrl,
			String storeName,String productName) {
		super();
		this.userNum = userNum;
		this.productNum = productNum;
		this.pdRating = pdRating;
		this.pdReviewContent = pdReviewContent;
		this.pdReviewImgUrl = pdReviewImgUrl;
		this.storeName = storeName;
		this.productName = productName;
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

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
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

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "ProdReview [reviewNum=" + reviewNum + ", userNum=" + userNum + ", productNum=" + productNum
				+ ", pdRating=" + pdRating + ", pdReviewContent=" + pdReviewContent + ", pdReviewImgUrl="
				+ pdReviewImgUrl + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", pdStatus=" + pdStatus
				+ ", pdComment=" + pdComment + ", pdCommentStatus=" + pdCommentStatus + ", storeName=" + storeName
				+ ", productName=" + productName + "]";
	}

	
	
}
