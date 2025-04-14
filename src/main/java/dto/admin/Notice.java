package dto.admin;

import java.sql.Date;

public class Notice {
	Integer noticeNum;
	Integer userNum;
	String title;
	String content;
	Date createdAt;
	Date updatedAt;
	Integer fixed;

	public Integer getNoticeNum() {
		return noticeNum;
	}

	public void setNoticeNum(Integer noticeNum) {
		this.noticeNum = noticeNum;
	}

	public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getFixed() {
		return fixed;
	}

	public void setFixed(Integer fixed) {
		this.fixed = fixed;
	}

	public Notice() {
		super();
	}

	public Notice(Integer userNum, String title, String content, Integer fixed) {
		super();
		this.userNum = userNum;
		this.title = title;
		this.content = content;
		this.fixed = fixed;
	}

	public String getPreviewContent() {
		String plain = content.replaceAll("<[^>]*>", ""); // HTML 태그 제거
		return plain.length() > 100 ? plain.substring(0, 100) : plain;
	}
}
