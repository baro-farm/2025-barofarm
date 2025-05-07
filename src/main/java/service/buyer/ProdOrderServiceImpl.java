package service.buyer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dao.buyer.ProductOrderDAO;
import dao.buyer.ProductOrderDAOImpl;
import dto.buyer.ProductOrder;
import util.MybatisSqlSessionFactory;
import vo.AdminProdOrderVO;
import vo.ProdCancelVO;
import vo.ProdOrderVO;

public class ProdOrderServiceImpl implements ProdOrderService {

	private ProductOrderDAO prodOrderDao(SqlSession sqlSession) {
		return new ProductOrderDAOImpl(sqlSession);
	}

	@Override
	public List<ProdOrderVO> selectUserProdOrderList(String userId) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			List<ProdOrderVO> prodOrderList = new ArrayList<>();
			prodOrderList = prodOrderDao(sqlSession).selectProdOrderList(userId);
			return prodOrderList;
		}
	}

	@Override
	public List<ProdOrderVO> selectUserProdOrderDetailList(Long pdOrderNum) throws Exception {
		// TODO Auto-generated method stub
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			List<ProdOrderVO> prodOrderList = new ArrayList<>();
			prodOrderList = prodOrderDao(sqlSession).selectProdDetailOrderList(pdOrderNum);
			return prodOrderList;
		}
	}

	@Override
	public void updateUserProdDeliveryStatus(Long pdOrderNum, String deliveryStatus) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			ProductOrder pdOrder = new ProductOrder();
			pdOrder.setPdOrderNum(pdOrderNum);
			pdOrder.setDeleveryStatus(deliveryStatus);

			prodOrderDao(sqlSession).updateDeliveryStatus(pdOrder);
			sqlSession.commit();
		}
	}

	@Override
	public Integer selectProductOrderCount(Long sellerNum) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdOrderVO> selectProductOrderList(Long sellerNum, int offset, int pageSize, String dateType,
			String startDate, String endDate, String searchType, String searchKeyword) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("sellerNum", sellerNum);
			param.put("offset", offset);
			param.put("pageSize", pageSize);
			param.put("dateType", dateType);
			param.put("startDate", startDate);
			param.put("endDate", endDate);
			param.put("searchType", searchType);
			param.put("searchKeyword", searchKeyword);

			return prodOrderDao(sqlSession).selectSellerProductOrderList(param);
		}
	}

	@Override
	public Integer countProductOrderList(Long sellerNum, String dateType, String startDate, String endDate,
			String searchType, String searchKeyword) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("sellerNum", sellerNum);
			param.put("dateType", dateType);
			param.put("startDate", startDate);
			param.put("endDate", endDate);
			param.put("searchType", searchType);
			param.put("searchKeyword", searchKeyword);
			return prodOrderDao(sqlSession).sellectCountSellerProductOrderList(param);
		}
	}

	@Override
	public void updateSellerProdTrackingNum(Long pdOrderNum, String deleveryStatus) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			Map<String, Object> param = new HashMap<>();
			param.put("pdOrderNum", pdOrderNum);
			param.put("deleveryStatus", deleveryStatus);
			prodOrderDao(sqlSession).updateSellerProdeliveryStatus(param);
			sqlSession.commit();
		}
	}

	public void insertProductOrder(SqlSession sqlSession, ProductOrder productOrder) throws Exception {
		ProductOrderDAO prodOrderDao = new ProductOrderDAOImpl(sqlSession);
		prodOrderDao.insertProductOrder(productOrder);
	}

	@Override
	public void insertProductOrderItem(SqlSession sqlSession, Long pdOrderNum, Long productNum, Long optionNum,
			int amount, int price) throws Exception {
//		prodOrderDao(sqlSession).insertProductOrderItem(sqlSession, poItem);
		ProductOrderDAO prodOrderDao = new ProductOrderDAOImpl(sqlSession);
		Map<String, Object> param = new HashMap<>();
		param.put("pdOrderNum", pdOrderNum);
		param.put("productNum", productNum);
		param.put("optionNum", optionNum);
		param.put("amount", amount);
		param.put("price", price);

		prodOrderDao.insertProductOrderItem(param);
	}

	// 유저 페이징 추가
	@Override
	public Integer selectUserProdOrderCount(Long userNum, String startDate, String endDate, String deliveryStatus) {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("userNum", userNum);
			param.put("startDate", startDate);
			param.put("endDate", endDate);
			param.put("deliveryStatus", deliveryStatus);
			return prodOrderDao(sqlSession).selectUserProdOrderCount(param);
		}
	}

	@Override
	public List<ProdOrderVO> selectUserProdOrderList(Long userNum, String startDate, String endDate,
			String deliveryStatus, int offset, int limit) {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("userNum", userNum);
			param.put("startDate", startDate);
			param.put("endDate", endDate);
			param.put("deliveryStatus", deliveryStatus);
			param.put("offset", offset);
			param.put("limit", limit);
			return prodOrderDao(sqlSession).selectUserProdOrderList(param);
		}
	}

	// 취소처리
	@Override
	public void cancelProdOrder(Long pdOrderNum, String reason, String reasonDetail) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession(false)) {

			prodOrderDao(sqlSession).processProdCancel(pdOrderNum, reason, reasonDetail);
			sqlSession.commit();
		} catch (Exception e) {
			
			throw e;
		}
	}

	// 취소 리스트
	@Override
	public Integer countSellerCancelList(Long sellerNum, String sort) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("sellerNum", sellerNum);
			param.put("sort", sort);
			return prodOrderDao(sqlSession).countSellerCancelList(param);
		}
	}

	@Override
	public List<ProdCancelVO> selectSellerCancelList(Long sellerNum, String sort, int offset, int limit)
			throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("sellerNum", sellerNum);
			param.put("sort", sort);
			param.put("offset", offset);
			param.put("limit", limit);
			return prodOrderDao(sqlSession).selectSellerCancelList(param);
		}
	}

	@Override
	public List<AdminProdOrderVO> selectAdminProductOrderList(Long sellerNum, int offset, int pageSize, String dateType,
			String startDate, String endDate, String searchType, String searchKeyword) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("offset", offset);
			param.put("pageSize", pageSize);
			param.put("dateType", dateType);
			param.put("startDate", startDate);
			param.put("endDate", endDate);
			param.put("searchType", searchType);
			param.put("searchKeyword", searchKeyword);

			if (sellerNum != null) {
				param.put("sellerNum", sellerNum);
			}

			return prodOrderDao(sqlSession).selectAdminProdOrderList(param);
		}
	}

	@Override
	public int countAdminOrderList(String dateType, String startDate, String endDate, String searchType,
			String searchKeyword) throws Exception {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession()) {

			Map<String, Object> param = new HashMap<>();
			param.put("dateType", dateType);
			param.put("startDate", startDate);
			param.put("endDate", endDate);
			param.put("searchType", searchType);
			param.put("searchKeyword", searchKeyword);
			return prodOrderDao(sqlSession).countAdminOrderList(param);
		}
	}
}
