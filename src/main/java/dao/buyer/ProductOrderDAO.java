package dao.buyer;

import java.util.List;

import dto.buyer.ProductOrder;
import vo.ProdOrderVO;

public interface ProductOrderDAO {
	List<ProdOrderVO> selectProdOrderList(String userId) throws Exception;
	List<ProdOrderVO> selectProdDetailOrderList(Long pdOrderNum) throws Exception;
	void updateDeliveryStatus(ProductOrder pdOrder) throws Exception;

}
