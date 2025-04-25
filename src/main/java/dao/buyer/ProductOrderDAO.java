package dao.buyer;

import java.util.List;
import java.util.Map;

import dto.buyer.ProductOrder;
import vo.ProdOrderVO;

public interface ProductOrderDAO {
	List<ProdOrderVO> selectProdOrderList(String userId) throws Exception;
	List<ProdOrderVO> selectProdDetailOrderList(Long pdOrderNum) throws Exception;
	void updateDeliveryStatus(ProductOrder pdOrder) throws Exception;
	
	//seller product order list
	List<ProdOrderVO> selectSellerProductOrderList(Map<String,Object> param) throws Exception;
	Integer countSellerProductOrderList(Map<String,Object> param) throws Exception;

}
