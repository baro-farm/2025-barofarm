package service.buyer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dao.buyer.ProductOrderDAO;
import dao.buyer.ProductOrderDAOImpl;
import dto.buyer.ProductOrder;
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

	public Long insertProductOrder(SqlSession sqlSession, Long userNum, int totalPrice, String address) throws Exception {
		Map<String, Object> param = new HashMap<>();
	    param.put("userNum", userNum);
	    param.put("pdTotalPrice", totalPrice);
	    param.put("address", address);
	    param.put("deleveryStatus", "배송준비");  // 기본값
	    param.put("orderStatus", "결제완료");    // 기본값

	    return prodOrderDao.insertProductOrder(sqlSession, param);
	}
	
	@Override
	public void insertProductOrderItem(SqlSession sqlSession, Long pdOrderNum, Long productNum, Long optionNum,
			int amount, int price) throws Exception {
		Map<String, Object> param = new HashMap<>();
        param.put("pdOrderNum", pdOrderNum);
        param.put("productNum", productNum);
        param.put("optionNum", optionNum);
        param.put("amount", amount);
        param.put("price", price);

		prodOrderDao.insertProductOrderItem(sqlSession, param);
	}
}
