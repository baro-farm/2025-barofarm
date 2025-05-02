package vo;

import java.time.LocalDate;

public class PackSubVO {
	
	Long subNum;
	Long userNum;
	Long sellerNum;
	Long packageNum;
	
	LocalDate subStartDate;
	LocalDate subEndDate;
	Boolean isSub;
	String addr;
	Integer subRound;
	String deleveryDate;
	String rname;
	String rphone;
	
	String packageName;
	LocalDate startDate;
	LocalDate endDate;
	String imgUrl;
	Integer packagePrice;
	String packageUnit;
	
	Long pkOrderNum;
	Integer pkTotalPrice;
	String userId;
	String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getSubNum() {
		return subNum;
	}

	public void setSubNum(Long subNum) {
		this.subNum = subNum;
	}

	public Long getUserNum() {
		return userNum;
	}

	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}

	public Long getSellerNum() {
		return sellerNum;
	}

	public void setSellerNum(Long sellerNum) {
		this.sellerNum = sellerNum;
	}

	public Long getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(Long packageNum) {
		this.packageNum = packageNum;
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

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getPackagePrice() {
		return packagePrice;
	}

	public void setPackagePrice(Integer packagePrice) {
		this.packagePrice = packagePrice;
	}

	
	public String getPackageUnit() {
		return packageUnit;
	}

	public void setPackageUnit(String packageUnit) {
		this.packageUnit = packageUnit;
	}

	public Long getPkOrderNum() {
		return pkOrderNum;
	}

	public void setPkOrderNum(Long pkOrderNum) {
		this.pkOrderNum = pkOrderNum;
	}

	public Integer getPkTotalPrice() {
		return pkTotalPrice;
	}

	public void setPkTotalPrice(Integer pkTotalPrice) {
		this.pkTotalPrice = pkTotalPrice;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
