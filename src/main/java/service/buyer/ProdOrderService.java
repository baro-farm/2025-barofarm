package service.buyer;

import java.util.List;

import vo.ProdOrderVO;

public interface ProdOrderService {
	List<ProdOrderVO> selectUserProdOrderList(String userId) throws Exception;
	List<ProdOrderVO> selectUserProdOrderDetailList(Long pdOrderNum) throws Exception;
	void updateUserProdDeliveryStatus(Long pdOrderNum, String deliveryStatus) throws Exception;
}
