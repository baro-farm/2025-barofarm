package service.seller;

import org.apache.ibatis.session.SqlSession;

import dao.seller.SellerDAO;
import dao.seller.SellerDAOImpl;
import util.MybatisSqlSessionFactory;

public class SellerDetailServiceImpl implements SellerDetailService {

	private SellerDAO sellerDao(SqlSession sqlSession) {
		return new SellerDAOImpl(sqlSession);
	}

	@Override
	public Long selectSellerNumById(String userId) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			return sellerDao(sqlSession).selectSellerNumByUserID(userId);
		}
	}

}
