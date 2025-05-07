package dao.buyer;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public interface PaymentDAO {
    void insertPayment(SqlSession sqlSession, Map<String, Object> param) throws Exception;
}
