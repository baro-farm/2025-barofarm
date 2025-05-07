package dto.seller;

public class SellerDetail {
	Long sellerNum;
	Long userNum;
	String storeName;
	String businessNum;
	boolean isAlarm;
	
	public SellerDetail() {
		super();
	}

	public SellerDetail(String storeName, String businessNum) {
		super();
		this.storeName = storeName;
		this.businessNum = businessNum;
	}

	public SellerDetail(Long sellerNum, Long userNum, String storeName, String businessNum, boolean isAlarm) {
		super();
		this.sellerNum = sellerNum;
		this.userNum = userNum;
		this.storeName = storeName;
		this.businessNum = businessNum;
		this.isAlarm = isAlarm;
	}

	public SellerDetail(Long userNum, String storeName, String businessNum, boolean isAlarm) {
		super();
		this.userNum = userNum;
		this.storeName = storeName;
		this.businessNum = businessNum;
		this.isAlarm = isAlarm;
	}

	public Long getSellerNum() {
		return sellerNum;
	}

	public void setSellerNum(Long sellerNum) {
		this.sellerNum = sellerNum;
	}

	public Long getUserNum() {
		return userNum;
	}

	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getBusinessNum() {
		return businessNum;
	}

	public void setBusinessNum(String businessNum) {
		this.businessNum = businessNum;
	}

	public boolean isAlarm() {
		return isAlarm;
	}

	public void setAlarm(boolean isAlarm) {
		this.isAlarm = isAlarm;
	}
	
}
