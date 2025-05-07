package service.seller;

import org.apache.ibatis.session.SqlSession;

import dao.seller.SellerDAO;
import dao.seller.SellerDAOImpl;
import dto.seller.SellerDetail;
import util.MybatisSqlSessionFactory;
import vo.SellerVO;

public class SellerServiceImpl implements SellerService {

	private SellerDAO sellerDao(SqlSession sqlSession) {
		return new SellerDAOImpl(sqlSession);
	}

	@Override
	public Long selectSellerNum(Long userNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			return sellerDao(sqlSession).selectSellerNum(userNum);
		}
	}

	@Override
	public SellerVO getSerllerDetail(Long userNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			return sellerDao(sqlSession).selectSerllerDetail(userNum);
		}
	}

	@Override
	public void changeIsAlarm(Long userNum) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {
			sellerDao(sqlSession).updateIsAlarm(userNum);
			sqlSession.commit();
		}
	}

	@Override
	public SellerDetail selectSellerDetail(Long sellerNum) {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			return sellerDao(sqlSession).selectSellerDetail(sellerNum);
		}
	}
}
