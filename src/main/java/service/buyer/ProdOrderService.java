package service.buyer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.ProductOrder;
import vo.AdminProdOrderVO;
import vo.ProdCancelVO;
import vo.ProdOrderVO;

public interface ProdOrderService {
	List<ProdOrderVO> selectUserProdOrderList(String userId) throws Exception;
	List<ProdOrderVO> selectUserProdOrderDetailList(Long pdOrderNum) throws Exception;
	void updateUserProdDeliveryStatus(Long pdOrderNum, String deliveryStatus) throws Exception;

	Integer selectProductOrderCount(Long sellerNum) throws Exception;
	
	//구매자 페이징
    Integer selectUserProdOrderCount(Long userNum, String startDate, String endDate, String deliveryStatus);
    List<ProdOrderVO> selectUserProdOrderList(Long userNum, String startDate, String endDate, String deliveryStatus, int offset, int limit);

	
	
    // 주문 리스트
    List<ProdOrderVO> selectProductOrderList(Long sellerNum, int offset, int pageSize, String dateType, 
                                             String startDate, String endDate, 
                                             String searchType, String searchKeyword) throws Exception;
 // 주문 수 (페이징용)
    Integer countProductOrderList(Long sellerNum, String dateType, String startDate, 
                                  String endDate, String searchType, String searchKeyword) throws Exception;
	void insertProductOrder(SqlSession sqlSession, ProductOrder productOrder) throws Exception;
//	void insertProductOrderItem(SqlSession sqlSession, ProductOrderItem poItem) throws Exception;
	void insertProductOrderItem(SqlSession sqlSession, Long pdOrderNum, Long productNum, Long optionNum, int amount, int price) throws Exception;
	void updateSellerProdTrackingNum(Long pdOrderNum, String deleveryStatus) throws Exception;
	
	//취소처리
	void cancelProdOrder(Long pdOrderNum, String reason, String reasonDetail) throws Exception;
	
	Integer countSellerCancelList(Long sellerNum, String sort) throws Exception;
	List<ProdCancelVO> selectSellerCancelList(Long sellerNum, String sort,int offset, int limit) throws Exception;
	// 관리자 주문 목록
	List<AdminProdOrderVO> selectAdminProductOrderList(Long sellerNum, int offset, int pageSize, String dateType, 
            String startDate, String endDate, 
            String searchType, String searchKeyword) throws Exception;
	// ProdOrderService.java
	int countAdminOrderList(String dateType, String startDate, String endDate,
	                        String searchType, String searchKeyword) throws Exception;

}
