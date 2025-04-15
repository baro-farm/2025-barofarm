package vo;

public class SellerVO {
    // user 테이블 정보
	private String userNum;
    private String userId;
    private String userName;
    private String phone;
    private String birthDate;
    private String email;
    private boolean isSeller;
    private boolean deleted;

    // sellerDetail 테이블 정보
    private String sellerNum;
    private String storeName;
    private String businessNum;
    private boolean isAlarm;

    String str="머지 테스트";
    private String test="merge";
    // 필요하다면 Getter/Setter, toString 등 추가
 
    public SellerVO() {
		super();
	}
	public SellerVO(String userNum, String storeName, String businessNum) {
		super();
		this.userNum = userNum;
		this.storeName = storeName;
		this.businessNum = businessNum;
	}
	
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isSeller() {
		return isSeller;
	}
	public void setSeller(boolean isSeller) {
		this.isSeller = isSeller;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public String getSellerNum() {
		return sellerNum;
	}
	public void setSellerNum(String sellerNum) {
		this.sellerNum = sellerNum;
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
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
    
    
}