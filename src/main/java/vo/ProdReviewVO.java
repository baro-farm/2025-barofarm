package vo;

import java.time.LocalDate;

public class ProdReviewVO {
	
	Long productNum; // 상품 번호
	Long pdOrderNum; // 주문 번호
	Long userNum; // 회원 번호
	String productName; // 상품 이름
	String storeName; // 스토어 이름
	String imgUrl;
	LocalDate orderdAt; // 구매일자
	LocalDate deadline; // 작성 기한 (구매일자 + 30일)
	
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

}
