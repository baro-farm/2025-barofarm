package dto.buyer;

import java.time.LocalDateTime;

public class Matching {
	Long matchingNum;
	Long kockNum;
	Long kcNum;
	Long buyerNum;
	Long sellerNum;
	LocalDateTime createdAt;
	public Matching() {
	}
	public Matching(Long matchingNum, Long kockNum, Long kcNum, Long buyerNum, Long sellerNum,
			LocalDateTime createdAt) {
		this.matchingNum = matchingNum;
		this.kockNum = kockNum;
		this.kcNum = kcNum;
		this.buyerNum = buyerNum;
		this.sellerNum = sellerNum;
		this.createdAt = createdAt;
	}
	public Matching(Long kockNum, Long kcNum, Long buyerNum, Long sellerNum) {
		this.kockNum = kockNum;
		this.kcNum = kcNum;
		this.buyerNum = buyerNum;
		this.sellerNum = sellerNum;
	}
	public Long getMatchingNum() {
		return matchingNum;
	}
	public void setMatchingNum(Long matchingNum) {
		this.matchingNum = matchingNum;
	}
	public Long getKockNum() {
		return kockNum;
	}
	public void setKockNum(Long kockNum) {
		this.kockNum = kockNum;
	}
	public Long getKcNum() {
		return kcNum;
	}
	public void setKcNum(Long kcNum) {
		this.kcNum = kcNum;
	}
	public Long getBuyerNum() {
		return buyerNum;
	}
	public void setBuyerNum(Long buyerNum) {
		this.buyerNum = buyerNum;
	}
	public Long getSellerNum() {
		return sellerNum;
	}
	public void setSellerNum(Long sellerNum) {
		this.sellerNum = sellerNum;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
