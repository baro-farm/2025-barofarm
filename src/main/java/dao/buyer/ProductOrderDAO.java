package dao.buyer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.ProductOrder;
import dto.buyer.ProductOrderItem;
import vo.AdminProdOrderVO;
import vo.ProdCancelVO;
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
//	void insertProductOrderItem(SqlSession sqlSession, ProductOrderItem poItem) throws Exception;

	//취소
	void processProdCancel(Long pdOrderNum, String reason, String detail) throws Exception;

	//판매자 취소 리스트
	Integer countSellerCancelList(Map<String, Object> param) throws Exception;
    List<ProdCancelVO> selectSellerCancelList(Map<String, Object> param) throws Exception;
    List<AdminProdOrderVO> selectAdminProdOrderList(Map<String, Object> param) throws Exception;
    int countAdminOrderList(Map<String, Object> param) throws Exception;  
}
