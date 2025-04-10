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

    private String test="merge";
    // 필요하다면 Getter/Setter, toString 등 추가
}