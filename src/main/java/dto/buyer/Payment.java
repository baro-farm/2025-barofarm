package dto.buyer;

public class Payment {
	Long payNum;
	Long pdOrderNum;
	Integer price;
	Integer transactionId; // pg 거래번호
	String pay; // 결제수단
}
