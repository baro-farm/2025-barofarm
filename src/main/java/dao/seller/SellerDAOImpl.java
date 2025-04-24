package dao.seller;

import org.apache.ibatis.session.SqlSession;

import dto.seller.SellerDetail;
import util.MybatisSqlSessionFactory;
import vo.SellerVO;

public class SellerDAOImpl implements SellerDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public void insertSellerDetail(SellerDetail seller) throws Exception {
		sqlSession.insert("mapper.seller.insertSeller", seller);
		sqlSession.commit();
	}

	@Override
	public Long selectSellerNumByUserID(String userId) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.seller.selectSellerNumByUserID", userId);
	}

	@Override
	public Long selectSellerNum(Long userNum) throws Exception {
		return sqlSession.selectOne("mapper.seller.selectSellerNum", userNum);
	}

	@Override
	public boolean doubleStoreNameCheck(String storeName) throws Exception {
		SellerDetail seller = sqlSession.selectOne("mapper.seller.selectStoreByName", storeName);
		return seller != null;
	}

	@Override
	public SellerVO selectSerllerDetail(Long userNum) throws Exception {
		return sqlSession.selectOne("mapper.seller.selectSellerVO",userNum);
	}

	@Override
	public void updateIsAlarm(Long userNum) throws Exception {
		sqlSession.update("mapper.seller.updateIsAlarm",userNum);
		sqlSession.commit();
	}
	
}
