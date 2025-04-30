package dao.buyer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.ProductOrder;
import dto.buyer.ProductOrderItem;
import vo.ProdOrderVO;

public interface ProductOrderDAO {
	List<ProdOrderVO> selectProdOrderList(String userId) throws Exception;
	List<ProdOrderVO> selectProdDetailOrderList(Long pdOrderNum) throws Exception;
	void updateDeliveryStatus(ProductOrder pdOrder) throws Exception;
	
	//user with paging
    Integer selectUserProdOrderCount(Map<String, Object> param);
    List<ProdOrderVO> selectUserProdOrderList(Map<String, Object> param);	
	
	//seller product order list
	List<ProdOrderVO> selectSellerProductOrderList(Map<String,Object> param) throws Exception;
	Integer sellectCountSellerProductOrderList(Map<String,Object> param) throws Exception;
	void updateProdTrackingNum(Map<String, Object> param) throws Exception;

	Integer countSellerProductOrderList(Map<String,Object> param) throws Exception;
	void insertProductOrder(SqlSession sqlSession, ProductOrder productOrder) throws Exception;
	void insertProductOrderItem(SqlSession sqlSession, Map<String,Object> param) throws Exception;
}
