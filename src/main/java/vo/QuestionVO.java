package vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class QuestionVO {
	//prod question 
	Long qaNum;
	Long productNum;
	String title;
	String content;
	LocalDate createdAt;
	
	//product
	String productName;
	String imgUrl;
	
	//seller detail
	Long sellerNum;
	String storeName;
	
	//pack Question
	Long packageNum;
	String packageName;
	
	Integer answerCount;
	
	String userId;

	
	public Long getQaNum() {
		return qaNum;
	}
	public void setQaNum(Long qaNum) {
		this.qaNum = qaNum;
	}
	public Long getProductNum() {
		return productNum;
	}
	public void setProductNum(Long productNum) {
		this.productNum = productNum;
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
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
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
	public Long getSellerNum() {
		return sellerNum;
	}
	public void setSellerNum(Long sellerNum) {
		this.sellerNum = sellerNum;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Integer getAnswerCount() {
		return answerCount;
	}
	public void setAnswerCount(Integer answerCount) {
		this.answerCount = answerCount;
	}
	
}
