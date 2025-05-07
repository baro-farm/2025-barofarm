package vo;

import java.time.LocalDate;

public class PackOrderVO {
	    //packageorder
		Long pkOrderNum;
		Long userNum;
		Long packageNum;
		Integer pkTotalPrice;
		LocalDate orderedAt;
		String deleveryStatus;
		Integer trackingNum;
		
		//user UserId
		String userId;
		
		//package
		Long sellerNum;
		String packageName;
		String imgUrl;
		
		//seller detail
		String StoreName;
		
		//package Subscribe
		Long subNum;
		LocalDate subStartDate;
		LocalDate subEndDate;
		Boolean isSub;
		String addr;
		String rname;
		String rphone;
		Integer subRound;
		String deleveryDate;

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

		public Long getPackageNum() {
			return packageNum;
		}

		public void setPackageNum(Long packageNum) {
			this.packageNum = packageNum;
		}

		public Integer getTrackingNum() {
			return trackingNum;
		}

		public void setTrackingNum(Integer trackingNum) {
			this.trackingNum = trackingNum;
		}

		public Long getSubNum() {
			return subNum;
		}

		public void setSubNum(Long subNum) {
			this.subNum = subNum;
		}

		public LocalDate getSubStartDate() {
			return subStartDate;
		}

		public void setSubStartDate(LocalDate subStartDate) {
			this.subStartDate = subStartDate;
		}

		public LocalDate getSubEndDate() {
			return subEndDate;
		}

		public void setSubEndDate(LocalDate subEndDate) {
			this.subEndDate = subEndDate;
		}

		public Boolean getIsSub() {
			return isSub;
		}

		public void setIsSub(Boolean isSub) {
			this.isSub = isSub;
		}

		public String getAddr() {
			return addr;
		}

		public void setAddr(String addr) {
			this.addr = addr;
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

		public Integer getSubRound() {
			return subRound;
		}

		public void setSubRound(Integer subRound) {
			this.subRound = subRound;
		}

		public String getDeleveryDate() {
			return deleveryDate;
		}

		public void setDeleveryDate(String deleveryDate) {
			this.deleveryDate = deleveryDate;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		
		
		

}
