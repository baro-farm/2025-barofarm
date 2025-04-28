package vo;

import java.time.LocalDate;

public class ProdOrderVO {
	//product order
	Long pdOrderNum;
	Long userNum;
	Integer pdTotalPrice;
	LocalDate orderDate;
	String deleveryStatus;
	String orderStatus;
	Integer trackingNum;
	
	//product order item
	Long orderItem;
	Long productNum;
	Integer amount;
	Integer totalPrice;
	
	//product
	Long sellerNum;
	String productName;
	String imgUrl;
	
	//product option
	String option; //옵션 이름
	Integer optionPrice;
	
	//seller detail
	String storeName;
	
	//user
	String userName;
	String userId;
	String address;
	String phone;

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

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
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

	public Long getProductNum() {
		return productNum;
	}

	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getSellerNum() {
		return sellerNum;
	}

	public void setSellerNum(Long sellerNum) {
		this.sellerNum = sellerNum;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public Integer getOptionPrice() {
		return optionPrice;
	}

	public void setOptionPrice(Integer optionPrice) {
		this.optionPrice = optionPrice;
	}

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(Long orderItem) {
		this.orderItem = orderItem;
	}

	public Integer getTrackingNum() {
		return trackingNum;
	}

	public void setTrackingNum(Integer trackingNum) {
		this.trackingNum = trackingNum;
	}

	@Override
	public String toString() {
		return "ProdOrderVO [pdOrderNum=" + pdOrderNum + ", userNum=" + userNum + ", pdTotalPrice=" + pdTotalPrice
				+ ", orderDate=" + orderDate + ", deleveryStatus=" + deleveryStatus + ", orderStatus=" + orderStatus
				+ ", productNum=" + productNum + ", amount=" + amount + ", totalPrice=" + totalPrice + ", sellerNum=" + sellerNum
				+ ", productName=" + productName + ", imgUrl=" + imgUrl + ", option=" + option + ", optionPrice="
				+ optionPrice + ", storeName=" + storeName +", userName:"+ userName +"]";
	}

	
}
