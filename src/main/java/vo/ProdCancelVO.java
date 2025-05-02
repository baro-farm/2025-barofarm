package vo;

import java.time.LocalDate;

public class ProdCancelVO {
	Long pdCancelNum;
	Long orderItem;
	String cancelStatus;
	String cancelReason;
	String cancelReasonDetail;
	LocalDate cancelRequestedAt;
	
	
	Long pdOrderNum;
	Long productNum;

	String productName;
	
	String optionName;
	Integer amount;
	Integer price;
	
	String userId;
	String rname;
	String rphone;
	String address;
	
	
	public Long getPdCancelNum() {
		return pdCancelNum;
	}
	public void setPdCancelNum(Long pdCancelNum) {
		this.pdCancelNum = pdCancelNum;
	}
	public Long getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(Long orderItem) {
		this.orderItem = orderItem;
	}
	public String getCancelStatus() {
		return cancelStatus;
	}
	public void setCancelStatus(String cancelStatus) {
		this.cancelStatus = cancelStatus;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public String getCancelReasonDetail() {
		return cancelReasonDetail;
	}
	public void setCancelReasonDetail(String cancelReasonDetail) {
		this.cancelReasonDetail = cancelReasonDetail;
	}
	public LocalDate getCancelRequestedAt() {
		return cancelRequestedAt;
	}
	public void setCancelRequestedAt(LocalDate cancelRequestedAt) {
		this.cancelRequestedAt = cancelRequestedAt;
	}
	public Long getPdOrderNum() {
		return pdOrderNum;
	}
	public void setPdOrderNum(Long pdOrderNum) {
		this.pdOrderNum = pdOrderNum;
	}
	public Long getProductNum() {
		return productNum;
	}
	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getRphone() {
		return rphone;
	}
	public void setRphone(String rphone) {
		this.rphone = rphone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
