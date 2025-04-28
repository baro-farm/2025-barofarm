package service.buyer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.ProdOrderVO;

public interface ProdOrderService {
	List<ProdOrderVO> selectUserProdOrderList(String userId) throws Exception;
	List<ProdOrderVO> selectUserProdOrderDetailList(Long pdOrderNum) throws Exception;
	void updateUserProdDeliveryStatus(Long pdOrderNum, String deliveryStatus) throws Exception;

	Integer selectProductOrderCount(Long sellerNum) throws Exception;
	
	Long insertProductOrder(SqlSession sqlSession, Long userNum, int totalPrice, String address) throws Exception;
	void insertProductOrderItem(SqlSession sqlSession, Long pdOrderNum, Long productNum, Long optionNum, int amount, int price) throws Exception;
}
