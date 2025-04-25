package service.buyer;

public interface PaymentService {
	boolean verifyPayment(String impUid, int amount) throws Exception;
	String getAccessToken() throws Exception;
	void processOrder(String impUid, Long[] cartNums) throws Exception;
}
