package service.buyer;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public interface PaymentService {
	Map<String, String> verifyPayment(String impUid, int amount) throws Exception;
	String getAccessToken() throws Exception;
	void insertPayment(SqlSession sqlSession, Long pdOrderNum, Long pkOrderNum, int price, String transactionId, String pay, String impUid, String merchantUid) throws Exception;
}
