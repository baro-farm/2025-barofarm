package dto.admin;

import java.time.LocalDate;

public class AdminQuestion {
	Long questionNum;
	Long userNum;
	String title;
	String content;
	LocalDate createdAt;
	LocalDate updatedAt;
	String type;
	
	public AdminQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminQuestion(Long userNum,String title, String content, String type) {
		super();
		this.userNum = userNum;
		this.title = title;
		this.content = content;
		this.type = type;
	}

	public Long getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(Long questionNum) {
		this.questionNum = questionNum;
	}

	public Long getUserNum() {
		return userNum;
	}

	public void setUserNum(Long userNum) {
		this.userNum = userNum;
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

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
