package service.buyer;

import java.util.List;

import vo.ProdOrderVO;

public interface ProdOrderService {
	List<ProdOrderVO> selectUserProdOrderList(String userId) throws Exception;
	
}
