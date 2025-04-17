package dto.seller;

import java.time.LocalDateTime;

public class Point {
	Long pointNum;
	Long uerNum;
	Integer Point;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	public Point() {
	}
	public Point(Long pointNum, Long uerNum, Integer point, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.pointNum = pointNum;
		this.uerNum = uerNum;
		Point = point;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public Point(Long uerNum, Integer point) {
		this.uerNum = uerNum;
		Point = point;
	}
	public Long getPointNum() {
		return pointNum;
	}
	public void setPointNum(Long pointNum) {
		this.pointNum = pointNum;
	}
	public Long getUerNum() {
		return uerNum;
	}
	public void setUerNum(Long uerNum) {
		this.uerNum = uerNum;
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
