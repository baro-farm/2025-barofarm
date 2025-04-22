package dto.seller;

import java.sql.Date;
import java.time.LocalDateTime;

public class PackageProduct {
	Long packageNum;
	Long sellerNum;
	Integer cateNum;
	String packageName;
	String content;
	Integer packagePrice;
	Integer stock;
	Date startDate;
	Date endDate;
	String packageUnit;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	Long salesVolume;
	String imgUrl;

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPackageUnit() {
		return packageUnit;
	}

	public void setPackageUnit(String packageUnit) {
		this.packageUnit = packageUnit;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	

	public PackageProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PackageProduct(Long sellerNum, Integer cateNum, String packageName, String content, Integer packagePrice,
			Integer stock, Date startDate, Date endDate, String packageUnit, String imgUrl) {
		super();
		this.sellerNum = sellerNum;
		this.cateNum = cateNum;
		this.packageName = packageName;
		this.content = content;
		this.packagePrice = packagePrice;
		this.stock = stock;
		this.startDate = startDate;
		this.endDate = endDate;
		this.packageUnit = packageUnit;
		this.imgUrl = imgUrl;
	}

	public PackageProduct(Long packageNum, Integer cateNum, String packageName, String content, Integer packagePrice,
			Integer stock, Date startDate, Date endDate, String packageUnit, LocalDateTime updatedAt, String imgUrl) {
		super();
		this.packageNum = packageNum;
		this.cateNum = cateNum;
		this.packageName = packageName;
		this.content = content;
		this.packagePrice = packagePrice;
		this.stock = stock;
		this.startDate = startDate;
		this.endDate = endDate;
		this.packageUnit = packageUnit;
		this.updatedAt = updatedAt;
		this.imgUrl = imgUrl;
	}
	
	
}
