package vo;

import java.time.LocalDate;

public class ProductVO {
    private Long productNum;
    private Long sellerNum;
    private String productName;
    private String storeName;
    private Integer price;
    private String imgUrl;
    private Boolean status;
    private Integer stock;
    

    // 리뷰 정보
    private Double avgRating;
    private Integer reviewCount;

    // 카테고리 정보
    private Integer cateNum;
    private String cateName;

    // 정렬/조회용
    private Integer salesVolume;
    private LocalDate createdAt;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
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
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Double getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(Double avgRating) {
		this.avgRating = avgRating;
	}
	public Integer getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(Integer reviewCount) {
		this.reviewCount = reviewCount;
	}
	public Integer getCateNum() {
		return cateNum;
	}
	public void setCateNum(Integer cateNum) {
		this.cateNum = cateNum;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public Integer getSalesVolume() {
		return salesVolume;
	}
	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
    
    
    
}