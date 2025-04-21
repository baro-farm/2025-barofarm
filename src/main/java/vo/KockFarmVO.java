package vo;

import java.time.LocalDate;

public class KockFarmVO {
	//콕팜 글
	Long kockNum;
	Long userNum;
	Long cateNum;
	String title;
	String content;
	LocalDate createdAt;
	boolean isMatched;
	
	//카테고리 테이블
	String name; //카테고리 명
	
	//콕팜 댓글 테이블
	Long kcNum;
	Integer kcCommentCount;
	
	//매칭 테이블
	Long matchingNum;
	Long sellerNum;
	
	//판매자 테이블
	String storeName;

	public Long getKockNum() {
		return kockNum;
	}

	public void setKockNum(Long kockNum) {
		this.kockNum = kockNum;
	}

	public Long getUserNum() {
		return userNum;
	}

	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}

	public Long getCateNum() {
		return cateNum;
	}

	public void setCateNum(Long cateNum) {
		this.cateNum = cateNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public boolean getIsMatched() {
		return isMatched;
	}

	public void setIsMatched(boolean isMatched) {
		this.isMatched = isMatched;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getKcNum() {
		return kcNum;
	}

	public void setKcNum(Long kcNum) {
		this.kcNum = kcNum;
	}

	public Integer getKcCommentCount() {
		return kcCommentCount;
	}

	public void setKcCommentCount(Integer kcCommentCount) {
		this.kcCommentCount = kcCommentCount;
	}

	public Long getMatchingNum() {
		return matchingNum;
	}

	public void setMatchingNum(Long matchingNum) {
		this.matchingNum = matchingNum;
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

	public String getContent() {
		return content;
	}

	public void setContent(String contetn) {
		this.content = contetn;
	}

	@Override
	public String toString() {
		return "KockFarmVO [kockNum=" + kockNum + ", userNum=" + userNum + ", cateNum=" + cateNum + ", title=" + title
				+ ", content=" + content + ", createdAt=" + createdAt + ", isMatched=" + isMatched + ", name=" + name
				+ ", kcNum=" + kcNum + ", kcCommentCount=" + kcCommentCount + ", matchingNum=" + matchingNum
				+ ", sellerNum=" + sellerNum + ", storeName=" + storeName + "]";
	}

	
	 
}
