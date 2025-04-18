package service.buyer;

import java.util.List;

import vo.PackOrderVO;

public interface PackOrderService {
	List<PackOrderVO> selectUserPackOrderList(String userId) throws Exception;
	void updateUserPackDeliveryStatus(Long pkOrderNum, String deliveryStatus) throws Exception;
}
