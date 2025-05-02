package dao.buyer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.PackageOrder;
import dto.buyer.PackageSubscribe;
import vo.PackOrderVO;

public interface PackageOrderDAO {
	List<PackOrderVO> selectPackOrderList(String userId) throws Exception;
	void updateDeliveryStatus(PackageOrder pkOrder) throws Exception;
	
	//user with paging
    Integer selectUserPackOrderCount(Map<String, Object> param);
    List<PackOrderVO> selectUserPackOrderList(Map<String, Object> param);	
	
	
	//seller order List
	List<PackOrderVO> selectSellerPackOrderList(Map<String, Object> param) throws Exception;
	Integer selectCountSellerPackOrderList(Map<String,Object> param) throws Exception;
	
	void updatePackTrackingNum(Map<String,Object> param) throws Exception;
	void insertPackageOrder(SqlSession sqlSession, PackageOrder packOrder) throws Exception;
	void insertSubscription(SqlSession sqlSession, PackageSubscribe sub) throws Exception;
}
