package vo;

import java.time.LocalDate;

public class ProdOrderVO {
	//product order
	Long pdOrderNum;
	Long userNum;
	Integer TotalPrice;
	LocalDate orderDate;
	String deleveryStatus;
	String orderStatus;
	
	//product order item
	Long productNum;
	Integer amount;
	Integer price;
	
	//product
	Long sellerNum;
	String productName;
	String imgUrl;
	
	//seller detail
	String StoreName;

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

	public Integer getTotalPrice() {
		return TotalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		TotalPrice = totalPrice;
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
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
		return StoreName;
	}

	public void setStoreName(String storeName) {
		StoreName = storeName;
	}
	
}
