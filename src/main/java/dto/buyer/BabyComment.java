package dto.buyer;

import java.time.LocalDateTime;

public class BabyComment {
	Long reNum;
	Long kcNum;
	Long userNum;
	String content;
	LocalDateTime createdAt;
	
	String userName;
	String storeName;
	public BabyComment() {
	}
	public BabyComment(Long reNum, Long kcNum, Long userNum, String content, LocalDateTime createdAt, String userName,
			String storeName) {
		super();
		this.reNum = reNum;
		this.kcNum = kcNum;
		this.userNum = userNum;
		this.content = content;
		this.createdAt = createdAt;
		this.userName = userName;
		this.storeName = storeName;
	}
	public BabyComment(Long kcNum, Long userNum, String content) {
		super();
		this.kcNum = kcNum;
		this.userNum = userNum;
		this.content = content;
	}
	public Long getReNum() {
		return reNum;
	}
	public void setReNum(Long reNum) {
		this.reNum = reNum;
	}
	public Long getKcNum() {
		return kcNum;
	}
	public void setKcNum(Long kcNum) {
		this.kcNum = kcNum;
	}
	public Long getUserNum() {
		return userNum;
	}
	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
}
