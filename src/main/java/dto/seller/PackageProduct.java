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
}
