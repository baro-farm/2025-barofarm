package vo;

import java.time.LocalDate;

public class ProdReviewVO {
	
	Long productNum; // 상품 번호
	Long pdOrderNum; // 주문 번호
	Long userNum; // 회원 번호
	String productName; // 상품 이름
	String storeName; // 스토어 이름
	Long sellerNum;
	String imgUrl;
	LocalDate orderdAt; // 구매일자
	LocalDate deadline; // 작성 기한 (구매일자 + 30일)
	LocalDate createdAt;
	String userId;
	Integer pdRating;
	String pdContent;
	
	String optionName; //옵션이름
	Long reviewNum;
	
	Boolean pdCommentStatus;
	String pdComment;
	
	public Long getProductNum() {
		return productNum;
	}
	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}
	public Long getPdOrderNum() {
		return pdOrderNum;
	}
	public void setPdOrderNum(Long pdOrderNum) {
		this.pdOrderNum = pdOrderNum;
	}
	public Long getUserNum() {
		return userNum;
	}
	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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
	public Integer getPdRating() {
		return pdRating;
	}
	public void setPdRating(Integer pdRating) {
		this.pdRating = pdRating;
	}
	public String getPdContent() {
		return pdContent;
	}
	public void setPdContent(String pdContent) {
		this.pdContent = pdContent;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public Long getReviewNum() {
		return reviewNum;
	}
	public void setReviewNum(Long reviewNum) {
		this.reviewNum = reviewNum;
	}
	public Boolean getPdCommentStatus() {
		return pdCommentStatus;
	}
	public void setPdCommentStatus(Boolean pdCommentStatus) {
		this.pdCommentStatus = pdCommentStatus;
	}
	public String getPdComment() {
		return pdComment;
	}
	public void setPdComment(String pdComment) {
		this.pdComment = pdComment;
	}
	public Long getSellerNum() {
		return sellerNum;
	}
	public void setSellerNum(Long sellerNum) {
		this.sellerNum = sellerNum;
	}
	
	

}
