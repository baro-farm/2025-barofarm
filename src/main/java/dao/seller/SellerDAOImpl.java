package dao.seller;

import org.apache.ibatis.session.SqlSession;

import util.MybatisSqlSessionFactory;
import vo.SellerVO;

public class SellerDAOImpl implements SellerDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public void insertSeller(SellerVO seller) throws Exception {
		sqlSession.insert("mapper.seller.insertSeller", seller);
		sqlSession.commit();
	}

}
