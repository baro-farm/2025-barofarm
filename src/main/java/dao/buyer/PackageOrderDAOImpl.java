package dao.buyer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.PackageOrder;
import dto.buyer.PackageSubscribe;
import util.MybatisSqlSessionFactory;
import vo.PackOrderVO;
import vo.ProdOrderVO;

public class PackageOrderDAOImpl implements PackageOrderDAO{
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public List<PackOrderVO> selectPackOrderList(String userId) throws Exception{
		Long userNum = sqlSession.selectOne("mapper.user.selectUserNumById",userId);
		return sqlSession.selectList("mapper.packOrder.packOrderList",userNum);
	}

	@Override
	public void updateDeliveryStatus(PackageOrder pkOrder) throws Exception {
		sqlSession.update("mapper.packOrder.updatePkDeliveryStatus", pkOrder);
		sqlSession.commit();
		
	}
	
	//seller order List
	
	@Override
	public List<PackOrderVO> selectSellerPackOrderList(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.packOrder.selectSellerPackOrderList",param);
	}

	@Override
	public Integer selectCountSellerPackOrderList(Map<String, Object> param) throws Exception {
		return sqlSession.selectOne("mapper.packOrder.selectCountSellerPackOrder",param);
	}
	
	
	@Override
	public void updatePackTrackingNum(Map<String, Object> param) throws Exception {
		sqlSession.update("mapper.packOrder.updatePkTrackingNum",param);
		sqlSession.commit();
	}

	//user list
	
	@Override
	public Integer selectUserPackOrderCount(Map<String, Object> param) {
		return 	sqlSession.selectOne("mapper.packOrder.selectUserPackOrderCount",param);

	}

	@Override
	public List<PackOrderVO> selectUserPackOrderList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return 	sqlSession.selectList("mapper.packOrder.selectUserPackOrderList",param);
	}

	@Override
	public void insertPackageOrder(SqlSession sqlSession, PackageOrder packOrder) throws Exception {
		sqlSession.insert("mapper.packOrder.insertPackOrder", packOrder);
	}
	
	@Override
	public void insertSubscription(SqlSession sqlSession, PackageSubscribe sub) throws Exception {
		sqlSession.insert("mapper.packageSub.insertSubscription", sub);	
	}
}
