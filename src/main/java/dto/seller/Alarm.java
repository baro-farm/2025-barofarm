package dto.seller;

import java.time.LocalDateTime;

public class Alarm {
	private Long alarmNum;
	private Long reNum;
	private Long seNum;
	private String content1;
	private String content2;
	private String type;
	private Long targetNum;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private boolean isChecked;
	
	private String userName;
	public Alarm() {
	}
	
	public Alarm(Long alarmNum, Long reNum, Long seNum, String content1, String content2, String type, Long targetNum,
			LocalDateTime createdAt, LocalDateTime updatedAt, boolean isChecked) {
		this.alarmNum = alarmNum;
		this.reNum = reNum;
		this.seNum = seNum;
		this.content1 = content1;
		this.content2 = content2;
		this.type = type;
		this.targetNum = targetNum;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.isChecked = isChecked;
	}

	public Alarm(Long reNum, Long seNum, String content1, String content2, String type) {
		this.reNum = reNum;
		this.seNum = seNum;
		this.content1 = content1;
		this.content2 = content2;
		this.type = type;
	}
	
	public Alarm(Long reNum, Long seNum, String content1, String content2, String type, Long targetNum) {
		super();
		this.reNum = reNum;
		this.seNum = seNum;
		this.content1 = content1;
		this.content2 = content2;
		this.type = type;
		this.targetNum = targetNum;
	}

	public Long getAlarmNum() {
		return alarmNum;
	}
	public void setAlarmNum(Long alarmNum) {
		this.alarmNum = alarmNum;
	}
	public Long getReNum() {
		return reNum;
	}
	public void setReNum(Long reNum) {
		this.reNum = reNum;
	}
	public Long getSeNum() {
		return seNum;
	}
	public void setSeNum(Long seNum) {
		this.seNum = seNum;
	}
	public String getContent1() {
		return content1;
	}
	public void setContent1(String content1) {
		this.content1 = content1;
	}
	public String getContent2() {
		return content2;
	}
	public void setContent2(String content2) {
		this.content2 = content2;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getTargetNum() {
		return targetNum;
	}
	public void setTargetNum(Long targetNum) {
		this.targetNum = targetNum;
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
