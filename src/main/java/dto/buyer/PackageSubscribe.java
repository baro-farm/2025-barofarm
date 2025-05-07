package dto.buyer;

import java.sql.Date;
import java.time.LocalDateTime;

public class PackageSubscribe {
	Long subNum;
	Long userNum;
	Long packageNum;
	Long sellerNum;
	Date subStartDate;
	Date subEndDate;
	Integer isSub;  // 구독 여부
	Integer subRound;  // 구독회차
	String deleveryDate;
	String deleveryCycle;
	String rname;
	String rphone;
	String addr;
	
	public PackageSubscribe() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public PackageSubscribe(Long userNum, Long packageNum, Long sellerNum, Date subStartDate,
			Date subEndDate, Integer isSub, Integer subRound, String deleveryDate, String deleveryCycle,
			String rname, String rphone, String addr) {
		super();
		this.userNum = userNum;
		this.packageNum = packageNum;
		this.sellerNum = sellerNum;
		this.subStartDate = subStartDate;
		this.subEndDate = subEndDate;
		this.isSub = isSub;
		this.subRound = subRound;
		this.deleveryDate = deleveryDate;
		this.deleveryCycle = deleveryCycle;
		this.rname = rname;
		this.rphone = rphone;
		this.addr = addr;
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

	public Integer getIsSub() {
		return isSub;
	}

	public void setIsSub(Integer isSub) {
		this.isSub = isSub;
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

	public String getDeleveryCycle() {
		return deleveryCycle;
	}

	public void setDeleveryCycle(String deleveryCycle) {
		this.deleveryCycle = deleveryCycle;
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}
