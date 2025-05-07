package dto.buyer;

import java.time.LocalDateTime;

public class ProdQuestion {
	Long qaNum;
	Long userNum;
	Long productNum;
	String title;
	String content;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	
	public ProdQuestion() {
		super();
	}

	public ProdQuestion(Long qaNum, Long userNum, Long productNum, String title, String content, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.qaNum = qaNum;
		this.userNum = userNum;
		this.productNum = productNum;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public ProdQuestion(Long qaNum, Long userNum, Long productNum, String title, String content) {
		super();
		this.qaNum = qaNum;
		this.userNum = userNum;
		this.productNum = productNum;
		this.title = title;
		this.content = content;
	}

	public Long getQaNum() {
		return qaNum;
	}

	public void setQaNum(Long quNum) {
		this.qaNum = quNum;
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

	@Override
	public String toString() {
		return "Question [quNum=" + qaNum + ", userNum=" + userNum + ", productNum=" + productNum + ", title=" + title
				+ ", content=" + content + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
}
