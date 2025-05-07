package dao.buyer;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.buyer.ProductOrder;
import util.MybatisSqlSessionFactory;
import vo.AdminProdOrderVO;
import vo.ProdCancelVO;
import vo.ProdOrderVO;

public class ProductOrderDAOImpl implements ProductOrderDAO {

	private final SqlSession sqlSession;

	public ProductOrderDAOImpl(SqlSession sqlSession) {

		this.sqlSession = sqlSession;

	}

	@Override
	public List<ProdOrderVO> selectProdOrderList(String userId) throws Exception {
		Long userNum = sqlSession.selectOne("mapper.user.selectUserNumById", userId);
		return sqlSession.selectList("mapper.prodOrder.prodOrderList", userNum);
	}

	@Override
	public List<ProdOrderVO> selectProdDetailOrderList(Long pdOrderNum) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.prodOrder.prodDetailOrderList", pdOrderNum);
	}

	@Override
	public void updateDeliveryStatus(ProductOrder pdOrder) throws Exception {
		sqlSession.update("mapper.prodOrder.updatePdDeliveryStatus", pdOrder);
	}

	// seller list
	@Override
	public List<ProdOrderVO> selectSellerProductOrderList(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.prodOrder.selectSellerProdOrderList", param);
	}

	@Override
	public Integer sellectCountSellerProductOrderList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.prodOrder.sellectCountSellerProdOrderList", param);
	}

	public void updateSellerProdeliveryStatus(Map<String, Object> param) throws Exception {
		sqlSession.update("mapper.prodOrder.updatePdDeliveryStatus", param);

	}

	@Override
	public void insertProductOrder(ProductOrder productOrder) throws Exception {
		sqlSession.insert("mapper.prodOrder.insertProductOrder", productOrder);
	}

	@Override
//	public void insertProductOrderItem(SqlSession sqlSession, ProductOrderItem poItem) throws Exception {
	public void insertProductOrderItem(Map<String, Object> param) throws Exception {
		sqlSession.insert("mapper.prodOrder.insertProductOrderItem", param);
	}

	@Override
	public Integer countSellerProductOrderList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	// user list
	@Override
	public Integer selectUserProdOrderCount(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.prodOrder.selectUserProdOrderCount", param);
	}

	@Override
	public List<ProdOrderVO> selectUserProdOrderList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.prodOrder.selectUserProdOrderList", param);
	}

	// 상품취소
	@Override
	public void processProdCancel(Long pdOrderNum, String reason, String detail) throws Exception {

		List<Long> orderItemList = sqlSession.selectList("mapper.prodOrder.selectOrderItemsByOrderNum", pdOrderNum);
		int refundPrice = 0;
		for (Long orderItem : orderItemList) {

			// 재고 복구
			Map<String, Object> stockParam = sqlSession.selectOne("mapper.prodOrder.selectOptionAndAmountByOrderItem",
					orderItem);

			int optionPrice = (int) stockParam.get("price"); // 또는 "optionPrice" 키
			int amount = (int) stockParam.get("amount");

			refundPrice += optionPrice * amount;

			Map<String, Object> param = new HashMap<>();
			param.put("orderItem", orderItem);
			param.put("cancelStatus", "취소완료");
			param.put("cancelReason", reason);
			param.put("cancelReasonDetail", detail);
			param.put("cancelRequestedAt", LocalDateTime.now());
			param.put("refundPrice", optionPrice * amount); // 개별 환불 가격

			sqlSession.insert("mapper.prodOrder.insertCancelLog", param);

			sqlSession.update("mapper.prodOrder.restoreStock", stockParam);
		}

		// 주문 상태 취소로 업데이트
		sqlSession.update("mapper.prodOrder.updateOrderStatus", pdOrderNum);

	}

	// 취소 리스트

	@Override
	public Integer countSellerCancelList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.prodOrder.countSellerCancelList", param);
	}

	@Override
	public List<ProdCancelVO> selectSellerCancelList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.prodOrder.selectSellerCancelList", param);
	}

	@Override
	public List<AdminProdOrderVO> selectAdminProdOrderList(Map<String, Object> param) throws Exception {
		return sqlSession.selectList("mapper.prodOrder.selectAdminOrderList", param);
	}

	@Override
	public int countAdminOrderList(Map<String, Object> param) throws Exception {
		return sqlSession.selectOne("mapper.prodOrder.countAdminOrderList", param);
	}
}
