package service.buyer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dao.buyer.PackageOrderDAO;
import dao.buyer.PackageOrderDAOImpl;
import dto.buyer.PackageOrder;
import dto.buyer.PackageSubscribe;
import vo.AdminPackOrderVO;
import vo.PackOrderVO;

public class PackOrderServiceImpl implements PackOrderService {
	
	private PackageOrderDAO packOrderDao;
	
	public PackOrderServiceImpl() {
		packOrderDao = new PackageOrderDAOImpl();
	}
	
	@Override
	public List<PackOrderVO> selectUserPackOrderList(String userId) throws Exception {
		List<PackOrderVO> packOrderList = new ArrayList<>();
		packOrderList = packOrderDao.selectPackOrderList(userId);
		return packOrderList;
	}

	@Override
	public void updateUserPackDeliveryStatus(Long pkOrderNum, String deliveryStatus) throws Exception {

		PackageOrder pkOrder = new PackageOrder();
		pkOrder.setPkOrderNum(pkOrderNum);
		pkOrder.setDeleveryStatus(deliveryStatus);
		
		packOrderDao.updateDeliveryStatus(pkOrder);
	}

	//seller List
	@Override
	public List<PackOrderVO> selectSellerPackOrderList(Long sellerNum, int offset, int pageSize, String dateType,
			String startDate, String endDate, String deliveryDay, String searchType, String searchKeyword)
			throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("sellerNum", sellerNum);
		param.put("offset",offset);
		param.put("pageSize",pageSize);
		param.put("dateType",dateType);
		param.put("startDate",startDate);
		param.put("endDate",endDate);
		param.put("deliveryDay",deliveryDay);
		param.put("searchType",searchType);
		param.put("searchKeyword",searchKeyword);
		
		return packOrderDao.selectSellerPackOrderList(param);
	}

	@Override
	public Integer selectCountSellerPackOrderList(Long sellerNum, String dateType, String startDate, String endDate,
			String deliveryDay, String searchType, String searchKeyword) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("sellerNum", sellerNum);
		param.put("dateType",dateType);
		param.put("startDate",startDate);
		param.put("endDate",endDate);
		param.put("deliveryDay",deliveryDay);
		param.put("searchType",searchType);
		param.put("searchKeyword",searchKeyword);	

		return packOrderDao.selectCountSellerPackOrderList(param);
	}

	@Override
	public void updateSellerPackTrackingNum(Long pkOrderNum, Integer trackingNum) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("pkOrderNum", pkOrderNum);
		param.put("trackingNum", trackingNum);
		
		packOrderDao.updatePackTrackingNum(param);

		
	}

	@Override
	public Integer selectUserPackOrderCount(Long userNum, String startDate, String endDate, String deliveryStatus) {
	       Map<String, Object> param = new HashMap<>();
	        param.put("userNum", userNum);
	        param.put("startDate", startDate);
	        param.put("endDate", endDate);
	        param.put("deliveryStatus", deliveryStatus);			
	        return packOrderDao.selectUserPackOrderCount(param);
	}

	@Override
	public List<PackOrderVO> selectUserPackOrderList(Long userNum, String startDate, String endDate,
			String deliveryStatus, int offset, int limit) {
        Map<String, Object> param = new HashMap<>();
        param.put("userNum", userNum);
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        param.put("deliveryStatus", deliveryStatus);
        param.put("offset", offset);
        param.put("limit", limit);
        System.out.println(param);
        return packOrderDao.selectUserPackOrderList(param);
	}

	@Override
	public void insertPackageOrder(SqlSession sqlSession, PackageOrder packOrder)
			throws Exception {
		packOrderDao.insertPackageOrder(sqlSession, packOrder);
	}
	
	@Override
	public void insertSubscription(SqlSession sqlSession, PackageSubscribe sub) throws Exception {
		packOrderDao.insertSubscription(sqlSession, sub);		
	}
	
	@Override
    public int countAdminPackOrderList(String startDate, String endDate, String searchType, String searchKeyword) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("startDate", startDate);
        paramMap.put("endDate", endDate);
        paramMap.put("searchType", searchType);
        paramMap.put("searchKeyword", searchKeyword);
        return packOrderDao.countAdminPackOrderList(paramMap);
    }

    @Override
    public List<AdminPackOrderVO> selectAdminPackOrderList(int offset, int pageSize, String startDate, String endDate, String searchType, String searchKeyword) throws Exception {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("offset", offset);
        paramMap.put("pageSize", pageSize);
        paramMap.put("startDate", startDate);
        paramMap.put("endDate", endDate);
        paramMap.put("searchType", searchType);
        paramMap.put("searchKeyword", searchKeyword);
        return packOrderDao.selectAdminPackOrderList(paramMap);
    }

	@Override
	public List<PackOrderVO> selectUserPackOrderDetailList(Long pkOrderNum) throws Exception {
		// TODO Auto-generated method stub
		return packOrderDao.selectPackDetailOrderList(pkOrderNum);
	}
}
