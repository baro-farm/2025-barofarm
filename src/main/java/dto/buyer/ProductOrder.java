package dto.buyer;

import java.time.LocalDateTime;

public class ProductOrder {
	Long pdOrderNum;
	Long userNum;
	Integer pdTotalPrice;
	LocalDateTime orderDate;
	String address;
	String deleveryStatus;
	String orderStatus;
	LocalDateTime orderdAt;
	String rName;
	String rPhone;
	
	public ProductOrder() {
		super();
	}

	public ProductOrder(Long userNum, Integer pdTotalPrice, String address, String deleveryStatus, String orderStatus, String rName, String rPhone) {
		super();
		this.userNum = userNum;
		this.pdTotalPrice = pdTotalPrice;
		this.address = address;
		this.deleveryStatus = deleveryStatus;
		this.orderStatus = orderStatus;
		this.rName = rName;
		this.rPhone = rPhone;
	}

	public Long getPdOrderNum() {
		return pdOrderNum;
	}

	public void setPdOrderNum(Long pdOrderNum) {
		this.pdOrderNum = pdOrderNum;
	}

	public Long getUserNum() {
		return userNum;
	}

	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}

	public Integer getPdTotalPrice() {
		return pdTotalPrice;
	}

	public void setPdTotalPrice(Integer pdTotalPrice) {
		this.pdTotalPrice = pdTotalPrice;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDeleveryStatus() {
		return deleveryStatus;
	}

	public void setDeleveryStatus(String deleveryStatus) {
		this.deleveryStatus = deleveryStatus;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public LocalDateTime getOrderdAt() {
		return orderdAt;
	}

	public void setOrderdAt(LocalDateTime orderdAt) {
		this.orderdAt = orderdAt;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public String getrPhone() {
		return rPhone;
	}

	public void setrPhone(String rPhone) {
		this.rPhone = rPhone;
	}

}
