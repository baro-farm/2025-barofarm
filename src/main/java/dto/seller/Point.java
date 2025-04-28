package dto.seller;

import java.time.LocalDateTime;

public class Point {
	Long pointNum;
	Long userNum;
	Integer Point;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	public Point() {
	}
	public Point(Long pointNum, Long userNum, Integer point, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.pointNum = pointNum;
		this.userNum = userNum;
		Point = point;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public Point(Long userNum, Integer point) {
		this.userNum = userNum;
		Point = point;
	}
	public Long getPointNum() {
		return pointNum;
	}
	public void setPointNum(Long pointNum) {
		this.pointNum = pointNum;
	}
	public Long getUserNum() {
		return userNum;
	}
	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}
	public Integer getPoint() {
		return Point;
	}
	public void setPoint(Integer point) {
		Point = point;
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
