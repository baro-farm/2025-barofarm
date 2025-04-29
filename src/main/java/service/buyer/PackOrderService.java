package service.buyer;

import java.util.List;

import vo.PackOrderVO;

public interface PackOrderService {
	List<PackOrderVO> selectUserPackOrderList(String userId) throws Exception;
	void updateUserPackDeliveryStatus(Long pkOrderNum, String deliveryStatus) throws Exception;

	//seller List
	List<PackOrderVO> selectSellerPackOrderList(Long sellerNum, int offset, int pageSize, String dateType,
            String startDate, String endDate, String deliveryDay,
            String searchType, String searchKeyword) throws Exception;
	
	Integer selectCountSellerPackOrderList(Long sellerNum, String dateType, String startDate, String endDate, String deliveryDay, String searchType, String searchKeyword) throws Exception;

	void updateSellerPackTrackingNum(Long pkOrderNum, Integer trackingNum) throws Exception;
}
