package service.buyer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.PackageOrder;
import dto.buyer.PackageSubscribe;
import vo.AdminPackOrderVO;
import vo.PackOrderVO;

public interface PackOrderService {
	List<PackOrderVO> selectUserPackOrderList(String userId) throws Exception;
	void updateUserPackDeliveryStatus(Long pkOrderNum, String deliveryStatus) throws Exception;

	//구매자 페이징
    Integer selectUserPackOrderCount(Long userNum, String startDate, String endDate, String deliveryStatus);
    List<PackOrderVO> selectUserPackOrderList(Long userNum, String startDate, String endDate, String deliveryStatus, int offset, int limit);

	
	//seller List
	List<PackOrderVO> selectSellerPackOrderList(Long sellerNum, int offset, int pageSize, String dateType,
            String startDate, String endDate, String deliveryDay,
            String searchType, String searchKeyword) throws Exception;
	
	Integer selectCountSellerPackOrderList(Long sellerNum, String dateType, String startDate, String endDate, String deliveryDay, String searchType, String searchKeyword) throws Exception;

	void updateSellerPackTrackingNum(Long pkOrderNum, Integer trackingNum) throws Exception;
	void insertPackageOrder(SqlSession sqlSession, PackageOrder packOrder) throws Exception;
	void insertSubscription(SqlSession sqlSession, PackageSubscribe sub) throws Exception;
	List<AdminPackOrderVO> selectAdminPackOrderList(int offset, int pageSize, String startDate, String endDate, String searchType, String searchKeyword) throws Exception;
	int countAdminPackOrderList(String startDate, String endDate, String searchType, String searchKeyword) throws Exception;
}
