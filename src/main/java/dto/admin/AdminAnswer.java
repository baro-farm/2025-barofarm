package dto.admin;

import java.time.LocalDateTime;

public class AdminAnswer {
	Long aaNum;
	Long questionNum;
	String content;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;

	public Long getAaNum() {
		return aaNum;
	}

	public void setAaNum(Long aaNum) {
		this.aaNum = aaNum;
	}

	public Long getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(Long questionNum) {
		this.questionNum = questionNum;
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

	public AdminAnswer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminAnswer(Long questionNum, String content) {
		super();
		this.questionNum = questionNum;
		this.content = content;
	}

	public AdminAnswer(Long questionNum, String content, LocalDateTime updatedAt) {
		super();
		this.questionNum = questionNum;
		this.content = content;
		this.updatedAt = updatedAt;
	}

}
