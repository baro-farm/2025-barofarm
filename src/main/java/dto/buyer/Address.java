package dto.buyer;

public class Address {
	Long addrNum;
	Long userNum;
	String nickname;
	String name;
	String phone;
	String postCode;
	String addr1;
	String addr2;
	boolean isDefault;
	
	public Address() {}
	
	public Address(Long addrNum, Long userNum, String nickname, String name, String phone, String postCode,
			String addr1, String addr2) {
		super();
		this.addrNum = addrNum;
		this.userNum = userNum;
		this.nickname = nickname;
		this.name = name;
		this.phone = phone;
		this.postCode = postCode;
		this.addr1 = addr1;
		this.addr2 = addr2;
	}
	
	public Address(Long addrNum, Long userNum, String nickname, String postCode,
			String addr1, String addr2) {
		super();
		this.addrNum = addrNum;
		this.userNum = userNum;
		this.nickname = nickname;
		this.postCode = postCode;
		this.addr1 = addr1;
		this.addr2 = addr2;
	}
	
	public Address(Long addrNum,String nickname, String name, String phone, String postCode, String addr1, String addr2 ) {
		this.addrNum = addrNum;
		this.nickname = nickname;
		this.name = name;
		this.phone = phone;
		this.postCode = postCode;
		this.addr1 = addr1;
		this.addr2 = addr2;
	}
	
	public Address(String nickname, String name, String phone, String postCode, String addr1, String addr2 ) {
		this.nickname = nickname;
		this.name = name;
		this.phone = phone;
		this.postCode = postCode;
		this.addr1 = addr1;
		this.addr2 = addr2;
	}

	public Long getAddrNum() {
		return addrNum;
	}

	public void setAddrNum(Long addrNum) {
		this.addrNum = addrNum;
	}

	public Long getUserNum() {
		return userNum;
	}

	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	

	public boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "Address [addrNum=" + addrNum + ", userNum=" + userNum + ", nickname=" + nickname + ", name=" + name
				+ ", phone=" + phone + ", postCode=" + postCode + ", addr1=" + addr1 + ", addr2=" + addr2 + "]";
	}
	
	
	
}
