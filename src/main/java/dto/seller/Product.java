package dto.seller;

import java.time.LocalDateTime;

public class Product {
	Long productNum;
	Long sellerNum;
	Integer cateNum;
	String productName;
	String content;
	Integer stock;
	Integer price;
	String imgUrl;
	boolean status;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	Long salesVolume;
	
	public Product() {
		super();
	}
	
	public Product(Long productNum, Long sellerNum, Integer cateNum, String productName, String content, Integer stock,
			Integer price, String imgUrl, Long salesVolume) {
		super();
		this.productNum = productNum;
		this.sellerNum = sellerNum;
		this.cateNum = cateNum;
		this.productName = productName;
		this.content = content;
		this.stock = stock;
		this.price = price;
		this.imgUrl = imgUrl;
		this.salesVolume = salesVolume;
	}
	

	public Product(Long productNum, Long sellerNum, Integer cateNum, String productName, String content, Integer stock,
			Integer price, String imgUrl, boolean status, LocalDateTime createdAt, LocalDateTime updatedAt,
			Long salesVolume) {
		super();
		this.productNum = productNum;
		this.sellerNum = sellerNum;
		this.cateNum = cateNum;
		this.productName = productName;
		this.content = content;
		this.stock = stock;
		this.price = price;
		this.imgUrl = imgUrl;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.salesVolume = salesVolume;
	}

	public Product(Long sellerNum, Integer cateNum, String productName, String content, Integer stock, Integer price,
			String imgUrl) {
		super();
		this.sellerNum = sellerNum;
		this.cateNum = cateNum;
		this.productName = productName;
		this.content = content;
		this.stock = stock;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	public Product(Long sellerNum, Integer cateNum, String productName, String content, Integer stock, Integer price,
			String imgUrl, boolean status) {
		super();
		this.sellerNum = sellerNum;
		this.cateNum = cateNum;
		this.productName = productName;
		this.content = content;
		this.stock = stock;
		this.price = price;
		this.imgUrl = imgUrl;
		this.status = status;
	}
	public Product(Long productNum, Long sellerNum, Integer cateNum, String productName, String content, Integer stock, Integer price,
			String imgUrl, boolean status, LocalDateTime updatedAt) {
		super();
		this.productNum = productNum;
		this.sellerNum = sellerNum;
		this.cateNum = cateNum;
		this.productName = productName;
		this.content = content;
		this.stock = stock;
		this.price = price;
		this.imgUrl = imgUrl;
		this.status = status;
		this.updatedAt = updatedAt;
	}
	
	public Product(Long productNum, boolean status) {
		super();
		this.productNum = productNum;
		this.status = status;
	}

	public Long getProductNum() {
		return productNum;
	}

	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}

	public Long getSellerNum() {
		return sellerNum;
	}

	public void setSellerNum(Long sellerNum) {
		this.sellerNum = sellerNum;
	}

	public Integer getCateNum() {
		return cateNum;
	}

	public void setCateNum(Integer cateNum) {
		this.cateNum = cateNum;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

	public Long getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Long salesVolume) {
		this.salesVolume = salesVolume;
	}

	@Override
	public String toString() {
		return "Product [productNum=" + productNum + ", sellerNum=" + sellerNum + ", cateNum=" + cateNum
				+ ", productName=" + productName + ", content=" + content + ", stock=" + stock + ", price=" + price
				+ ", imgUrl=" + imgUrl + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", salesVolume=" + salesVolume + "]";
	}

}
