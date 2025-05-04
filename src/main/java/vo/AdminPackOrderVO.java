package vo;

import java.sql.Date;
import java.sql.Timestamp;

public class AdminPackOrderVO {
	private Long pkOrderNum;
    private String packageName;
    private String userId;
    private String userName;
    private String rName;
    private String addr;
    private String rPhone;
    private String deleveryDate;
    private Date subStartDate;
    private Date subEndDate;
    private Integer subRound;
    private String deleveryStatus;
    private String trackingNum;
    private Timestamp orderedAt;
    private int pkTotalPrice;
    
    private String sellerId;
    private String storeName;
    
	public Long getPkOrderNum() {
		return pkOrderNum;
	}
	public void setPkOrderNum(Long pkOrderNum) {
		this.pkOrderNum = pkOrderNum;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
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
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getrPhone() {
		return rPhone;
	}
	public void setrPhone(String rPhone) {
		this.rPhone = rPhone;
	}
	public String getDeleveryDate() {
		return deleveryDate;
	}
	public void setDeleveryDate(String deleveryDate) {
		this.deleveryDate = deleveryDate;
	}
	public Date getSubStartDate() {
		return subStartDate;
	}
	public void setSubStartDate(Date subStartDate) {
		this.subStartDate = subStartDate;
	}
	public Date getSubEndDate() {
		return subEndDate;
	}
	public void setSubEndDate(Date subEndDate) {
		this.subEndDate = subEndDate;
	}
	public Integer getSubRound() {
		return subRound;
	}
	public void setSubRound(Integer subRound) {
		this.subRound = subRound;
	}
	public String getDeleveryStatus() {
		return deleveryStatus;
	}
	public void setDeleveryStatus(String deleveryStatus) {
		this.deleveryStatus = deleveryStatus;
	}
	public String getTrackingNum() {
		return trackingNum;
	}
	public void setTrackingNum(String trackingNum) {
		this.trackingNum = trackingNum;
	}
	public Timestamp getOrderedAt() {
		return orderedAt;
	}
	public void setOrderedAt(Timestamp orderedAt) {
		this.orderedAt = orderedAt;
	}
	public int getPkTotalPrice() {
		return pkTotalPrice;
	}
	public void setPkTotalPrice(int pkTotalPrice) {
		this.pkTotalPrice = pkTotalPrice;
	}
    
	public String getSellerId() {
	    return sellerId;
	}
	public void setSellerId(String sellerId) {
	    this.sellerId = sellerId;
	}

	public String getStoreName() {
	    return storeName;
	}
	public void setStoreName(String storeName) {
	    this.storeName = storeName;
	}
}
