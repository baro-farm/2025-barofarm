package service.buyer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dao.buyer.ProductOrderDAO;
import dao.buyer.ProductOrderDAOImpl;
import dto.buyer.ProductOrder;
import vo.AdminProdOrderVO;
import vo.ProdCancelVO;
import vo.ProdOrderVO;

public class ProdOrderServiceImpl implements ProdOrderService {
	
	private ProductOrderDAO prodOrderDao;
	
	public ProdOrderServiceImpl() {
		prodOrderDao =  new ProductOrderDAOImpl();
	}
	
	@Override
	public List<ProdOrderVO> selectUserProdOrderList(String userId) throws Exception {
		List<ProdOrderVO> prodOrderList = new ArrayList<>();
		prodOrderList = prodOrderDao.selectProdOrderList(userId);
		return prodOrderList;
	}

	@Override
	public List<ProdOrderVO> selectUserProdOrderDetailList(Long pdOrderNum) throws Exception {
		// TODO Auto-generated method stub
		List<ProdOrderVO> prodOrderList = new ArrayList<>();
		prodOrderList = prodOrderDao.selectProdDetailOrderList(pdOrderNum);
		return prodOrderList;
	}

	@Override
	public void updateUserProdDeliveryStatus(Long pdOrderNum, String deliveryStatus) throws Exception {
		ProductOrder pdOrder = new ProductOrder();
		pdOrder.setPdOrderNum(pdOrderNum);
		pdOrder.setDeleveryStatus(deliveryStatus);
		
		prodOrderDao.updateDeliveryStatus(pdOrder);		
	}

	
	
	@Override
	public Integer selectProductOrderCount(Long sellerNum) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProdOrderVO> selectProductOrderList(Long sellerNum, int offset, int pageSize, String dateType,
			String startDate, String endDate, String searchType, String searchKeyword) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("sellerNum", sellerNum);
        param.put("offset", offset);
        param.put("pageSize", pageSize);
        param.put("dateType", dateType);
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        param.put("searchType", searchType);
        param.put("searchKeyword", searchKeyword);
      
		return prodOrderDao.selectSellerProductOrderList(param);
	}

	@Override
	public Integer countProductOrderList(Long sellerNum, String dateType, String startDate, String endDate,
			String searchType, String searchKeyword) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("sellerNum", sellerNum);
        param.put("dateType", dateType);
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        param.put("searchType", searchType);
        param.put("searchKeyword", searchKeyword);
        return prodOrderDao.sellectCountSellerProductOrderList(param);
	}
	
	@Override
	public void updateSellerProdTrackingNum(Long pdOrderNum, String deleveryStatus) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("pdOrderNum", pdOrderNum);
		param.put("deleveryStatus", deleveryStatus);
		prodOrderDao.updateSellerProdeliveryStatus(param);
	}
	

	public void insertProductOrder(SqlSession sqlSession, ProductOrder productOrder) throws Exception {
		prodOrderDao.insertProductOrder(sqlSession, productOrder);
	}
	
	@Override
	public void insertProductOrderItem(SqlSession sqlSession, Long pdOrderNum, Long productNum, Long optionNum, int amount, int price) throws Exception {
//		prodOrderDao.insertProductOrderItem(sqlSession, poItem);
		Map<String, Object> param = new HashMap<>();
        param.put("pdOrderNum", pdOrderNum);
        param.put("productNum", productNum);
        param.put("optionNum", optionNum);
        param.put("amount", amount);
        param.put("price", price);

		prodOrderDao.insertProductOrderItem(sqlSession, param);
	}

	//유저 페이징 추가
	@Override
	public Integer selectUserProdOrderCount(Long userNum, String startDate, String endDate, String deliveryStatus) {
        Map<String, Object> param = new HashMap<>();
        param.put("userNum", userNum);
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        param.put("deliveryStatus", deliveryStatus);		
        return prodOrderDao.selectUserProdOrderCount(param);
	}

	@Override
	public List<ProdOrderVO> selectUserProdOrderList(Long userNum, String startDate, String endDate,
			String deliveryStatus, int offset, int limit) {
        Map<String, Object> param = new HashMap<>();
        param.put("userNum", userNum);
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        param.put("deliveryStatus", deliveryStatus);
        param.put("offset", offset);
        param.put("limit", limit);
        return prodOrderDao.selectUserProdOrderList(param);
	}

	//취소처리
	@Override
	public void cancelProdOrder(Long pdOrderNum, String reason, String reasonDetail) throws Exception {

		prodOrderDao.processProdCancel(pdOrderNum, reason, reasonDetail);
	}

	//취소 리스트
	@Override
	public Integer countSellerCancelList(Long sellerNum, String sort) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("sellerNum", sellerNum);
        param.put("sort", sort);
        return prodOrderDao.countSellerCancelList(param);
	}

	@Override
	public List<ProdCancelVO> selectSellerCancelList(Long sellerNum, String sort, int offset, int limit)
			throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("sellerNum", sellerNum);
        param.put("sort", sort);
        param.put("offset", offset);
        param.put("limit", limit);
        return prodOrderDao.selectSellerCancelList(param);
	}

	@Override
	public List<AdminProdOrderVO> selectAdminProductOrderList(Long sellerNum, int offset, int pageSize, String dateType,
	                                                          String startDate, String endDate,
	                                                          String searchType, String searchKeyword) throws Exception {
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

	    return prodOrderDao.selectAdminProdOrderList(param);
	}

	@Override
	public int countAdminOrderList(String dateType, String startDate, String endDate,
	                               String searchType, String searchKeyword) throws Exception {
	    Map<String, Object> param = new HashMap<>();
	    param.put("dateType", dateType);
	    param.put("startDate", startDate);
	    param.put("endDate", endDate);
	    param.put("searchType", searchType);
	    param.put("searchKeyword", searchKeyword);
	    return prodOrderDao.countAdminOrderList(param);
	}
}
