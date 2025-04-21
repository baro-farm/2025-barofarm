package vo;

import java.time.LocalDateTime;

public class KockCommentVO {
	Long commentNum;
	Long kockNum;
	String title;
	String content;
	LocalDateTime createdAt;
	String commentType;
	public Long getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(Long commentNum) {
		this.commentNum = commentNum;
	}
	public Long getKockNum() {
		return kockNum;
	}
	public void setKockNum(Long kockNum) {
		this.kockNum = kockNum;
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
	public String getCommentType() {
		return commentType;
	}
	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}
	@Override
	public String toString() {
		return "KockFarmCommentVO [commentNum=" + commentNum + ", kockNum=" + kockNum + ", title=" + title
				+ ", content=" + content + ", createdAt=" + createdAt + ", commentType=" + commentType + "]";
	}
	
	
}
