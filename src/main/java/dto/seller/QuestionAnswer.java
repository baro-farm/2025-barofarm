package dto.seller;

import java.time.LocalDateTime;

public class QuestionAnswer {
	Long paNum;
	Long qaNum;
	String content;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	
	public QuestionAnswer() {
		super();
	}

	public QuestionAnswer(Long qaNum, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.qaNum = qaNum;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public QuestionAnswer(Long paNum, Long qaNum, String content, LocalDateTime createdAt) {
		super();
		this.paNum = paNum;
		this.qaNum = qaNum;
		this.content = content;
		this.createdAt = createdAt;
	}
	
	public QuestionAnswer(Long paNum, Long qaNum, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.paNum = paNum;
		this.qaNum = qaNum;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getPaNum() {
		return paNum;
	}

	public void setPaNum(Long paNum) {
		this.paNum = paNum;
	}

	public Long getQaNum() {
		return qaNum;
	}

	public void setQaNum(Long qaNum) {
		this.qaNum = qaNum;
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

}
