package dto.admin;

import java.sql.Date;
import java.time.LocalDateTime;

public class CustomerService {
	Long questionNum;
	String userId;
	String title;
	String questionContent;
	LocalDateTime questionCreatedAt;
	LocalDateTime questionUpdatedAt;
	String type;
	String answerContent;
	LocalDateTime answerCreatedAt;
	LocalDateTime answerUpdatedAt;
	Long answerStatus;

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

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public LocalDateTime getQuestionCreatedAt() {
		return questionCreatedAt;
	}

	public void setQuestionCreatedAt(LocalDateTime questionCreatedAt) {
		this.questionCreatedAt = questionCreatedAt;
	}

	public LocalDateTime getQuestionUpdatedAt() {
		return questionUpdatedAt;
	}

	public void setQuestionUpdatedAt(LocalDateTime questionUpdatedAt) {
		this.questionUpdatedAt = questionUpdatedAt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public Long getAnswerStatus() {
		return answerStatus;
	}

	public void setAnswerStatus(Long answerStatus) {
		this.answerStatus = answerStatus;
	}

	public LocalDateTime getAnswerCreatedAt() {
		return answerCreatedAt;
	}

	public void setAnswerCreatedAt(LocalDateTime answerCreatedAt) {
		this.answerCreatedAt = answerCreatedAt;
	}

	public LocalDateTime getAnswerUpdatedAt() {
		return answerUpdatedAt;
	}

	public void setAnswerUpdatedAt(LocalDateTime answerUpdatedAt) {
		this.answerUpdatedAt = answerUpdatedAt;
	}

	public CustomerService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPreviewContent() {
	    if (questionContent == null) return "";
	    String plain = questionContent.replaceAll("<[^>]*>", "");
	    return plain.length() > 100 ? plain.substring(0, 100) + "..." : plain;
	}
}
