package service.buyer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.ProductOrder;
import vo.ProdOrderVO;

public interface ProdOrderService {
	List<ProdOrderVO> selectUserProdOrderList(String userId) throws Exception;
	List<ProdOrderVO> selectUserProdOrderDetailList(Long pdOrderNum) throws Exception;
	void updateUserProdDeliveryStatus(Long pdOrderNum, String deliveryStatus) throws Exception;

	Integer selectProductOrderCount(Long sellerNum) throws Exception;
	
    // 주문 리스트
    List<ProdOrderVO> selectProductOrderList(Long sellerNum, int offset, int pageSize, String dateType, 
                                             String startDate, String endDate, 
                                             String searchType, String searchKeyword) throws Exception;
 // 주문 수 (페이징용)
    Integer countProductOrderList(Long sellerNum, String dateType, String startDate, 
                                  String endDate, String searchType, String searchKeyword) throws Exception;
	void insertProductOrder(SqlSession sqlSession, ProductOrder productOrder) throws Exception;
	void insertProductOrderItem(SqlSession sqlSession, Long pdOrderNum, Long productNum, Long optionNum, int amount, int price) throws Exception;

	void updateSellerProdTrackingNum(Long pdOrderNum, Integer trackingNum) throws Exception;
	
	
}
