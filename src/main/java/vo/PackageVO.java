package vo;

import java.time.LocalDate;

public class PackageVO {
	private Long packageNum;
	private Long sellerNum;
	private Integer cateNum;
	private String packageName;
	private String content;
	private Integer packagePrice;
	private Integer stock;
	private LocalDate startDate;
	private LocalDate endDate;
	private String packageUnit;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	private Long salesVolume;
	private String imgUrl;
	
	private String storeName;
	
	// 리뷰 정보
    private Double avgRating;
    private Integer reviewCount;
    
    private String cateName;
	
	public PackageVO() {
		super();
	}

	public PackageVO(Long packageNum, String packageName, Integer packagePrice, Long salesVolume, String imgUrl,
			Double avgRating, Integer reviewCount, LocalDate startDate) {
		super();
		this.packageNum = packageNum;
		this.packageName = packageName;
		this.packagePrice = packagePrice;
		this.salesVolume = salesVolume;
		this.imgUrl = imgUrl;
		this.avgRating = avgRating;
		this.reviewCount = reviewCount;
		this.startDate = startDate;
	}

	public Long getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(Long packageNum) {
		this.packageNum = packageNum;
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

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPackagePrice() {
		return packagePrice;
	}

	public void setPackagePrice(Integer packagePrice) {
		this.packagePrice = packagePrice;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getPackageUnit() {
		return packageUnit;
	}

	public void setPackageUnit(String packageUnit) {
		this.packageUnit = packageUnit;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Long salesVolume) {
		this.salesVolume = salesVolume;
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

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	
	
	

}
