package dao.seller;

import org.apache.ibatis.session.SqlSession;

import dto.seller.SellerDetail;
import util.MybatisSqlSessionFactory;

public class SellerDAOImpl implements SellerDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public void insertSellerDetail(SellerDetail seller) throws Exception {
		sqlSession.insert("mapper.seller.insertSeller", seller);
		sqlSession.commit();
	}

	@Overrid
	public Long selectSellerNum(Long userNum) throws Exception {
		return sqlSession.selectOne("mapper.seller.selectSellerNum", userNum);
	}
  
  @Override
	public boolean doubleStoreNameCheck(String storeName) throws Exception {
		 SellerDetail seller = sqlSession.selectOne("mapper.seller.selectStoreByName", storeName);
		 return seller != null;
	}
}
