package dto.buyer;

import java.time.LocalDateTime;

public class KockComment {
	Long kcNum;
	Long kockNum;
	Long userNum;
	LocalDateTime createdAt;
	String content;
	
	String userName;
	String storeName;
	public KockComment() {
	}
	public KockComment(Long kcNum, Long kockNum, Long userNum, LocalDateTime createdAt, String content,
			String userName, String storeName) {
		this.kcNum = kcNum;
		this.kockNum = kockNum;
		this.userNum = userNum;
		this.createdAt = createdAt;
		this.content = content;
		this.userName = userName;
		this.storeName = storeName;
	}
	public Long getKcNum() {
		return kcNum;
	}
	public void setKcNum(Long kcNum) {
		this.kcNum = kcNum;
	}
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
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
