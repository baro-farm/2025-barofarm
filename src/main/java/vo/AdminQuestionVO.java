package vo;

import java.time.LocalDate;

public class AdminQuestionVO {
	Long questionNum;
	String userId;
	String title;
	String content;
	LocalDate createdAt;
	String type;
	Boolean answerStatus;
	String aContent;
	LocalDate aUpdatedAt;
	
	
	public AdminQuestionVO(Long questionNum, String userId, String title, String content, LocalDate createdAt,
			String type, Boolean answerStatus) {
		super();
		this.questionNum = questionNum;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
		this.type = type;
		this.answerStatus = answerStatus;
	}
	
	
	
	
	public Long getQuestionNum() {
		return questionNum;
	}
	public void setQuestionNum(Long questionNum) {
		this.questionNum = questionNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean getAnswerStatus() {
		return answerStatus;
	}
	public void setAnswerStatus(Boolean answerStatus) {
		this.answerStatus = answerStatus;
	}
	public String getaContent() {
		return aContent;
	}
	public void setaContent(String aContent) {
		this.aContent = aContent;
	}
	public LocalDate getaUpdatedAt() {
		return aUpdatedAt;
	}
	public void setaUpdatedAt(LocalDate aUpdatedAt) {
		this.aUpdatedAt = aUpdatedAt;
	}
	public AdminQuestionVO() {
		super();
	}
}
