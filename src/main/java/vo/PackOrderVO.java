package vo;

import java.time.LocalDate;

public class PackOrderVO {
	    //packageorder
		Long pkOrderNum;
		Long userNum;
		Integer pkTotalPrice;
		LocalDate orderedAt;
		String deleveryStatus;
		
		//package
		Long sellerNum;
		String packageName;
		String imgUrl;
		
		//seller detail
		String StoreName;

		public Long getPkOrderNum() {
			return pkOrderNum;
		}

		public void setPkOrderNum(Long pkOrderNum) {
			this.pkOrderNum = pkOrderNum;
		}

		public Long getUserNum() {
			return userNum;
		}

		public void setUserNum(Long userNum) {
			this.userNum = userNum;
		}

		public Integer getPkTotalPrice() {
			return pkTotalPrice;
		}

		public void setPkTotalPrice(Integer pkTotalPrice) {
			this.pkTotalPrice = pkTotalPrice;
		}

		public LocalDate getOrderedAt() {
			return orderedAt;
		}

		public void setOrderedAt(LocalDate orderedAt) {
			this.orderedAt = orderedAt;
		}

		public String getDeleveryStatus() {
			return deleveryStatus;
		}

		public void setDeleveryStatus(String deleveryStatus) {
			this.deleveryStatus = deleveryStatus;
		}

		public Long getSellerNum() {
			return sellerNum;
		}

		public void setSellerNum(Long sellerNum) {
			this.sellerNum = sellerNum;
		}

		public String getPackageName() {
			return packageName;
		}

		public void setPackageName(String packageName) {
			this.packageName = packageName;
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
