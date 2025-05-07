package dto.buyer;

import java.time.LocalDateTime;

public class Payment {
	Long payNum;
	Long pdOrderNum;
	Long pkOrderNum;
	Integer price;
	Integer transactionId; // pg 거래번호
	String pay; // 결제수단
	LocalDateTime paidAt;
	String impUid;
	String merchantUid;
	
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(Long pdOrderNum, Long pkOrderNum, Integer price, Integer transactionId, String pay,
			LocalDateTime paidAt, String impUid, String merchantUid) {
		super();
		this.pdOrderNum = pdOrderNum;
		this.pkOrderNum = pkOrderNum;
		this.price = price;
		this.transactionId = transactionId;
		this.pay = pay;
		this.paidAt = paidAt;
		this.impUid = impUid;
		this.merchantUid = merchantUid;
	}

	public Long getPayNum() {
		return payNum;
	}

	public void setPayNum(Long payNum) {
		this.payNum = payNum;
	}

	public Long getPdOrderNum() {
		return pdOrderNum;
	}

	public void setPdOrderNum(Long pdOrderNum) {
		this.pdOrderNum = pdOrderNum;
	}

	public Long getPkOrderNum() {
		return pkOrderNum;
	}

	public void setPkOrderNum(Long pkOrderNum) {
		this.pkOrderNum = pkOrderNum;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public LocalDateTime getPaidAt() {
		return paidAt;
	}

	public void setPaidAt(LocalDateTime paidAt) {
		this.paidAt = paidAt;
	}

	public String getImpUid() {
		return impUid;
	}

	public void setImpUid(String impUid) {
		this.impUid = impUid;
	}

	public String getMerchantUid() {
		return merchantUid;
	}

	public void setMerchantUid(String merchantUid) {
		this.merchantUid = merchantUid;
	}
	
}
