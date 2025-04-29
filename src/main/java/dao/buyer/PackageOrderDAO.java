package dao.buyer;

import java.util.List;
import java.util.Map;

import dto.buyer.PackageOrder;
import vo.PackOrderVO;

public interface PackageOrderDAO {
	List<PackOrderVO> selectPackOrderList(String userId) throws Exception;
	void updateDeliveryStatus(PackageOrder pkOrder) throws Exception;
	
	//seller order List
	List<PackOrderVO> selectSellerPackOrderList(Map<String, Object> param) throws Exception;
	Integer selectCountSellerPackOrderList(Map<String,Object> param) throws Exception;
	
	void updatePackTrackingNum(Map<String,Object> param) throws Exception;
	

}
