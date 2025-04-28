package dao.buyer;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import util.MybatisSqlSessionFactory;

public class PaymentDAOImpl implements PaymentDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();
	
	@Override
    public void insertPayment(SqlSession sqlSession, Map<String, Object> param) throws Exception {
//        PaymentMapper mapper = sqlSession.getMapper(PaymentMapper.class);
//        mapper.insertPayment(param);
		sqlSession.insert("mapper.payment.insertPayment", param);
    }

}
